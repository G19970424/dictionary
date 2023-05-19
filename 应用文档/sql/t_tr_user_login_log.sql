/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : dictionary_user

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 19/05/2023 17:55:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_tr_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_tr_user_login_log`;
CREATE TABLE `t_tr_user_login_log`  (
  `F_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `F_USER_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `F_LOGIN_TIME` datetime(0) NULL DEFAULT NULL,
  `F_LOGIN_STATUS` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`F_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
