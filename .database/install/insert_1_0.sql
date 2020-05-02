-- ACCOUNT MODULE --
INSERT INTO account.account (id, version, city, postalCode, streetNumber, name, surname, phone, accountStatus, email, createDate, password)
VALUES
('2a5c0cad-4de6-45a0-aebe-2a71cf06232b', 0, 'Gdańsk', '80-855', 'Rajska 25', 'Mariola', 'Andrzejkiewicz', '506924212', 'ACTIVE', 'user1@test.com', '2018-09-02', '$2a$10$xQFh0JV0fMDfX5Wf.IrGtusNlUCGfMc8YVsR1WY8/etwG4eQuNTqC'),
('8a186e67-b796-406f-8e6a-3dc483143121', 0, 'Gdańsk', '80-394', 'Kołobrzeska 41C', 'Paweł', 'Zajączkowski', '391521444', 'ACTIVE', 'user2@test.com', '2018-09-02', '$2a$10$TTIwcLEOgdWvZWG6JQD2bOLWnfumPZXGH78m8tQD0hGS01iu4lQsu'),
('c4029244-e8ff-4328-8658-28964dda3c4e', 0, 'Gdańsk', '80-179', 'Ostrzycka 15', 'Antoni', 'Michalkiewicz', '529174232', 'ACTIVE', 'user3@test.com', '2018-09-02', '$2a$10$fZdOhq3nXydUt3toHjquz.IxsZYhzLQsJhi1UxJFu2jZDwvMTYjfq');

INSERT INTO account.borough (id, version, city, postalCode, streetNumber, email, name, nipNumber, phone)
VALUES (1, 1, 'test', '80-164', 'Test Street', 'brak@wp.pl', 'New ', '957010564', '123123123');

INSERT INTO account.child (id, version, city, postalCode, streetNumber, dateOfBirth, name, surname, gender, peselNumber, additionDate, endingDate, borough_id)
VALUES
('45034cab-c252-4b2b-9887-59b7ad7431cd', 0, 'Gdańsk', '80-855', 'Rajska 25', '2016-11-10', 'Zuzanna', 'Andrzejkiewicz', 'FEMALE', '16311049421', '2019-09-02', '2021-08-30', null),
('fec1b907-c9ce-4327-8bc6-abe353a76ba2', 0, 'Gdańsk', '80-855', 'Rajska 25', '2015-04-23', 'Monika', 'Andrzejkiewicz', 'FEMALE', '15242312244', '2018-09-02', '2020-08-30', null),
('1c7fe144-f71a-4599-b8bf-04aa92d7ab6d', 0, 'Gdańsk', '80-394', 'Kołobrzeska 41C', '2016-07-10', 'Michał', 'Zajączkowski', 'MALE', '16271043831', '2019-09-01', '2021-08-30', null),
('1cb1e3fc-e0c9-45f4-861d-24884a4f64ad', 0, 'Gdańsk', '80-394', 'Kołobrzeska 41C', '2015-02-12', 'Agnieszka', 'Zajączkowska', 'FEMALE', '15221227381', '2018-09-01', '2020-08-30', null),
('0560d77d-e0db-4914-ae4a-4f39690ecb2d', 0, 'Gdańsk', '80-179', 'Ostrzycka 15', '2016-12-22', 'Adam', 'Michalkiewicz', 'MALE', '16322215477', '2019-09-02', '2021-08-30', null),
('067b5db4-de4e-401e-9cac-7f6289e96c19', 0, 'Gdańsk', '80-179', 'Ostrzycka 15', '2015-04-24', 'Paweł', 'Michalkiewicz', 'MALE', '15242444891', '2018-09-02', '2020-08-30', null),
('15ad869b-14c4-4be1-8456-d7f5fea6b557', 0, 'Gdańsk', '80-179', 'Ostrzycka 15', '2015-02-23', 'Julia', 'Michalkiewicz', 'FEMALE', '15222363729', '2018-09-01', '2020-08-30', null);

INSERT INTO account.child_childstatuses (Child_id, childStatuses)
VALUES
('45034cab-c252-4b2b-9887-59b7ad7431cd', 'NEW'),
('0560d77d-e0db-4914-ae4a-4f39690ecb2d', 'NEW'),
('067b5db4-de4e-401e-9cac-7f6289e96c19', 'NEW'),
('fec1b907-c9ce-4327-8bc6-abe353a76ba2', 'NEW'),
('15ad869b-14c4-4be1-8456-d7f5fea6b557', 'NEW'),
('1cb1e3fc-e0c9-45f4-861d-24884a4f64ad', 'NEW'),
('1c7fe144-f71a-4599-b8bf-04aa92d7ab6d', 'NEW');

