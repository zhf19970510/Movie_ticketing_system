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

 Date: 31/10/2019 22:43:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for film
-- ----------------------------
DROP TABLE IF EXISTS `film`;
CREATE TABLE `film`  (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ftype` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fintroduce` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  PRIMARY KEY (`fid`) USING BTREE,
  UNIQUE INDEX `fname`(`fname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of film
-- ----------------------------
INSERT INTO `film` VALUES (1, '双子杀手', '动作', '暂无简介');
INSERT INTO `film` VALUES (2, '沉睡的魔咒2', '惊悚', '暂无简介');
INSERT INTO `film` VALUES (3, '海贼王·狂热行动', '动漫', '暂无简介');
INSERT INTO `film` VALUES (4, '哪吒之魔童降世', '动漫', '暂无简介');
INSERT INTO `film` VALUES (5, '登山者', '冒险', '暂无简介');
INSERT INTO `film` VALUES (6, '喜剧之王', '喜剧', '《喜剧之王》是星辉海外有限公司出品的一部喜剧电影，由李力持、周星驰执导，周星驰、');
INSERT INTO `film` VALUES (7, '夏洛特烦恼', '喜剧', '暂无简介');
INSERT INTO `film` VALUES (8, '卧虎藏龙', '武侠', '暂无简介');
INSERT INTO `film` VALUES (9, '倚天屠龙记', '武侠', '暂无简介');

SET FOREIGN_KEY_CHECKS = 1;
