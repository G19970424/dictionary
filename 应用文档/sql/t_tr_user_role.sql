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

 Date: 19/05/2023 17:55:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_tr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_tr_user_role`;
CREATE TABLE `t_tr_user_role`  (
  `F_USER_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `F_ROLE_ID` int(0) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`F_USER_ID`, `F_ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-角色表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
