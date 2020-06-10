/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : test_dt

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-06-09 18:19:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cfg_taskstatus
-- ----------------------------
DROP TABLE IF EXISTS `cfg_taskstatus`;
CREATE TABLE `cfg_taskstatus` (
  `task_id` int NOT NULL,
  `project_id` int NOT NULL,
  `status` enum('待测试','测试中','测试通过') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '待测试',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_taskstatus
-- ----------------------------
INSERT INTO `cfg_taskstatus` VALUES ('7', '1', '待测试');
