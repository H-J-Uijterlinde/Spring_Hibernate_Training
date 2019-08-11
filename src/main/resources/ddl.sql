CREATE USER 'semafoor' @ 'localhost' IDENTIFIED BY 'semafoor';

CREATE SCHEMA MUSICDB;
GRANT ALL PRIVILEGES ON MUSICDB . * TO 'semafoor'@'localhost';
FLUSH PRIVILEGES;

SET GLOBAL time_zone = '+1:00';