use backend;

insert into person (first_name, last_name, middle_name, email, cell_phone, alternate_phone, gender, ssn, dob, user_id)
values
	('shiang', 'hu', 'mid', 'b02505002@gmail.com', '8585314317', '123123', 'male', 'N0123123', 'Y09131', 2)
;
set @newPersonId = last_insert_id();	

insert into employee (person_id, title, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_expiration_date, house_id)
values
	(@newPersonId, 'Engineer', '2021-03-15', '2050-04-04', 'https://beaconfire-team-boba.s3-us-west-1.amazonaws.com/1611711365325-husky.jpg', 
		'Audi 8', 3, '2021-03-25', '2026-03-25', 'N01002131', '2028-01-24', 2)
;

set @newEmployeeId = last_insert_id();
insert into facility_report (title, employee_id, report_date, description, status)
values
	('table broken', @newEmployeeId, '2021-01-27', 'table leg broken', 'pending')
;

set @newReportId = last_insert_id();
insert into facility_report_detail (report_id, employee_id, comments, created_date, last_modification_date)
values
	(@newReportId, @newEmployeeId, 'wood is wet', '2021-01-29', '2021-02-01')
;

insert into contact (person_id, relationship, title, is_reference, is_emergency, is_landlord)
values 
	(@newPersonId, 'emergency contact', 'father', 0, 1, 0)
;

insert into address (address_line_1, address_line_2, city, zipcode, state_name, state_abbr, person_id)
values
	('richmond', '23', 'irvine', '92620', 'california', 'ca', @newPersonId)
;

insert into personal_document (employee_id, path, title, comment, created_date, create_by)
values
	(@newEmployeeId, 'https://beaconfire-team-boba.s3-us-west-1.amazonaws.com/1611711498205-i983.pdf', 'myi983'
		, 'sign fast', '2018-05-21', 'ucsd')
;
 
insert into application_workflow (employee_id, created_date, modification_date, status, comments, type)
values 
	(@newEmployeeId, '2021-03-16', '2021-03-16', 'pending', 'waiting sign i983', 'opt')
;