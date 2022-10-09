DROP TABLE IF EXISTS music_categories;
CREATE TABLE music_categories (
  id int(11) DEFAULT NULL,
  name varchar(75) DEFAULT NULL
);

DROP TABLE IF EXISTS music_recordings;
CREATE TABLE music_recordings (
  recording_id int(11) DEFAULT NULL,
  artist_name varchar(75) DEFAULT NULL,
  title varchar(75) DEFAULT NULL,
  category varchar(75) DEFAULT NULL,
  image_name varchar(75) DEFAULT NULL,
  num_tracks int(11) DEFAULT NULL,
  price float DEFAULT NULL,
  stock_count int(11) DEFAULT NULL
);

DROP TABLE IF EXISTS music_recordings_tx;
CREATE TABLE music_recordings_tx (
  recording_id int(11) DEFAULT NULL,
  artist_name varchar(75) DEFAULT NULL,
  title varchar(75) DEFAULT NULL,
  category varchar(75) DEFAULT NULL,
  image_name varchar(75) DEFAULT NULL,
  num_tracks int(11) DEFAULT NULL,
  price float DEFAULT NULL,
  new_price float DEFAULT '0',
  stock_count int(11) DEFAULT NULL
);

DROP TABLE IF EXISTS music_tracks;
CREATE TABLE music_tracks (
  id int(11) DEFAULT NULL,
  recording_id int(11) DEFAULT NULL,
  title varchar(75) DEFAULT NULL,
  duration int(11) DEFAULT NULL
);

DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_name varchar(75) DEFAULT NULL,
  role_name varchar(75) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  user_name varchar(75) NOT NULL DEFAULT '',
  user_pass varchar(75) DEFAULT NULL,
  PRIMARY KEY (user_name)
);

DROP TABLE IF EXISTS video_actors;
CREATE TABLE video_actors (
  id int(11) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  recording_id int(11) DEFAULT NULL
);

DROP TABLE IF EXISTS video_categories;
CREATE TABLE video_categories (
  id int(11) DEFAULT NULL,
  name varchar(50) DEFAULT NULL
);


CREATE SEQUENCE video_categories_seq START WITH 101 INCREMENT BY 1;


DROP TABLE IF EXISTS video_recordings;
CREATE TABLE video_recordings (
  recording_id int(11) DEFAULT NULL,
  director varchar(50) DEFAULT NULL,
  title varchar(50) DEFAULT NULL,
  category varchar(50) DEFAULT NULL,
  image_name varchar(50) DEFAULT NULL,
  duration int(11) DEFAULT NULL,
  rating varchar(50) DEFAULT NULL,
  year_released int(11) DEFAULT NULL,
  price float DEFAULT NULL,
  stock_count int(11) DEFAULT NULL
);

DROP TABLE IF EXISTS video_recordings_tx;
CREATE TABLE video_recordings_tx (
  recording_id int(11) DEFAULT NULL,
  director varchar(50) DEFAULT NULL,
  title varchar(50) DEFAULT NULL,
  category varchar(50) DEFAULT NULL,
  image_name varchar(50) DEFAULT NULL,
  duration int(11) DEFAULT NULL,
  rating varchar(50) DEFAULT NULL,
  year_released int(11) DEFAULT NULL,
  price float DEFAULT NULL,
  new_price float DEFAULT '0',
  stock_count int(11) DEFAULT NULL
);

