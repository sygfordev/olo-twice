/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 47.75.147.194:3306
 Source Schema         : database_101

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/05/2021 10:11:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for ch_pm_contract_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_contract_info`;
CREATE TABLE `ch_pm_contract_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `CONT_CLASS` int(0) DEFAULT NULL COMMENT '合同类别  固定期限|无固定期限',
  `CONT_CLASS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合同类别-名称',
  `CONT_CYCLE` int(0) DEFAULT NULL COMMENT '合同期限（合同周期）  无固定期限|1年|2年等',
  `CONT_CYCLE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合同期限-名称',
  `CONT_NO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合同编号',
  `CONT_START_TIME` datetime DEFAULT NULL COMMENT '合同开始时间',
  `CONT_END_TIME` datetime DEFAULT NULL COMMENT '合同结束时间',
  `CONT_EXPIRE_WARN_TIME` datetime DEFAULT NULL COMMENT '合同到期预警时间',
  `CONT_ORDER` int(0) DEFAULT NULL COMMENT '合同顺序',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_contract_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_dossier_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_dossier_info`;
CREATE TABLE `ch_pm_dossier_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `DOSSIER_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '人事档案编号',
  `DOSSIER_STORAGE` int(0) DEFAULT NULL COMMENT '人事档案存放地',
  `DOSSIER_STORAGE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '人事档案存放地-名称',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_dossier_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_edu_head
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_edu_head`;
CREATE TABLE `ch_pm_edu_head`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `EDU_DEG_4NOW` int(0) DEFAULT NULL COMMENT '受教学位',
  `EDU_DEG_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '受教学位-名称',
  `EDU_LEV_4NOW` int(0) DEFAULT NULL COMMENT '学历（高中|大专|本科|硕士|博士等）',
  `EDU_LEV_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历名称',
  `EDU_LEV_4SAL` int(0) DEFAULT NULL COMMENT '工资学历',
  `EDU_LEV_4SAL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工资学历-名称',
  `STATUS` int(0) DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_edu_head
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_edu_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_edu_info`;
CREATE TABLE `ch_pm_edu_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `EDU_SCH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '受教院校',
  `EDU_MAJ` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '受教专业',
  `EDU_DEG` int(0) DEFAULT NULL COMMENT '受教学位',
  `EDU_DEG_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '受教学位-名称',
  `EDU_TYPE` int(0) DEFAULT NULL COMMENT '教育类型（全日制|函授|自考|电大|网络教育）',
  `EDU_TYPE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教育类型-名称',
  `EDU_LEV` int(0) DEFAULT NULL COMMENT '学历（高中|大专|本科|硕士|博士等）',
  `EDU_LEV_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历-名称',
  `EDU_ORDER` int(0) DEFAULT NULL COMMENT '学历顺序',
  `EDU_START_TIME` datetime DEFAULT NULL COMMENT '受教开始时间',
  `EDU_END_TIME` datetime DEFAULT NULL COMMENT '受教结束时间（毕业）',
  `EDU_MAX` int(0) DEFAULT NULL COMMENT '是否最高学历  0：否  1：是',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_edu_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_family_member_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_family_member_info`;
CREATE TABLE `ch_pm_family_member_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `MEM_RELATION` int(0) DEFAULT NULL COMMENT '家庭成员关系',
  `MEM_RELATION_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭成员关系-名称',
  `MEM_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭成员姓名',
  `MEM_WK_COM` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭成员工作单位',
  `MEM_TELNO` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭成员联系电话',
  `MEM_ADDR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现详细住址',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_family_member_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_file_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_file_info`;
