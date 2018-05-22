/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : db_crm

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-23 01:21:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `khno` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `postCode` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', 'KH21321321', '北京大牛科技', '北京海淀区双榆树东里15号', '100027', '010-62263393', '0');
INSERT INTO `t_customer` VALUES ('16', 'KH20150526073022', '风驰科技', '321', '21', '321', '1');
INSERT INTO `t_customer` VALUES ('17', 'KH20150526073023', '巨人科技', null, null, null, '1');
INSERT INTO `t_customer` VALUES ('18', 'KH20150526073024', '新人科技', null, null, null, null);
INSERT INTO `t_customer` VALUES ('19', 'KH20150526073025', '好人集团', null, null, null, null);
INSERT INTO `t_customer` VALUES ('20', 'KH20150526073026', '新浪', null, null, null, null);
INSERT INTO `t_customer` VALUES ('21', 'KH20150526073027', '百度', null, null, null, null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `trueName` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', 'Jack', 'java1234@qq.com', '123456789', '系统管理员');
INSERT INTO `t_user` VALUES ('2', 'json1234', '123', 'Json', 'json@qq.com', '232132121', '销售主管');
INSERT INTO `t_user` VALUES ('3', 'xiaoming', '123', '小明', 'khjl01@qq.com', '2321321', '客户经理');
INSERT INTO `t_user` VALUES ('4', 'xiaohong', '123', '小红', 'khjl02@qq.com', '21321', '客户经理');
INSERT INTO `t_user` VALUES ('5', 'xiaozhang', '123', '小张', 'khjl03@qq.com', '3242323', '客户经理');
INSERT INTO `t_user` VALUES ('6', 'daqian', '123', '曹大千', 'gaoguan@qq.com', '5434232', '高管');
INSERT INTO `t_user` VALUES ('7', '21', '321', '321321', '321@qq.com', '321', '系统管理员');
INSERT INTO `t_user` VALUES ('9', '21', '32132', '321', '321@qq.com', '321', '销售主管');
