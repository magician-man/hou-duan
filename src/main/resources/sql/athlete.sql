/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-03-11 18:52:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for athlete
-- ----------------------------
DROP TABLE IF EXISTS `athlete`;
CREATE TABLE `athlete` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `athlete_code` varchar(20) NOT NULL COMMENT '运动员编码',
  `reg_code` varchar(50) DEFAULT NULL COMMENT '运动员注册号',
  `team_code` varchar(20) DEFAULT NULL COMMENT '参赛队伍编码',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(50) DEFAULT NULL COMMENT '证件号',
  `id_type` int(11) unsigned DEFAULT NULL COMMENT '证件类型',
  `gender` tinyint(1) unsigned DEFAULT NULL COMMENT '性别',
  `age` int(11) unsigned DEFAULT NULL COMMENT '年龄',
  `competition_event_code` varchar(20) DEFAULT NULL COMMENT '比赛项目编码',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `deleted` int(11) DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of athlete
-- ----------------------------