CREATE TABLE `ch_pm_file_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `MODULE_CODE` int(0) NOT NULL COMMENT '模块编码',
  `MODULE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块名称',
  `FILE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `FILE_CONTENT` mediumblob NOT NULL COMMENT '文件内容',
  `FILE_TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型（文件后缀）',
  `FILE_SIZE` bigint(0) NOT NULL COMMENT '文件大小',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE,
  INDEX `idx_WKID_STATUS_MODNO`(`WORKER_ID`, `STATUS`, `MODULE_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_hos_branch
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_hos_branch`;
CREATE TABLE `ch_pm_hos_branch`  (
  `hbh_no` int(0) NOT NULL,
  `hbh_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sorts` int(0) DEFAULT 0,
  `status` int(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`hbh_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_hos_branch
-- ----------------------------
INSERT INTO `ch_pm_hos_branch` VALUES (1, '机关一支部', 1, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (2, '机关二支部', 2, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (3, '后勤支部', 3, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (4, '医技支部', 4, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (5, '药材支部', 5, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (6, '外科一支部', 6, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (7, '外科二支部', 7, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (8, '内科一支部', 8, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (9, '内科二支部', 9, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (10, '门诊综合大楼一支部', 10, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (11, '门诊综合大楼二支部', 11, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (12, '一分院支部', 12, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (13, '三分院支部', 13, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (14, '鑫珠春分院支部', 14, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (15, '李封分院支部', 15, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (16, '新华分院支部', 16, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (17, '凯马分院支部', 17, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (18, '九里山分院支部', 18, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (19, '演马分院支部', 19, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (20, '赵固一矿、二矿支部卫生所', 20, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (21, '转科', 21, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (22, '慈佑颐康院', 22, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (23, '调研员', 23, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (24, '协理员', 24, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (25, '内退人员', 25, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (26, '人力资源市场', 26, 0);
INSERT INTO `ch_pm_hos_branch` VALUES (27, '待调配部门', 27, 0);

-- ----------------------------
-- Table structure for ch_pm_hos_depart
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_hos_depart`;
CREATE TABLE `ch_pm_hos_depart`  (
  `hdp_no` int(0) NOT NULL,
  `hdp_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hbh_no` int(0) NOT NULL,
  `sorts` int(0) DEFAULT 0,
  `status` int(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`hdp_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_hos_depart
-- ----------------------------
INSERT INTO `ch_pm_hos_depart` VALUES (1, '院领导', 1, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (2, '综合办公室', 1, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (3, '党委工作部', 1, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (4, '纪委监察科', 1, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (5, '工会', 1, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (6, '人力资源部', 1, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (7, '物价审计科', 1, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (8, '武装保卫部', 2, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (9, '财务科', 2, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (10, '医务处', 2, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (11, '病案管理科', 2, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (12, '法制办', 2, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (13, '护理部', 2, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (14, '医保办公室', 2, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (15, '感控管理科', 2, 8, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (16, '医疗发展部', 2, 9, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (17, '安全环保部', 2, 10, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (18, '疾病预防控制科', 2, 11, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (19, '科教科', 2, 12, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (20, '监督科', 2, 13, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (21, '后勤保障部', 3, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (22, '住院处', 3, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (23, '信息科', 3, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (24, '劳动服务部', 3, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (25, '检验科', 4, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (26, '影像科', 4, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (27, '特检科', 4, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (28, '核医学科', 4, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (29, '放疗科', 4, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (30, '病理科', 4, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (31, '输血科', 4, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (32, '临床营养科', 4, 8, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (33, '职防所', 4, 9, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (34, '健康管理中心', 4, 10, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (35, '红外线', 4, 11, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (36, '药库', 5, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (37, '西药房', 5, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (38, '中药房', 5, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (39, '门诊综合大楼西药房', 5, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (40, '临床药学科', 5, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (41, '医学装备部', 5, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (42, '器械管理部', 5, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (43, '静配用药调配中心', 5, 8, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (44, '神经外科一区', 6, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (45, '神经外科二区', 6, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (46, '泌尿外科', 6, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (47, '骨科二区', 6, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (48, '骨科三区', 6, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (49, '骨科四区', 6, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (50, '骨科五区', 6, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (51, '外科支部', 6, 8, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (52, '总院门诊', 7, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (53, '消毒供应中心', 7, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (54, '总院手术室', 7, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (55, '重症医学科', 7, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (56, '心胸外科', 7, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (57, '普通外科一区', 7, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (58, '普通外科三区', 7, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (59, '普通外科四区', 7, 8, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (60, '甲状腺乳腺科', 7, 9, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (61, '肺科', 8, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (62, '感染性疾病科', 8, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (63, '高压氧', 8, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (64, '急诊科', 8, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (65, '肿瘤内科一区', 8, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (66, '肿瘤内科二区', 8, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (67, '心血管内科一区', 8, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (68, '心血管内科二区', 8, 8, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (69, '神经内科一区', 8, 9, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (70, '神经内科二区', 8, 10, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (71, '神经重症科', 8, 11, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (72, '神经介入科', 8, 12, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (73, '内分泌代谢科', 9, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (74, '肾病科', 9, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (75, '血液科', 9, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (76, '消化内科', 9, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (77, '呼吸与危重症医学科一区', 9, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (78, '中西医结合科', 9, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (79, '焦煤职工疗养院', 9, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (80, '门诊综合大楼门诊外科', 10, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (81, '门诊综合大楼门诊', 10, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (82, '康复科', 10, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (83, '门诊综合大楼手术室', 10, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (84, '皮肤科', 10, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (85, '口腔科', 10, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (86, '口腔修复科', 10, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (87, '口腔正畸科', 10, 8, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (88, '小儿科', 10, 9, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (89, '妇产科', 10, 10, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (90, '门诊综合大楼办公室', 10, 11, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (91, '门诊大楼门诊', 10, 12, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (92, '门诊中医科', 10, 13, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (93, '耳鼻喉科', 11, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (94, '眼科', 11, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (95, '眼科门诊', 11, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (96, '眼特检', 11, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (97, '普通外科二区', 11, 5, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (98, '骨科一区', 11, 6, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (99, '呼吸与危重症医学科二区', 11, 7, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (100, '一分院', 12, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (101, '三分院', 13, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (102, '冯营分院', 13, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (103, '方庄分院', 13, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (104, '鑫珠春分院', 14, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (105, '李封分院', 15, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (106, '焦西门诊部', 16, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (107, '新华分院', 16, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (108, '新东卫生所', 16, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (109, '宝雨山矿卫生所', 16, 4, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (110, '韩王分院', 17, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (111, '凯马分院', 17, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (112, '中马分院', 17, 3, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (113, '九里山分院', 18, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (114, '古汉山矿卫生所', 18, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (115, '演马分院', 19, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (116, '赵固一矿卫生所', 20, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (117, '赵固二矿卫生所', 20, 2, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (118, '转科', 21, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (119, '慈佑颐康院', 22, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (120, '调研员', 23, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (121, '协理员', 24, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (122, '内退人员', 25, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (123, '人力资源市场', 26, 1, 0);
INSERT INTO `ch_pm_hos_depart` VALUES (124, '待调配部门', 27, 1, 0);

-- ----------------------------
-- Table structure for ch_pm_hos_secdep
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_hos_secdep`;
CREATE TABLE `ch_pm_hos_secdep`  (
  `hsd_no` int(0) NOT NULL,
  `hsd_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hdp_no` int(0) NOT NULL,
  `hbh_no` int(0) NOT NULL,
  `sorts` int(0) DEFAULT 0,
  `status` int(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`hsd_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_hos_secdep
-- ----------------------------
INSERT INTO `ch_pm_hos_secdep` VALUES (1, '院领导', 1, 1, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (2, '综合办公室', 2, 1, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (3, '招标办', 2, 1, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (4, '信访办', 3, 1, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (5, '车队', 3, 1, 4, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (6, '党委工作部', 3, 1, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (7, '团委', 3, 1, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (8, '纪委监察科', 4, 1, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (9, '工会', 5, 1, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (10, '老干部科', 5, 1, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (11, '人力资源部', 6, 1, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (12, '核算办', 6, 1, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (13, '物价审计科', 7, 1, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (14, '武装保卫部', 8, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (15, '财务科', 9, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (16, '医务处', 10, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (17, '保健科', 10, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (18, '健康教育科', 10, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (19, '病案管理科', 11, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (20, '法制办', 12, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (21, '护理部', 13, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (22, '质控科', 13, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (23, '医保办公室', 14, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (24, '感控管理科', 15, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (25, '医疗发展部', 16, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (26, '安全环保部', 17, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (27, '疾病预防控制科', 17, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (28, '科教科', 19, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (29, '监督科', 20, 2, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (30, '后勤保障部', 21, 3, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (31, '基建科', 21, 3, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (32, '膳食科', 21, 3, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (33, '图书馆', 21, 3, 4, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (34, '宿舍', 21, 3, 5, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (35, '电工组', 21, 3, 6, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (36, '门诊综合大楼电工组', 21, 3, 7, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (37, '机修组', 21, 3, 8, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (38, '门诊综合大楼机修组', 21, 3, 9, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (39, '空调班', 21, 3, 10, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (40, '住院处', 22, 3, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (41, '信息科', 23, 3, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (42, '劳动服务部', 24, 3, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (43, '检验科', 25, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (44, 'CT室', 26, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (45, '磁共振室', 26, 4, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (46, '放射科', 26, 4, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (47, '超声科', 27, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (48, 'TCD', 27, 4, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (49, '心电图室', 27, 4, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (50, '核医学科', 28, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (51, '放疗科', 29, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (52, '病理科', 30, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (53, '输血科', 31, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (54, '临床营养科', 32, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (55, '职防所', 33, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (56, '健康管理中心', 34, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (57, '红外线', 35, 4, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (58, '药库', 36, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (59, '西药房', 37, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (60, '中药房', 38, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (61, '门诊综合大楼西药房', 39, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (62, '临床药学科', 40, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (63, '医学装备部', 41, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (64, '器械管理部', 42, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (65, '静配用药调配中心', 43, 5, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (66, '神经外科一区', 44, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (67, '神经外科二区', 45, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (68, '泌尿外科', 46, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (69, '骨科二区', 47, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (70, '骨科三区', 48, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (71, '骨科四区', 49, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (72, '骨科五区', 50, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (73, '外科支部', 51, 6, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (74, '总院导医台', 52, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (75, '总院服务中心', 52, 7, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (76, '总院采血室', 52, 7, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (77, '总院专家门诊', 52, 7, 4, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (78, '消毒供应中心', 53, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (79, '总院手术室', 54, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (80, '重症医学科', 55, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (81, '心胸外科', 56, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (82, '普通外科一区', 57, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (83, '普通外科三区', 58, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (84, '普通外科四区', 59, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (85, '甲状腺乳腺科', 60, 7, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (86, '肺科', 61, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (87, '感染性疾病科', 62, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (88, '高压氧', 63, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (89, '急诊科', 64, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (90, '肿瘤内科一区', 65, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (91, '肿瘤内科二区', 66, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (92, '心血管内科一区', 67, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (93, '心血管内科二区', 68, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (94, '神经内科一区', 69, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (95, '神经内科二区', 70, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (96, '神经重症科', 71, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (97, '神经介入科', 72, 8, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (98, '内分泌代谢科', 73, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (99, '肾病科', 74, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (100, '血液科', 75, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (101, '消化内科', 76, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (102, '内镜中心', 76, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (103, '呼吸与危重症医学科一区', 77, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (104, '中西医结合科', 78, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (105, '焦煤职工疗养院', 79, 9, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (106, '门诊综合大楼门诊外科', 80, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (107, '门诊综合大楼导医台', 81, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (108, '门诊综合大楼服务中心', 81, 10, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (109, '门诊综合大楼采血室', 81, 10, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (110, '门诊综合大楼专家门诊', 81, 10, 4, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (111, '康复科', 82, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (112, '门诊综合大楼手术室', 83, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (113, '皮肤科', 84, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (114, '口腔科', 85, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (115, '口腔科病房', 85, 10, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (116, '口腔科门诊', 85, 10, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (117, '修复科', 86, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (118, '正畸科', 87, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (119, '小儿科', 88, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (120, '妇产科', 89, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (121, '门诊综合大楼办公室', 90, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (122, '门诊大楼门诊', 91, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (123, '门诊中医科', 92, 10, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (124, '耳鼻喉科', 93, 11, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (125, '眼科', 94, 11, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (126, '眼科门诊', 95, 11, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (127, '眼特检', 96, 11, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (128, '普通外科二区', 97, 11, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (129, '骨科一区', 98, 11, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (130, '呼吸与危重症医学科二区', 99, 11, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (131, '一分院机关', 100, 12, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (132, '一分院办公室', 100, 12, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (133, '一分院后勤', 100, 12, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (134, '急诊科', 100, 12, 4, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (135, '检验科', 100, 12, 5, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (136, '影像科', 100, 12, 6, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (137, 'B超室', 100, 12, 7, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (138, '妇产科', 100, 12, 8, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (139, '外科', 100, 12, 9, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (140, '内一科', 100, 12, 10, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (141, '内二科', 100, 12, 11, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (142, '供应室', 100, 12, 12, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (143, '药房', 100, 12, 13, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (144, '口腔科', 100, 12, 14, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (145, '住院处', 100, 12, 15, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (146, '截瘫病房', 100, 12, 16, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (147, '五官科', 100, 12, 17, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (148, '三分院机关', 101, 13, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (149, '三分院办公室', 101, 13, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (150, '三分院后勤', 101, 13, 3, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (151, '一区', 101, 13, 4, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (152, '二区', 101, 13, 5, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (153, '三区', 101, 13, 6, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (154, '药房', 101, 13, 7, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (155, '检验科', 101, 13, 8, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (156, '住院处', 101, 13, 9, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (157, '冯营分院', 102, 13, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (158, '方庄分院', 103, 13, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (159, '鑫珠春分院', 104, 14, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (160, '李封分院', 105, 15, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (161, '焦西门诊部', 106, 16, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (162, '新华分院', 107, 16, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (163, '新东卫生所', 108, 16, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (164, '宝雨山矿卫生所', 109, 16, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (165, '韩王分院', 110, 17, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (166, '凯马分院', 111, 17, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (167, '中马分院', 112, 17, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (168, '九里山分院', 113, 18, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (169, '古汉山矿卫生所', 114, 18, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (170, '演马分院', 115, 19, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (171, '馨园社区', 115, 19, 2, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (172, '赵固一矿卫生所', 116, 20, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (173, '赵固二矿卫生所', 117, 20, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (174, '转科', 118, 21, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (175, '慈佑颐康院', 119, 22, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (176, '调研员', 120, 23, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (177, '协理员', 121, 24, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (178, '内退人员', 122, 25, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (179, '人力资源市场', 123, 26, 1, 0);
INSERT INTO `ch_pm_hos_secdep` VALUES (180, '待调配部门', 124, 27, 1, 0);

-- ----------------------------
-- Table structure for ch_pm_import_record
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_import_record`;
CREATE TABLE `ch_pm_import_record`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `BATCH_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '批次号',
  `BATCH_TYPE` int(0) NOT NULL COMMENT '批次类型',
  `BATCH_TYPE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '批次类型名称',
  `BATCH_USER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `SUC_NUM` bigint(0) DEFAULT NULL COMMENT '成功数量',
  `FAI_NUM` bigint(0) DEFAULT NULL COMMENT '失败数量',
  `BATCH_MSG` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '批次消息',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：校验失败  1：导入成功',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_import_record
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_incdec_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_incdec_info`;
CREATE TABLE `ch_pm_incdec_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `ENTRY_TIME` datetime DEFAULT NULL COMMENT '进入时间',
  `ENTRY_CHAN` int(0) DEFAULT NULL COMMENT '进入渠道',
  `ENTRY_CHAN_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '进入渠道-名称',
  `QUIT_TIME` datetime DEFAULT NULL COMMENT '离职时间',
  `QUIT_REASON` int(0) DEFAULT NULL COMMENT '离职原因 退休|病退|辞职|辞退|工作调动等',
  `QUIT_REASON_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '离职原因-名称',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_incdec_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_other_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_other_info`;
CREATE TABLE `ch_pm_other_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `EPIDS_PREC_FL_HC_STAFF` int(0) DEFAULT NULL COMMENT '是否疫情防控一线医护人员  0:否 1:是',
  `HON_MONEY_GOT_TIME` datetime DEFAULT NULL COMMENT '荣誉金取得时间',
  `CAE_WK_YEARS` int(0) DEFAULT NULL COMMENT '核减工龄年限',
  `IS_DISABILITY` int(0) DEFAULT NULL COMMENT '是否残疾人',
  `DISABILITY_LEV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '残疾鉴定级别',
  `IS_INJURY_ONJOB` int(0) DEFAULT NULL COMMENT '是否工伤',
  `INJURY_LEV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工伤鉴定级别',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注1',
  `REMARK2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注2',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_other_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_posit_head
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_posit_head`;
CREATE TABLE `ch_pm_posit_head`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `POSIT_4NOW` int(0) DEFAULT NULL COMMENT '现任职务',
  `POSIT_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现任职务-名称',
  `POSIT_DEPART_4NOW` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现任职部门',
  `POSIT_LEVEL_4NOW` int(0) DEFAULT NULL COMMENT '现级别（正科|副科等的编号）',
  `POSIT_LEVEL_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现级别-名称',
  `POSIT_4NOW_START_TIME` datetime DEFAULT NULL COMMENT '现任职务开始时间',
  `POSIT_4NOW_END_TIME` datetime DEFAULT NULL COMMENT '现任职务结束时间',
  `TREAT_LEVEL_4NOW` int(0) DEFAULT NULL COMMENT '现待遇级别（正科级|副科级等的编号）',
  `TREAT_LEVEL_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现待遇级别-名称',
  `TREAT_4NOW_START_TIME` datetime DEFAULT NULL COMMENT '现待遇级别开始时间',
  `TREAT_4NOW_END_TIME` datetime DEFAULT NULL COMMENT '现待遇级别结束时间',
  `TREAT_4NOW_YEARS` int(0) DEFAULT NULL COMMENT '现待遇级别年限',
  `ON_CHU_START_TIME` datetime DEFAULT NULL COMMENT '任正处开始时间',
  `ON_CHU_END_TIME` datetime DEFAULT NULL COMMENT '任正处结束时间',
  `ON_CHU_DETUPY_START_TIME` datetime DEFAULT NULL COMMENT '任副处开始时间',
  `ON_CHU_DETUPY_END_TIME` datetime DEFAULT NULL COMMENT '任副处结束时间',
  `ON_KE_START_TIME` datetime DEFAULT NULL COMMENT '任正科开始时间',
  `ON_KE_END_TIME` datetime DEFAULT NULL COMMENT '任正科结束时间',
  `ON_KE_DETUPY_START_TIME` datetime DEFAULT NULL COMMENT '任副科开始时间',
  `ON_KE_DETUPY_END_TIME` datetime DEFAULT NULL COMMENT '任副科结束时间',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_posit_head
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_posit_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_posit_info`;
CREATE TABLE `ch_pm_posit_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `POSIT` int(0) DEFAULT NULL COMMENT '职务',
  `POSIT_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职务-名称',
  `POSIT_DEPART` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任职部门',
  `POSIT_LEVEL` int(0) DEFAULT NULL COMMENT '行政级别-编号（正处级|副处级等）',
  `POSIT_LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '行政级别-名称',
  `POSIT_MAX` int(0) DEFAULT NULL COMMENT '是否最高职务 0：否  1：是',
  `ON_POSIT_ORDER` int(0) DEFAULT NULL COMMENT '任职顺序',
  `ON_POSIT_START_TIME` datetime DEFAULT NULL COMMENT '任职开始时间',
  `ON_POSIT_END_TIME` datetime DEFAULT NULL COMMENT '任职开始时间',
  `ON_POSIT_DOCNO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任职文号',
  `CON_POSIT` int(0) DEFAULT NULL COMMENT '兼任职务',
  `CON_POSIT_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼任职务-名称',
  `CON_POSIT_DEPART` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职部门',
  `CON_POSIT_START_TIME` datetime DEFAULT NULL COMMENT '兼职开始时间',
  `CON_POSIT_END_TIME` datetime DEFAULT NULL COMMENT '兼职结束时间',
  `TREAT_LEVEL` int(0) DEFAULT NULL COMMENT '待遇级别（正科级|副科级等的编号）',
  `TREAT_LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '待遇级别-名称',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_posit_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_skills_head
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_skills_head`;
CREATE TABLE `ch_pm_skills_head`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `SKILLS_4NOW` int(0) DEFAULT NULL COMMENT '现技能等级',
  `SKILLS_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现技能等级-名称',
  `SKILLS_4NOW_GOT_TIME` datetime DEFAULT NULL COMMENT '现技能等级取得时间',
  `SKILLS_CLASS_4NOW` int(0) DEFAULT NULL COMMENT '现技能等级序列（技能分类）',
  `SKILLS_CLASS_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现技能等级序列-名称',
  `SKILLS_LEVEL_4NOW` int(0) DEFAULT NULL COMMENT '现技能等级级别(五级|四级|三级|二级|一级)',
  `SKILLS_LEVEL_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现技能等级级别-名称',
  `TREAT_LEVEL_4NOW` int(0) DEFAULT NULL COMMENT '现技能等级待遇级别(五级|四级|三级|二级|一级)',
  `TREAT_LEVEL_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现技能等级待遇级别-名称',
  `SKILLS_4NOW_H_START_TIME` datetime DEFAULT NULL COMMENT '现技能初聘开始时间',
  `SKILLS_4NOW_H_END_TIME` datetime DEFAULT NULL COMMENT '现技能聘任终止时间',
  `SKILLS_MAX_4NOW` int(0) DEFAULT NULL COMMENT '是否现最高技能等级  0：否  1：是',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_skills_head
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_skills_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_skills_info`;
CREATE TABLE `ch_pm_skills_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `SKILLS` int(0) DEFAULT NULL COMMENT '技能等级',
  `SKILLS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能等级-名称',
  `SKILLS_GOT_TIME` datetime DEFAULT NULL COMMENT '技能等级取得时间',
  `SKILLS_CLASS` int(0) DEFAULT NULL COMMENT '技能等级序列（技能分类）',
  `SKILLS_CLASS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能等级序列-名称',
  `SKILLS_LEVEL` int(0) DEFAULT NULL COMMENT '技能等级级别(五级|四级|三级|二级|一级)',
  `SKILLS_LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能等级级别-名称',
  `SKILLS_MAX` int(0) DEFAULT NULL COMMENT '是否最高职称 0：否  1：是',
  `SKILLS_ORDER` int(0) DEFAULT NULL COMMENT '技能顺序',
  `SKILLS_CERT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能证书编号',
  `SKILLS_ONIT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能任职文号',
  `SKILLS_HIRE_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能初聘文号',
  `SKILLS_H_START_TIME` datetime DEFAULT NULL COMMENT '技能初聘开始时间',
  `SKILLS_H_END_TIME` datetime DEFAULT NULL COMMENT '技能初聘结束时间',
  `SKILLS_H_CYCLE` int(0) DEFAULT NULL COMMENT '技能聘任周期',
  `SKILLS_OTH` int(0) DEFAULT NULL COMMENT '其他技能',
  `SKILLS_OTH_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他技能-名称',
  `SKILLS_OTH_GOT_TIME` datetime DEFAULT NULL COMMENT '其他技能取得时间',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_skills_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_spec_profe
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_spec_profe`;
CREATE TABLE `ch_pm_spec_profe`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `SPEC_P_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '特殊工种名称',
  `SPEC_P_START_TIME` datetime DEFAULT NULL COMMENT '特殊工种开始时间',
  `SPEC_P_END_TIME` datetime DEFAULT NULL COMMENT '特殊工种结束时间',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_spec_profe
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_station
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_station`;
CREATE TABLE `ch_pm_station`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `WAGES_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员编号（工资出账）',
  `IDENTITY` int(0) DEFAULT NULL COMMENT '个人身份  1:干部 2:工人',
  `IDENTITY_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个人身份-名称',
  `WK_MODALITY` int(0) DEFAULT NULL COMMENT '用工形式',
  `WK_MODALITY_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用工形式-名称',
  `WK_TYPE` int(0) DEFAULT NULL COMMENT '用工类型',
  `WK_TYPE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用工类型-名称',
  `LABOR_REL_NO` int(0) DEFAULT NULL COMMENT '劳动关系编号',
  `LABOR_REL_CN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '劳动关系名称',
  `STATION_SITU` int(0) DEFAULT NULL COMMENT '在岗情况',
  `STATION_SITU_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '在岗情况-名称',
  `STATION_TYPE` int(0) DEFAULT NULL COMMENT '岗位类型',
  `STATION_TYPE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位类型-名称',
  `STATION_STATUS` int(0) DEFAULT NULL COMMENT '岗位状态',
  `STATION_STATUS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位状态-名称',
  `STATION_SEQ_NO` int(0) DEFAULT NULL COMMENT '岗位序列编号',
  `STATION_SEQ_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位序列名称',
  `IS_MAJOR_PERSON` int(0) DEFAULT NULL COMMENT '是否专业人员',
  `IN_MAJOR_NOW` int(0) DEFAULT NULL COMMENT '现从事专业',
  `IN_MAJOR_NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现从事专业-名称',
  `WORK_AREA` int(0) DEFAULT NULL COMMENT '工作地域',
  `WORK_AREA_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作地域-名称',
  `HOS_BRANCH` int(0) DEFAULT NULL COMMENT '医院支部',
  `HOS_BRANCH_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院支部-名称',
  `HOS_DEPART_1LEVEL` int(0) DEFAULT NULL COMMENT '医院一级科室',
  `HOS_DEPART_1LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院一级科室-名称',
  `HOS_DEPART_2LEVEL` int(0) DEFAULT NULL COMMENT '医院二级科室',
  `HOS_DEPART_2LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院二级科室-名称',
  `HOME_ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现家庭详细住址',
  `TELPHONE_NO` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `MAIL_BOX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_WAGES_ID`(`WAGES_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_station
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_title_head
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_title_head`;
CREATE TABLE `ch_pm_title_head`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `TITLE_4NOW` int(0) DEFAULT NULL COMMENT '现职称',
  `TITLE_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现职称-名称',
  `TITLE_4NOW_GOT_TIME` datetime DEFAULT NULL COMMENT '现职称取得时间',
  `TITLE_4NOW_GOT_YEARS` int(0) DEFAULT NULL COMMENT '现职称取得年限',
  `TITLE_CLASS_4NOW` int(0) DEFAULT NULL COMMENT '现职称序列（职称分类）',
  `TITLE_CLASS_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现职称序列-名称',
  `TITLE_LEVEL_4NOW` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现职称级别(士级|初级|中级|副高级|正高级)',
  `TITLE_LEVEL_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现职称级别-名称',
  `TREAT_LEVEL_4NOW` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现职称待遇级别(士级|初级|中级|副高级|正高级)',
  `TREAT_LEVEL_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现职称待遇级别-名称',
  `TITLE_4NOW_H_START_TIME` datetime DEFAULT NULL COMMENT '现职称初聘开始时间',
  `TITLE_4NOW_H_END_TIME` datetime DEFAULT NULL COMMENT '现职称聘任终止时间',
  `TITLE_MAX_4NOW` int(0) DEFAULT NULL COMMENT '是否现最高职称  0：否  1：是',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_title_head
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_title_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_title_info`;
CREATE TABLE `ch_pm_title_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `TITLE` int(0) DEFAULT NULL COMMENT '职称',
  `TITLE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称-名称',
  `TITLE_LEVEL` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称级别(士级|初级|中级|副高级|正高级)',
  `TITLE_LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称级别-名称',
  `TITLE_CLASS` int(0) DEFAULT NULL COMMENT '职称序列（职称分类）',
  `TITLE_CLASS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称序列-名称',
  `TITLE_MAX` int(0) DEFAULT NULL COMMENT '是否最高职称 0：否  1：是',
  `TITLE_ORDER` int(0) DEFAULT NULL COMMENT '职称顺序',
  `TITLE_CERT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称证书编号',
  `TITLE_ONIT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称任职文号',
  `TITLE_HIRE_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称初聘文号',
  `TITLE_H_START_TIME` datetime DEFAULT NULL COMMENT '职称初聘开始时间',
  `TITLE_H_END_TIME` datetime DEFAULT NULL COMMENT '职称初聘结束时间',
  `TITLE_H_CYCLE` int(0) DEFAULT NULL COMMENT '职称聘任周期',
  `TITLE_OTH` int(0) DEFAULT NULL COMMENT '其他职称',
  `TITLE_OTH_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他职称-名称',
  `TITLE_OTH_GOT_TIME` datetime DEFAULT NULL COMMENT '其他职称取得时间',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_title_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_trans_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_trans_info`;
CREATE TABLE `ch_pm_trans_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `CARD_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `DEPART_BEFORE` int(0) DEFAULT NULL COMMENT '原部门',
  `DEPART_BEFORE_CN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原部门-名称',
  `POSIT_BEFORE` int(0) DEFAULT NULL COMMENT '原岗位',
  `POSIT_BEFORE_CN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原岗位-名称',
  `DEPART_AFTER` int(0) DEFAULT NULL COMMENT '新部门',
  `DEPART_AFTER_CN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '新部门-名称',
  `POSIT_AFTER` int(0) DEFAULT NULL COMMENT '新岗位',
  `POSIT_AFTER_CN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '新岗位-名称',
  `TRANS_DOCNO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调动文号',
  `TRANS_TIME` datetime DEFAULT NULL COMMENT '调动时间',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_trans_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_work_expe_info
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_work_expe_info`;
CREATE TABLE `ch_pm_work_expe_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) NOT NULL COMMENT '职工编号',
  `WK_COM_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作单位名称',
  `WK_DEP_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作部门名称',
  `WK_STATION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作岗位',
  `WK_POSIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '担任职务',
  `WK_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称',
  `TRANS_DOCNO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调动文号',
  `WK_START_TIME` datetime DEFAULT NULL COMMENT '工作开始时间',
  `WK_END_TIME` datetime DEFAULT NULL COMMENT '工作结束时间',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_work_expe_info
-- ----------------------------

-- ----------------------------
-- Table structure for ch_pm_worker
-- ----------------------------
DROP TABLE IF EXISTS `ch_pm_worker`;
CREATE TABLE `ch_pm_worker`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工姓名',
  `SEX` int(0) DEFAULT NULL COMMENT '职工性别  0：女  1：男  -1：未知',
  `AGE` int(0) DEFAULT NULL COMMENT '职工年龄',
  `CARD_TYPE` int(0) DEFAULT NULL COMMENT '卡类型（身份）',
  `CARD_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '卡号（身份）',
  `BIRTH_DAY` datetime DEFAULT NULL COMMENT '出生日期',
  `NATION` int(0) DEFAULT NULL COMMENT '民族',
  `NATION_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '民族名称',
  `POLITICS` int(0) DEFAULT NULL COMMENT '政治面貌',
  `POLITICS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '政治面貌名称',
  `POLITICS_IN_TIME` datetime DEFAULT NULL COMMENT '政治面貌加入时间',
  `FIRST_WORK_TIME` datetime DEFAULT NULL COMMENT '首次参加工作时间',
  `WORKED_YEAR` int(0) DEFAULT NULL COMMENT '工龄',
  `INTO_LOCAL_COMP_TIME` datetime DEFAULT NULL COMMENT '进入本单位时间',
  `NATIVE_PLACE_PROV` int(0) DEFAULT NULL COMMENT '籍贯-省份',
  `NATIVE_PLACE_PROV_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '籍贯-省份名称',
  `NATIVE_PLACE_CITY` int(0) DEFAULT NULL COMMENT '籍贯-城市',
  `NATIVE_PLACE_CITY_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '籍贯-城市名称',
  `NATIVE_PLACE_AREA` int(0) DEFAULT NULL COMMENT '籍贯-区县',
  `NATIVE_PLACE_AREA_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '籍贯-区县名称',
  `RESIDENCE_TYPE` int(0) DEFAULT NULL COMMENT '户口性质',
  `RESIDENCE_TYPE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户口性质名称',
  `RESIDENCE_BIRTHLAND_PROV` int(0) DEFAULT NULL COMMENT '户口所在地-省份',
  `RESIDENCE_BIRTHLAND_PROV_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户口所在地-省份名称',
  `RESIDENCE_BIRTHLAND_CITY` int(0) DEFAULT NULL COMMENT '户口所在地-城市',
  `RESIDENCE_BIRTHLAND_CITY_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户口所在地-城市名称',
  `RESIDENCE_BIRTHLAND_AREA` int(0) DEFAULT NULL COMMENT '户口所在地-区县',
  `RESIDENCE_BIRTHLAND_AREA_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户口所在地-区县名称',
  `RESIDENCE_POLICE_STATION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户口所在地-所属派出所',
  `HOME_PROV` int(0) DEFAULT NULL COMMENT '现家庭详细住址-省份',
  `HOME_PROV_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现家庭详细住址-省份名称',
  `HOME_CITY` int(0) DEFAULT NULL COMMENT '现家庭详细住址-城市',
  `HOME_CITY_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现家庭详细住址-城市名称',
  `HOME_ADDR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现家庭详细住址-详细地址',
  `TELPHONE_NO` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `MAIL_BOX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `SALARY_ADJUST_TYPE` int(0) DEFAULT NULL COMMENT '调资类别',
  `SALARY_ADJUST_TYPE_CN` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调资类别-中文',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `idx_CARD_TYPE_NO_NAME`(`CARD_TYPE`, `CARD_NO`, `NAME`) USING BTREE,
  INDEX `idx_CARD_NO`(`CARD_NO`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ch_pm_worker
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
