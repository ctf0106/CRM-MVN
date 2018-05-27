/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : db_crm

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-28 01:16:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('2', '京东科技', '测试地址');
INSERT INTO `t_company` VALUES ('3', '测试公司', '北京市');

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
  `fund` float(11,2) DEFAULT NULL,
  `financing` float(11,2) DEFAULT NULL,
  `companyID` int(11) DEFAULT NULL,
  `barcode` text,
  `qrcode` text,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('85', 'KH20180527012513', '测试测试', '北京市', '100000', '13699987774', '1425.00', '1365.00', '2', 'iVBORw0KGgoAAAANSUhEUgAAAV4AAABGAQAAAACsosH1AAAAS0lEQVR42u3KMQrAIBBFQcFW+FdZ\r\nSLvg1QO2gb2KYCusSZFbvG6KKZm5r1i6x+pmzadNj1CV+6sRTXp2r/a9QiaTyWQymUwmk8l/Pi5T\r\nVUvUuq13AAAAAElFTkSuQmCC', 'iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABFElEQVR42u3aOxKDIBCAYag8hkfV\r\no3KElFZueC2ikzEp4jrJ/DQqflQ7sCzq5JP2cDAYDAaD/RRbXG2jrOniZa4dHmbHypPMiU1hyHfd\r\nC5gRm3OIMisDUgBH2C0sT54BdjPbDYAZM124pNo36xvsAtaS+LJF7CzXwy5gXVu3l2f7Xtj3WQzW\r\nVMSQknieKKUPZshif4hoCsd542FmLLUQ4+TaVkpybZHvYFZMl6vgNH8HtTBDVgrrOkfSYx4Fs2St\r\n6Zql2ysPM2Pdhjalbs3ftbaAGbFW3LU4jVvYYGZMD/2kmy2w+5ieNL0sK2BWrJ1xvKg+YJcyaR8f\r\naokt68nRK+wKtj/0O3yXg9kwfoeAwWAw2N+wJz1nbrxJICxvAAAAAElFTkSuQmCC', '2018-05-27 13:25:13', '2018-05-27 13:25:13');
