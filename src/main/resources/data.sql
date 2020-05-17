INSERT INTO role(id, name) VALUES (1, 'USER'), (2, 'ADMIN');
INSERT INTO users(id, activation_hash, email, first_name, is_activated, last_name, password, phone_number) VALUES
  (20, '---', 'admin@admin.pl', 'Admin', true, 'Admin', '$2a$10$AHa4kpwMfydMEx0J207Seu4AnlogkvbM8sDdpY6BypcvsTkI9xS3i', '123456789');

INSERT INTO users_roles(users_id, roles_id)
  SELECT (SELECT id FROM users WHERE email='admin@admin.pl'), (SELECT id FROM role WHERE name='ADMIN');

INSERT INTO users_roles(users_id, roles_id)
  SELECT (SELECT id FROM users WHERE email='admin@admin.pl'), (SELECT id FROM role WHERE name='USER');

INSERT INTO price_table(id, price_per_distance, tax_percent) VALUES (1, 0.5, 23.0);

INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (20 , 'cairo' , 'london' , 100 ,'2020-06-1 14:12', '2020-06-1 18:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (21 , 'Oman' , 'Tokyo' , 1222 ,'2020-06-2 14:12' , '2020-06-2 15:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (22 , 'Djibouti' , 'lebanon' , 10220 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (23 , 'Belmopan' , 'Dhaka' , 1444 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (24 , 'Thimphu' , 'Porto Novo' , 12200 ,'2020-06-1 12:12' , '2020-06-2 14:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (25 , 'cairo' , 'london' , 1030 ,'2020-05-12 12:12' , '2020-05-12 14:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (26 , 'Santo Domingo' , 'london' , 1000 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (27 , 'Sarajevo' , 'london' , 133300 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (28 , 'cairo' , 'Brasilia' , 1088 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (29 , 'Ottawa' , 'Santiago' , 13330 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (30 , 'Copenhagen' , 'Santo Domingo' , 15555 ,'2020-05-12 12:12' , '2020-05-12 14:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (31 , 'San Salvador' , 'Santiago' , 1333 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (32 , 'Asmara' , 'Suva' , 10222 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (33 , 'Tallinn' , 'Malabo' , 17400 ,'2020-05-12 12:12' , '2020-05-12 14:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (34 , 'Palikir' , 'Berlin' , 1220 ,'2020-06-2 16:12' , '2020-06-3 19:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (35 , 'Monrovia' , 'Budapest' , 1999 ,'2020-06-1 14:12', '2020-06-1 18:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (36 , 'Ottawa' , '	Georgetown' , 13333 ,'2020-06-1 14:12', '2020-06-1 18:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (37 , 'Jakarta' , 'Santiago' , 10000 ,'2020-06-1 14:12', '2020-06-1 18:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (38 , 'New Delhi' , '	Santiago' , 1000 ,'2020-06-1 14:12', '2020-06-1 18:12' , 30 );
INSERT INTO connection(id , start_place , end_place , distance , departure_date , arrival_date , places ) VALUES (39 , 'Dublin' , '	Quito' , 1234 ,'2020-06-1 14:12', '2020-06-1 18:12' , 30 );