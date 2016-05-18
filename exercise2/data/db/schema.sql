DROP DATABASE IF EXISTS star;
CREATE DATABASE IF NOT EXISTS star CHARACTER SET = 'utf8' COLLATE 'utf8_bin';
USE star;

CREATE TABLE Star (
  id                     BIGINT AUTO_INCREMENT PRIMARY KEY,
  commonName             VARCHAR(32) NOT NULL,
  distanceInLightYears   DECIMAL (6, 1) NOT NULL,
  description            TEXT
);

