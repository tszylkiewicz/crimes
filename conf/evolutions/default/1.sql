# --- First database schema

# --- !Ups

set ignorecase true;

create table category (
  id                        bigint not null,
  name                      varchar(255) not null,
  constraint pk_category primary key (id))
;

create table crime (
  id                        bigint not null,
  description               varchar(255) not null,
  date                      timestamp,
  resolution                varchar(255),
  category_id                bigint,
  street                varchar(255),
  city                varchar(255),
  district                varchar(255),
  latitude                double,
  longitude                double,
  constraint pk_crime primary key (id))
;

create table person (
  id                        bigint not null,
  firstName                      varchar(255),
  lastName                      varchar(255),
  phone                      varchar(255),
  email                      varchar(255),
  constraint pk_person primary key (id))
;

create sequence category_seq start with 1000;

create sequence crime_seq start with 1000;

create sequence person_seq start with 1000;

alter table crime add constraint fk_crime_category_1 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_crime_category_1 on crime (category_id);

alter table crime add constraint fk_crime_person_1 foreign key (person_id) references person (id) on delete restrict on update restrict;
create index ix_crime_person_1 on crime (person_id);

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists category;

drop table if exists crime;

drop table if exists person;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists cateogry_seq;

drop sequence if exists crime_seq;

drop sequence if exists person_seq;
