-- ACCOUNT MODULE --
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('2a5c0cad-4de6-45a0-aebe-2a71cf06232b', 0, 'test City 1', '80-261', 'street Number 1', 'user1', 'user1', '123132132', 'ACTIVE', 'user1@test.com', '2020-02-06', '$2a$10$xQFh0JV0fMDfX5Wf.IrGtusNlUCGfMc8YVsR1WY8/etwG4eQuNTqC');
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('8a186e67-b796-406f-8e6a-3dc483143121', 0, 'test City 2', '80-264', 'street Number 2', 'user2', 'user2', '123132132', 'ACTIVE', 'user2@test.com', '2020-01-20', '$2a$10$TTIwcLEOgdWvZWG6JQD2bOLWnfumPZXGH78m8tQD0hGS01iu4lQsu');
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('c4029244-e8ff-4328-8658-28964dda3c4e', 0, 'test City 3', '80-265', 'street Number 3', 'user3', 'user3', '123132132', 'ACTIVE', 'user3@test.com', '2020-02-06', '$2a$10$fZdOhq3nXydUt3toHjquz.IxsZYhzLQsJhi1UxJFu2jZDwvMTYjfq');

INSERT INTO account.borough (id, version, city, postalCode, streetNumber, email, name, nipNumber, phone) VALUES (1, 1, 'test', '80-164', 'Test Street', 'brak@wp.pl', 'New ', '957010564', '123123123');
INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id) VALUES ('0560d77d-e0db-4914-ae4a-4f39690ecb2d', 0, 'city4', '80-174', 'Street 174', '1994-07-10', 'Name3', 'surname4', 'MALE', '94071105694', '2007-12-02', '2007-12-02', null);
INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id) VALUES ('067b5db4-de4e-401e-9cac-7f6289e96c19', 0, 'city4', '80-174', 'Street 174', '1994-07-10', 'Name3', 'surname4', 'MALE', '94071105694', '2007-12-02', '2007-12-02', null);
INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id) VALUES ('15ad869b-14c4-4be1-8456-d7f5fea6b557', 0, 'city4', '80-174', 'Street 174', '1994-07-10', 'Name3', 'surname4', 'MALE', '94071105694', '2007-12-01', '2007-12-01', null);
INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id) VALUES ('1c7fe144-f71a-4599-b8bf-04aa92d7ab6d', 0, 'city4', '80-174', 'Street 174', '1994-07-10', 'Name3', 'surname4', 'MALE', '94071105694', '2007-12-01', '2007-12-01', null);
INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id) VALUES ('1cb1e3fc-e0c9-45f4-861d-24884a4f64ad', 0, 'city4', '80-174', 'Street 174', '1994-07-10', 'Name3', 'surname4', 'MALE', '94071105694', '2007-12-01', '2007-12-01', null);
INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id) VALUES ('45034cab-c252-4b2b-9887-59b7ad7431cd', 0, 'city4', '80-174', 'Street 174', '1994-07-10', 'Name3', 'surname4', 'MALE', '94071105694', '2007-12-02', '2007-12-02', null);
INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id) VALUES ('fec1b907-c9ce-4327-8bc6-abe353a76ba2', 0, 'city4', '80-174', 'Street 174', '1994-07-10', 'Name3', 'surname4', 'MALE', '94071105694', '2007-12-02', '2007-12-02', null);
INSERT INTO account.child_childstatuses (Child_id, childStatuses) VALUES ('45034cab-c252-4b2b-9887-59b7ad7431cd', 'NEW');
INSERT INTO account.child_childstatuses (Child_id, childStatuses) VALUES ('0560d77d-e0db-4914-ae4a-4f39690ecb2d', 'NEW');
INSERT INTO account.child_childstatuses (Child_id, childStatuses) VALUES ('067b5db4-de4e-401e-9cac-7f6289e96c19', 'NEW');
INSERT INTO account.child_childstatuses (Child_id, childStatuses) VALUES ('fec1b907-c9ce-4327-8bc6-abe353a76ba2', 'NEW');
INSERT INTO account.child_childstatuses (Child_id, childStatuses) VALUES ('15ad869b-14c4-4be1-8456-d7f5fea6b557', 'NEW');
INSERT INTO account.child_childstatuses (Child_id, childStatuses) VALUES ('1cb1e3fc-e0c9-45f4-861d-24884a4f64ad', 'NEW');
INSERT INTO account.child_childstatuses (Child_id, childStatuses) VALUES ('1c7fe144-f71a-4599-b8bf-04aa92d7ab6d', 'NEW');
INSERT INTO account.employee (employeeType, id) VALUES ('TEACHER', '2a5c0cad-4de6-45a0-aebe-2a71cf06232b');
INSERT INTO account.guardian (id) VALUES ('c4029244-e8ff-4328-8658-28964dda3c4e');
INSERT INTO account.role (id, version, name) VALUES (1, 0, 'USER');
INSERT INTO account.role (id, version, name) VALUES (2, 0, 'TEACHER');
INSERT INTO account.role (id, version, name) VALUES (3, 0, 'ADMINISTRATOR');
INSERT INTO account.role_account (fk_role, fk_account) VALUES (1, '2a5c0cad-4de6-45a0-aebe-2a71cf06232b');
INSERT INTO account.role_account (fk_role, fk_account) VALUES (2, '8a186e67-b796-406f-8e6a-3dc483143121');
INSERT INTO account.role_account (fk_role, fk_account) VALUES (3, 'c4029244-e8ff-4328-8658-28964dda3c4e');
INSERT INTO account.role_privileges (Role_id, privileges) VALUES (1, 'USER');
INSERT INTO account.role_privileges (Role_id, privileges) VALUES (2, 'TEACHER');
INSERT INTO account.role_privileges (Role_id, privileges) VALUES (3, 'ADMINISTRATOR');


