create database if not exists company;
use company;
CREATE TABLE IF NOT EXISTS `employee` (
        `id` int NOT NULL AUTO_INCREMENT,
        `employeeName` varchar(50) NOT NULL,
        `employeeSurname` varchar(50) NOT NULL,
         PRIMARY KEY (`id`),
         UNIQUE KEY (`employeeSurname`)
);
