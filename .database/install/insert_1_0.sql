-- ACCOUNT MODULE --
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('2a5c0cad-4de6-45a0-aebe-2a71cf06232b', 0, 'test City', 'test postalcode', 'street Number 23', 'Test Name ', 'Test surname', '123132132', 'ACTIVE', 'test@test.com', '2020-02-06', '$2a$10$29SWVndzaD3yaN4Dnc1nxOVDk3l1ecrulU./lJy6hIIBR3ae5jk1.');
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('8a186e67-b796-406f-8e6a-3dc483143121', 0, 'test City', '80-264', 'street Number 23', 'Test Name', 'Test surname', '123132132', 'NOT_ACTIVE', 'test1@test.com', '2020-01-20', '$2a$10$6Ex4TJuKwju/zqr15A7Bv.2DLg3kGobThW0JhKNrtpnu6gNNv54ZC');
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('8a186e67-b796-406f-8e6a-3dc483143122', 0, 'test City', '80-284', 'street Number 456', 'Guardian1', 'Guardian2', '123132132', 'ACTIVE', 'test3@test.com', '2020-01-20', '$2a$10$6Ex4TJuKwju/zqr15A7Bv.2DLg3kGobThW0JhKNrtpnu6gNNv54ZC');
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('a7912412-12ce-40fe-b6a2-deb77ede555b', 0, 'test City', '10-100', 'street Number 23', 'Test Name ', 'Test surname', '123132132', 'ACTIVE', 'test2@test.com', '2020-01-20', '$2a$10$ssO8y2Ll4HRZtyBrEM.FA.nCGYy01LWi1PBs5UhCXgsu90.bxOANy');
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password) VALUES ('c4029244-e8ff-4328-8658-28964dda3c4e', 0, 'test City', 'test postalcode', 'street Number 23', 'Test Name', 'Test surname', '123132132', 'NOT_ACTIVE', 'test@test.com', '2020-02-06', '$2a$10$N.HbRP6eyzLlNaJvd4wMfOvgI56btTUeaMqG1We1Sz4O94ERZGYEW');

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
INSERT INTO account.role_account (fk_role, fk_account) VALUES (2, '2a5c0cad-4de6-45a0-aebe-2a71cf06232b');
INSERT INTO account.role_account (fk_role, fk_account) VALUES (1, 'c4029244-e8ff-4328-8658-28964dda3c4e');
INSERT INTO account.role_account (fk_role, fk_account) VALUES (2, 'c4029244-e8ff-4328-8658-28964dda3c4e');
INSERT INTO account.role_privileges (Role_id, privileges) VALUES (1, 'USER');
INSERT INTO account.role_privileges (Role_id, privileges) VALUES (2, 'TEACHER');



-- CALENDAR MODULE --
-- CORE MODULE --
-- FINANCES MODULE --
INSERT INTO finances.balance (`version`,`amount`, `childId`, `guardianId`)
VALUES (0,0.00, '7767d645-5408-4e79-9e4e-173b1fb23274', '25e7d80a-a38e-496c-afc2-dd5733fdf8ac');
INSERT INTO finances.balance (`version`,`amount`, `childId`, `guardianId`)
VALUES (0,0.00, 'bd73f070-cac9-48db-8037-7c1d34596584', '43750616-5f5d-4f34-9df8-d7759c180f72');
INSERT INTO finances.balance (`version`,`amount`, `childId`, `guardianId`)
VALUES (0,0.00, 'cb34b97c-bbe9-4719-9091-0e0939804426', '25e7d80a-a38e-496c-afc2-dd5733fdf8ac');
-- GROUPS MODULE --
-- MEAL MODULE --
-- PAYMENTS MODULE --
-- RECEIVABLES MODULE --
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'61109010140000071219812874','ING Bank','2019-10-14','7767d645-5408-4e79-9e4e-173b1fb23274','Andrzej Małysz','Za dziecko XYZ','25e7d80a-a38e-496c-afc2-dd5733fdf8ac','429630192312',390.50,'PLN','2019-10-14','201967193405359451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'61109010140000071219812874','ING Bank','2019-11-17','7767d645-5408-4e79-9e4e-173b1fb23274','Andrzej Małysz','Za dziecko XYZ','25e7d80a-a38e-496c-afc2-dd5733fdf8ac','429630192312',412.30,'PLN','2019-11-17','201967174335322451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'56570065000000446723541423','Bank Millennium','2019-10-12','bd73f070-cac9-48db-8037-7c1d34596584','Antoni Michalkiewicz','Za dziecko XYZ','43750616-5f5d-4f34-9df8-d7759c180f72','432673408632',312.70,'PLN','2019-10-12','201967174335322451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'56570065000000446723541423','Bank Millennium','2019-11-12','bd73f070-cac9-48db-8037-7c1d34596584','Antoni Michalkiewicz','Za dziecko XYZ','43750616-5f5d-4f34-9df8-d7759c180f72','432673408632',366.70,'PLN','2019-11-12','201967122332622451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'56570065000000446723541423','Bank Millennium','2019-12-12','bd73f070-cac9-48db-8037-7c1d34596584','Antoni Michalkiewicz','Za dziecko XYZ','43750616-5f5d-4f34-9df8-d7759c180f72','432673408632',623.70,'PLN','2019-12-12','201963374373423451');
INSERT INTO receivables.transaction (`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (0,'61109010140000071219812874','ING Bank','2019-11-23','cb34b97c-bbe9-4719-9091-0e0939804426','Andrzej Małysz','Za dziecko XYZ','25e7d80a-a38e-496c-afc2-dd5733fdf8ac','429630195555',390.50,'PLN','2019-10-14','201967193405359451');

INSERT INTO receivables.transactionmapping (`version`,`childId`, `guardianId`, `title`)
VALUES (0,'7767d645-5408-4e79-9e4e-173b1fb23274','25e7d80a-a38e-496c-afc2-dd5733fdf8ac','429630192312');
INSERT INTO receivables.transactionmapping (`version`,`childId`, `guardianId`, `title`)
VALUES (0,'bd73f070-cac9-48db-8037-7c1d34596584','43750616-5f5d-4f34-9df8-d7759c180f72','432673408632');
INSERT INTO receivables.transactionmapping (`version`,`childId`, `guardianId`, `title`)
VALUES (0,'cb34b97c-bbe9-4719-9091-0e0939804426','25e7d80a-a38e-496c-afc2-dd5733fdf8ac','429630195555');

INSERT INTO receivables.cashpayment (`version`,`childId`, `contractorDetails`, `guardianId`, `title`, `transactionAmount`, `transactionCurrency`, `transactionDate`)
VALUES (0,'7767d645-5408-4e79-9e4e-173b1fb23274','Andrzej Małysz','25e7d80a-a38e-496c-afc2-dd5733fdf8ac','429630192312',200.50,'PLN','2019-12-12');

-- FINAL COMMIT FOR ALL CHANGES
COMMIT;