INSERT INTO account.employee (employeeType, id)
VALUES
('TEACHER', '2a5c0cad-4de6-45a0-aebe-2a71cf06232b');

INSERT INTO account.guardian (id)
VALUES
('c4029244-e8ff-4328-8658-28964dda3c4e'),
('8a186e67-b796-406f-8e6a-3dc483143121'),
('2a5c0cad-4de6-45a0-aebe-2a71cf06232b');

INSERT INTO account.guardian_child (fk_guardian, fk_child)
VALUES
('2a5c0cad-4de6-45a0-aebe-2a71cf06232b', '45034cab-c252-4b2b-9887-59b7ad7431cd'),
('2a5c0cad-4de6-45a0-aebe-2a71cf06232b', 'fec1b907-c9ce-4327-8bc6-abe353a76ba2'),
('8a186e67-b796-406f-8e6a-3dc483143121', '1c7fe144-f71a-4599-b8bf-04aa92d7ab6d'),
('8a186e67-b796-406f-8e6a-3dc483143121', '1cb1e3fc-e0c9-45f4-861d-24884a4f64ad'),
('c4029244-e8ff-4328-8658-28964dda3c4e', '0560d77d-e0db-4914-ae4a-4f39690ecb2d'),
('c4029244-e8ff-4328-8658-28964dda3c4e', '067b5db4-de4e-401e-9cac-7f6289e96c19'),
('c4029244-e8ff-4328-8658-28964dda3c4e', '15ad869b-14c4-4be1-8456-d7f5fea6b557');

INSERT INTO account.role (id, version, name)
VALUES
(1, 0, 'USER'),
(2, 0, 'TEACHER'),
(3, 0, 'ADMINISTRATOR');

INSERT INTO account.role_account (fk_role, fk_account)
VALUES
(1, '2a5c0cad-4de6-45a0-aebe-2a71cf06232b'),
(2, '8a186e67-b796-406f-8e6a-3dc483143121'),
(3, 'c4029244-e8ff-4328-8658-28964dda3c4e');

INSERT INTO account.role_privileges (Role_id, privileges)
VALUES
(1, 'USER'),
(2, 'USER'),
(2, 'TEACHER'),
(3, 'USER'),
(3, 'TEACHER'),
(3, 'ADMINISTRATOR');


-- CALENDAR MODULE --
INSERT INTO calendar.absence (`version`, `childId`, `date`, `reason`)
VALUES
(0, 'bd73f070-cac9-48db-8037-7c1d34596584', '2019-11-12', 'Choroba'),
(0, 'bd73f070-cac9-48db-8037-7c1d34596584', '2019-10-25', 'Wyjazd'),
(0, 'cb34b97c-bbe9-4719-9091-0e0939804426', '2018-10-25', 'Choroba');

INSERT INTO calendar.dayoffwork (`version`, `date`, `eventType`, `name`)
VALUES
(0, '2020-01-01', 'HOLIDAY', 'Nowy Rok'),
(0, '2020-01-06', 'HOLIDAY', 'Trzech Króli'),
(0, '2020-04-12', 'HOLIDAY', 'Wielkanoc'),
(0, '2020-04-13', 'HOLIDAY', 'Poniedziałek Wielkanocny'),
(0, '2020-05-01', 'HOLIDAY', 'Święto Pracy'),
(0, '2020-05-03', 'HOLIDAY', 'Święto Konstytucji 3 Maja'),
(0, '2020-05-31', 'HOLIDAY', 'Zesłanie Ducha Świętego'),
(0, '2020-06-11', 'HOLIDAY', 'Boże Ciało'),
(0, '2020-08-15', 'HOLIDAY', 'Wniebowziecie Najswietszej Maryi Panny'),
(0, '2020-11-01', 'HOLIDAY', 'Wszystkich Świętych'),
(0, '2020-11-11', 'HOLIDAY', 'Święto Niepodległości'),
(0, '2020-12-25', 'HOLIDAY', 'Boże Narodzenie (pierwszy dzień)'),
(0, '2020-12-26', 'HOLIDAY', 'Boże Narodzenie (drugi dzień)'),
(0, '2020-05-12', 'INTERNAL_EVENT', 'Remont');


