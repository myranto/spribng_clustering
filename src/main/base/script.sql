\c computer;

create table admin(
  id serial primary key ,
  nom varchar(40) not null ,
  email varchar(40) not  null unique ,
  mdp varchar(40) not null
);

insert into admin(nom, email, mdp) VALUES ('admin','admin@gmail.com','admin');

create table client(
  id serial primary key ,
  nom varchar(40) not null ,
  email varchar(40) not  null unique ,
  mdp varchar(40) not null
);

insert into client(nom, email, mdp) VALUES ('myranto','my@gmail.com','mmm');
insert into client(nom, email, mdp) VALUES ('mirantsoa','mira@gmail.com','love');


create table computer(
  id  serial primary key ,
  name varchar(40) not null,
  prix double precision not null check ( prix>=0 ),
  description text not null ,
  date_sortie date default null,
  date_creation TIMESTAMP default current_timestamp,
  image text not null,
  lieu varchar(40) not null,
  idclient int not null references client(id),
  status int check (status >=0 and status<=1)
);
-- select id, name, prix, description, date_sortie,  lieu ,status ,idclient from computer;
-- alter table computer add column status int default 0;
-- alter table computer add column idclient int references client(id);
create table type(
    id serial primary key ,
    nom varchar(40) not null
);

create table Type_event_detail(
    idtype int not null references type(id),
    date_debut date not null default current_date,
    date_fin date default null,
    remise int not null default 0,
    id_cp int not null references computer(id)
);


insert into type(nom) values ('article'),('evenement');

create or replace view  Computer_view as select t.nom,ted.*, name, prix, description, date_sortie,c.image,c.lieu,c.status ,c.idclient,c2.nom nom_client,c.date_creation from  computer c join Type_event_detail Ted on c.id = Ted.id_cp join type t on Ted.idtype = t.id join client c2 on c.idclient = c2.id;
-- select name,date_sortie,status from Computer_view;
