/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.0.96-community-nt : Database - sales
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sales` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sales`;

/*Table structure for table `zsgx_customer` */

DROP TABLE IF EXISTS `zsgx_customer`;

CREATE TABLE `zsgx_customer` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) default NULL COMMENT '客户姓名',
  `sex` int(1) default '0' COMMENT '客户性别',
  `phone` varchar(15) default NULL COMMENT '客户电话',
  `address` varchar(100) default NULL COMMENT '客户地址',
  `qq` varchar(15) default NULL COMMENT '客户QQ',
  `total_spend` decimal(8,2) default '0.00' COMMENT '消费金额',
  `remain` varchar(500) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息';

/*Data for the table `zsgx_customer` */

/*Table structure for table `zsgx_employee` */

DROP TABLE IF EXISTS `zsgx_employee`;

CREATE TABLE `zsgx_employee` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(100) default NULL COMMENT '账号',
  `password` varchar(100) default NULL COMMENT '密码 MD5 加密',
  `state` tinyint(4) default '1' COMMENT '状态 0:删除;1:正常;2:冻结;',
  `sort` int(11) default NULL COMMENT '序号',
  `creater_id` varchar(50) default NULL COMMENT '创建人ID',
  `create_time` datetime default NULL COMMENT '创建时间',
  `modifyer_id` varchar(50) default NULL COMMENT '修改人ID',
  `modify_time` datetime default NULL COMMENT '修改时间',
  `role_id` varchar(50) default NULL COMMENT '用户组ID',
  `isadmin` tinyint(4) default '0' COMMENT '是否是系统管理员 0:否;1:是;',
  `org_id` bigint(20) default NULL COMMENT '组织机构id',
  `org_name` varchar(50) default NULL COMMENT '组织机构名称',
  `linkman` varchar(50) default NULL COMMENT '联系人',
  `tel` varchar(20) default NULL COMMENT '办公电话',
  `phone` varchar(20) default NULL COMMENT '手机',
  `email` varchar(50) default NULL COMMENT '邮箱',
  `fax` varchar(20) default NULL COMMENT '传真',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工';

/*Data for the table `zsgx_employee` */

insert  into `zsgx_employee`(`id`,`name`,`password`,`state`,`sort`,`creater_id`,`create_time`,`modifyer_id`,`modify_time`,`role_id`,`isadmin`,`org_id`,`org_name`,`linkman`,`tel`,`phone`,`email`,`fax`) values ('1','admin','21232f297a57a5a743894a0e4a801fc3',1,0,'1','2012-09-26 18:26:07','1','2013-09-13 13:28:34','ff8080813b098be8013b2b8d4a150041',1,1,'北京市人事局','李四','010-111111','13388888888','13388888888@163.com','010-111111');

/*Table structure for table `zsgx_goods` */

DROP TABLE IF EXISTS `zsgx_goods`;

CREATE TABLE `zsgx_goods` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'ID',
  `name` varchar(200) default NULL COMMENT '商品名称',
  `img` varchar(255) default NULL COMMENT '商品图片',
  `stock` int(11) default NULL COMMENT '库存',
  `introduction` varchar(500) default NULL COMMENT '简介',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

/*Data for the table `zsgx_goods` */

/*Table structure for table `zsgx_menu` */

DROP TABLE IF EXISTS `zsgx_menu`;

CREATE TABLE `zsgx_menu` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(100) default NULL COMMENT '名称',
  `pid` varchar(50) default NULL COMMENT '上级菜单ID -1代表没有上级菜单',
  `degree` tinyint(4) default NULL COMMENT '深度 从1开始',
  `url` varchar(255) default NULL COMMENT '点击链接url',
  `sort` int(11) default NULL COMMENT '序号',
  `author` varchar(100) default NULL COMMENT '菜单所属Action代号,默认是action name',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

/*Data for the table `zsgx_menu` */

insert  into `zsgx_menu`(`id`,`name`,`pid`,`degree`,`url`,`sort`,`author`) values ('01','商品管理','-1',1,NULL,1,NULL),('0101','商品列表','01',2,'zsgx/goods!index.action',2,'goods'),('0102','进货记录','01',2,'zsgx/purchaseRecord!index.action',3,'purchaseRecord'),('0103','销售记录','01',2,'zsgx/salesRecord!index.action',4,'salesRecord'),('02','客户管理','-1',1,NULL,5,NULL),('0201','客户信息','02',2,'zsgx/customer!index.action',6,'customer'),('03','经营统计','-1',1,'zsgx/statistics!index.action',7,NULL),('0301','进货统计','03',2,'zsgx/purchaseRecord!toStatistics.action',8,'purchaseRecord'),('0302','销售统计','03',2,'zsgx/statistics!index.action',9,'statistics');

/*Table structure for table `zsgx_purchase_record` */

DROP TABLE IF EXISTS `zsgx_purchase_record`;

