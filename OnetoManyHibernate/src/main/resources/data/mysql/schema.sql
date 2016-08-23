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






