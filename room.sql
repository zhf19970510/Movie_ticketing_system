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

 Date: 31/10/2019 22:44:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `rprice` double NOT NULL DEFAULT 88.88,
  `rsize` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '10,10',
  `cid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE,
  UNIQUE INDEX `rname`(`rname`) USING BTREE,
  INDEX `rk_cid`(`cid`) USING BTREE,
  CONSTRAINT `rk_cid` FOREIGN KEY (`cid`) REFERENCES `cinema` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '激光1号厅', 88.88, '10,10', 1);
INSERT INTO `room` VALUES (2, '激光2号厅', 44.44, '10,10', 1);
INSERT INTO `room` VALUES (3, '激光3号厅', 88.88, '10,10', 1);
INSERT INTO `room` VALUES (4, 'DTS巨幕厅1号厅', 88.88, '10,10', 2);
INSERT INTO `room` VALUES (5, 'DTS巨幕厅2号厅', 88.88, '10,10', 2);
INSERT INTO `room` VALUES (6, 'DTS巨幕厅3号厅', 88.88, '10,10', 2);
INSERT INTO `room` VALUES (7, '1号厅', 88.88, '10,10', 3);
INSERT INTO `room` VALUES (8, '2号厅', 88.88, '10,10', 3);
INSERT INTO `room` VALUES (9, '3号厅', 88.88, '10,10', 3);

SET FOREIGN_KEY_CHECKS = 1;
