-- H2 DATABASE NOTES
-- use CURRENT_TIMESTAMP() instead of NOW()
-- DATE format is yyyy-MM-dd
-- H2 and sequence problems: http://lees2bytes.blogspot.com.ee/2010/05/h2-database-junit-and-sequence-problems.html

-- enterprise
SELECT enterprise_id.nextval from DUAL;
INSERT INTO enterprise (enterprise, name, full_name, created) VALUES (enterprise_id.currval, 'Yhendatud Systeemid', 'Oy yhendatud Systeemid Ltd', CURRENT_TIMESTAMP());

-- person
SELECT person_id.nextval from DUAL;
INSERT INTO person (person, first_name, last_name, identity_code, birth_date, created) VALUES (person_id.currval, 'Juhan', 'Juurikas', '54637474', '1967-11-11', CURRENT_TIMESTAMP());

-- employee
SELECT employee_id.nextval from DUAL;
INSERT INTO employee (employee, person_fk, enterprise_fk, active) VALUES (employee_id.currval, 1, 1, 'Y');

-- user_account
SELECT user_account_id.nextval from DUAL;
INSERT INTO user_account (user_account, subject_type_fk, subject_fk, username, passw, status) VALUES (user_account_id.currval, 3, 1, 'juhan', '$2a$06$KBkRPtHZ885IKg0CWbZTM.I0nmDv5M0xjKU8aB3eNmgA/TH2SlqVm', 1);

-- authentication
SELECT authentication_id.nextval from DUAL;
INSERT INTO authentication (id, token, user_account) VALUES (authentication_id.currval, 'asd', 1);
