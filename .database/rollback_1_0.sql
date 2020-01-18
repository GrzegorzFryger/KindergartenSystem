DROP SCHEMA IF EXISTS account;
DROP SCHEMA IF EXISTS core;
DROP SCHEMA IF EXISTS calendar;
DROP SCHEMA IF EXISTS classrooms;
DROP SCHEMA IF EXISTS meal;
DROP SCHEMA IF EXISTS payments;
DROP SCHEMA IF EXISTS receivables;
DROP SCHEMA IF EXISTS finances;

DROP USER IF EXISTS 'account'@'localhost';
DROP USER IF EXISTS 'calendar'@'localhost';
DROP USER IF EXISTS 'core'@'localhost';
DROP USER IF EXISTS 'classrooms'@'localhost';
DROP USER IF EXISTS 'meal'@'localhost';
DROP USER IF EXISTS 'payments'@'localhost';
DROP USER IF EXISTS 'receivables'@'localhost';
DROP USER IF EXISTS 'finances'@'localhost';
COMMIT;