-- DROP DATABASE IF EXISTS repairshop;
-- CREATE DATABASE IF NOT EXISTS repairshop CHARACTER SET = 'utf8' COLLATE 'utf8_bin';
-- USE repairshop;

CREATE SEQUENCE authentication_id;

CREATE TABLE authentication (
  id            bigint NOT NULL DEFAULT nextval('authentication_id'),
  token         varchar(255) NOT NULL,
  user_account  bigint NOT NULL,

  CONSTRAINT id_pk PRIMARY KEY (id),
  CONSTRAINT token_uq UNIQUE (token)
);

CREATE OR REPLACE FUNCTION logout(tok VARCHAR(255))
RETURNS void AS $$
BEGIN
  DELETE FROM authentication WHERE token = tok;
END;
$$ LANGUAGE plpgsql;
