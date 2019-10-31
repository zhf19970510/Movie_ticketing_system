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

 Date: 31/10/2019 22:44:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sessions
-- ----------------------------
DROP TABLE IF EXISTS `sessions`;
CREATE TABLE `sessions`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `startTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `endTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  `fid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE,
  INDEX `rk_rid`(`rid`) USING BTREE,
  INDEX `rk_fid`(`fid`) USING BTREE,
  CONSTRAINT `rk_fid` FOREIGN KEY (`fid`) REFERENCES `film` (`fid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rk_rid` FOREIGN KEY (`rid`) REFERENCES `room` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sessions
-- ----------------------------
INSERT INTO `sessions` VALUES (1, '2019-10-21 12:00', '2019-10-21 14:00', 1, 1);
INSERT INTO `sessions` VALUES (2, '2019-10-21 14:00', '2019-10-21 16:00', 1, 1);
INSERT INTO `sessions` VALUES (3, '2019-10-21 16:00', '2019-10-21 18:00', 1, 2);
INSERT INTO `sessions` VALUES (4, '2019-10-21 18:00', '2019-10-21 20:00', 1, 2);
INSERT INTO `sessions` VALUES (5, '2019-10-21 12:00', '2019-10-21 14:00', 2, 4);
INSERT INTO `sessions` VALUES (6, '2019-10-21 14:00', '2019-10-21 16:00', 2, 4);
INSERT INTO `sessions` VALUES (7, '2019-10-21 16:00', '2019-10-21 18:00', 2, 3);
INSERT INTO `sessions` VALUES (8, '2019-10-21 18:00', '2019-10-21 20:00', 2, 3);
INSERT INTO `sessions` VALUES (9, '2019-10-21 12:00', '2019-10-21 14:00', 3, 5);
INSERT INTO `sessions` VALUES (10, '2019-10-21 14:00', '2019-10-21 16:00', 3, 5);
INSERT INTO `sessions` VALUES (11, '2019-10-21 16:00', '2019-10-21 18:00', 3, 6);
INSERT INTO `sessions` VALUES (12, '2019-10-21 18:00', '2019-10-21 20:00', 3, 6);
INSERT INTO `sessions` VALUES (13, '2019-10-21 12:00', '2019-10-21 14:00', 4, 4);
INSERT INTO `sessions` VALUES (14, '2019-10-21 14:00', '2019-10-21 16:00', 4, 4);
INSERT INTO `sessions` VALUES (15, '2019-10-21 16:00', '2019-10-21 18:00', 4, 3);
INSERT INTO `sessions` VALUES (16, '2019-10-21 18:00', '2019-10-21 20:00', 4, 3);
INSERT INTO `sessions` VALUES (17, '2019-10-21 12:00', '2019-10-21 14:00', 5, 4);
INSERT INTO `sessions` VALUES (18, '2019-10-21 14:00', '2019-10-21 16:00', 5, 4);
INSERT INTO `sessions` VALUES (19, '2019-10-21 16:00', '2019-10-21 18:00', 5, 3);
INSERT INTO `sessions` VALUES (20, '2019-10-21 18:00', '2019-10-21 20:00', 5, 3);
INSERT INTO `sessions` VALUES (21, '2019-10-21 12:00', '2019-10-21 14:00', 6, 5);
INSERT INTO `sessions` VALUES (22, '2019-10-21 14:00', '2019-10-21 16:00', 6, 5);
INSERT INTO `sessions` VALUES (23, '2019-10-21 16:00', '2019-10-21 18:00', 6, 6);
INSERT INTO `sessions` VALUES (24, '2019-10-21 18:00', '2019-10-21 20:00', 6, 6);
INSERT INTO `sessions` VALUES (25, '2019-10-21 12:00', '2019-10-21 14:00', 7, 4);
INSERT INTO `sessions` VALUES (26, '2019-10-21 14:00', '2019-10-21 16:00', 7, 4);
INSERT INTO `sessions` VALUES (27, '2019-10-21 16:00', '2019-10-21 18:00', 7, 3);
INSERT INTO `sessions` VALUES (28, '2019-10-21 18:00', '2019-10-21 20:00', 7, 3);
INSERT INTO `sessions` VALUES (29, '2019-10-21 12:00', '2019-10-21 14:00', 8, 5);
INSERT INTO `sessions` VALUES (30, '2019-10-21 14:00', '2019-10-21 16:00', 8, 5);
INSERT INTO `sessions` VALUES (31, '2019-10-21 16:00', '2019-10-21 18:00', 8, 6);
INSERT INTO `sessions` VALUES (32, '2019-10-21 18:00', '2019-10-21 20:00', 8, 6);
INSERT INTO `sessions` VALUES (33, '2019-10-21 12:00', '2019-10-21 14:00', 9, 1);
INSERT INTO `sessions` VALUES (34, '2019-10-21 14:00', '2019-10-21 16:00', 9, 1);
INSERT INTO `sessions` VALUES (35, '2019-10-21 16:00', '2019-10-21 18:00', 9, 2);
INSERT INTO `sessions` VALUES (36, '2019-10-21 18:00', '2019-10-21 20:00', 9, 2);
INSERT INTO `sessions` VALUES (38, '2019-10-21 20:00', '2019-10-21 22:00', 2, 8);

SET FOREIGN_KEY_CHECKS = 1;
