INSERT INTO role(code, description)VALUES ('USER','simple user');
INSERT INTO role(code, description)VALUES ('ADMIN','admin user');

INSERT INTO userrole(username,role_code)VALUES ('giacompa','USER');
INSERT INTO userrole(username,role_code)VALUES ('giacompa','ADMIN');
INSERT INTO userrole(username,role_code)VALUES ('lugraci','USER');

INSERT INTO userrole(username,role_code)VALUES ('AlexandroVassallo','USER');
INSERT INTO userrole(username,role_code)VALUES ('AlexandroVassallo','ADMIN');
INSERT INTO userrole(username,role_code)VALUES ('SalvoBattaglia','USER');
INSERT INTO userrole(username,role_code)VALUES ('SalvoBattaglia','ADMIN');
INSERT INTO userrole(username,role_code)VALUES ('GiuseppeMinore','USER');
INSERT INTO userrole(username,role_code)VALUES ('GiuseppeMinore','ADMIN');
INSERT INTO userrole(username,role_code)VALUES ('FedericoAnnaloro','USER');
INSERT INTO userrole(username,role_code)VALUES ('FedericoAnnaloro','ADMIN');

INSERT INTO security(username,password)values('AlexandroVassallo','AlexandroVassallo');
INSERT INTO security(username,password)values('SalvoBattaglia','SalvoBattaglia');
INSERT INTO security(username,password)values('GiuseppeMinore','GiuseppeMinore');
INSERT INTO security(username,password)values('FedericoAnnaloro','FedericoAnnaloro');


INSERT INTO security(username,password)values('giacompa','giacompa');
INSERT INTO security(username,password)values('lugraci','lugraci');

INSERT INTO security(username,password)values('giacompa','giacompa');
INSERT INTO security(username,password)values('lugraci','lugraci');

INSERT INTO userx(username, address, city, postcode, email, timebirth) VALUES ('giacompa','via tommaso marcellini 5','Palermo','90135','giancarlo.compagno@eng.it','1976-09-08');
INSERT INTO userx(username, address, city, postcode, email, timebirth) VALUES ('lugraci' ,'via tommaso marcellini 5','Palermo','90135','luigi.graci@eng.it','1982-09-08');

INSERT INTO userx(username, address, city, postcode, email, timebirth, chatId) VALUES ('AlexandroVassallo','via tommaso marcellini 5','Palermo','90135','gruppo2eng@gmail.com','1976-09-08','15279014');
INSERT INTO userx(username, address, city, postcode, email, timebirth, chatId) VALUES ('SalvoBattaglia' ,'via tommaso marcellini 5','Palermo','90135','gruppo2eng@gmail.com','1982-09-08','18128983');
INSERT INTO userx(username, address, city, postcode, email, timebirth, chatId) VALUES ('GiuseppeMinore','via tommaso marcellini 5','Palermo','90135','gruppo2eng@gmail.com','1976-09-08','26674639');
INSERT INTO userx(username, address, city, postcode, email, timebirth, chatId) VALUES ('FedericoAnnaloro' ,'via tommaso marcellini 5','Palermo','90135','gruppo2eng@gmail.com','1982-09-08','39341460');


INSERT INTO supplier(username,info)values('giacompa','Info fatturazione');

INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 1');
INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 2');
INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 3');
INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 4');
INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 5');
INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 6');
INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 7');
INSERT INTO product(oid,description)VALUES (nextval('PRODUCT_ID_SEQ'), 'Product 8');

--alter table product add column description varchar(255);
--update product set description = description;
--alter table product drop column description; 