-- CALENDAR MODULE --
INSERT INTO calendar.absence (`version`, `childId`, `date`, `reason`)
VALUES (0, 'bd73f070-cac9-48db-8037-7c1d34596584', '2019-11-12', 'Choroba');
INSERT INTO calendar.absence (`version`, `childId`, `date`, `reason`)
VALUES (0, 'bd73f070-cac9-48db-8037-7c1d34596584', '2019-10-25', 'Wyjazd');
INSERT INTO calendar.absence (`version`, `childId`, `date`, `reason`)
VALUES (0, 'cb34b97c-bbe9-4719-9091-0e0939804426', '2018-10-25', 'Choroba');

INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-01-01', 'HOLIDAY', 'Nowy Rok');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-01-06', 'HOLIDAY', 'Trzech Króli');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-04-12', 'HOLIDAY', 'Wielkanoc');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-04-13', 'HOLIDAY', 'Poniedziałek Wielkanocny');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-05-01', 'HOLIDAY', 'Święto Pracy');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-05-03', 'HOLIDAY', 'Święto Konstytucji 3 Maja');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-05-31', 'HOLIDAY', 'Zesłanie Ducha Świętego');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-06-11', 'HOLIDAY', 'Boże Ciało');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-08-15', 'HOLIDAY', 'Wniebowziecie Najswietszej Maryi Panny');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-11-01', 'HOLIDAY', 'Wszystkich Świętych');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-11-11', 'HOLIDAY', 'Święto Niepodległości');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-12-25', 'HOLIDAY', 'Boże Narodzenie (pierwszy dzień)');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-12-26', 'HOLIDAY', 'Boże Narodzenie (drugi dzień)');
INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES (0, '2020-05-12', 'INTERNAL_EVENT', 'Remont');



-- CORE MODULE --
-- FINANCES MODULE --
-- GROUPS MODULE --
INSERT INTO classrooms.classroom(`id`, `version`, `groupName`, `groupDescription`)
VALUES (1, 0, 'Klasa A', 'Opis klasy A');
INSERT INTO classrooms.classroom(`id`, `version`, `groupName`, `groupDescription`)
VALUES (2, 0, 'Klasa B', 'Opis klasy B');
INSERT INTO classrooms.classroom(`id`, `version`, `groupName`, `groupDescription`)
VALUES (3, 0, 'Klasa C', 'Opis klasy C');
INSERT INTO classrooms.classroom(`id`, `version`, `groupName`, `groupDescription`)
VALUES (4, 0, 'Klasa D', 'Opis klasy D');

INSERT INTO classrooms.child(`id`)
VALUES('0560d77d-e0db-4914-ae4a-4f39690ecb2d');
INSERT INTO classrooms.child(`id`)
VALUES('067b5db4-de4e-401e-9cac-7f6289e96c19');
INSERT INTO classrooms.child(`id`)
VALUES('15ad869b-14c4-4be1-8456-d7f5fea6b557');
INSERT INTO classrooms.child(`id`)
VALUES('1cb1e3fc-e0c9-45f4-861d-24884a4f64ad');
INSERT INTO classrooms.child(`id`)
VALUES('45034cab-c252-4b2b-9887-59b7ad7431cd');
INSERT INTO classrooms.child(`id`)
VALUES('fec1b907-c9ce-4327-8bc6-abe353a76ba2');

INSERT INTO classrooms.classroom_child(`group_id`, `child_id`)
VALUES('1', '0560d77d-e0db-4914-ae4a-4f39690ecb2d');
INSERT INTO classrooms.classroom_child(`group_id`, `child_id`)
VALUES('1', '067b5db4-de4e-401e-9cac-7f6289e96c19');
INSERT INTO classrooms.classroom_child(`group_id`, `child_id`)
VALUES('2', '15ad869b-14c4-4be1-8456-d7f5fea6b557');
INSERT INTO classrooms.classroom_child(`group_id`, `child_id`)
VALUES('3', '1cb1e3fc-e0c9-45f4-861d-24884a4f64ad');
INSERT INTO classrooms.classroom_child(`group_id`, `child_id`)
VALUES('3', '45034cab-c252-4b2b-9887-59b7ad7431cd');
INSERT INTO classrooms.classroom_child(`group_id`, `child_id`)
VALUES('4', 'fec1b907-c9ce-4327-8bc6-abe353a76ba2');


