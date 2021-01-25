use backend;

insert visa_status(visa_type, active, modification_date, create_user) values ("Green Card",1,"2021-01-23-00-00","admin"),("Citizen",1,"2021-01-23-00-00","admin"),("H1-B",0,"2021-01-23-00-00","admin"),("L2",0,"2021-01-23-00-00","admin"),("F1(CPT/OPT)",0,"2021-01-23-00-00","admin"),("H4",0,"2021-01-23-00-00","admin");

insert person values (1,"admin","admin","admin","admin@gmail.com","9998887777","8887776666","female","0","2021-01-23",1);
insert contact values (1,1,"friend","manager",0,0,1);
insert house(contact_id, address, number_of_person) values (1,"123 ave, Princeton, NJ, 00001",5),(1,"555 Parkview Ln, Princeton, NJ, 00001",5),(1,"999 ave, Princeton, NJ, 00001",5);
insert facility(type, quantity, house_id) values ("Bed",5,1),("Mattress",5,1),("Table",5,1),("Chair",5,1);
insert facility(type, quantity, house_id) values ("Bed",6,2),("Mattress",5,2),("Table",4,2),("Chair",10,2);
insert facility(type, quantity, house_id) values ("Bed",4,3),("Mattress",4,3),("Table",7,3),("Chair",5,3);
