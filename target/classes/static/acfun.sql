/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : acfun

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-07-10 16:30:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `acid` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `likecount` int(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `viewcount` int(255) DEFAULT NULL,
  `content` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
