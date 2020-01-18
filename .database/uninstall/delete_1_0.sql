-- ACCOUNT MODULE --
-- CALENDAR MODULE --
-- CORE MODULE --
-- FINANCES MODULE --
DELETE FROM finances.balance;
-- GROUPS MODULE --
-- MEAL MODULE --
-- PAYMENTS MODULE --
-- RECEIVABLES MODULE --
DELETE FROM receivables.transaction;
DELETE FROM receivables.transactionmapping;
DELETE FROM receivables.cashpayment;
-- FINAL COMMIT FOR ALL CHANGES
COMMIT;
