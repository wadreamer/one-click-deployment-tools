/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : test_dt

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-06-09 18:18:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cfg_taskfile
-- ----------------------------
DROP TABLE IF EXISTS `cfg_taskfile`;
CREATE TABLE `cfg_taskfile` (
  `task_id` int NOT NULL,
  `full_path` varchar(250) NOT NULL,
  `file_id` int NOT NULL,
  PRIMARY KEY (`task_id`,`full_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_taskfile
-- ----------------------------
INSERT INTO `cfg_taskfile` VALUES ('7', '/abc/ddd', '1');
INSERT INTO `cfg_taskfile` VALUES ('7', '/abc/fff', '2');
INSERT INTO `cfg_taskfile` VALUES ('7', '/cde/gfd', '3');
