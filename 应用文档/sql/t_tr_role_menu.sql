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

 Date: 19/05/2023 17:55:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_tr_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_tr_role_menu`;
CREATE TABLE `t_tr_role_menu`  (
  `F_ROLE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色id',
  `F_MENU_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限id',
  PRIMARY KEY (`F_ROLE_ID`, `F_MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色-权限表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