INSERT INTO `t_customer` VALUES ('86', 'KH20180527013752', '的点点滴滴多多', '北京市', '234344', '12366554789', '145555.00', '145555.00', '2', 'iVBORw0KGgoAAAANSUhEUgAAAV4AAABGAQAAAACsosH1AAAASUlEQVR42u3KsQnAQAhA0QNbwVWE\r\ntMKtHrgBXEWwDRhSZIv/6rdm5rmy7T693TXKKzJNLMK78kiL6hb/3iKTyWQymUwmk8nkP78BeUnP\r\nrAqtygAAAABJRU5ErkJggg==', 'iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABFUlEQVR42u3YQRKDIAxA0bDyGB4V\r\njuoRunQlLQGEaW11Idh2PosO0scqEwIRf2TcBAaDwWCwn2KzpDHOJnwa79KCgfVj8cu78bHBTkOY\r\n1X/AOjEXAqNMymyEXcPKD+wy5pdDwYK1YfngWoP1+XyDNWB1EY9st9bDzmZc3b/kvWA1YiVRpmHZ\r\niimsIUs5YiepN7jNKgNrxLR0D+u6BkvSAwPWjfmYIzbONFG0pMA6Mn1Yy1q644blJViwpqyMUkhK\r\nNYH1YeVCG4pGOsIkZRCsF6sfd14TJZZzWF/21G5KawPsChYuVWM+vewkBnYF83Hms4D1ZHXTT29R\r\nYvZ6g7CTWWn6abbkXe97g7DzGe1NGAwGg/0NuwPI6Zjs0Lw75AAAAABJRU5ErkJggg==', '2018-05-27 13:37:52', '2018-05-27 13:37:52');
INSERT INTO `t_customer` VALUES ('87', 'KH20180527014535', '111111333', '111111111', '333', '333', '333.00', '3333.00', '2', 'iVBORw0KGgoAAAANSUhEUgAAAV4AAABGAQAAAACsosH1AAAASklEQVR42u3KsQkAMQhA0YDtgasI\r\naYWsHnAAVwnYHhiuuC3+q9/o7ndm6Y5aZo8fO56pou5Wopkh4Uvse4NMJpPJZDKZTCaT/3wB9vRB\r\nm/NrDV0AAAAASUVORK5CYII=', 'iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABD0lEQVR42u3ZOxKDIBCAYag8hkeF\r\no3qElFYSl4fgmDEpZE0yP4Uj+lntyO6CCZ+Mh4HBYDAY7KfYbPIYZytTG3x+YGF6LM2CH9cP3DTI\r\nXfsCpsS8BCYyU+9G2D2sXmC3sbB8FCxYH1YWri1Y5+sbrANrk3hib3M97GpG6f4l/YKLEas/yjQs\r\nr2IK68pSqpBaNmwf+HGGKTIRJXW7PM0NBkyVyaNo048SUwpMk235W2ayZrmwlraHYMF6sjqavm7L\r\nJjAdVgvaEqK8hME0WdvclSoKps922005k/tjloFpMNluqnYyFnYHCzlERcA0WT0MSmtWYidnRrDL\r\n2e5gdDKlxT49P4VdzNjehMFgMNjfsCdvQmpm0tUd+AAAAABJRU5ErkJggg==', '2018-05-27 13:45:35', '2018-05-27 13:45:35');
INSERT INTO `t_customer` VALUES ('76', 'KH20180526072434', '11113', '111', '444', '3424234', '123.00', '333.00', '2', 'iVBORw0KGgoAAAANSUhEUgAAAV4AAABGAQAAAACsosH1AAAAS0lEQVR42u3KsQkAIQxAUcFWyCqB\r\ntIKrH9geZJWAbSByxW3xX/1aVaX5kWefpTpmaEx36T4iw7a/SyxX1+81MplMJpPJZDKZTP7zBQPM\r\n+v9ihmg4AAAAAElFTkSuQmCC', 'iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABGElEQVR42u3aQRKDIAxAUVhxDI+q\r\nR/UIXbqClgQQO9V2UeO089lY8bnKQBKsS5+Mm4PBYDAY7KfY4soYUswXn6Yy4WF2TO/SlNk4B/nV\r\nPYAZsUlCJExfyAEcYJcwWTwBdjHbvAAzZnXjSsW+2d9gJ7CWxJc1Yke5HnYC60ZcHx7VvbDvs0ew\r\nRhUhJ3FZKDoHs2NaQIl4WjceZsZaAZWv0lYk6S3yHMyQ6fKYa2/hJInPMEMmjbUcb4Sopx1yC7Nl\r\nbUjYavrYL6VgJ7BNQbuuoNJbwIxYa+40WH0mhxmyeuiXutUCu4rFFqcX3QfMjkkmL1+EPMyS1Y0r\r\nF1Cudtz7+xvsBLY59HO1lj3M9bBvM/4OAYPBYLC/YXfFT0fUPVytjgAAAABJRU5ErkJggg==', '2018-05-26 19:24:34', '2018-05-26 19:24:34');
INSERT INTO `t_customer` VALUES ('88', 'KH20180527114354', '客户测试', '客户地址', '100000', '13699985556', '123.00', '145.00', '2', 'iVBORw0KGgoAAAANSUhEUgAAAV4AAABGAQAAAACsosH1AAAAS0lEQVR42u3KsQkAIQxAUcH2IKsE\r\nbANZXbAVsopgexDvCrf47eOVzHxbbOlju+pjS5dFSBVz+7RP9zG96v8KmUwmk8lkMplMJt98ABSA\r\niCelMzjcAAAAAElFTkSuQmCC', 'iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABEklEQVR42u3aSxKCMAyA4XbVY3BU\r\nOCpHcMmq1T6SVkfRhYTR+bsByscqQ5MGXPpkXBwMBoPBYD/FNtfGlGI++LS0CQ+zY/UqLZnNayhn\r\nww2YEVtKiAqrD+QATrBTWHl5AuxkdvcAzJjJwpWafbO+wQ5gmsS3HrG9XA87gA0j9pt7dS/s++wW\r\nrLmKkJN4eVHqHMyO1RC5QThNJDArVgso3dcF2VvkogpmxZJMrbK3cPUSZsli7nH06EgOgZkyHVJK\r\nSXnlYWZsKGi1qk3ac4IZMd3c9Sqqhw1mxqTp1zpN8+pgZzIpaJ/sPmBmbBlKW+dhlmxcuOq3iLjT\r\neoUdwR6afvot4mWuh32f8TsEDAaDwf6GXQGdI0SqXJqK4gAAAABJRU5ErkJggg==', '2018-05-27 23:43:54', '2018-05-27 23:43:54');
INSERT INTO `t_customer` VALUES ('90', 'KH20180527115518', '客户测试测试', '北京市', '120', '13699985556', '120.00', '120.00', '2', 'iVBORw0KGgoAAAANSUhEUgAAAV4AAABGAQAAAACsosH1AAAASklEQVR42u3KsQkAMQhA0YDtgasI\r\naYWsHnAAVxFsDxKuuC3+q98457wzW3f0Mnu8rDxTRX15zYrdEr7EvjfIZDKZTCaTyWQy+c8XEbmV\r\nR7aeINYAAAAASUVORK5CYII=', 'iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABFUlEQVR42u3aPRKDIBCG4bXiGB41\r\nHtUjpLSC8LOAxjGxASaZl8KR5Em147crE3F31lNgMBgMBvsptomu2W+WeZvcoh9MsH4s7bzI23oH\r\n68iWUJhUJ61YqB1sCKvFgg1kVh6rwAaxHFzWf2vc13yDNWCliW/TrV4Pa8Dqqv3749wLa8BSSGl1\r\nNLPqZAXrxMIzYuKtBpdbjW8kDtaRlc6RmrixGmHn4II1Zak6cYoqQ5XM53yDNWP5kdldQnpdPVmw\r\nJuzQxGvZ3gdaWFN2GGjjNr/hwTqy8nIXf2BsuvhG4mA9WT300/n2aqCFtWe5nacXDDGwEYyj/qFs\r\nH1wimlmrwDqyYxN3dnf8B+vF+DsEDAaDwf6GvQCYJ5UOCmyR0gAAAABJRU5ErkJggg==', '2018-05-27 23:55:18', '2018-05-27 23:55:18');
INSERT INTO `t_customer` VALUES ('91', 'KH20180528121554', '111dddd', 'dddd', '234344', '12366554789', '123.00', '123.00', '2', 'iVBORw0KGgoAAAANSUhEUgAAAV4AAABGAQAAAACsosH1AAAAS0lEQVR42u3KsQkAIQxAUcFWcJXA\r\ntYGsLqQ9yCpCWiFyxW3xX/1aVZ0nci5PExm6ZWvE7K6+svt6Tc6wLt9rZDKZTCaTyWQymfznCyul\r\n1hVu/Yu2AAAAAElFTkSuQmCC', 'iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABF0lEQVR42u3aOxKCMBCA4VBxDI6a\r\nHJUjWFKxumQhMGqwMMvo/CkYHl+qHdiHBvlk3QIMBoPBYD/FpmBrkDlEmTpJdqOD+bF8JWnQs7HX\r\ns/0DmBNLGpiUg5U3aOxgl7ASLNh1TONkG2D+TI4vytn3DdaAbUl8svfmJNfDGrDDSuvDet0L+zab\r\nLDBLiKJexrGf9QDzY48CKncU+X5mGjaYI1u+VLmZmHdxgrmy0tetvUXOHCPMnS2DDgvR42AjD5gj\r\ns7U1E2O1lII1YM8FrQXw3egV1oId7y+7epg/K+OmfSKpTKVgTZlsP0NEqQQL1pBZJhetasMA82Wl\r\nnoqy9hbV0Svs66wkcRt0vE4fsJaMv0PAYDAY7G/YHZmymBpP69kKAAAAAElFTkSuQmCC', '2018-05-28 00:15:54', '2018-05-28 00:15:54');

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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '曹先生', 'caotengfei@cctv.com', '123456789');
INSERT INTO `t_user` VALUES ('10', 'adminadmin', 'admin', '曹先生', 'admin@admin.com', '1111112');
INSERT INTO `t_user` VALUES ('11', '66666', '66666', '66666', 'admin@admin.com', '12366554789');
INSERT INTO `t_user` VALUES ('12', '66666444', '66666444', '66666444', '66666444@qq.com', '1369998885');
