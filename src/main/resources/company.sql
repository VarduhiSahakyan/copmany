create database if not exists company;
use company;
DROP TABLE IF EXISTS `Association`;
CREATE TABLE IF NOT EXISTS `employee` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
        `employeeName` varchar(50) NOT NULL,
        `employeeSurname` varchar(50) NOT NULL,
         PRIMARY KEY (`id`),
         UNIQUE KEY (`employeeSurname`)
);
