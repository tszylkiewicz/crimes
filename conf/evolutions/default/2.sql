# --- Sample dataset

# --- !Ups

insert into category (id, name) values (1, 'Assault');
insert into category (id, name) values (2, 'Burglary');
insert into category (id, name) values (3, 'Murder');
insert into category (id, name) values (4, 'Arson');

insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude) 
values (1, 'Battery', '1993-10-21', 'Arrest', 1, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude) 
values (2, 'Battery', '1993-10-21', 'Arrest', 2, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude) 
values (3, 'Battery', '1993-10-21', 'Arrest', 3, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219);

# --- !Downs

delete from crimes;
delete from category;