-- MEAL MODULE --
INSERT INTO `meal`.`meal` (`childID`, `dietType`, `mealFromDate`, `mealPrice`, `mealStatus`, `mealToDate`, `mealType`) VALUES('0560d77d-e0db-4914-ae4a-4f39690ecb2d','VEGETARIAN', '2020-01-27 11:00:00.000000', '15.50', '0', '2020-08-26 22:00:00.000000', 'BREAKFAST');
INSERT INTO `meal`.`meal` (`childID`, `dietType`, `mealFromDate`, `mealPrice`, `mealStatus`, `mealToDate`, `mealType`) VALUES('0560d77d-e0db-4914-ae4a-4f39690ecb2d','VEGETARIAN', '2020-01-27 11:00:00.000000', '56.44', '0', '2020-08-26 22:00:00.000000', 'DINER');
INSERT INTO `meal`.`meal` (`childID`, `dietType`, `mealFromDate`, `mealPrice`, `mealStatus`, `mealToDate`, `mealType`) VALUES('067b5db4-de4e-401e-9cac-7f6289e96c19','VEGETARIAN', '2020-01-27 11:00:00.000000', '56.44', '0', '2020-08-26 22:00:00.000000', 'DINER');
INSERT INTO `meal`.`meal` (`childID`, `dietType`, `mealFromDate`, `mealPrice`, `mealStatus`, `mealToDate`, `mealType`) VALUES('15ad869b-14c4-4be1-8456-d7f5fea6b557','VEGETARIAN', '2020-01-27 11:00:00.000000', '56.44', '0', '2020-08-26 22:00:00.000000', 'DINER');

INSERT INTO `meal`.`mealprice` (`mealPrice`, `mealType`) VALUES ('56.44','DINER');
INSERT INTO `meal`.`mealprice` (`mealPrice`, `mealType`) VALUES ('15.50','BREAKFAST');

INSERT INTO `meal`.`mealconfiguration` (`id`, `version`, `emailToSendMealOrder`) VALUES ('1', '0', 'patyk@int.pl');


-- PAYMENTS MODULE --
-- RECEIVABLES MODULE --
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'61109010140000071219812874','ING Bank','2019-10-14','0560d77d-e0db-4914-ae4a-4f39690ecb2d','Andrzej Małysz','Za dziecko XYZ','c4029244-e8ff-4328-8658-28964dda3c4e','429630192312',390.50,'PLN','2019-10-14','201967193405359451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'61109010140000071219812874','ING Bank','2019-11-17','0560d77d-e0db-4914-ae4a-4f39690ecb2d','Andrzej Małysz','Za dziecko XYZ','c4029244-e8ff-4328-8658-28964dda3c4e','429630192312',412.30,'PLN','2019-11-17','201967174335322451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'56570065000000446723541423','Bank Millennium','2019-10-12','067b5db4-de4e-401e-9cac-7f6289e96c19','Antoni Michalkiewicz','Za dziecko XYZ','c4029244-e8ff-4328-8658-28964dda3c4e','432673408632',312.70,'PLN','2019-10-12','201967174335322451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'56570065000000446723541423','Bank Millennium','2019-11-12','067b5db4-de4e-401e-9cac-7f6289e96c19','Antoni Michalkiewicz','Za dziecko XYZ','c4029244-e8ff-4328-8658-28964dda3c4e','432673408632',366.70,'PLN','2019-11-12','201967122332622451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'56570065000000446723541423','Bank Millennium','2019-12-12','067b5db4-de4e-401e-9cac-7f6289e96c19','Antoni Michalkiewicz','Za dziecko XYZ','c4029244-e8ff-4328-8658-28964dda3c4e','432673408632',623.70,'PLN','2019-12-12','201963374373423451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'61109010140000071219812874','ING Bank','2019-11-23','067b5db4-de4e-401e-9cac-7f6289e96c19','Andrzej Małysz','Za dziecko XYZ','c4029244-e8ff-4328-8658-28964dda3c4e','429630195555',390.50,'PLN','2019-10-14','201967193405359451');

INSERT INTO receivables.transactionmapping (`version`,`childId`, `guardianId`, `title`)
VALUES (0,'067b5db4-de4e-401e-9cac-7f6289e96c19','c4029244-e8ff-4328-8658-28964dda3c4e','20200314753452');
INSERT INTO receivables.transactionmapping (`version`,`childId`, `guardianId`, `title`)
VALUES (0,'0560d77d-e0db-4914-ae4a-4f39690ecb2d','c4029244-e8ff-4328-8658-28964dda3c4e','20200314645723');

INSERT INTO receivables.cashpayment (`version`,`childId`, `contractorDetails`, `guardianId`, `title`, `transactionAmount`, `transactionCurrency`, `transactionDate`)
VALUES (0,'7767d645-5408-4e79-9e4e-173b1fb23274','Andrzej Małysz','25e7d80a-a38e-496c-afc2-dd5733fdf8ac','429630192312',200.50,'PLN','2019-12-12');

-- FINAL COMMIT FOR ALL CHANGES
COMMIT;
