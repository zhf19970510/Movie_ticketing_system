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

 Date: 31/10/2019 22:43:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `commentsId` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `fid` int(11) NULL DEFAULT NULL,
  `fscore` double NOT NULL DEFAULT 6,
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  PRIMARY KEY (`commentsId`) USING BTREE,
  INDEX `rk_uid_comments`(`uid`) USING BTREE,
  INDEX `rk_fid_comments`(`fid`) USING BTREE,
  CONSTRAINT `rk_fid_comments` FOREIGN KEY (`fid`) REFERENCES `film` (`fid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rk_uid_comments` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 5, 1, 8.5, '这是一部三分之二的时间里都是弱光场景的3D电影');
INSERT INTO `comments` VALUES (2, 9, 1, 9.2, '李安作为一名探索者，从《少年派的奇幻漂流》到《比利·林恩的中场战事》，一直走在电影技术前沿，为观众带去一次又一次的全新观赏体验。');

SET FOREIGN_KEY_CHECKS = 1;
