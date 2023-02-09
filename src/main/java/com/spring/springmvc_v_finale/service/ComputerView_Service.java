package com.spring.springmvc_v_finale.service;

import com.spring.springmvc_v_finale.model.view.Computer_view;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ComputerView_Service extends ServiceModel{
    public int count=0;
    public String  query="";
    public int count_created = -1;

    public int getCount_created() throws Exception {
        if (count_created==-1)
            count_created = getCount(query_created);
//        System.out.println(count_created);
        return count_created;
    }


    public void setCount_created(int count_created) {
        this.count_created = count_created;
    }

    String query_created = "SELECT * from computer_view where status=0 order by id_cp asc";
    //date de sortie,date debut event et date fin s'il ya
    private String getDateCondition(Date date1, Date fin, String ref, String ref2){
        if ((date1 == null) && (fin==null)){
            return " 2=2 ";
        }
        if (date1==null)
            return  " ("+ref2+"<='"+fin+"') ";
        if (fin==null)
            return  " ("+ref+">='"+date1+"') ";
        return "("+ref+">='"+date1+"' and "+ref2+"<='"+fin+"')";
    }
    private String getDateCond(Timestamp date1,String ref){
        if ((date1 == null)){
            return " 2=2 ";
        }
        return "("+ref+">='"+date1+"')";
    }
    //* condition prix et remise
    private String chiffreCondition(int chiffre,String ref){
        if (chiffre <=0) {
            return "1=1";
        }
        return ref+" <= "+chiffre;
    }
    private String prixSql(double prix){
        if (prix<=0) {
            return "1=1";
        }
        return " prix <="+prix;
    }
    private String sqlType(int idtype){
        if (idtype<=0) {
            return "1=1";
        }
        return "idtype ="+idtype;
    }

//    condition pour name et description de l'oridnateur
    private String constructKeyword(String key,String reference){
        String sql = "(";
        String[] tab = key.split(" ");
        if (key==null)
            return " 1=1 ";
        for (int i = 0; i < tab.length; i++) {
            if (i== tab.length-1)
            {
                sql+="upper("+reference+") like upper('%"+tab[i]+"%'))";
                break;
            }
            sql+="upper("+reference+") like upper('%"+tab[i]+"%') or ";
        }
        return sql;
    }
    private String createSql(Computer_view search){
        String sql = "select * from Computer_view where 1=1 and  status=1 and date_sortie<=current_timestamp and ("+constructKeyword(search.getDescription(),"description")
        +" or "+constructKeyword(search.getName(),"name")+") and "+getDateCondition(search.getDate_debut(),search.getDate_fin(),"date_debut","date_fin")
        +" and "+getDateCond(search.getDate_sortie(),"date_sortie")+" and "+sqlType(search.getIdtype())+" and "+chiffreCondition(search.getRemise(),"remise" )
        + " and " +prixSql(search.getPrix());

        return sql;
    }
    public int getCount(String sql) throws Exception {
        return dao.SelectByNativeQuery(sql).size();
    }
    public List<Computer_view> advancedSearch(Computer_view search,int first,int last,int action) throws Exception {
        String sql = createSql(search);
        if (action==1)
            query = sql;
        count = (action==1)?getCount(sql):getCount(query);
        List<Computer_view> list = dao.paginateResult((action==1)?sql:query,first,last, Computer_view.class);
       return list;
    }
    public List<Computer_view> selectAllCreated(int first,int size) throws Exception {
//        String sql = "SELECT * from computer_view where status=0 order by id_cp asc";
        List<Computer_view> list = dao.paginateResult(query_created,first,size, Computer_view.class);
        System.out.println(list.size());
        return list;
    }
}
