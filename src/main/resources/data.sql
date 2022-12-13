CREATE TABLE IF NOT EXISTS shortened_url_entity (
   id INTEGER NOT NULL AUTO_INCREMENT,
   access_count INTEGER DEFAULT NULL,
   created_date DATE DEFAULT NULL,
   long_url VARCHAR(255) DEFAULT NULL,
   short_url VARCHAR(255) DEFAULT NULL,
   shortened_count INTEGER DEFAULT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS user_entity (
   user_id INTEGER NOT NULL AUTO_INCREMENT,
   user_email VARCHAR(255) DEFAULT NULL,
   user_password VARCHAR(255) DEFAULT NULL,
   user_role INTEGER DEFAULT NULL,
   PRIMARY KEY (`user_id`)
);