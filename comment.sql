/*
 Navicat Premium Data Transfer

 Source Server         : TL_connection
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : comments

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 10/03/2020 14:43:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments_reply
-- ----------------------------
DROP TABLE IF EXISTS `comments_reply`;
CREATE TABLE `comments_reply`  (
  `id` int(11) NOT NULL COMMENT '评论父表id',
  `comment_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论唯一标识',
  `from_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论者id',
  `from_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论者名字',
  `from_avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '评论者头像',
  `to_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '被评论者id',
  `to_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '被评论者名字',
  `to_avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '被评论者头像',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `like_num` int(11) NOT NULL DEFAULT 0 COMMENT '评论点赞数',
  INDEX `comment_id`(`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments_reply
-- ----------------------------
INSERT INTO `comments_reply` VALUES (1, 'd2302c300b294798bf691e5e626a45d3', '12345aaa', '小花', 'icon.jpg', '12345aaa', '小明', NULL, '第一条子评论', '2020-03-09 21:45:31', 1);
INSERT INTO `comments_reply` VALUES (2, '7dde7927c3dd487c8f9f67a3951cbb23', '12345aaa', '小花', 'icon.jpg', '12345aaa', '小花', NULL, 'bug2', '2020-03-09 21:49:54', 0);
INSERT INTO `comments_reply` VALUES (1, '70b7e86e065c4b8cbd10bb040cfd8f06', '12345aaa', '小花', 'icon.jpg', '12345aaa', '小明', NULL, 'buggggggg', '2020-03-09 21:53:29', 1);
INSERT INTO `comments_reply` VALUES (2, '11209c9e579348bea6da01941eba7121', '12345aaa', '小花', 'icon.jpg', '12345aaa', '小花', NULL, 'aaaaaaaaaaa', '2020-03-09 21:53:44', 0);
INSERT INTO `comments_reply` VALUES (2, '5013eb8b1bee4c0892c3f4fb95ba2b44', '12345aaa', '小花', 'icon.jpg', '12345aaa', '小花', NULL, 'aaaaaaaaaaaaaaaaabgawawbawhawh', '2020-03-09 21:53:51', 0);
INSERT INTO `comments_reply` VALUES (1, '625e1a6ff2a34f7eb7ce16348223823f', '12345aaa', '小花', 'icon.jpg', '12345aaa', '小明', NULL, '是我入戏太深', '2020-03-09 21:54:21', 1);

-- ----------------------------
-- Table structure for comments_root
-- ----------------------------
DROP TABLE IF EXISTS `comments_root`;
CREATE TABLE `comments_root`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论主键id',
  `type` tinyint(1) NOT NULL COMMENT '评论类型：对人评论，对项目评论，对资源评论',
  `owner_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '被评论者id，可以是人、项目、资源',
  `from_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论者id',
  `from_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论者名字',
  `from_avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '评论者头像',
  `like_num` int(11) NULL DEFAULT 0 COMMENT '点赞的数量',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `comment_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论唯一标识',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `owner_id`(`owner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments_root
-- ----------------------------
INSERT INTO `comments_root` VALUES (1, 1, 'talents100020', '12345aaa', '小明', 'icon.jpg', 1, '测试，第一条评论', '2020-03-09 21:39:30', '5e16fd492dd2426588d4cdfb6c412dbf');
INSERT INTO `comments_root` VALUES (2, 1, 'talents100020', '12345aaa', '小花', 'icon.jpg', 0, 'bug', '2020-03-09 21:49:34', '4b1f80695fb44df0ab46a7b17b6dac95');

-- ----------------------------
-- Table structure for liked
-- ----------------------------
DROP TABLE IF EXISTS `liked`;
CREATE TABLE `liked`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `obj_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应对象的id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '点赞用户的id',
  `like_status` int(1) NOT NULL DEFAULT 1 COMMENT '点赞的状态，点赞1，取消0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liked
-- ----------------------------
INSERT INTO `liked` VALUES (1, '5e16fd492dd2426588d4cdfb6c412dbf', '12345aaa', 1);
INSERT INTO `liked` VALUES (2, 'd2302c300b294798bf691e5e626a45d3', '12345aaa', 1);
INSERT INTO `liked` VALUES (3, '70b7e86e065c4b8cbd10bb040cfd8f06', '12345aaa', 1);
INSERT INTO `liked` VALUES (4, '625e1a6ff2a34f7eb7ce16348223823f', '12345aaa', 1);

SET FOREIGN_KEY_CHECKS = 1;
