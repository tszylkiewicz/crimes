# --- Sample dataset

# --- !Ups

insert into category (id, name) values (1, 'Assault');
insert into category (id, name) values (2, 'Arson');
insert into category (id, name) values (3, 'Burglary');
insert into category (id, name) values (4, 'Fraud');
insert into category (id, name) values (5, 'Kidnapping');
insert into category (id, name) values (6, 'Theft');
insert into category (id, name) values (7, 'Robbery');
insert into category (id, name) values (8, 'Prostitution');
insert into category (id, name) values (9, 'Suicide');
insert into category (id, name) values (10, 'Vandalism');


insert into person (id, firstName, lastName, phone, email, rank) 
values (1, 'John', 'Snow', '123456789', 'johnsnow@edu.p.lodz.pl', 'Constable');
insert into person (id, firstName, lastName, phone, email, rank) 
values (2, 'Jack', 'Sparrow', '123123123', 'jacksparrow@gmail.com', 'Sergeant');
insert into person (id, firstName, lastName, phone, email, rank) 
values (3, 'Mike', 'Tyson', '387340912', 'tiger@onet.eu', 'Aspirant');
insert into person (id, firstName, lastName, phone, email, rank) 
values (4, 'Tom', 'Hardy', '8710918931', 't.hardy@ebay.com', 'Commissioner');
insert into person (id, firstName, lastName, phone, email, rank) 
values (5, 'Frodo', 'Baggins', '123875109', 'baggins@amazon.co', 'Inspector');
insert into person (id, firstName, lastName, phone, email, rank) 
values (6, 'Bruce', 'Lee', '091861289', 'tiger@allegro.pl', 'Detective');

insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (1, 'BATTERY WITH SERIOUS INJURIES', '2019-02-19', 'Arrest', 1, 'Wiejska', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 1);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (2, 'BATTERY', '2019-05-21', 'None', 1, 'Wojska Polskiego', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 2);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (3, 'AGGRAVATED ASSAULT WITH A KNIFE', '2019-07-17', 'Arrest', 1, 'Polna', 'Warsaw', 'mazowieckie', 81.172633, 85.581219, 3);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (4, 'ARSON OF A VEHICLE', '2019-12-31', 'Arrest', 2, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219, 4);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (5, 'ARSON OF AN INHABITED DWELLING', '2019-11-09', 'None', 2, 'Wojska Polskiego', 'Warsaw', 'mazowieckie', 81.172633, 85.581219, 4);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (6, 'BURGLARY,STORE UNDER CONSTRUCTION, FORCIBLE ENTRY', '2019-03-11', 'Arrest', 3, 'Polna', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 5);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (7, 'FRAUDULENT USE OF AUTOMATED TELLER CARD', '2019-01-16', 'None', 4, 'Wiejska', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 5);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (8, 'KIDNAPPING, ADULT VICTIM', '2019-05-15', 'Arrest', 5, 'Wojska Polskiego', 'Warsaw', 'mazowieckie', 81.172633, 85.581219, 6);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (9, 'CHILD STEALING', '2019-09-19', 'Arrest', 5, 'Politechniki', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 5);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (10, 'PETTY THEFT SHOPLIFTING', '2019-11-20', 'None', 6, 'Politechniki', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 6);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (11, 'GRAND THEFT FROM LOCKED AUTO', '2020-06-21', 'Arrest', 6, 'Sienkiewicz', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 2);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (12, 'ROBBERY, ARMED WITH A KNIFE', '2020-05-26', 'None', 7, 'Sienkiewicz', 'Lodz', 'lodzkie', 81.172633, 85.581219, 2);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (13, 'SOLICITS LEWD ACT', '2020-08-28', 'None', 8, 'Wojska Polskiego', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 1);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (14, 'SOLICITS FOR ACT OF PROSTITUTION', '2020-08-29', 'None', 8, 'Wiejska', 'Gdansk', 'pomorskie', 81.172633, 85.581219, 1);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (15, 'HUMAN TRAFFICKING', '2020-09-12', 'Arrest', 8, 'Politechniki', 'Lodz', 'lodzkie', 81.172633, 85.581219, 3);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (16, 'LOITERING FOR PURPOSE OF PROSTITUTION', '2020-03-13', 'Arrest', 8, 'Wojska Polskiego', 'Lodz', 'lodzkie', 81.172633, 85.581219, 3);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (17, 'KIDNAPPING, JUVENILE VICTIM', '2020-05-05', 'None', 5, 'Sienkiewicz', 'Warsaw', 'mazowieckie', 81.172633, 85.581219, 4);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (18, 'SUICIDE BY JUMPING', '2020-01-08', 'Arrest', 9, 'Wiejska', 'Lodz', 'lodzkie', 81.172633, 85.581219, 4);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (19, 'SUICIDE BY FIREARMS', '2020-10-02', 'Arrest', 9, 'Wiejska', 'Warsaw', 'mazowieckie', 81.172633, 85.581219, 5);
insert into crime (id, description, date, resolution, category_id, street, city, district, latitude, longitude, person_id) 
values (20, 'MALICIOUS MISCHIEF, VANDALISM', '2020-11-01', 'None', 10, 'Polna', 'Warsaw', 'mazowieckie', 81.172633, 85.581219, 6);


# --- !Downs

delete from crimes;
delete from category;
delete from person;