package com.hdfs.olo.olo_web.plugins.common.constant;

import java.util.HashMap;
import java.util.Map;

import com.hdfs.olo.olo_web.personnel.model.ChPmContractInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmFamilyMemberInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmOthInfo4ImpModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSpecProfeModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmStationModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmTitleHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmTitleInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkExpeInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;

public class ArchiveImportConst {

	public final static Map<Integer,Map<String,String>> headerAlias; 
	static {
		headerAlias = new HashMap<Integer,Map<String,String>>();
		//worker  导入列
		Map<String, String> header_4base = new HashMap<String, String>();
		header_4base.put("姓名", "name");
		header_4base.put("性别", "sex");
		//header_4base.put("年龄", "age");
		header_4base.put("身份证号", "cardNo");
		header_4base.put("出生日期", "birthDay");
		header_4base.put("民族", "nation");
		header_4base.put("政治面貌", "politics");
		header_4base.put("加入时间", "politicsInTime");
		header_4base.put("首次工作时间", "firstWorkTime");
		header_4base.put("进入本单位时间", "intoLocalCompTime");
		header_4base.put("籍贯-省份", "nativePlaceProv");
		header_4base.put("籍贯-城市", "nativePlaceCity");
		header_4base.put("籍贯-区县", "nativePlaceArea");
		header_4base.put("户口性质", "residenceType");
		header_4base.put("户口所在地-省份", "residenceBirthlandProv");
		header_4base.put("户口所在地-城市", "residenceBirthlandCity");
		header_4base.put("户口所在地-区县", "residenceBirthlandArea");
		header_4base.put("所属派出所", "residencePoliceStation");
		header_4base.put("家庭住址-省份", "homeProv");
		header_4base.put("家庭住址-城市", "homeCity");
		header_4base.put("家庭住址详细地址", "homeAddr");
		header_4base.put("手机号", "telphoneNo");
		header_4base.put("邮箱", "mailBox");
		header_4base.put("调资类别", "salaryAdjustType");
		headerAlias.put(0, header_4base);
		
		
		//edu_info
		Map<String, String> header_4eduInfo = new HashMap<String, String>();
		header_4eduInfo.put("身份证号", "cardNo");
		header_4eduInfo.put("学习院校", "eduSch");
		header_4eduInfo.put("专业", "eduMaj");
		header_4eduInfo.put("学位", "eduDeg");
		header_4eduInfo.put("教育类型", "eduType");
		header_4eduInfo.put("学历", "eduLev");
		header_4eduInfo.put("学习开始时间", "eduStartTime");
		header_4eduInfo.put("学习结束时间", "eduEndTime");
		header_4eduInfo.put("是否最高学历", "eduMax");
		headerAlias.put(1, header_4eduInfo);
		
		Map<String, String> header_4workExpe = new HashMap<String, String>();
		header_4workExpe.put("身份证号", "cardNo");
		header_4workExpe.put("工作单位名称", "wkComName");
		header_4workExpe.put("工作部门名称", "wkDepName");
		header_4workExpe.put("工作岗位", "wkStation");
		header_4workExpe.put("担任职务", "wkPosit");
		header_4workExpe.put("职称", "wkTitle");
		header_4workExpe.put("调动文号", "transDocno");
		header_4workExpe.put("开始工作时间", "wkStartTime");
		header_4workExpe.put("工作结束时间", "wkEndTime");
		headerAlias.put(2, header_4workExpe);
		
		Map<String, String> header_4positInfo = new HashMap<String, String>();
		header_4positInfo.put("身份证号", "cardNo");
		header_4positInfo.put("职务", "posit");
		header_4positInfo.put("任职部门", "positDepart");
		header_4positInfo.put("行政级别", "positLevel");
		header_4positInfo.put("是否最高职务", "positMax");
		header_4positInfo.put("任职开始时间", "onPositStartTime");
		header_4positInfo.put("任职结束时间", "onPositEndTime");
		header_4positInfo.put("任职文号", "onPositDocno");
		header_4positInfo.put("兼任职务", "conPosit");
		header_4positInfo.put("兼职部门", "conPositDepart");
		header_4positInfo.put("兼职开始时间", "conPositStartTime");
		header_4positInfo.put("兼职结束时间", "conPositEndTime");
		header_4positInfo.put("待遇级别", "treatLevel");
		headerAlias.put(3, header_4positInfo);
		
		Map<String, String> header_4titleInfo = new HashMap<String, String>();
		header_4titleInfo.put("身份证号", "cardNo");
		header_4titleInfo.put("职称", "title");
		header_4titleInfo.put("职称级别", "titleLevel");
		header_4titleInfo.put("职称序列", "titleClass");
		header_4titleInfo.put("是否最高职称", "titleMax");
		header_4titleInfo.put("职称证书编号", "titleCertNo");
		header_4titleInfo.put("职称任职文号", "titleOnitNo");
		header_4titleInfo.put("职称初聘文号", "titleHireNo");
		header_4titleInfo.put("职称初聘开始时间", "titleHStartTime");
		header_4titleInfo.put("职称初聘结束时间", "titleHEndTime");
		header_4titleInfo.put("职称聘任周期", "titleHCycle");
		header_4titleInfo.put("其他职称", "titleOth");
		header_4titleInfo.put("其他职称取得时间", "titleOthGotTime");
		headerAlias.put(4, header_4titleInfo);
		
		Map<String, String> header_5skillsInfo = new HashMap<String, String>();
		header_5skillsInfo.put("身份证号", "cardNo");
		header_5skillsInfo.put("技能等级", "skills");
		header_5skillsInfo.put("技能等级取得时间", "skillsGotTime");
		header_5skillsInfo.put("技能等级序列", "skillsClass");
		header_5skillsInfo.put("技能等级级别", "skillsLevel");
		header_5skillsInfo.put("是否最高技能", "skillsMax");
		header_5skillsInfo.put("技能证书编号", "skillsCertNo");
		header_5skillsInfo.put("技能任职文号", "skillsOnitNo");
		header_5skillsInfo.put("技能初聘文号", "skillsHireNo");
		header_5skillsInfo.put("技能初聘开始时间", "skillsHStartTime");
		header_5skillsInfo.put("技能初聘结束时间", "skillsHEndTime");
		header_5skillsInfo.put("技能聘任周期", "skillsHCycle");
		header_5skillsInfo.put("其他技能", "skillsOth");
		header_5skillsInfo.put("其他技能取得时间", "skillsOthGotTime");
		headerAlias.put(5, header_5skillsInfo);
		
		Map<String, String> header_6eduHead = new HashMap<String, String>();
		header_6eduHead.put("身份证号", "cardNo");
		header_6eduHead.put("现学历", "eduLev4now");
		header_6eduHead.put("现学位", "eduDeg4now");
		header_6eduHead.put("工资学历", "eduLev4sal");
		headerAlias.put(6, header_6eduHead);
		
		Map<String, String> header_7positHead = new HashMap<String, String>();
		header_7positHead.put("身份证号", "cardNo");
		header_7positHead.put("现任职务", "posit4now");
		header_7positHead.put("现任职部门", "positDepart4now");
		header_7positHead.put("现行政级别", "positLevel4now");
		header_7positHead.put("现任职务开始时间", "posit4nowStartTime");
		header_7positHead.put("现任职务结束时间", "posit4nowEndTime");
		header_7positHead.put("现待遇级别", "treatLevel4now");
		header_7positHead.put("现待遇级别开始时间", "treat4nowStartTime");
		header_7positHead.put("现待遇级别结束时间", "treat4nowEndTime");
		header_7positHead.put("现待遇级别年限", "treat4nowYears");
		header_7positHead.put("任正处开始时间", "onChuStartTime");
		header_7positHead.put("任正处结束时间", "onChuEndTime");
		header_7positHead.put("任副处开始时间", "onChuDetupyStartTime");
		header_7positHead.put("任副处结束时间", "onChuDetupyEndTime");
		header_7positHead.put("任正科开始时间", "onKeStartTime");
		header_7positHead.put("任正科结束时间", "onKeEndTime");
		header_7positHead.put("任副科开始时间", "onKeDetupyStartTime");
		header_7positHead.put("任副科结束时间", "onKeDetupyEndTime");
		headerAlias.put(7, header_7positHead);
		
		Map<String, String> header_8titleHead = new HashMap<String, String>();
		header_8titleHead.put("身份证号", "cardNo");
		header_8titleHead.put("现职称", "title4now");
		header_8titleHead.put("现职称取得时间", "title4nowGotTime");
		header_8titleHead.put("现职称取得年限", "title4nowGotYears");
		header_8titleHead.put("现职称序列", "titleClass4now");
		header_8titleHead.put("现职称级别", "titleLevel4now");
		header_8titleHead.put("现职称待遇级别", "treatLevel4now");
		header_8titleHead.put("现职称初聘开始时间", "title4nowHStartTime");
		header_8titleHead.put("现职称聘任结束时间", "title4nowHEndTime");
		header_8titleHead.put("是否现最高职称", "titleMax4now");
		headerAlias.put(8, header_8titleHead);
		
		Map<String, String> header_9skillsHead = new HashMap<String, String>();
		header_9skillsHead.put("身份证号", "cardNo");
		header_9skillsHead.put("现技能等级", "skills4now");
		header_9skillsHead.put("现技能等级取得时间", "skills4nowGotTime");
		header_9skillsHead.put("现技能等级序列", "skillsClass4now");
		header_9skillsHead.put("现技能等级级别", "skillsLevel4now");
		header_9skillsHead.put("现技能等级待遇级别", "treatLevel4now");
		header_9skillsHead.put("现技能初聘开始时间", "skills4nowHStartTime");
		header_9skillsHead.put("现技能聘任结束时间", "skills4nowHEndTime");
		header_9skillsHead.put("是否现最高技能等级", "skillsMax4now");
		headerAlias.put(9, header_9skillsHead);
		
		Map<String, String> header_10station = new HashMap<String, String>();
		header_10station.put("身份证号", "cardNo");
		header_10station.put("人员编号", "wagesId");
		header_10station.put("个人身份", "identity");
		header_10station.put("用工形式", "wkModality");
		header_10station.put("用工类型", "wkType");
		header_10station.put("劳动关系", "laborRelNo");
		header_10station.put("在岗情况", "stationSitu");
		header_10station.put("岗位类型", "stationType");
		header_10station.put("岗位状态", "stationStatus");
		header_10station.put("岗位序列", "stationSeqNo");
		header_10station.put("是否专业人员", "isMajorPerson");
		header_10station.put("现从事专业", "inMajorNowCn");
		header_10station.put("工作地域", "workArea");
		header_10station.put("医院支部", "hosBranch");
		header_10station.put("医院一级科室", "hosDepart1level");
		header_10station.put("医院二级科室", "hosDepart2level");
		header_10station.put("现家庭详细住址", "homeAddress");
		header_10station.put("联系电话", "telphoneNo");
		header_10station.put("邮箱", "mailBox");
		headerAlias.put(10, header_10station);
		
		Map<String, String> header_11ContInfo = new HashMap<String, String>();
		header_11ContInfo.put("身份证号", "cardNo");
		header_11ContInfo.put("合同类别", "contClass");
		header_11ContInfo.put("合同期限", "contCycle");
		header_11ContInfo.put("合同编号", "contNo");
		header_11ContInfo.put("合同开始时间", "contStartTime");
		header_11ContInfo.put("合同结束时间", "contEndTime");
		headerAlias.put(11, header_11ContInfo);
		
		Map<String, String> header_12Family = new HashMap<String, String>();
		header_12Family.put("身份证号", "cardNo");
		header_12Family.put("家庭成员关系", "memRelation");
		header_12Family.put("家庭成员姓名", "memName");
		header_12Family.put("家庭成员工作单位", "memWkCom");
		header_12Family.put("家庭成员联系电话", "memTelno");
		header_12Family.put("现详细住址", "memAddr");
		headerAlias.put(12, header_12Family);
		
		Map<String, String> header_13SpecProfe = new HashMap<String, String>();
		header_13SpecProfe.put("身份证号", "cardNo");
		header_13SpecProfe.put("特殊工种名称", "specPName");
		header_13SpecProfe.put("特殊工种开始时间", "specPStartTime");
		header_13SpecProfe.put("特殊工种结束时间", "specPEndTime");
		headerAlias.put(13, header_13SpecProfe);
		
		Map<String, String> header_14Oths = new HashMap<String, String>();
		header_14Oths.put("身份证号", "cardNo");
		header_14Oths.put("是否疫情防控一线医护人员", "epidsPrecFlHcStaff");
		header_14Oths.put("荣誉金取得时间", "honMoneyGotTime");
		header_14Oths.put("核减工龄年限", "caeWkYears");
		header_14Oths.put("是否残疾人", "isDisability");
		header_14Oths.put("残疾鉴定级别", "disabilityLev");
		header_14Oths.put("是否工伤", "isInjryOnJob");
		header_14Oths.put("工伤鉴定级别", "injryLev");
		header_14Oths.put("人事档案编号", "dossierNo");
		header_14Oths.put("人事档案存放地", "dossierStorage");
		header_14Oths.put("进入时间", "entryTime");
		header_14Oths.put("进入渠道", "entryChan");
		header_14Oths.put("离职时间", "quitTime");
		header_14Oths.put("离职原因", "quitReason");
		headerAlias.put(14, header_14Oths);
	}
	
	
	@SuppressWarnings("rawtypes")
	public static Map<Class,Map<String,String>> loadAdapterHead(Integer type)
	{
		if(null == type) return null;
		Map<String,String> heads = headerAlias.get(type);
		Map<Class,Map<String,String>> retMap = new HashMap<Class,Map<String,String>>();
		switch(type)
		{
		case 0:
			retMap.put(ChPmWorkerModel.class, heads);
			break;
		case 1:
			retMap.put(ChPmEduInfoModel.class, heads);
			break;
		case 2:
			retMap.put(ChPmWorkExpeInfoModel.class, heads);
			break;
		case 3:
			retMap.put(ChPmPositInfoModel.class, heads);
			break;
		case 4:
			retMap.put(ChPmTitleInfoModel.class, heads);
			break;
		case 5:
			retMap.put(ChPmSkillsInfoModel.class, heads);
			break;
		case 6:
			retMap.put(ChPmEduHeadModel.class, heads);
			break;
		case 7:
			retMap.put(ChPmPositHeadModel.class, heads);
			break;
		case 8:
			retMap.put(ChPmTitleHeadModel.class, heads);
			break;
		case 9:
			retMap.put(ChPmSkillsHeadModel.class, heads);
			break;
		case 10:
			retMap.put(ChPmStationModel.class, heads);
			break;
		case 11:
			retMap.put(ChPmContractInfoModel.class, heads);
			break;
		case 12:
			retMap.put(ChPmFamilyMemberInfoModel.class, heads);
			break;
		case 13:
			retMap.put(ChPmSpecProfeModel.class, heads);
			break;
		case 14:
			retMap.put(ChPmOthInfo4ImpModel.class, heads);
			break;
		}
		return retMap;
	}
}
