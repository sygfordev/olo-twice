-- ----------------------------
-- Table structure for ch_sa_payslip
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_payslip`;
CREATE TABLE `ch_sa_payslip`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `WORKER_ID` bigint(0) DEFAULT NULL COMMENT '职工编号',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工姓名',
  `CARD_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号',
  `BANK_CARD_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行卡号',
  `MOBILE_NO` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `WK_MODALITY` int(0) DEFAULT NULL COMMENT '用工形式(人员类别)',
  `WK_MODALITY_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用工形式(人员类别)名称',
  `WAGES_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员编号（工资出账）',
  `POSIT` int(0) DEFAULT NULL COMMENT '职务',
  `POSIT_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职务-名称',
  `TITLE` int(0) DEFAULT NULL COMMENT '职称',
  `TITLE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称-名称',
  `HOS_BRANCH` int(0) DEFAULT NULL COMMENT '医院支部',
  `HOS_BRANCH_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院支部-名称',
  `HOS_DEPART_1LEVEL` int(0) DEFAULT NULL COMMENT '医院一级科室',
  `HOS_DEPART_1LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院一级科室-名称',
  `HOS_DEPART_2LEVEL` int(0) DEFAULT NULL COMMENT '医院二级科室',
  `HOS_DEPART_2LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院二级科室-名称',
  
  `DEPART_CLASS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门类别',
  `DEPART_CLASS_POP` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门类别属性',
  `STATION_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位',
  `STATION_TYPE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位类型',
  `STATION_STATUS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位状态',
  `STATION_SEQ_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位序列',
  `TITLE_CLASS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称序列（职称分类）',
  `SKILLS_LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能等级级别',
  `EDU_LEV_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现学历',
  `YEARLY_SALARY_MAN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '年薪制人员',
  `SA_SUM_PROJECT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工资汇总表项目',
  `RPT_WK_DEP_CLASS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报工系统部门分类',
  
  `MANWK_STANDARD` decimal(5,2) DEFAULT NULL COMMENT '标准工数',
  `MANWK_ATTEND` decimal(5,2) DEFAULT NULL COMMENT '出勤工数',
  `MANWK_MINER` decimal(5,2) DEFAULT NULL COMMENT '旷工工数',
  `MANWK_QUIT` decimal(5,2) DEFAULT NULL COMMENT '离职工数',
  `MANWK_SICK` decimal(5,2) DEFAULT NULL COMMENT '病假工数',
  `MANWK_MATERNITY` decimal(5,2) DEFAULT NULL COMMENT '产假工数',
  `MANWK_PRIVPASSION` decimal(5,2) DEFAULT NULL COMMENT '事假工数',
  `MANWK_OVERTIME` decimal(5,2) DEFAULT NULL COMMENT '加班工数',
  `WAGE_STANDARD_4POSIT` decimal(10,2) DEFAULT NULL COMMENT '岗位工资标准',
  `WAGE_DAY_4POSIT` decimal(10,2) DEFAULT NULL COMMENT '岗位日工资',
  `WAGE_ATTEND` decimal(10,2) DEFAULT NULL COMMENT '出勤工资',
  `WAGE_SICK` decimal(10,2) DEFAULT NULL COMMENT '病假工资',
  `WAGE_POSIT_TOTAL` decimal(10,2) DEFAULT NULL COMMENT '岗位工资合计',
  `WAGE_NIGHT_SHIFT` decimal(10,2) DEFAULT NULL COMMENT '夜班费用',
  `WAGE_OVERTIME` decimal(10,2) DEFAULT NULL COMMENT '加班工资',
  `WAGE_YEARG` decimal(10,2) DEFAULT NULL COMMENT '年功工资',
  `ALLOWANCE_HYGIENE` decimal(10,2) DEFAULT NULL COMMENT '卫生津贴',
  `WAGE_ERROR_CORRENT` decimal(10,2) DEFAULT NULL COMMENT '纠错工资',
  `SUPP_4TEL` decimal(10,2) DEFAULT NULL COMMENT '电补',
  
  `SUPP_4TRAFFIC` decimal(10,2) DEFAULT NULL COMMENT '交通补贴',
  `SUPP_4MINING` decimal(10,2) DEFAULT NULL COMMENT '下矿（井、乡）补贴',
  `SUPP_4OTHER` decimal(10,2) DEFAULT NULL COMMENT '其他补贴',
  `SUPP_4UNIVE_STU_LIFE` decimal(10,2) DEFAULT NULL COMMENT '大学生生活补贴',
  `SUPP_4OTH` decimal(10,2) DEFAULT NULL COMMENT '其他',
  `SUPP_TOTAL` decimal(10,2) DEFAULT NULL COMMENT '津补贴合计',
  
  `WAGE_TOTAL` decimal(10,2) DEFAULT NULL COMMENT '工资合计',
  `WAGE_JX_TOTAL` decimal(10,2) DEFAULT NULL COMMENT '绩效工资合计',
  `WAGE_PAYABLE_TOTAL` decimal(10,2) DEFAULT NULL COMMENT '应发工资合计',
  `BX_PENSION` decimal(10,2) DEFAULT NULL COMMENT '养老保险',
  `BX_MEDICAL` decimal(10,2) DEFAULT NULL COMMENT '医疗保险',
  `BX_UNEMPLOY` decimal(10,2) DEFAULT NULL COMMENT '失业保险',
  `BX_SERIOUS_ILLNESS` decimal(10,2) DEFAULT NULL COMMENT '大病保险',
  `BX_HOUSFUND` decimal(10,2) DEFAULT NULL COMMENT '住房公积金',
  `BX_ANNUAL_CORP_GOLD` decimal(10,2) DEFAULT NULL COMMENT '年企业金',
  `BX_TOTAL` decimal(10,2) DEFAULT NULL COMMENT '保险合计',
  `CUT_WATER2ELECT` decimal(10,2) DEFAULT NULL COMMENT '水电费',
  `CUT_HYGIENE` decimal(10,2) DEFAULT NULL COMMENT '卫生费',
  `CUT_OTHER` decimal(10,2) DEFAULT NULL COMMENT '其他扣款',
  `CUT_ARREARS` decimal(10,2) DEFAULT NULL COMMENT '职工欠款',
  `CUT_TOTAL` decimal(10,2) DEFAULT NULL COMMENT '扣款合计',
  `TAXABLE_WAGE` decimal(10,2) DEFAULT NULL COMMENT '计税工资',
  `TAX_INCOME_PERSONAL` decimal(10,2) DEFAULT NULL COMMENT '个税',
  `NET_SALARY` decimal(10,2) DEFAULT NULL COMMENT '实发工资',
  `NET_TARGET_YEARMONTH` varchar(20) NOT NULL COMMENT '工资年月',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `BTIMP_NO` varchar(25) NOT NULL COMMENT '导入编号',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_WORKER_ID`(`WORKER_ID`) USING BTREE,
  INDEX `idx_WAGES_ID`(`WAGES_ID`) USING BTREE,
  INDEX `idx_CARD_NO_YEARMONTH_STATUS`(`CARD_NO`,`NET_TARGET_YEARMONTH`,`STATUS`) USING BTREE,
  INDEX `idx_BANK_CARD_NO`(`BANK_CARD_NO`) USING BTREE,
  INDEX `idx_BTIMP_NO`(`BTIMP_NO`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for ch_sa_payslip_reward
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_payslip_reward`;
CREATE TABLE `ch_sa_payslip_reward`  (
  `CARD_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号',
  `TARGET_YEARMONTH` varchar(20) NOT NULL COMMENT '奖励年月',
  `REWARD_ITEM` varchar(150) DEFAULT NULL COMMENT '奖励项',
  `REWARD_AMOUNT` decimal(10,2) DEFAULT NULL COMMENT '奖励金额',
  `BTIMP_NO` varchar(25) NOT NULL COMMENT '导入编号',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：异常',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  INDEX MULTIIDX(`CARD_NO`,`TARGET_YEARMONTH`),
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for ch_sa_payslip_imprecord
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_payslip_imprecord`;
CREATE TABLE `ch_sa_payslip_imprecord`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `BATCH_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '批次号',
  `BATCH_TYPE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '批次类型名称',
  `BATCH_USER_ACCOUNT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人账户',
  `BATCH_USER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `SUC_NUM` bigint(0) DEFAULT NULL COMMENT '成功数量',
  `FAI_NUM` bigint(0) DEFAULT NULL COMMENT '失败数量',
  `EXE_NUM` bigint(0) DEFAULT NULL COMMENT '异常数量',
  `EXIST_NUM` bigint(0) DEFAULT NULL COMMENT '已存在数量',
  `BATCH_MSG` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '批次消息',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：校验失败  1：导入成功',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for ch_sa_adjust_salary
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_adjust_salary`;
CREATE TABLE `ch_sa_adjust_salary`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工姓名',
  `CARD_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '卡号（身份）',
  `WAGES_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员编号（工资出账）',
  `WAGES_SEQ` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工资账序号',
  `WAGES_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工资账姓名',
  `WAGES_MODALITY_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工资账用工形式',
  `WAGES_DEPART` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工资账科室',
  `WAGES_STANDARD_BEF` decimal(10,2) DEFAULT NULL COMMENT '原岗位工资标准',
  `SALARY_ADJUST_TYPE_CN` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调资类别',
  `HOS_DEPART_1LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院一级科室',
  `HOS_DEPART_2LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院二级科室',
  `EDU_4MAX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最高学历',
  `EDU_4FIRST` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第一学历',
  `EDU_4WAGE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工资学历',
  `FIRST_WORK_TIME` datetime DEFAULT NULL COMMENT '首次参加工作时间',
  `WORKED_YEAR` double(4,2) DEFAULT NULL COMMENT '工龄',
  `POSIT_4NOW_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现行政职务',
  `POSIT_4NOW_START_TIME` datetime DEFAULT NULL COMMENT '任职开始时间',
  `POSIT_4NOW_YEARS` double(4,2) DEFAULT NULL COMMENT '现行政级别年限',
  `POSIT_LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '行政级别',
  `POSIT_LEVEL_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '行政级别代码',
  `ON_PRIN_POSIT_START_TIME` datetime DEFAULT NULL COMMENT '任正职时间',
  `ON_PRIN_POSIT_YEARS` double(4,2) DEFAULT NULL COMMENT '任正职年限',
  `ON_DEPT_POSIT_START_TIME` datetime DEFAULT NULL COMMENT '任副职时间',
  `ON_DEPT_POSIT_YEARS` double(4,2) DEFAULT NULL COMMENT '任副职年限',
  `TITLE_4MAX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最高职称',
  `TITLE_4MAX_GOT_TIME` datetime DEFAULT NULL COMMENT '最高职称取得时间',
  `TITLE_4MAX_GOT_YEARS` double(4,2) DEFAULT NULL COMMENT '最高职称取得年限',
  `TITLE_4MAX_CLASS_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称序列',
  `TITLE_4MAX_LEVEL_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称级别',
  `ADJUST_4TITLE` decimal(10,2) DEFAULT NULL COMMENT '职称调资',
  `ADJUST_4POSIT` decimal(10,2) DEFAULT NULL COMMENT '职务调资',
  `ADJUST_4EDU` decimal(10,2) DEFAULT NULL COMMENT '学历调资',
  `WAGES_STANDARD_AFT` decimal(10,2) DEFAULT NULL COMMENT '调整后工资标准',
  `ADJUST_PROOF` varchar(20) DEFAULT NULL COMMENT '调资依据',
  `ADJUST_DIFFE` decimal(10,2) DEFAULT NULL COMMENT '调资差额',
  `FORMULA_ID` bigint(0) DEFAULT NULL COMMENT '公式ID',
  `RECORD_ID` bigint(0) DEFAULT NULL COMMENT '调资记录ID',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：已删除',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_RECORD_ID`(`RECORD_ID`) USING BTREE,
  INDEX `idx_FORMULA_ID`(`FORMULA_ID`) USING BTREE,
  INDEX `idx_SALARY_ADJUST_TYPE_CN_STATUS`(`SALARY_ADJUST_TYPE_CN`,`STATUS`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for ch_sa_adjust_formula
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_adjust_formula`;
CREATE TABLE `ch_sa_adjust_formula`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `TARGET` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标：职称/职务/学历',
  `TARGET_LEVEL_CN` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标等级',
  `FORMULA_2DOWN` decimal(10,2) DEFAULT NULL COMMENT '2年及以下',
  `FORMULA_3TO4` decimal(10,2) DEFAULT NULL COMMENT '3-4年',
  `FORMULA_5TO6` decimal(10,2) DEFAULT NULL COMMENT '5-6年',
  `FORMULA_7TO8` decimal(10,2) DEFAULT NULL COMMENT '7-8年',
  `FORMULA_9TO10` decimal(10,2) DEFAULT NULL COMMENT '9-10年',
  `FORMULA_11TO12` decimal(10,2) DEFAULT NULL COMMENT '11-12年',
  `FORMULA_13UP` decimal(10,2) DEFAULT NULL COMMENT '13年以上',
  `GRATDATIONS` decimal(10,2) DEFAULT NULL COMMENT '级差',
  `FORMULA_TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公式分类',
  `UNIQUE_KEY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一批次编号',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：已过期',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_FORMULA_TYPE_STATUS`(`FORMULA_TYPE`,`STATUS`) USING BTREE,
  INDEX `idx_FORMULA_TYPE_UNIQUE_KEY`(`FORMULA_TYPE`,`UNIQUE_KEY`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for ch_sa_adjust_record
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_adjust_record`;
CREATE TABLE `ch_sa_adjust_record`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ADJUST_MONTH` varchar(20) DEFAULT NULL COMMENT '调资年月',
  `ADJUST_FORMULAS` varchar(80) DEFAULT NULL COMMENT '调资公式列表',
  `ADJUST_ACCOUNT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作账户',
  `ADJUST_USER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：已删除',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_STATUS`(`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for ch_sa_wechat_account
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_wechat_account`;
CREATE TABLE `ch_sa_wechat_account`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `CARD_NO` varchar(25) NOT NULL COMMENT '身份证号',
  `TYPE` int(2) NOT NULL COMMENT '账户类型 0微信公众号 1:其他',
  `PASSWD` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `ERR_TIMES` int(2) default 0 NOT NULL COMMENT '当天密码输错次数',
  `STATUS` int(0) NOT NULL DEFAULT 0 COMMENT '状态 0：正常  1：已删除',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_CARD_NO_STATUS`(`CARD_NO`,`STATUS`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ch_sa_wechat_token
-- ----------------------------
DROP TABLE IF EXISTS `ch_sa_wechat_token`;
CREATE TABLE `ch_sa_wechat_token`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `TOKEN` varchar(20) DEFAULT NULL COMMENT 'token',
  `TYPE` int(2) DEFAULT NULL COMMENT 'token类型',
  `CARD_NO` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_CARD_NO`(`CARD_NO`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