CREATE TABLE `zsgx_purchase_record` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `goods_id` bigint(20) default NULL COMMENT '商品',
  `goods_num` int(11) default NULL COMMENT '进货数量',
  `purchase_price` decimal(10,2) default NULL COMMENT '进货价',
  `total_money` decimal(10,2) default NULL COMMENT '总支出',
  `purchase_date` bigint(20) default NULL COMMENT '进货时间',
  `purchase_date_str` varchar(20) default NULL COMMENT '进货时间',
  `purchase_year` varchar(5) default NULL COMMENT '进货年份',
  `purchase_month` varchar(3) default NULL COMMENT '进货月份',
  `purchase_day` varchar(3) default NULL COMMENT '进货日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进货表';

/*Data for the table `zsgx_purchase_record` */

/*Table structure for table `zsgx_role` */

DROP TABLE IF EXISTS `zsgx_role`;

CREATE TABLE `zsgx_role` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(255) default NULL COMMENT '名称',
  `template_id` varchar(50) default NULL COMMENT '权限模版ID',
  `remark` varchar(1000) default NULL COMMENT '备注',
  `state` tinyint(4) default '1' COMMENT '状态 0:删除;1:正常;',
  `sort` int(11) default NULL COMMENT '序号',
  `creater_id` varchar(50) default NULL COMMENT '创建人ID',
  `create_time` datetime default NULL COMMENT '创建时间',
  `modifyer_id` varchar(50) default NULL COMMENT '修改人ID',
  `modify_time` datetime default NULL COMMENT '修改时间',
  `grade_type` tinyint(4) default '3' COMMENT '类型(0系统管理,1专技,2二级,3三级)',
  `project_type` tinyint(4) default NULL COMMENT '项目（0系统管理,1高研班,2基地项目,3急需人才项目,4岗位培训项目）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组';

/*Data for the table `zsgx_role` */

/*Table structure for table `zsgx_role_menu` */

DROP TABLE IF EXISTS `zsgx_role_menu`;

CREATE TABLE `zsgx_role_menu` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `role_id` varchar(50) NOT NULL COMMENT '用户组ID',
  `menu_id` varchar(50) NOT NULL COMMENT '菜单ID',
  `role_bit` int(11) default '0' COMMENT '增1 删2 改3 查4  bit值 0代表没有权限',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组-菜单关系表';

/*Data for the table `zsgx_role_menu` */

/*Table structure for table `zsgx_roletemplate` */

DROP TABLE IF EXISTS `zsgx_roletemplate`;

CREATE TABLE `zsgx_roletemplate` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(255) default NULL COMMENT '权限模版名称',
  `state` tinyint(4) default '1' COMMENT '状态 0:删除;1:正常;',
  `sort` int(11) default NULL COMMENT '序号',
  `creater_id` varchar(50) default NULL COMMENT '创建人ID',
  `create_time` datetime default NULL COMMENT '创建时间',
  `modifyer_id` varchar(50) default NULL COMMENT '修改人ID',
  `modify_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限模版';

/*Data for the table `zsgx_roletemplate` */

/*Table structure for table `zsgx_roletemplate_menu` */

DROP TABLE IF EXISTS `zsgx_roletemplate_menu`;

CREATE TABLE `zsgx_roletemplate_menu` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `template_id` varchar(50) NOT NULL COMMENT '权限模版ID',
  `menu_id` varchar(50) NOT NULL COMMENT '菜单ID',
  `role_bit` int(11) default '0' COMMENT '增1 删2 改3 查4  bit值 0代表没有权限',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模版-菜单关系表';

/*Data for the table `zsgx_roletemplate_menu` */

/*Table structure for table `zsgx_sales_record` */

DROP TABLE IF EXISTS `zsgx_sales_record`;

CREATE TABLE `zsgx_sales_record` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `goods_id` bigint(20) default NULL COMMENT '商品',
  `sales_number` int(11) default NULL COMMENT '销售数量',
  `sales_price` decimal(10,2) default NULL COMMENT '销售价格',
  `purchase_price` decimal(10,2) default NULL COMMENT '商品进价',
  `sales_date` bigint(20) default NULL COMMENT '销售时间',
  `sales_year` varchar(5) default NULL COMMENT '销售年份',
  `sales_month` varchar(3) default NULL COMMENT '销售月份',
  `sales_day` varchar(3) default NULL COMMENT '销售日期',
  `income` decimal(10,2) default NULL COMMENT '收入',
  `profit` decimal(10,2) default NULL COMMENT '利润',
  `remain` varchar(500) default NULL COMMENT '备注',
  `customer_id` bigint(20) default NULL COMMENT '客户',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售记录';

/*Data for the table `zsgx_sales_record` */

/*Table structure for table `zsgx_statistics` */

DROP TABLE IF EXISTS `zsgx_statistics`;

CREATE TABLE `zsgx_statistics` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `s_year` varchar(5) default NULL COMMENT '年份',
  `s_month` varchar(2) default NULL COMMENT '月份',
  `s_day` varchar(2) default NULL COMMENT '日',
  `s_in` decimal(8,2) default NULL COMMENT '总收入',
  `s_profit` decimal(8,2) default NULL COMMENT '总利润',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经营统计';

/*Data for the table `zsgx_statistics` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
