-- ACCOUNT MODULE --
-- CORE MODULE --
-- FINANCES MODULE --
-- GROUPS MODULE --
-- MEAL MODULE --
-- PAYMENTS MODULE --
-- RECEIVABLES MODULE --
INSERT INTO receivables.transaction (`id`,`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (1,0,'61 1090 1014 0000 0712 1981 2874','ING Bank','2019-10-14',NULL,'Andrzej Małysz','Za dziecko XYZ',NULL,'429630192312',390.50,'PLN','2019-10-14','201967193405359451');
INSERT INTO receivables.transaction (`id`,`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (2,0,'61 1090 1014 0000 0712 1981 2874','ING Bank','2019-11-17',NULL,'Andrzej Małysz','Za dziecko XYZ',NULL,'429630192312',412.30,'PLN','2019-11-17','201967174335322451');
INSERT INTO receivables.transaction (`id`,`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (3,0,'56 1090 1014 0000 4467 2354 1423','Bank Millennium','2019-10-12',NULL,'Antoni Michalkiewicz','Za dziecko XYZ',NULL,'432673408632',312.70,'PLN','2019-10-12','201967174335322451');
INSERT INTO receivables.transaction (`id`,`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (4,0,'56 1090 1014 0000 4467 2354 1423','Bank Millennium','2019-11-12',NULL,'Antoni Michalkiewicz','Za dziecko XYZ',NULL,'432673408632',366.70,'PLN','2019-11-12','201967122332622451');
INSERT INTO receivables.transaction (`id`,`version`,`accountNumber`,`bankName`,`bookingDate`,`childId`,`contractorDetails`,`details`,`guardianId`,`title`,`transactionAmount`,`transactionCurrency`,`transactionDate`,`transactionNumber`)
VALUES (5,0,'56 1090 1014 0000 4467 2354 1423','Bank Millennium','2019-12-12',NULL,'Antoni Michalkiewicz','Za dziecko XYZ',NULL,'432673408632',623.70,'PLN','2019-12-12','201963374373423451');

UPDATE receivables.hibernate_sequence SET next_val = 6;

-- FINAL COMMIT FOR ALL CHANGES
COMMIT;
