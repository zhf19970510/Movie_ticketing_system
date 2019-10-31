/*
 Navicat Premium Data Transfer

 Source Server         : localDatabase
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : filmdb

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 31/10/2019 22:44:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `userNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `upass` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '1234',
  `utype` int(11) NOT NULL DEFAULT 0,
  `money` double NULL DEFAULT 100,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `userNo`(`userNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'charles', '1234', 2, 100);
INSERT INTO `users` VALUES (2, 'jack', '1234', 1, 100);
INSERT INTO `users` VALUES (3, 'rouse', '1234', 1, 100);
INSERT INTO `users` VALUES (4, 'marry', '1234', 1, 100);
INSERT INTO `users` VALUES (5, 'tom', '1234', 0, 11.120000000000005);
INSERT INTO `users` VALUES (6, 'jam', '1234', 0, 61.120000000000005);
INSERT INTO `users` VALUES (7, 'tina', '1234', 0, 100);
INSERT INTO `users` VALUES (8, 'moto', '1234', 0, 100);
INSERT INTO `users` VALUES (9, 'zhangsan', '66666', 0, 111.12);
INSERT INTO `users` VALUES (12, 'alibaba', '55555', 1, 100);

SET FOREIGN_KEY_CHECKS = 1;
