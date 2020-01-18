-- ACCOUNT MODULE --
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
