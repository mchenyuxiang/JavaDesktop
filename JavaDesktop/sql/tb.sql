/*
SQLyog Ultimate v9.20 
MySQL - 5.6.24 : Database - db_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_system`;

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(40) NOT NULL,
  `adminPassword` varchar(40) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_admin` */

/*Table structure for table `tb_depart` */

DROP TABLE IF EXISTS `tb_depart`;

CREATE TABLE `tb_depart` (
  `departId` int(8) NOT NULL AUTO_INCREMENT COMMENT '出发城市ID',
  `departName` varchar(40) DEFAULT NULL COMMENT '出发城市名称',
  `departInfo` varchar(1000) DEFAULT NULL COMMENT '出发城市信息',
  PRIMARY KEY (`departId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_depart` */

/*Table structure for table `tb_destination` */

DROP TABLE IF EXISTS `tb_destination`;

CREATE TABLE `tb_destination` (
  `destinationId` int(8) NOT NULL AUTO_INCREMENT COMMENT '目的地线路ID',
  `destinationName` varchar(40) DEFAULT NULL COMMENT '目的地名称',
  `destinationInfo` varchar(1000) DEFAULT NULL COMMENT '目的地信息',
  PRIMARY KEY (`destinationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_destination` */

/*Table structure for table `tb_line` */

DROP TABLE IF EXISTS `tb_line`;

CREATE TABLE `tb_line` (
  `lineId` int(8) NOT NULL AUTO_INCREMENT COMMENT '线路Id编号',
  `lineName` varchar(50) DEFAULT NULL COMMENT '线路名称',
  `salerName` varchar(50) DEFAULT NULL COMMENT '销售名称',
  `supplierName` varchar(50) DEFAULT NULL COMMENT '供应商名称',
  `shopName` varchar(50) DEFAULT NULL COMMENT '店铺名称',
  `destinationName` varchar(50) DEFAULT NULL COMMENT '目的地名称',
  `departName` varchar(50) DEFAULT NULL COMMENT '出发城市名称',
  `expenseInfo` varchar(1000) DEFAULT NULL COMMENT '自费信息',
  `isThrough` int(8) DEFAULT NULL COMMENT '是否转机',
  PRIMARY KEY (`lineId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_line` */

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

insert  into `tb_role`(`roleId`,`roleName`) values (1,'admin'),(2,'sale'),(3,'supplier');

/*Table structure for table `tb_saler` */

DROP TABLE IF EXISTS `tb_saler`;

CREATE TABLE `tb_saler` (
  `salerId` int(8) NOT NULL AUTO_INCREMENT COMMENT '销售ID',
  `salerName` varchar(40) NOT NULL COMMENT '销售用户名',
  `salerPhone` varchar(40) DEFAULT NULL COMMENT '销售电话',
  `salerTel` varchar(40) DEFAULT NULL COMMENT '销售座机',
  `salerQQ` varchar(20) DEFAULT NULL COMMENT '销售QQ',
  `salerWeiXin` varchar(40) DEFAULT NULL COMMENT '销售微信',
  `salerPassword` varchar(50) DEFAULT NULL COMMENT '销售登陆密码',
  PRIMARY KEY (`salerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_saler` */

/*Table structure for table `tb_shop` */

DROP TABLE IF EXISTS `tb_shop`;

CREATE TABLE `tb_shop` (
  `shopId` int(8) NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `shopName` varchar(40) DEFAULT NULL COMMENT '店铺名称',
  `shopInfo` varchar(1000) DEFAULT NULL COMMENT '店铺信息',
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_shop` */

/*Table structure for table `tb_supplier` */

DROP TABLE IF EXISTS `tb_supplier`;

CREATE TABLE `tb_supplier` (
  `supplierId` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `supplierName` varchar(40) NOT NULL COMMENT '供应商名称',
  `supplierAddress` varchar(200) DEFAULT NULL COMMENT '供应商地址',
  `supplierContact` varchar(200) DEFAULT NULL COMMENT '供应商联系人',
  `supplierPhone` varchar(20) DEFAULT NULL COMMENT '供应商手机',
  `supplierTel` varchar(20) DEFAULT NULL COMMENT '供应商座机',
  `supplierQQ` varchar(20) DEFAULT NULL COMMENT '供应商QQ',
  `supplierWinXin` varchar(20) DEFAULT NULL COMMENT '供应商微信',
  `supplierPassword` varchar(50) DEFAULT NULL COMMENT '供应商登陆密码',
  PRIMARY KEY (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_supplier` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userLogin` varchar(40) CHARACTER SET latin1 NOT NULL,
  `userPwd` varchar(200) CHARACTER SET latin1 NOT NULL,
  `userName` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `userRoleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`userId`,`userLogin`,`userPwd`,`userName`,`userRoleId`) values (1,'admin','admin','admin',1),(2,'sale','sale','sale',2),(3,'supplier','supplier','supplier',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
