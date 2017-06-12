DROP TABLE IF EXISTS person CASCADE ;
DROP TABLE IF EXISTS phone CASCADE;
DROP SEQUENCE global_seq CASCADE;
CREATE SEQUENCE global_seq START 1;
CREATE TABLE person (
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name            VARCHAR NOT NULL,
  email           VARCHAR,
  vk              VARCHAR
);
CREATE UNIQUE INDEX person_unique_idx ON person (id);
CREATE TABLE phone (
  phone           VARCHAR PRIMARY KEY,
  personid       INTEGER NOT NULL,
  FOREIGN KEY (personid) REFERENCES person (id)
);
CREATE UNIQUE INDEX phone_uniq_idx ON phone (phone);
