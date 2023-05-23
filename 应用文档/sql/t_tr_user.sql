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

 Date: 19/05/2023 17:55:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_tr_user
-- ----------------------------
DROP TABLE IF EXISTS `t_tr_user`;
CREATE TABLE `t_tr_user`  (
  `F_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `F_USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `F_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录密码',
  `F_SALT` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '随机盐',
  `F_LOCK` tinyint(1) NULL DEFAULT NULL COMMENT '是否锁定',
  `F_SEX` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `F_STATUS` int(0) NULL DEFAULT NULL COMMENT '用户状态',
  `F_CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `F_LAST_LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `F_LOGIN_ERROR_NUMBER` int(0) NULL DEFAULT NULL COMMENT '近十分钟登录失败次数',
  PRIMARY KEY (`F_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
