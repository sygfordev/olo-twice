package com.hdfs.olo.olo_web.social.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;

public class SocialExportHeadUtil {

	public static JSONObject createHead()
	{
		String head1 = 
				"{"+
				"'hTitle':'中央医院-社保单',"+
				"'heads':["+
				"{"+
				"'field':'serialNo',"+
				"'title':'序号',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':0,"+
				"'colE':0"+
				"},"+
				"{"+
				"'field':'name',"+
				"'title':'姓名',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':1,"+
				"'colE':1"+
				"},"+
				"{"+
				"'field':'cardNo',"+
				"'title':'身份证号',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':2,"+
				"'colE':2"+
				"},"+
				"{"+
				"'field':'compName',"+
				"'title':'单位名称',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':3,"+
				"'colE':3"+
				"},"+
				"{"+
				"'field':'wkModalityCn',"+
				"'title':'用工类别',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':4,"+
				"'colE':4"+
				"},"+
				"{"+
				"'field':'workAreaCn',"+
				"'title':'工作地域',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':5,"+
				"'colE':5"+
				"},"+
				"{"+
				"'field':'positCn',"+
				"'title':'职务',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':6,"+
				"'colE':6"+
				"},"+
				"{"+
				"'field':'titleCn',"+
				"'title':'职称',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':7,"+
				"'colE':7"+
				"},"+
				"{"+
				"'field':'hosBranchCn',"+
				"'title':'支部',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':8,"+
				"'colE':8"+
				"},"+
				"{"+
				"'field':'hosDepart1levelCn',"+
				"'title':'一级科室',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':9,"+
				"'colE':9"+
				"},"+
				"{"+
				"'field':'hosDepart2levelCn',"+
				"'title':'二级科室',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':10,"+
				"'colE':10"+
				"},"+
				"{"+
				"'field':'socialYmonth',"+
				"'title':'社保年月',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':11,"+
				"'colE':11"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'"+DateTimeHelper.getMonth(new Date())+"月份社保',"+
				"'rowS':0,"+
				"'rowE':0,"+
				"'colS':12,"+
				"'colE':100,"+
				"'subs':["+
				"{"+
				"'field':'',"+
				"'title':'养老保险',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':12,"+
				"'colE':22,"+
				"'subs':["+
				//养老开始
				"{"+
				"'field':'sPenBase',"+
				"'title':'基数[养老]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':12,"+
				"'colE':12"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':13,"+
				"'colE':14,"+
				"'subs':["+
				"{"+
				"'field':'sPenCompRatio',"+
				"'title':'单位应缴比例[养老]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':13,"+
				"'colE':13"+
				"},"+
				"{"+
				"'field':'sPenCompAmount',"+
				"'title':'单位应缴金额[养老]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':14,"+
				"'colE':14"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':15,"+
				"'colE':16,"+
				"'subs':["+
				"{"+
				"'field':'sPenPersRatio',"+
				"'title':'个人应缴比例[养老]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':15,"+
				"'colE':15"+
				"},"+
				"{"+
				"'field':'sPenPersAmount',"+
				"'title':'个人应缴金额[养老]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':16,"+
				"'colE':16"+
				"}"+
				"]},"+
				"{"+
				"'field':'sPenCompRecapAmount',"+
				"'title':'单位实缴金额[养老]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':17,"+
				"'colE':17"+
				"},"+
				"{"+
				"'field':'sPenPersRecapAmount',"+
				"'title':'个人实缴金额[养老]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':18,"+
				"'colE':18"+
				"},"+
				"{"+
				"'field':'sPenCompRecapDiffe',"+
				"'title':'单位实缴差额[养老]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':19,"+
				"'colE':19"+
				"},"+
				"{"+
				"'field':'sPenPersRecapDiffe',"+
				"'title':'个人实缴差额[养老]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':20,"+
				"'colE':20"+
				"},"+
				"{"+
				"'field':'sPenCompOverpaid',"+
				"'title':'单位多缴[养老]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':21,"+
				"'colE':21"+
				"},"+
				"{"+
				"'field':'sPenPersComple',"+
				"'title':'个人补缴[养老]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':22,"+
				"'colE':22"+
				"}"+
				//养老结束
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'医疗保险',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':23,"+
				"'colE':33,"+
				"'subs':["+
				//医疗开始
				"{"+
				"'field':'sMediBase',"+
				"'title':'基数[医疗]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':23,"+
				"'colE':23"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':24,"+
				"'colE':25,"+
				"'subs':["+
				"{"+
				"'field':'sMediCompRatio',"+
				"'title':'单位应缴比例[医疗]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':24,"+
				"'colE':24"+
				"},"+
				"{"+
				"'field':'sMediCompAmount',"+
				"'title':'单位应缴金额[医疗]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':25,"+
				"'colE':25"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':26,"+
				"'colE':27,"+
				"'subs':["+
				"{"+
				"'field':'sMediPersRatio',"+
				"'title':'个人应缴比例[医疗]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':26,"+
				"'colE':26"+
				"},"+
				"{"+
				"'field':'sMediPersAmount',"+
				"'title':'个人应缴金额[医疗]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':27,"+
				"'colE':27"+
				"}"+
				"]},"+
				"{"+
				"'field':'sMediCompRecapAmount',"+
				"'title':'单位实缴金额[医疗]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':28,"+
				"'colE':28"+
				"},"+
				"{"+
				"'field':'sMediPersRecapAmount',"+
				"'title':'个人实缴金额[医疗]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':29,"+
				"'colE':29"+
				"},"+
				"{"+
				"'field':'sMediCompRecapDiffe',"+
				"'title':'单位实缴差额[医疗]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':30,"+
				"'colE':30"+
				"},"+
				"{"+
				"'field':'sMediPersRecapDiffe',"+
				"'title':'个人实缴差额[医疗]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':31,"+
				"'colE':31"+
				"},"+
				"{"+
				"'field':'sMediCompOverpaid',"+
				"'title':'单位多缴[医疗]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':32,"+
				"'colE':32"+
				"},"+
				"{"+
				"'field':'sMediPersComple',"+
				"'title':'个人补缴[医疗]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':33,"+
				"'colE':33"+
				"}"+
				//医疗结束
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'失业保险',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':34,"+
				"'colE':44,"+
				"'subs':["+
				//失业开始
				"{"+
				"'field':'sUnempBase',"+
				"'title':'基数[失业]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':34,"+
				"'colE':34"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':35,"+
				"'colE':36,"+
				"'subs':["+
				"{"+
				"'field':'sUnempCompRatio',"+
				"'title':'单位应缴比例[失业]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':35,"+
				"'colE':35"+
				"},"+
				"{"+
				"'field':'sUnempCompAmount',"+
				"'title':'单位应缴金额[失业]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':36,"+
				"'colE':36"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':37,"+
				"'colE':38,"+
				"'subs':["+
				"{"+
				"'field':'sUnempPersRatio',"+
				"'title':'个人应缴比例[失业]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':37,"+
				"'colE':37"+
				"},"+
				"{"+
				"'field':'sUnempPersAmount',"+
				"'title':'个人应缴金额[失业]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':38,"+
				"'colE':38"+
				"}"+
				"]},"+
				"{"+
				"'field':'sUnempCompRecapAmount',"+
				"'title':'单位实缴金额[失业]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':39,"+
				"'colE':39"+
				"},"+
				"{"+
				"'field':'sUnempPersRecapAmount',"+
				"'title':'个人实缴金额[失业]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':40,"+
				"'colE':40"+
				"},"+
				"{"+
				"'field':'sUnempCompRecapDiffe',"+
				"'title':'单位实缴差额[失业]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':41,"+
				"'colE':41"+
				"},"+
				"{"+
				"'field':'sUnempPersRecapDiffe',"+
				"'title':'个人实缴差额[失业]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':42,"+
				"'colE':42"+
				"},"+
				"{"+
				"'field':'sUnempCompOverpaid',"+
				"'title':'单位多缴[失业]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':43,"+
				"'colE':43"+
				"},"+
				"{"+
				"'field':'sUnempPersComple',"+
				"'title':'个人补缴[失业]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':44,"+
				"'colE':44"+
				"}"+
				//失业结束
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'工伤保险',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':45,"+
				"'colE':55,"+
				"'subs':["+
				//工伤开始
				"{"+
				"'field':'sInjuryBase',"+
				"'title':'基数[工伤]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':45,"+
				"'colE':45"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':46,"+
				"'colE':47,"+
				"'subs':["+
				"{"+
				"'field':'sInjuryCompRatio',"+
				"'title':'单位应缴比例[工伤]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':46,"+
				"'colE':46"+
				"},"+
				"{"+
				"'field':'sInjuryCompAmount',"+
				"'title':'单位应缴金额[工伤]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':47,"+
				"'colE':47"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':48,"+
				"'colE':49,"+
				"'subs':["+
				"{"+
				"'field':'sInjuryPersRatio',"+
				"'title':'个人应缴比例[工伤]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':48,"+
				"'colE':48"+
				"},"+
				"{"+
				"'field':'sInjuryPersAmount',"+
				"'title':'个人应缴金额[工伤]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':49,"+
				"'colE':49"+
				"}"+
				"]},"+
				"{"+
				"'field':'sInjuryCompRecapAmount',"+
				"'title':'单位实缴金额[工伤]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':50,"+
				"'colE':50"+
				"},"+
				"{"+
				"'field':'sInjuryPersRecapAmount',"+
				"'title':'个人实缴金额[工伤]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':51,"+
				"'colE':51"+
				"},"+
				"{"+
				"'field':'sInjuryCompRecapDiffe',"+
				"'title':'单位实缴差额[工伤]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':52,"+
				"'colE':52"+
				"},"+
				"{"+
				"'field':'sInjuryPersRecapDiffe',"+
				"'title':'个人实缴差额[工伤]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':53,"+
				"'colE':53"+
				"},"+
				"{"+
				"'field':'sInjuryCompOverpaid',"+
				"'title':'单位多缴[工伤]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':54,"+
				"'colE':54"+
				"},"+
				"{"+
				"'field':'sInjuryPersComple',"+
				"'title':'个人补缴[工伤]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':55,"+
				"'colE':55"+
				"}"+
				//工伤结束
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'生育保险',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':56,"+
				"'colE':66,"+
				"'subs':["+
				//生育开始
				"{"+
				"'field':'sBirthBase',"+
				"'title':'基数[生育]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':56,"+
				"'colE':56"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':57,"+
				"'colE':58,"+
				"'subs':["+
				"{"+
				"'field':'sBirthCompRatio',"+
				"'title':'单位应缴比例[生育]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':57,"+
				"'colE':57"+
				"},"+
				"{"+
				"'field':'sBirthCompAmount',"+
				"'title':'单位应缴金额[生育]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':58,"+
				"'colE':58"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分[生育]',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':59,"+
				"'colE':60,"+
				"'subs':["+
				"{"+
				"'field':'sBirthPersRatio',"+
				"'title':'个人应缴比例[生育]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':59,"+
				"'colE':59"+
				"},"+
				"{"+
				"'field':'sBirthPersAmount',"+
				"'title':'个人应缴金额[生育]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':60,"+
				"'colE':60"+
				"}"+
				"]},"+
				"{"+
				"'field':'sBirthCompRecapAmount',"+
				"'title':'单位实缴金额[生育]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':61,"+
				"'colE':61"+
				"},"+
				"{"+
				"'field':'sBirthPersRecapAmount',"+
				"'title':'个人实缴金额[生育]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':62,"+
				"'colE':62"+
				"},"+
				"{"+
				"'field':'sBirthCompRecapDiffe',"+
				"'title':'单位实缴差额[生育]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':63,"+
				"'colE':63"+
				"},"+
				"{"+
				"'field':'sBirthPersRecapDiffe',"+
				"'title':'个人实缴差额[生育]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':64,"+
				"'colE':64"+
				"},"+
				"{"+
				"'field':'sBirthCompOverpaid',"+
				"'title':'单位多缴[生育]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':65,"+
				"'colE':65"+
				"},"+
				"{"+
				"'field':'sBirthPersComple',"+
				"'title':'个人补缴[生育]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':66,"+
				"'colE':66"+
				"}"+
				//生育结束
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'年金',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':67,"+
				"'colE':77,"+
				"'subs':["+
				//年金开始
				"{"+
				"'field':'sAnnuityBase',"+
				"'title':'基数[年金]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':67,"+
				"'colE':67"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':68,"+
				"'colE':69,"+
				"'subs':["+
				"{"+
				"'field':'sAnnuityCompRatio',"+
				"'title':'单位应缴比例[年金]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':68,"+
				"'colE':68"+
				"},"+
				"{"+
				"'field':'sAnnuityCompAmount',"+
				"'title':'单位应缴金额[年金]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':69,"+
				"'colE':69"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':70,"+
				"'colE':71,"+
				"'subs':["+
				"{"+
				"'field':'sAnnuityPersRatio',"+
				"'title':'个人应缴比例[年金]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':70,"+
				"'colE':70"+
				"},"+
				"{"+
				"'field':'sAnnuityPersAmount',"+
				"'title':'个人应缴金额[年金]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':71,"+
				"'colE':71"+
				"}"+
				"]},"+
				"{"+
				"'field':'sAnnuityCompRecapAmount',"+
				"'title':'单位实缴金额[年金]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':72,"+
				"'colE':72"+
				"},"+
				"{"+
				"'field':'sAnnuityPersRecapAmount',"+
				"'title':'个人实缴金额[年金]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':73,"+
				"'colE':73"+
				"},"+
				"{"+
				"'field':'sAnnuityCompRecapDiffe',"+
				"'title':'单位实缴差额[年金]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':74,"+
				"'colE':74"+
				"},"+
				"{"+
				"'field':'sAnnuityPersRecapDiffe',"+
				"'title':'个人实缴差额[年金]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':75,"+
				"'colE':75"+
				"},"+
				"{"+
				"'field':'sAnnuityCompOverpaid',"+
				"'title':'单位多缴[年金]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':76,"+
				"'colE':76"+
				"},"+
				"{"+
				"'field':'sAnnuityPersComple',"+
				"'title':'个人补缴[年金]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':77,"+
				"'colE':77"+
				"}"+
				//年金结束
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'省统筹',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':78,"+
				"'colE':88,"+
				"'subs':["+
				//省统筹开始
				"{"+
				"'field':'sOvpBase',"+
				"'title':'基数[统筹]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':78,"+
				"'colE':78"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':79,"+
				"'colE':80,"+
				"'subs':["+
				"{"+
				"'field':'sOvpCompRatio',"+
				"'title':'单位应缴比例[统筹]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':79,"+
				"'colE':79"+
				"},"+
				"{"+
				"'field':'sOvpCompAmount',"+
				"'title':'单位应缴金额[统筹]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':80,"+
				"'colE':80"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':81,"+
				"'colE':82,"+
				"'subs':["+
				"{"+
				"'field':'sOvpPersRatio',"+
				"'title':'个人应缴比例[统筹]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':81,"+
				"'colE':81"+
				"},"+
				"{"+
				"'field':'sOvpPersAmount',"+
				"'title':'个人应缴金额[统筹]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':82,"+
				"'colE':82"+
				"}"+
				"]},"+
				"{"+
				"'field':'sOvpCompRecapAmount',"+
				"'title':'单位实缴金额[统筹]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':83,"+
				"'colE':83"+
				"},"+
				"{"+
				"'field':'sOvpPersRecapAmount',"+
				"'title':'个人实缴金额[统筹]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':84,"+
				"'colE':84"+
				"},"+
				"{"+
				"'field':'sOvpCompRecapDiffe',"+
				"'title':'单位实缴差额[统筹]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':85,"+
				"'colE':85"+
				"},"+
				"{"+
				"'field':'sOvpPersRecapDiffe',"+
				"'title':'个人实缴差额[统筹]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':86,"+
				"'colE':86"+
				"},"+
				"{"+
				"'field':'sOvpCompOverpaid',"+
				"'title':'单位多缴[统筹]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':87,"+
				"'colE':87"+
				"},"+
				"{"+
				"'field':'sOvpPersComple',"+
				"'title':'个人补缴[统筹]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':88,"+
				"'colE':88"+
				"}"+
				//省统筹结束
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'补充医疗',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':89,"+
				"'colE':99,"+
				"'subs':["+
				//补充医疗开始
				"{"+
				"'field':'sSpMediBase',"+
				"'title':'基数[补充]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':89,"+
				"'colE':89"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':90,"+
				"'colE':91,"+
				"'subs':["+
				"{"+
				"'field':'sSpMediCompRatio',"+
				"'title':'单位应缴比例[补充]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':90,"+
				"'colE':90"+
				"},"+
				"{"+
				"'field':'sSpMediCompAmount',"+
				"'title':'单位应缴金额[补充]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':91,"+
				"'colE':91"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人应缴部分',"+
				"'rowS':2,"+
				"'rowE':2,"+
				"'colS':92,"+
				"'colE':93,"+
				"'subs':["+
				"{"+
				"'field':'sSpMediPersRatio',"+
				"'title':'个人应缴比例[补充]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':92,"+
				"'colE':92"+
				"},"+
				"{"+
				"'field':'sSpMediPersAmount',"+
				"'title':'个人应缴金额[补充]',"+
				"'rowS':3,"+
				"'rowE':3,"+
				"'colS':93,"+
				"'colE':93"+
				"}"+
				"]},"+
				"{"+
				"'field':'sSpMediCompRecapAmount',"+
				"'title':'单位实缴金额[补充]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':94,"+
				"'colE':94"+
				"},"+
				"{"+
				"'field':'sSpMediPersRecapAmount',"+
				"'title':'个人实缴金额[补充]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':95,"+
				"'colE':95"+
				"},"+
				"{"+
				"'field':'sSpMediCompRecapDiffe',"+
				"'title':'单位实缴差额[补充]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':96,"+
				"'colE':96"+
				"},"+
				"{"+
				"'field':'sSpMediPersRecapDiffe',"+
				"'title':'个人实缴差额[补充]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':97,"+
				"'colE':97"+
				"},"+
				"{"+
				"'field':'sSpMediCompOverpaid',"+
				"'title':'单位多缴[补充]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':98,"+
				"'colE':98"+
				"},"+
				"{"+
				"'field':'sSpMediPersComple',"+
				"'title':'个人补缴[补充]',"+
				"'rowS':2,"+
				"'rowE':3,"+
				"'colS':99,"+
				"'colE':99"+
				"}"+
				//补充医疗结束
				"]},"+
				"{"+
				"'field':'remark4month',"+
				"'title':'月备注',"+
				"'rowS':1,"+
				"'rowE':3,"+
				"'colS':100,"+
				"'colE':100"+
				"}"+
				"]},"+
				"{"+
				"'field':'remark4year',"+
				"'title':'年备注',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':101,"+
				"'colE':101"+
				"},"+
				"{"+
				"'field':'persRecapDiffe4addup',"+
				"'title':'本年个人实缴累计差额',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':102,"+
				"'colE':102"+
				"},"+
				"{"+
				"'field':'persCompleDiffe4addup',"+
				"'title':'本年个人补缴累计差额',"+
				"'rowS':0,"+
				"'rowE':3,"+
				"'colS':103,"+
				"'colE':103"+
				"}"+
				"]"+
				"}";
		System.out.println(head1);
		return JSONObject.parseObject(head1);
	}
	
