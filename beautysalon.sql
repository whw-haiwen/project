/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : beautysalon

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-03-29 17:00:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(32) NOT NULL,
  `menu_url` varchar(255) NOT NULL COMMENT '菜单路径',
  `parent_menu_id` varchar(32) NOT NULL COMMENT '父级菜单id',
  `state` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0 未启动 1 启用中）',
  `create_uid` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_uid` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for message_notification
-- ----------------------------
DROP TABLE IF EXISTS `message_notification`;
CREATE TABLE `message_notification` (
  `id` varchar(32) NOT NULL,
  `content` varchar(255) NOT NULL COMMENT '通知内容',
  `state` char(1) NOT NULL DEFAULT '0' COMMENT '通知状态（0 通知消息发送中 1 已通知  2 用户已阅读(浏览器通知所用） ）',
  `notification_type` char(1) NOT NULL DEFAULT '0' COMMENT '通知类型（0 浏览器访问通知  1 短信通知）',
  `user_id` varchar(255) NOT NULL COMMENT '通知用户',
  `create_uid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息通知';

-- ----------------------------
-- Records of message_notification
-- ----------------------------

-- ----------------------------
-- Table structure for oder_details
-- ----------------------------
DROP TABLE IF EXISTS `oder_details`;
CREATE TABLE `oder_details` (
  `id` varchar(32) NOT NULL,
  `orderid` varchar(32) NOT NULL COMMENT '订单id',
  `project_id` varchar(32) NOT NULL COMMENT '消费服务项id',
  `unit_price` double NOT NULL DEFAULT '0' COMMENT '最终单价',
  `describe` varchar(255) DEFAULT NULL COMMENT '订单描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情';

-- ----------------------------
-- Records of oder_details
-- ----------------------------

-- ----------------------------
-- Table structure for orderaudit
-- ----------------------------
DROP TABLE IF EXISTS `orderaudit`;
CREATE TABLE `orderaudit` (
  `id` varchar(32) NOT NULL,
  `orders_id` varchar(32) NOT NULL COMMENT '订单id',
  `audit_status` char(1) NOT NULL DEFAULT '0' COMMENT '审核状态（0 待审核中 1 审核通过 2 审核不通过（即重新填报） ）',
  `describe` varchar(255) DEFAULT NULL COMMENT '审核状态描述',
  `update_uid` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单审核';

-- ----------------------------
-- Records of orderaudit
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(32) NOT NULL COMMENT '唯一标识',
  `shops_id` varchar(32) NOT NULL COMMENT '店铺id',
  `total_sum` double(255,2) NOT NULL COMMENT '消费总金额',
  `create_uid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `id` varchar(32) NOT NULL,
  `project_name` varchar(255) NOT NULL COMMENT '服务项名称',
  `unit_price` double NOT NULL DEFAULT '0' COMMENT '单价',
  `describe` varchar(255) DEFAULT NULL COMMENT '消费项目项描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务项表';

-- ----------------------------
-- Records of projects
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `role_state` char(255) NOT NULL DEFAULT '0' COMMENT '角色状态（0 未启用 1 启用中）',
  `create_uid` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_uid` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for roleandmenu
-- ----------------------------
DROP TABLE IF EXISTS `roleandmenu`;
CREATE TABLE `roleandmenu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  `create_uid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_uid` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单关联关系表';

-- ----------------------------
-- Records of roleandmenu
-- ----------------------------

-- ----------------------------
-- Table structure for roleandusers
-- ----------------------------
DROP TABLE IF EXISTS `roleandusers`;
CREATE TABLE `roleandusers` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `create_uid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_uid` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与用户关联关系表';

-- ----------------------------
-- Records of roleandusers
-- ----------------------------

-- ----------------------------
-- Table structure for settlement_ratio
-- ----------------------------
DROP TABLE IF EXISTS `settlement_ratio`;
CREATE TABLE `settlement_ratio` (
  `id` varchar(32) NOT NULL,
  `job_status` char(1) NOT NULL COMMENT '职位状态（1 全职 2 兼职）',
  `proportion` double(255,2) NOT NULL COMMENT '结算比列（百分比）',
  `type` char(255) NOT NULL COMMENT '类别（1 技术老师  2 个人业绩 3 市场团队）',
  `state` char(1) NOT NULL DEFAULT '0' COMMENT '是否启动（0 未启动 1 启动中）',
  `create_uid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_uid` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资结算比列表';

-- ----------------------------
-- Records of settlement_ratio
-- ----------------------------

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `id` varchar(32) NOT NULL COMMENT '店铺id',
  `shop_namse` varchar(255) NOT NULL COMMENT '店铺名称',
  `shop_telephone` varchar(11) DEFAULT NULL COMMENT '店铺联系电话',
  `personIn_charge_name` varchar(255) DEFAULT NULL COMMENT '店铺负责人名称',
  `shop_address` varchar(255) DEFAULT NULL COMMENT '店铺地址',
  `create_uid` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_uid` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表';

-- ----------------------------
-- Records of shops
-- ----------------------------

-- ----------------------------
-- Table structure for teams
-- ----------------------------
DROP TABLE IF EXISTS `teams`;
CREATE TABLE `teams` (
  `id` varchar(32) NOT NULL,
  `team_name` varchar(255) NOT NULL COMMENT '团队负责人',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级团队id',
  `describe` varchar(255) DEFAULT NULL COMMENT '团队描述',
  `create_uid` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_uid` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团队表';

-- ----------------------------
-- Records of teams
-- ----------------------------

-- ----------------------------
-- Table structure for team_members
-- ----------------------------
DROP TABLE IF EXISTS `team_members`;
CREATE TABLE `team_members` (
  `id` varchar(32) NOT NULL,
  `team_id` varchar(32) NOT NULL COMMENT '团队id',
  `user_id` varchar(32) NOT NULL COMMENT '成员id',
  `whether_fuzere` char(1) NOT NULL DEFAULT '0' COMMENT '是否负责人（0 否 1 是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团队成员表';

-- ----------------------------
-- Records of team_members
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` varchar(32) NOT NULL COMMENT '用户唯一标识',
  `u_name` varchar(10) NOT NULL COMMENT '用户名',
  `account_number` varchar(32) NOT NULL COMMENT '登录账号',
  `password` varchar(255) NOT NULL DEFAULT 'e10adc3949ba59abbe56e057f20f883e' COMMENT '密码（默认123456）',
  `birthday` date NOT NULL COMMENT '生日（出生年月日  YYYY-mm-dd）',
  `telephone` varchar(11) NOT NULL COMMENT '联系电话',
  `position` char(1) NOT NULL COMMENT '1 技术老师 2 业务人员',
  `occupation_state` char(1) NOT NULL DEFAULT '1' COMMENT '在职状态（0 离职 1 全职 2 兼职）',
  `user_address` varchar(255) DEFAULT NULL COMMENT '用户住址',
  `create_uid` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_uid` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('fgfdhghk04klf', '管理员', '00000', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-29', '18514588980', '1', '1', '重庆市花卉园', '00000', '2020-03-29 16:22:46', null, null);
INSERT INTO `users` VALUES ('jdp04uksjrd', '用户1', '00001', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-11', '18514588980', '1', '1', '重庆市花卉园', '00000', '2020-03-29 16:28:17', null, null);

-- ----------------------------
-- Table structure for usersandshops
-- ----------------------------
DROP TABLE IF EXISTS `usersandshops`;
CREATE TABLE `usersandshops` (
  `id` varchar(32) NOT NULL COMMENT '唯一id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `stores_id` varchar(32) NOT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和店铺关联关系表';

-- ----------------------------
-- Records of usersandshops
-- ----------------------------
