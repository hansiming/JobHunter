/*
SQLyog 企业版 - MySQL GUI v8.14
MySQL - 5.5.40 : Database - jobhunter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jobhunter` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `jobhunter`;

/*Table structure for table `clawer_tast` */

DROP TABLE IF EXISTS `clawer_tast`;

CREATE TABLE `clawer_tast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `url` varchar(1024) COLLATE utf8_bin NOT NULL,
  `job_count` int(11) DEFAULT '0',
  `statu` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `thread_count` int(11) not null,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `clawer_tast` */

/*Table structure for table `jobs` */

DROP TABLE IF EXISTS `jobs`;

CREATE TABLE `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `address_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_time` varchar(255) COLLATE utf8_bin NOT NULL,
  `min_money` int(11) NOT NULL,
  `max_monty` int(11) NOT NULL,
  `education_require` varchar(255) COLLATE utf8_bin NOT NULL,
  `company_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `remark` varchar(2048) COLLATE utf8_bin NOT NULL,
  `task_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `jobs` */

/*Table structure for table `protal_type` */

DROP TABLE IF EXISTS `protal_type`;

CREATE TABLE `protal_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `protal_type` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) COLLATE utf8_bin NOT NULL,
  `passwd` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `users` */

insert  into `users`(`id`,`userName`,`passwd`) values (1,'hsm','12345');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
