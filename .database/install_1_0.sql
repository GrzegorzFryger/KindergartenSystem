CREATE SCHEMA IF NOT EXISTS account;
CREATE SCHEMA IF NOT EXISTS core;
CREATE SCHEMA IF NOT EXISTS classrooms;
CREATE SCHEMA IF NOT EXISTS meal;
CREATE SCHEMA IF NOT EXISTS payments;
CREATE SCHEMA IF NOT EXISTS receivables;
CREATE SCHEMA IF NOT EXISTS finances;

CREATE USER IF NOT EXISTS 'account'@'localhost' IDENTIFIED BY 'account01';
CREATE USER IF NOT EXISTS 'core'@'localhost' IDENTIFIED BY 'core01';
CREATE USER IF NOT EXISTS 'classrooms'@'localhost' IDENTIFIED BY 'classrooms01';
CREATE USER IF NOT EXISTS 'meal'@'localhost' IDENTIFIED BY 'meal01';
CREATE USER IF NOT EXISTS 'payments'@'localhost' IDENTIFIED BY 'payments01';
CREATE USER IF NOT EXISTS 'receivables'@'localhost' IDENTIFIED BY 'receivables01';
CREATE USER IF NOT EXISTS 'finances'@'localhost' IDENTIFIED BY 'finances01';

GRANT ALL PRIVILEGES ON account.* TO 'account'@'localhost';
GRANT ALL PRIVILEGES ON core.* TO 'core'@'localhost';
GRANT ALL PRIVILEGES ON classrooms.* TO 'classrooms'@'localhost';
GRANT ALL PRIVILEGES ON meal.* TO 'meal'@'localhost';
GRANT ALL PRIVILEGES ON payments.* TO 'payments'@'localhost';
GRANT ALL PRIVILEGES ON receivables.* TO 'receivables'@'localhost';
GRANT ALL PRIVILEGES ON finances.* TO 'finances'@'localhost';
COMMIT;