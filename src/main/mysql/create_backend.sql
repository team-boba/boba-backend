create database backend;
use backend;

create table person (
	id int auto_increment primary key,
    firstName varchar(200) not null,
	lastName varchar(200) not null,
    middleName varchar(200),
    email varchar(200) not null,
    cellPhone varchar(200) not null,
    alternatePhone varchar(200),
    gender varchar(200),
    ssn varchar(200) not null,
    dob varchar(200) not null,
    userId int not null
);

create table address (
	id int auto_increment primary key, 
    addressLine1 varchar(500) not null,
    addressLine2 varchar(500) not null,
    city varchar(200) not null,
    zipcode varchar(200) not null,
    stateName varchar(200) not null,
    stateAbbr varchar(200) not null,
    personId int not null,
	foreign key (personId) references person(id) on update cascade on delete cascade
);

create table visa_status (
	id int auto_increment primary key, 
    visaType varchar(200) not null,
    active int not null,
    modificationDate varchar(200) not null,
    createUser varchar(200) not null
);

create table contact (
	id int auto_increment primary key, 
    personId int not null,
    relationship varchar(200) not null,
    title varchar(200) not null,
    isReferrence int not null,
    isEmergency int not null,
    isLandlord int not null,
	foreign key (personId) references person(id) on update cascade on delete cascade
);

create table house (
	id int auto_increment primary key, 
    contactId int not null,
    address varchar(200) not null,
    numberOfPerson int not null,
    foreign key (contactId) references contact(id) on update cascade on delete cascade
);

create table employee (
	id int auto_increment primary key,
    personId int not null,
    title varchar(200),
    startDate varchar(200),
    endDate varchar(200),
	avartar varchar(200),
    car varchar(500),
    visaStatusId int not null,
    visaStartDate varchar(200) not null,
    visaEndDate varchar(200) not null,
    driverLisence varchar(200),
    driverLisenceExpirationDate varchar(200),
    houseId int not null,
    foreign key (personId) references person(id) on update cascade on delete cascade,
    foreign key (visaStatusId) references visa_status(id) on update cascade on delete cascade,
    foreign key (houseId) references house(id) on update cascade on delete cascade
);

create table personal_document (
	id int auto_increment primary key, 
    employeeId int not null, 
    path varchar(500) not null,
    title varchar(200) not null,
    comment varchar(500),
    createdDate varchar(200) not null,
    createBy varchar(200) not null,
    foreign key (employeeId) references employee(id) on update cascade on delete cascade
);

create table digital_document (
	id int auto_increment primary key, 
    type varchar(200) not null, 
    required int not null,
    templateLocation varchar(500) not null,
    description varchar(500) not null
);

create table application_workflow (
	id int auto_increment primary key, 
    employeeId int not null,
    createdDate varchar(200) not null, 
    modificationDate varchar(200),
    status varchar(200) not null,
    comments varchar(500),
    type varchar(200),
    foreign key (employeeId) references employee(id) on update cascade on delete cascade
);

create table facility (
	id int auto_increment primary key, 
    type varchar(200) not null,
    description varchar(500), 
    quantity int not null,
    houseId int not null,
    foreign key (houseId) references house(id) on update cascade on delete cascade
);

create table facility_report (
	id int auto_increment primary key, 
    title varchar(200) not null,
    employeeId int not null,
    reportDate varchar(200) not null,
    description varchar(500) not null, 
    status varchar(200) not null,
    foreign key (employeeId) references employee(id) on update cascade on delete cascade
);

create table facility_report_detail (
	id int auto_increment primary key, 
    reportId int not null,
    employeeId int not null,
    comments varchar(1000) not null,
    createdDate varchar(200) not null,
    lastModificationDate varchar(200),
    foreign key (employeeId) references employee(id) on update cascade on delete cascade,
    foreign key (reportId) references facility_report(id) on update cascade on delete cascade
);