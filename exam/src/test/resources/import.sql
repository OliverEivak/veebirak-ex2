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

SELECT person_id.nextval from DUAL;
INSERT INTO person (person, first_name, last_name, identity_code, birth_date, created) VALUES (person_id.currval, 'Marten','Maasikas','672727337XX','1977-11-11',NOW());

SELECT person_id.nextval from DUAL;
INSERT INTO person (person, first_name, last_name, identity_code, birth_date, created) VALUES (person_id.currval, 'Tanel','Tuisk','672727337XX','1980-11-11',NOW());

SELECT person_id.nextval from DUAL;
INSERT INTO person (person, first_name, last_name, identity_code, birth_date, created) VALUES (person_id.currval, 'Kaarel','Klient','5555555555XXXX','1970-11-11',NOW());

-- employee
SELECT employee_id.nextval from DUAL;
INSERT INTO employee (employee, person_fk, enterprise_fk, active) VALUES (employee_id.currval, 1, 1, 'Y');

SELECT employee_id.nextval from DUAL;
INSERT INTO employee (employee, person_fk,enterprise_fk, active) VALUES (employee_id.currval, 2,1,'Y');

SELECT employee_id.nextval from DUAL;
INSERT INTO employee (employee, person_fk,enterprise_fk, active) VALUES (employee_id.currval, 3,1,'Y');

-- user_account
SELECT user_account_id.nextval from DUAL; -- pw=test
INSERT INTO user_account (user_account, subject_type_fk, subject_fk, username, passw, status) VALUES (user_account_id.currval, 3, 1, 'juhan', '$2a$06$KBkRPtHZ885IKg0CWbZTM.I0nmDv5M0xjKU8aB3eNmgA/TH2SlqVm', 1);

SELECT user_account_id.nextval from DUAL; -- pw=best
INSERT INTO user_account (user_account, subject_type_fk, subject_fk,username, passw,status) VALUES (user_account_id.currval, 3, 2,'marten','$2a$06$0Ak6R5i/5j1NfUuC2QISWOGB6ulDmUF1GOZRKGFV0nSPd8X3mkW12',1);

SELECT user_account_id.nextval from DUAL; -- pw=next
INSERT INTO user_account (user_account, subject_type_fk, subject_fk,username, passw,status) VALUES (user_account_id.currval, 3, 3,'tanel','$2a$06$0OY4tdQRAWcCX7an1f6j6.okioeDejGtcTZDTAn2Bi18qqhBHuodO',1);

-- customer
SELECT customer_id.nextval from DUAL;
INSERT INTO customer (customer, subject_fk, subject_type_fk) VALUES (customer_id.currval, 4, 1);

-- authentication
SELECT authentication_id.nextval from DUAL;
INSERT INTO authentication (id, token, user_account) VALUES (authentication_id.currval, 'asd', 1);

