/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-03-11 20:46:01
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`
(
    `id`          int(11)      NOT NULL,
    `team_code`   varchar(20)  NOT NULL,
    `team_name`   varchar(20)  NOT NULL,
    `province`    varchar(20)  NOT NULL,
    `contact`     varchar(20)  NOT NULL,
    `phone`       varchar(50)  NOT NULL,
    `description` varchar(255) NOT NULL,
    `created_at`  datetime     NOT NULL,
    `updated_at`  datetime     NOT NULL,
    `created_by`  varchar(50)  NOT NULL,
    `updated_by`  varchar(50)  NOT NULL,
    `deleted`     int(11) DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
