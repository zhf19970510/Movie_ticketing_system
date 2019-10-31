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

 Date: 31/10/2019 22:43:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `seat` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `rk_sid`(`sid`) USING BTREE,
  INDEX `rk_uid`(`uid`) USING BTREE,
  CONSTRAINT `rk_sid` FOREIGN KEY (`sid`) REFERENCES `sessions` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rk_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 5, '1,1');
INSERT INTO `orders` VALUES (2, 1, 6, '1,2');
INSERT INTO `orders` VALUES (3, 1, 9, '1,3');

SET FOREIGN_KEY_CHECKS = 1;