-- CORE MODULE --
-- FINANCES MODULE --
INSERT INTO finances.balancehistory(`version`, `amountOfChange`, `childId`, `date`, `operationType`, `title`)
VALUES
(0, 390.50, '0560d77d-e0db-4914-ae4a-4f39690ecb2d', '2019-10-14', 'INCREASE', '20180902634522'),
(0, 412.30, '0560d77d-e0db-4914-ae4a-4f39690ecb2d', '2019-11-17', 'INCREASE', '20180902634522'),
(0, 312.70, '067b5db4-de4e-401e-9cac-7f6289e96c19', '2019-10-12', 'INCREASE', '20190902442123'),
(0, 366.70, '067b5db4-de4e-401e-9cac-7f6289e96c19', '2019-11-12', 'INCREASE', '20190902442123'),
(0, 623.70, '067b5db4-de4e-401e-9cac-7f6289e96c19', '2019-12-12', 'INCREASE', '20190902442123'),
(0, 390.50, '067b5db4-de4e-401e-9cac-7f6289e96c19', '2019-11-23', 'INCREASE', '20190902442123'),
(0, 200.50, '0560d77d-e0db-4914-ae4a-4f39690ecb2d', '2019-12-12', 'INCREASE', '20180902634522');

INSERT INTO finances.accountNumber(`version`, `accountNumber`, `street`, `city`, `postalCode`)
VALUES
(0, '27105020040000300201355387', 'Olsztyńska 15', 'Gdańsk', '80-041');

-- GROUPS MODULE --
INSERT INTO classrooms.classroom(`id`, `version`, `groupName`, `groupDescription`)
VALUES
(1, 0, 'Klasa A', 'Opis klasy A'),
(2, 0, 'Klasa B', 'Opis klasy B'),
(3, 0, 'Klasa C', 'Opis klasy C'),
(4, 0, 'Klasa D', 'Opis klasy D');

INSERT INTO classrooms.child(`id`)
VALUES
('0560d77d-e0db-4914-ae4a-4f39690ecb2d'),
('067b5db4-de4e-401e-9cac-7f6289e96c19'),
('15ad869b-14c4-4be1-8456-d7f5fea6b557'),
('1cb1e3fc-e0c9-45f4-861d-24884a4f64ad'),
('45034cab-c252-4b2b-9887-59b7ad7431cd'),
('fec1b907-c9ce-4327-8bc6-abe353a76ba2');

INSERT INTO classrooms.classroom_child(`group_id`, `child_id`)
VALUES
('1', '0560d77d-e0db-4914-ae4a-4f39690ecb2d'),
('1', '067b5db4-de4e-401e-9cac-7f6289e96c19'),
('2', '15ad869b-14c4-4be1-8456-d7f5fea6b557'),
('3', '1cb1e3fc-e0c9-45f4-861d-24884a4f64ad'),
('3', '45034cab-c252-4b2b-9887-59b7ad7431cd'),
('4', 'fec1b907-c9ce-4327-8bc6-abe353a76ba2');


-- MEAL MODULE --
INSERT INTO `meal`.`meal` (`childID`, `dietType`, `mealFromDate`, `mealPrice`, `mealStatus`, `mealToDate`, `mealType`)
VALUES
('0560d77d-e0db-4914-ae4a-4f39690ecb2d','VEGETARIAN', '2020-01-27 11:00:00.000000', '15.50', '0', '2020-08-26 22:00:00.000000', 'BREAKFAST'),
('0560d77d-e0db-4914-ae4a-4f39690ecb2d','VEGETARIAN', '2020-01-27 11:00:00.000000', '56.44', '0', '2020-08-26 22:00:00.000000', 'DINER'),
('067b5db4-de4e-401e-9cac-7f6289e96c19','VEGETARIAN', '2020-01-27 11:00:00.000000', '56.44', '0', '2020-08-26 22:00:00.000000', 'DINER'),
('15ad869b-14c4-4be1-8456-d7f5fea6b557','VEGETARIAN', '2020-01-27 11:00:00.000000', '56.44', '0', '2020-08-26 22:00:00.000000', 'DINER');

