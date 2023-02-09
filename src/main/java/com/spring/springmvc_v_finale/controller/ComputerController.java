package com.spring.springmvc_v_finale.controller;

import com.spring.springmvc_v_finale.hib.AccessHibernate;
import com.spring.springmvc_v_finale.model.Computer;
import com.spring.springmvc_v_finale.model.Type_event_detail;
import com.spring.springmvc_v_finale.model.view.Computer_view;
import com.spring.springmvc_v_finale.service.ComputerView_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.spring.springmvc_v_finale.model.Type;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ComputerController {
    @Autowired
    AccessHibernate dao;
    @Autowired
    private ComputerView_Service service;
    public static int modPage=6;
    public static int modcree=4;
    public static String sql="";
    public static int sizeSql=-1;
    @GetMapping("/insert")
    public  ModelAndView form() throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("list",dao.findAll(Type.class));
        model.setViewName("computer/formulaire");
        return model;
    }
    @PostMapping("validate_insert")
    public ModelAndView insert(@ModelAttribute Computer cmp, @ModelAttribute Type_event_detail detail,
                         @RequestParam("images") CommonsMultipartFile file, HttpServletRequest req,
        @RequestParam("idtype") int idtype,Model model
    ) throws Exception {
        try {
            detail.setIdtype(new Type(idtype));
            if (detail.getDate_fin().toString().equals("1000-01-02")) {
                detail.setDate_fin(null);
            }
//            byte[] data = file.getBytes();
//            String filePath
//                    = req.getServletContext().getRealPath("/")
//                    + "ressources"
//                    + File.separator + "image" + File.separator
//                    + file.getOriginalFilename();
//
//            FileOutputStream fileout
//                    = new FileOutputStream(filePath);
//            fileout.write(data);
//
//            fileout.close();

//            cmp.setImage(filePath);
            HttpSession session = req.getSession();
            if (session.getAttribute("idcli") == null) {
                throw new Exception("vous devez vous connecter");
            }
            Timestamp t = new Timestamp(System.currentTimeMillis());
            int iduser = (int) session.getAttribute("idcli");
            cmp.setIdclient(iduser);
            cmp.setDate_creation(t);
            dao.save(cmp);
            detail.setId_cp(cmp);
            dao.save(detail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return getList(0,6);
    }
    @GetMapping("/list")
    public ModelAndView getList(@RequestParam("first") Integer first,@RequestParam("last") Integer last) throws Exception {
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName("computer/list");
            first= (first!=null)?first:1;
            last= (last!=null)?last:modPage;
//            List<Computer_view> list = dao.pagination(new Computer_view(),first,modPage);
            List<Computer_view> list = dao.paginateResult("SELECT * from computer_view where status=1 and date_sortie<=current_timestamp order by id_cp asc",first,modPage, Computer_view.class);
            long t = service.getCount("SELECT * from computer_view where status=1 and date_sortie<=current_timestamp order by id_cp asc");
            long size = t/modPage;
            if (t%modPage!=0)
                size++;
            model.addObject("type",dao.findAll(Type.class));
            model.addObject("count",size);
            model.addObject("list",list);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return model;
    }
    @GetMapping("/publiates")
    public ModelAndView publiate(@RequestParam("id") int id, @RequestParam("date_sortie") String time, @RequestParam("time")String lera) throws Exception {
        String t = time+" "+lera+":00";
        System.out.println(t);
        Timestamp tmp = Timestamp.valueOf(t);

           Computer  c = dao.findById(Computer.class,id);
             c.setDate_sortie(tmp);
            c.setStatus(1);
            dao.update(c);
            return getList(0,6);
    }
    @GetMapping("/publier/{id_cp}")
    public ModelAndView publiate(@PathVariable("id_cp") int id_Computer) throws Exception {
        Computer c = new Computer();
        c .setId(id_Computer);
        c = dao.findById(Computer.class,id_Computer);
        Timestamp t = new Timestamp(System.currentTimeMillis());
        c.setDate_sortie(t);
        c.setStatus(1);
        dao.update(c);
        return getList(0,6);
    }
    @GetMapping("/list_cree")
    public ModelAndView selectAllCreated(@RequestParam("first") Integer first) throws Exception {
        ModelAndView model = new ModelAndView();
        model.setViewName("computer/list_created");
        long t =service.getCount_created();
        System.out.println(t+" aloha t");
        long size = t/modcree;
        System.out.println(size+" size");
        if (t%modcree!=0)
            size++;
        System.out.println(size);
        model.addObject("count",size);
        model.addObject("list",service.selectAllCreated(first,modcree));
        return model;
    }
    @PostMapping("/search")
    public ModelAndView Search(HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView();
        try {
            int first = Integer.parseInt(req.getParameter("first"));
            int last = Integer.parseInt(req.getParameter("last"));
        int idtype = ((req.getParameter("idtype")!="")&&(req.getParameter("idtype")!=null))? Integer.parseInt((req.getParameter("idtype"))) :0;
        String keyword = ((req.getParameter("keyword")!="")&&(req.getParameter("keyword")!=null))?(req.getParameter("keyword")):"";
        double prix = ((req.getParameter("prix")!="")&&(req.getParameter("prix")!=null))? Double.parseDouble((req.getParameter("prix"))) :0;


        Timestamp date_sortie = ((req.getParameter("date_sortie")!="")&&(req.getParameter("date_sortie")!=null))? Timestamp.valueOf((req.getParameter("date_sortie"))) :null;
        Date date_debut = ((req.getParameter("date_debut")!="")&&(req.getParameter("date_debut")!=null))? Date.valueOf((req.getParameter("date_debut"))) :null;
        Date date_fin = null;
            if (req.getParameter("date_fin") != null) {
                date_fin = (!req.getParameter("date_fin").equals("1000-01-02"))? Date.valueOf((req.getParameter("date_fin"))) :null;
            }
            int remise = 0;
            if (req.getParameter("remise") != null) {
                remise= (req.getParameter("remise")!="0")? Integer.parseInt((req.getParameter("remise"))) :0;
            }
        Computer_view tosearch = new Computer_view(idtype, date_debut, date_fin, remise, prix, date_sortie,keyword);
        mod.setViewName("computer/list");
            String action = req.getParameter("action");
            List<Computer_view> list = null;
            HttpSession session = req.getSession();
            if (action.equals("see")){
                System.out.println("tafiditra ato aloha");
                 if (session.getAttribute("query")!=null){
                     service.query = (String) session.getAttribute("query");
//                     System.out.println("eto amzay "+service.query);
                    list = service.advancedSearch(tosearch,first,modPage,2);
                 }
            }else{
                list = service.advancedSearch(tosearch,first,modPage,1);
            System.out.println("eto le izy avant "+service.query);
                session.setAttribute("query",service.query);
//                System.out.println("eeeeeeeeee ooo"+session.getAttribute("query"));
            }
        if (service.count<=modPage)
            sizeSql = 1;
        else
            sizeSql = service.count/modPage;
        sql = service.query;
//        List<Computer_view> list = dao.pagination(new Computer_view(),first,last);
//        long size = list.size()/modPage;

        mod.addObject("type",dao.findAll(Type.class));
        mod.addObject("count",sizeSql);
        mod.addObject("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mod;
    }
    public AccessHibernate getDao() {
        return dao;
    }

    public void setDao(AccessHibernate dao) {
        this.dao = dao;
    }

}
