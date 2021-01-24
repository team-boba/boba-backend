use backend;

insert visa_status(visaType, active, modificationDate, createUser) values ("Green Card",1,"01/23/2021","admin"),("Citizen",1,"01/23/2021","admin"),("H1-B",0,"01/23/2021","admin"),("L2",0,"01/23/2021","admin"),("F1(CPT/OPT)",0,"01/23/2021","admin"),("H4",0,"01/23/2021","admin");

insert person values (1,"admin","admin","admin","admin@gmail.com","9998887777","8887776666","female","0","02/18/2014",1);
insert contact values (1,1,"friend","manager",0,0,1);
insert house(contactId, address, numberOfPerson) values (1,"123 ave, Princeton, NJ, 00001",5),(1,"555 Parkview Ln, Princeton, NJ, 00001",5),(1,"999 ave, Princeton, NJ, 00001",5);
insert facility(type, quantity, houseId) values ("Bed",5,1),("Mattress",5,1),("Table",5,1),("Chair",5,1);
insert facility(type, quantity, houseId) values ("Bed",6,2),("Mattress",5,2),("Table",4,2),("Chair",10,2);
insert facility(type, quantity, houseId) values ("Bed",4,3),("Mattress",4,3),("Table",7,3),("Chair",5,3);