INSERT INTO `meal`.`mealprice` (`mealPrice`, `mealType`)
VALUES
('56.44','DINER'),
('15.50','BREAKFAST');

INSERT INTO `meal`.`mealconfiguration` (`id`, `version`, `emailToSendMealOrder`)
VALUES
('1', '0', 'patyk@int.pl');


-- PAYMENTS MODULE --
-- RECEIVABLES MODULE --
INSERT INTO receivables.transaction (`version`, `accountNumber`, `bankName`, `bookingDate`, `childId`, `contractorDetails`, `details`, `guardianId`, `title`, `transactionAmount`, `transactionCurrency`, `transactionDate`, `transactionNumber`)
VALUES
(0, '61109010140000071219812874', 'ING Bank', '2019-10-14', '0560d77d-e0db-4914-ae4a-4f39690ecb2d', 'Antoni Michalkiewicz', 'Za dziecko XYZ', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20180902634522', 390.50, 'PLN', '2019-10-14', '201967193405359451'),
(0, '61109010140000071219812874', 'ING Bank', '2019-11-17', '0560d77d-e0db-4914-ae4a-4f39690ecb2d', 'Antoni Michalkiewicz', 'Za dziecko XYZ', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20180902634522', 412.30, 'PLN', '2019-11-17', '201967174335322451'),
(0, '56570065000000446723541423', 'ING Bank', '2019-10-12', '067b5db4-de4e-401e-9cac-7f6289e96c19', 'Antoni Michalkiewicz', 'Za dziecko XYZ', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20190902442123', 312.70, 'PLN', '2019-10-12', '201967174335322451'),
(0, '56570065000000446723541423', 'ING Bank', '2019-11-12', '067b5db4-de4e-401e-9cac-7f6289e96c19', 'Antoni Michalkiewicz', 'Za dziecko XYZ', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20190902442123', 366.70, 'PLN', '2019-11-12', '201967122332622451'),
(0, '56570065000000446723541423', 'ING Bank', '2019-12-12', '067b5db4-de4e-401e-9cac-7f6289e96c19', 'Antoni Michalkiewicz', 'Za dziecko XYZ', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20190902442123', 623.70, 'PLN', '2019-12-12', '201963374373423451'),
(0, '61109010140000071219812874', 'ING Bank', '2019-11-23', '067b5db4-de4e-401e-9cac-7f6289e96c19', 'Antoni Michalkiewicz', 'Za dziecko XYZ', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20190902442123', 390.50, 'PLN', '2019-10-14', '201967193405359451');

INSERT INTO receivables.transactionmapping (`version`, `childId`, `guardianId`, `title`)
VALUES
(0, '45034cab-c252-4b2b-9887-59b7ad7431cd', '2a5c0cad-4de6-45a0-aebe-2a71cf06232b', '20190902241253'),
(0, 'fec1b907-c9ce-4327-8bc6-abe353a76ba2', '2a5c0cad-4de6-45a0-aebe-2a71cf06232b', '20180902646734'),
(0, '1c7fe144-f71a-4599-b8bf-04aa92d7ab6d', '8a186e67-b796-406f-8e6a-3dc483143121', '20190902613423'),
(0, '1cb1e3fc-e0c9-45f4-861d-24884a4f64ad', '8a186e67-b796-406f-8e6a-3dc483143121', '20180902724421'),
(0, '067b5db4-de4e-401e-9cac-7f6289e96c19', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20190902442123'),
(0, '0560d77d-e0db-4914-ae4a-4f39690ecb2d', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20180902634522'),
(0, '15ad869b-14c4-4be1-8456-d7f5fea6b557', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20180902745634');

INSERT INTO receivables.cashpayment (`version`, `childId`, `contractorDetails`, `guardianId`, `title`, `transactionAmount`, `transactionCurrency`, `transactionDate`)
VALUES
(0, '0560d77d-e0db-4914-ae4a-4f39690ecb2d', 'Antoni Michalkiewicz', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20180902634522', 200.50, 'PLN', '2019-12-12'),
(0, '067b5db4-de4e-401e-9cac-7f6289e96c19', 'Antoni Michalkiewicz', 'c4029244-e8ff-4328-8658-28964dda3c4e', '20190902442123', 310.30, 'PLN', '2020-03-01');

-- FINAL COMMIT FOR ALL CHANGES
COMMIT;
