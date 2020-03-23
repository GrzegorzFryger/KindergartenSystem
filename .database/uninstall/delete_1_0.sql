-- ACCOUNT MODULE --
-- CALENDAR MODULE --
DELETE FROM calendar.absence;
DELETE FROM calendar.dayoffwork;
-- CORE MODULE --
-- FINANCES MODULE --
DELETE FROM finances.balancehistory;
-- GROUPS MODULE --
DELETE FROM classrooms.child;
DELETE FROM classrooms.classroom;
DELETE FROM classrooms.classroom_child;
-- MEAL MODULE --
-- PAYMENTS MODULE --
-- RECEIVABLES MODULE --
DELETE FROM receivables.transaction;
DELETE FROM receivables.transactionmapping;
DELETE FROM receivables.cashpayment;
-- FINAL COMMIT FOR ALL CHANGES
COMMIT;
