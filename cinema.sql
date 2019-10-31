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

 Date: 31/10/2019 22:42:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cinema
-- ----------------------------
DROP TABLE IF EXISTS `cinema`;
CREATE TABLE `cinema`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '上海',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  UNIQUE INDEX `cname`(`cname`) USING BTREE,
  INDEX `rk_uid_cinema`(`uid`) USING BTREE,
  CONSTRAINT `rk_uid_cinema` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cinema
-- ----------------------------
INSERT INTO `cinema` VALUES (1, '万达影城', '上海', '金山区龙皓路1088号万达广场四楼', 2);
INSERT INTO `cinema` VALUES (2, '中宏影城', '上海', '宝山区殷高西路555号西区3楼', 3);
INSERT INTO `cinema` VALUES (3, '国泰电影院', '上海', '黄浦区淮海中路870号', 4);

SET FOREIGN_KEY_CHECKS = 1;
