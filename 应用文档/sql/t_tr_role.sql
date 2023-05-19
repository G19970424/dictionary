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

 Date: 19/05/2023 17:54:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_tr_role
-- ----------------------------
DROP TABLE IF EXISTS `t_tr_role`;
CREATE TABLE `t_tr_role`  (
  `F_ROLE_ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `F_ROLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `F_ROLE_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色关键字',
  `F_LOCKED` tinyint(1) NULL DEFAULT NULL COMMENT '是否被锁定',
  `F_STATUS` tinyint(1) NULL DEFAULT NULL COMMENT '状态：',
  `F_REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `F_CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`F_ROLE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
