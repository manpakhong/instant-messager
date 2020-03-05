DROP TABLE IF EXISTS user;
CREATE TABLE USER (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR(255) NOT NULL ,
  PASSWORD VARCHAR(255) NOT NULL ,
  UNIQUE KEY UNI_USER_USERNAME (USERNAME),
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS role;
CREATE TABLE role (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  ROLE_NAME VARCHAR(255) NOT NULL,
  UNIQUE KEY UNI_ROLE_ROLE_NAME (ROLE_NAME),
  PRIMARY KEY (ID)
);

/** JOIN TABLES */

DROP TABLE IF EXISTS USER_ROLE;
CREATE TABLE user_role(
  ID INT(11) NOT NULL AUTO_INCREMENT,
  USER_ID INT(11) NOT NULL,
  ROLE_ID INT(11) NOT NULL,
  PRIMARY KEY (ID)
);