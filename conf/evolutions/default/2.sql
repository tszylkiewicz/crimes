# --- Sample dataset

# --- !Ups

insert into category (id, name) values (1, 'Assault');
insert into category (id, name) values (2, 'Burglary');
insert into category (id, name) values (3, 'Murder');
insert into category (id, name) values (4, 'Arson');

insert into person (id, firstName, lastName, phone, email, rank) 
values (1, 'John', 'Snow', '123456789', 'johnsnow@edu.p.lodz.pl', 'Aspirant');
insert into person (id, firstName, lastName, phone, email, rank) 
values (2, 'Jack', 'Sparrow', '123123123', 'jacksparrow@gmail.com', 'Commissioner');
insert into person (id, firstName, lastName, phone, email, rank) 
values (3, 'Mike', 'Tyson', '387340912', 'tiger@onet.eu', 'Detective');

insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (1, 'Battery', '1993-10-21', 'Arrest', 1, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219, 1);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (2, 'Battery', '1993-10-21', 'Arrest', 2, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219, 2);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (3, 'Battery', '1993-10-21', 'Arrest', 3, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219, 3);

# --- !Downs

delete from crimes;
delete from category;
delete from person;