	/**
	 * 生成补缴单表头
	 * @param months
	 * @return
	 */
	public static JSONObject createSuppleHead() {
		String head1 = 
				"{"+
				"'hTitle':'中央医院-社保补缴单',"+
				"'heads':["+
				"{"+
				"'field':'name',"+
				"'title':'名称',"+
				"'rowS':0,"+
				"'rowE':1,"+
				"'colS':0,"+
				"'colE':0"+
				"},"+
				"{"+
				"'field':'idcard',"+
				"'title':'身份证号',"+
				"'rowS':0,"+
				"'rowE':1,"+
				"'colS':1,"+
				"'colE':1"+
				"},"+
				"{"+
				"'field':'social_ymonth',"+
				"'title':'补缴时间',"+
				"'rowS':0,"+
				"'rowE':1,"+
				"'colS':2,"+
				"'colE':2"+
				"},"+
				"{"+
				"'field':'msize',"+
				"'title':'月数',"+
				"'rowS':0,"+
				"'rowE':1,"+
				"'colS':3,"+
				"'colE':3"+
				"},"+
				"{"+
				"'field':'base',"+
				"'title':'基数',"+
				"'rowS':0,"+
				"'rowE':1,"+
				"'colS':4,"+
				"'colE':4"+
				"},"+
				"{"+
				"'field':'',"+
				"'title':'单位部分',"+
				"'rowS':0,"+
				"'rowE':0,"+
				"'colS':5,"+
				"'colE':14,"+
				"'subs':["+
				"{"+
				"'field':'c_pen',"+
				"'title':'养老[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':5,"+
				"'colE':5"+
				"},"+
				"{"+
				"'field':'c_medi',"+
				"'title':'医疗[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':6,"+
				"'colE':6"+
				"},"+
				"{"+
				"'field':'c_unemp',"+
				"'title':'失业[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':7,"+
				"'colE':7"+
				"},"+
				"{"+
				"'field':'c_injury',"+
				"'title':'工伤[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':8,"+
				"'colE':8"+
				"},"+
				"{"+
				"'field':'c_birth',"+
				"'title':'生育[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':9,"+
				"'colE':9"+
				"},"+
				"{"+
				"'field':'c_annuity',"+
				"'title':'年金[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':10,"+
				"'colE':10"+
				"},"+
				"{"+
				"'field':'c_ovp',"+
				"'title':'省统筹[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':11,"+
				"'colE':11"+
				"},"+
				"{"+
				"'field':'c_spmedi',"+
				"'title':'补充医疗[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':12,"+
				"'colE':12"+
				"},"+
				"{"+
				"'field':'c_serious',"+
				"'title':'大病[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':13,"+
				"'colE':13"+
				"},"+
				"{"+
				"'field':'sumb',"+
				"'title':'小计[单位]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':14,"+
				"'colE':14"+
				"}"+
				"]},"+
				"{"+
				"'field':'',"+
				"'title':'个人部分',"+
				"'rowS':0,"+
				"'rowE':0,"+
				"'colS':15,"+
				"'colE':24,"+
				"'subs':["+
				"{"+
				"'field':'p_pen',"+
				"'title':'养老[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':15,"+
				"'colE':15"+
				"},"+
				"{"+
				"'field':'p_medi',"+
				"'title':'医疗[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':16,"+
				"'colE':16"+
				"},"+
				"{"+
				"'field':'p_unemp',"+
				"'title':'失业[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':17,"+
				"'colE':17"+
				"},"+
				"{"+
				"'field':'p_injury',"+
				"'title':'工伤[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':18,"+
				"'colE':18"+
				"},"+
				"{"+
				"'field':'p_birth',"+
				"'title':'生育[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':19,"+
				"'colE':19"+
				"},"+
				"{"+
				"'field':'p_annuity',"+
				"'title':'年金[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':20,"+
				"'colE':20"+
				"},"+
				"{"+
				"'field':'p_ovp',"+
				"'title':'省统筹[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':21,"+
				"'colE':21"+
				"},"+
				"{"+
				"'field':'p_spmedi',"+
				"'title':'补充医疗[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':22,"+
				"'colE':22"+
				"},"+
				"{"+
				"'field':'p_serious',"+
				"'title':'大病[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':23,"+
				"'colE':23"+
				"},"+
				"{"+
				"'field':'suma',"+
				"'title':'小计[个人]',"+
				"'rowS':1,"+
				"'rowE':1,"+
				"'colS':24,"+
				"'colE':24"+
				"}"+
				"]},"+
				"{"+
				"'field':'total',"+
				"'title':'合计',"+
				"'rowS':0,"+
				"'rowE':1,"+
				"'colS':25,"+
				"'colE':25"+
				"},"+
				"{"+
				"'field':'remark_4month',"+
				"'title':'月备注',"+
				"'rowS':0,"+
				"'rowE':1,"+
				"'colS':26,"+
				"'colE':26"+
				"}"+
				"]}";
		return JSONObject.parseObject(head1);
	}
}