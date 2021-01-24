create database backend;
use backend;

create table person (
	id int auto_increment primary key,
    first_name varchar(200) not null,
	last_name varchar(200) not null,
    middle_name varchar(200),
    email varchar(200) not null,
    cell_phone varchar(200) not null,
    alternate_phone varchar(200),
    gender varchar(200),
    ssn varchar(200) not null,
    dob varchar(200) not null,
    user_id int not null
);

create table address (
	id int auto_increment primary key, 
    address_line_1 varchar(500) not null,
    address_line_2 varchar(500) not null,
    city varchar(200) not null,
    zipcode varchar(200) not null,
    state_name varchar(200) not null,
    state_abbr varchar(200) not null,
    person_id int not null,
	foreign key (person_id) references person(id) on update cascade on delete cascade
);

create table visa_status (
	id int auto_increment primary key, 
    visa_type varchar(200) not null,
    active int not null,
    modification_date varchar(200) not null,
    create_user varchar(200) not null
);

create table contact (
	id int auto_increment primary key, 
    person_id int not null,
    relationship varchar(200) not null,
    title varchar(200) not null,
    is_referrence int not null,
    is_emergency int not null,
    is_landlord int not null,
	foreign key (person_id) references person(id) on update cascade on delete cascade
);

create table house (
	id int auto_increment primary key, 
    contact_id int not null,
    address varchar(200) not null,
    number_of_person int not null,
    foreign key (contact_id) references contact(id) on update cascade on delete cascade
);

create table employee (
	id int auto_increment primary key,
    person_id int not null,
    title varchar(200),
    start_date varchar(200),
    end_date varchar(200),
	avartar varchar(500),
    car varchar(500),
    visa_status_id int not null,
    visa_start_date varchar(200) not null,
    visa_end_date varchar(200) not null,
    driver_lisence varchar(200),
    driver_lisence_expiration_date varchar(200),
    house_id int not null,
    foreign key (person_id) references person(id) on update cascade on delete cascade,
    foreign key (visa_status_id) references visa_status(id) on update cascade on delete cascade,
    foreign key (house_id) references house(id) on update cascade on delete cascade
);

create table personal_document (
	id int auto_increment primary key, 
    employee_id int not null, 
    path varchar(500) not null,
    title varchar(200) not null,
    comment varchar(500),
    created_date varchar(200) not null,
    create_by varchar(200) not null,
    foreign key (employee_id) references employee(id) on update cascade on delete cascade
);

create table digital_document (
	id int auto_increment primary key, 
    type varchar(200) not null, 
    required int not null,
    template_location varchar(500) not null,
    description varchar(500) not null
);

create table application_workflow (
	id int auto_increment primary key, 
    employee_id int not null,
    created_date varchar(200) not null, 
    modification_date varchar(200),
    status varchar(200) not null,
    comments varchar(500),
    type varchar(200),
    foreign key (employee_id) references employee(id) on update cascade on delete cascade
);

create table facility (
	id int auto_increment primary key, 
    type varchar(200) not null,
    description varchar(500), 
    quantity int not null,
    house_id int not null,
    foreign key (house_id) references house(id) on update cascade on delete cascade
);

create table facility_report (
	id int auto_increment primary key, 
    title varchar(200) not null,
    employee_id int not null,
    report_date varchar(200) not null,
    description varchar(500) not null, 
    status varchar(200) not null,
    foreign key (employee_id) references employee(id) on update cascade on delete cascade
);

create table facility_report_detail (
	id int auto_increment primary key, 
    report_id int not null,
    employee_id int not null,
    comments varchar(1000) not null,
    created_date varchar(200) not null,
    last_modification_date varchar(200),
    foreign key (employee_id) references employee(id) on update cascade on delete cascade,
    foreign key (report_id) references facility_report(id) on update cascade on delete cascade
);