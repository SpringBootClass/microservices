-- One to One Mapping
DROP DATABASE IF EXISTS empaddressdb;
CREATE DATABASE empaddressdb; /*!40100 DEFAULT CHARACTER SET utf8 */;


DROP TABLE IF EXISTS empaddressdb.employees;
/* EMPLOYEE table */
CREATE TABLE empaddressdb.employees (
    emp_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    emp_fname VARCHAR(50) NOT NULL DEFAULT '',
    emp_lname VARCHAR(50) NOT NULL DEFAULT '',
    emp_desg  VARCHAR(50) NOT NULL DEFAULT '',
    emp_sal BIGINT(10) NOT NULL DEFAULT 0,
    emp_phn VARCHAR(15) NOT NULL DEFAULT '',
    PRIMARY KEY (emp_id)
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE empaddressdb.employees AUTO_INCREMENT=1001;


DROP TABLE IF EXISTS empaddressdb.employeeaddresses;
/* EMPLOYEEADDRESS table */
CREATE TABLE empaddressdb.employeeaddresses (
    emp_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    street VARCHAR(50) NOT NULL DEFAULT '',
    city VARCHAR(50) NOT NULL DEFAULT '',
    state VARCHAR(50) NOT NULL DEFAULT '',
    country VARCHAR(50) NOT NULL DEFAULT '',
    PRIMARY KEY (emp_id),
    CONSTRAINT FK_EMP FOREIGN KEY (emp_id) REFERENCES empaddressdb.employees (emp_id)
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE empaddressdb.employeeaddresses AUTO_INCREMENT=1001;


-- One to Many Mapping

DROP TABLE IF EXISTS empaddressdb.carts;
CREATE TABLE empaddressdb.carts (
  cart_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  total decimal(10,0) NOT NULL,
  name varchar(10) DEFAULT NULL,
  PRIMARY KEY (cart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE empaddressdb.carts AUTO_INCREMENT=5;


DROP TABLE IF EXISTS empaddressdb.Items;
CREATE TABLE empaddressdb.Items (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  cart_id int(11) unsigned NOT NULL,
  item_id varchar(10) NOT NULL,
  item_total decimal(10,0) NOT NULL,
  quantity int(3) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_cart (cart_id),
  CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES empaddressdb.carts (cart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE empaddressdb.Items AUTO_INCREMENT=5;


-- Many to Many Mapping
--create database usersdb;
-- 
--use usersdb;

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






