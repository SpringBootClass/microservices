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


