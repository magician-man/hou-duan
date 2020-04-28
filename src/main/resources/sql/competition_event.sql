/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-03-11 20:45:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for competition_event
-- ----------------------------
DROP TABLE IF EXISTS `competition_event`;
CREATE TABLE `competition_event` (
  `id` int(11) NOT NULL,
  `competition_event_code` varchar(20) NOT NULL,
  `competition_event_name` varchar(20) NOT NULL,
  `suit_type` int(11) NOT NULL,
  `athlete_code` varchar(20) NOT NULL,
  `plan_start_at` date NOT NULL,
  `plan_end_at` date NOT NULL,
  `status` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_by` varchar(50) NOT NULL,
  `deleted` int(11) DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition_event
-- ----------------------------
