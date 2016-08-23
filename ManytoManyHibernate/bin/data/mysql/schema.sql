-- Many to Many Mapping


DROP TABLE IF EXISTS empaddressdb.users;
CREATE TABLE empaddressdb.users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  PRIMARY KEY (user_id)
);
 
DROP TABLE IF EXISTS empaddressdb.groups; 
CREATE TABLE empaddressdb.groups (
  group_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  PRIMARY KEY (group_id)
);
 
DROP TABLE IF EXISTS empaddressdb.Users_Groups; 
CREATE TABLE empaddressdb.Users_Groups (
  user_id int(11) NOT NULL,
  group_id int(11) NOT NULL,
  PRIMARY KEY (user_id,group_id),
  KEY fk_user (user_id),
  KEY fk_group (group_id),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES empaddressdb.users (user_id),
  CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES empaddressdb.groups (group_id)
);






