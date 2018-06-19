/*
Navicat MySQL Data Transfer

Source Server         : zzs
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : website

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-19 04:36:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pdfdata`
-- ----------------------------
DROP TABLE IF EXISTS `pdfdata`;
CREATE TABLE `pdfdata` (
  `ID` int(50) NOT NULL AUTO_INCREMENT,
  `Name_File` varchar(100) NOT NULL,
  `PDF_Number` int(50) DEFAULT NULL,
  `Img_Width` int(20) DEFAULT NULL,
  `Img_Height` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pdfdata
-- ----------------------------
