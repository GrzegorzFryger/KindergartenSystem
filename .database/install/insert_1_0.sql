-- ACCOUNT MODULE --
LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('8a186e67-b796-406f-8e6a-3dc483143121',0,'test City','80-264','street Number 23','Test Name','Test surname','123132132','NOT_ACTIVE','test1@test.com','2020-01-20','$2a$10$6Ex4TJuKwju/zqr15A7Bv.2DLg3kGobThW0JhKNrtpnu6gNNv54ZC'),('8a186e67-b796-406f-8e6a-3dc483143122',0,'test City','80-284','street Number 456','Guardian1','Guardian2','123132132','ACTIVE','test3@test.com','2020-01-20','$2a$10$6Ex4TJuKwju/zqr15A7Bv.2DLg3kGobThW0JhKNrtpnu6gNNv54ZC'),('a7912412-12ce-40fe-b6a2-deb77ede555b',0,'test City','10-100','street Number 23','Test Name ','Test surname','123132132','ACTIVE','test2@test.com','2020-01-20','$2a$10$ssO8y2Ll4HRZtyBrEM.FA.nCGYy01LWi1PBs5UhCXgsu90.bxOANy');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `borough` WRITE;
/*!40000 ALTER TABLE `borough` DISABLE KEYS */;
INSERT INTO `borough` VALUES (1,1,'test','80-164','Test Street','brak@wp.pl','New ','957010564','123123123');
/*!40000 ALTER TABLE `borough` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `child` WRITE;
/*!40000 ALTER TABLE `child` DISABLE KEYS */;
INSERT INTO `child` VALUES ('3e09083b-9ded-4607-bcc7-8dfe67c1b99a',3,'Update city','80-180','Update street','2007-12-02','Update Name','Update surname','MALE','94071105694','2007-12-02','2007-12-02',1),('7a64bff6-d8e1-4250-a5bd-78deaa0dff76',0,'Update city','80-180','Update street','1994-07-10','Update Name','Update surname','MALE','94071105694','2007-12-01','2007-12-01',NULL);
/*!40000 ALTER TABLE `child` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `children_group` WRITE;
/*!40000 ALTER TABLE `children_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `children_group` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('TEACHER','a7912412-12ce-40fe-b6a2-deb77ede555b');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `child_childstatuses` WRITE;
/*!40000 ALTER TABLE `child_childstatuses` DISABLE KEYS */;
INSERT INTO `child_childstatuses` VALUES ('7a64bff6-d8e1-4250-a5bd-78deaa0dff76','NEW');
/*!40000 ALTER TABLE `child_childstatuses` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `guardian` WRITE;
/*!40000 ALTER TABLE `guardian` DISABLE KEYS */;
INSERT INTO `guardian` VALUES ('8a186e67-b796-406f-8e6a-3dc483143121'),('8a186e67-b796-406f-8e6a-3dc483143122');
/*!40000 ALTER TABLE `guardian` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `guardian_child` WRITE;
/*!40000 ALTER TABLE `guardian_child` DISABLE KEYS */;
INSERT INTO `guardian_child` VALUES ('8a186e67-b796-406f-8e6a-3dc483143121','3e09083b-9ded-4607-bcc7-8dfe67c1b99a'),('8a186e67-b796-406f-8e6a-3dc483143121','7a64bff6-d8e1-4250-a5bd-78deaa0dff76');
/*!40000 ALTER TABLE `guardian_child` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,0,'TEACHER'),(2,0,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `role_account` WRITE;
/*!40000 ALTER TABLE `role_account` DISABLE KEYS */;
INSERT INTO `role_account` VALUES (2,'8a186e67-b796-406f-8e6a-3dc483143121'),(1,'a7912412-12ce-40fe-b6a2-deb77ede555b');
/*!40000 ALTER TABLE `role_account` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `role_privileges` WRITE;
/*!40000 ALTER TABLE `role_privileges` DISABLE KEYS */;
INSERT INTO `role_privileges` VALUES (1,'TEACHER'),(2,'USER');
/*!40000 ALTER TABLE `role_privileges` ENABLE KEYS */;
UNLOCK TABLES;

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
