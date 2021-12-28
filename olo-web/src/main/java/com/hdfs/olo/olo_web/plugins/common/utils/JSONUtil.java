package com.hdfs.olo.olo_web.plugins.common.utils;

import java.util.Iterator;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

	/**
	 * 获取JSON对象中的JSON层级
	 * @param temp
	 * @param count
	 * @return
	 */
	public static int getLength(JSONObject jobj,Integer count){
		count = null == count?0:count;
        Boolean flag=true;
        Iterator iteratorValue=jobj.values().iterator();
        Iterator iteratorKey=jobj.keySet().iterator();
        while (iteratorValue.hasNext()){
            String value=iteratorValue.next().toString();
            System.out.println(iteratorKey.next()+":"+value);
            if (value.startsWith("{")){
                if (flag) {  //单层只记录一次
                    count++;
                    flag=false;
                }
                int tmp=getLength(JSONObject.parseObject(value),count);
                if (tmp>count)
                    count=tmp;
            }
        }
        return count;
    }
	
	public static void main(String args[])
	{
		String tmp = "{'hTitle':'中央医院-工资单','heads':[{'field':'序号','title':'序号','rowS':0,'rowE':2,'colS':0,'colE':0},{'field':'wagesId','title':'人员编号','rowS':0,'rowE':2,'colS':1,'colE':1},{'field':'name','title':'职工姓名','rowS':0,'rowE':2,'colS':2,'colE':2},{'field':'cardNo','title':'身份证号','rowS':0,'rowE':2,'colS':3,'colE':3},{'field':'bankCardNo','title':'银行卡号','rowS':0,'rowE':2,'colS':4,'colE':4},{'field':'mobileNo','title':'手机号','rowS':0,'rowE':2,'colS':5,'colE':5},{'field':'positCn','title':'职务','rowS':0,'rowE':2,'colS':6,'colE':6},{'field':'hosDepart1levelCn','title':'部门','rowS':0,'rowE':2,'colS':7,'colE':7},{'field':'wkModalityCn','title':'人员类别','rowS':0,'rowE':2,'colS':8,'colE':8},{'field':'工数','title':'工数','rowS':0,'rowE':0,'colS':9,'colE':14,'subs':[{'field':'manwkStandard','title':'标准工数','rowS':1,'rowE':2,'colS':9,'colE':9},{'field':'manwkAttend','title':'出勤工数','rowS':1,'rowE':2,'colS':10,'colE':10},{'field':'manwkMiner','title':'旷工工数','rowS':1,'rowE':2,'colS':11,'colE':11},{'field':'manwkSick','title':'病假工数','rowS':1,'rowE':2,'colS':12,'colE':12},{'field':'manwkMaternity','title':'产假工数','rowS':1,'rowE':2,'colS':13,'colE':13},{'field':'manwkPrivpassion','title':'事假工数','rowS':1,'rowE':2,'colS':14,'colE':14}]},{'field':'应发工资','title':'应发工资','rowS':0,'rowE':0,'colS':15,'colE':42,'subs':[{	'field':'wageStandard4posit',	'title':'岗位工资标准',	'rowS':1,	'rowE':2,	'colS':15,	'colE':15},{	'field':'wageDay4posit',	'title':'岗位日工资',	'rowS':1,	'rowE':2,	'colS':16,	'colE':16},{	'field':'岗位工资',	'title':'岗位工资',	'rowS':1,	'rowE':1,	'colS':17,	'colE':19,	'subs':[{	'field':'wageAttend',	'title':'出勤工资',	'rowS':2,	'rowE':2,	'colS':17,	'colE':17},{	'field':'wageSick',	'title':'病假工资',	'rowS':2,	'rowE':2,	'colS':18,	'colE':18},{	'field':'wagePositTotal',	'title':'岗位工资合计',	'rowS':2,	'rowE':2,	'colS':19,	'colE':19}	]},{	'field':'wageNightShift',	'title':'夜班费用',	'rowS':1,	'rowE':2,	'colS':20,	'colE':20},{	'field':'加班工资',	'title':'加班工资',	'rowS':1,	'rowE':2,	'colS':21,	'colE':22,	'subs':[{	'field':'manwkOvertime',	'title':'加班工数',	'rowS':2,	'rowE':2,	'colS':21,	'colE':21},	{	'field':'wageOvertime',	'title':'加班工资',	'rowS':2,	'rowE':2,	'colS':22,	'colE':22}	]},{	'field':'wageYearg',	'title':'年功工资',	'rowS':1,	'rowE':2,	'colS':23,	'colE':23},{	'field':'allowanceHygiene',	'title':'卫生津贴',	'rowS':1,	'rowE':2,	'colS':24,	'colE':24},{	'field':'wageErrorCorrent',	'title':'纠错工资',	'rowS':1,	'rowE':2,	'colS':25,	'colE':25},{	'field':'supp4tel',	'title':'电补',	'rowS':1,	'rowE':2,	'colS':26,	'colE':26},{	'field':'supp4oth',	'title':'其他',	'rowS':1,	'rowE':2,	'colS':27,	'colE':27},{	'field':'wageTotal',	'title':'工资合计',	'rowS':1,	'rowE':2,	'colS':28,	'colE':28},{	'field':'绩效工资',	'title':'绩效工资',	'rowS':1,	'rowE':1,	'colS':29,	'colE':41,	'subs':[{	'field':'wageJxComb',	'title':'经合办绩效',	'rowS':2,	'rowE':2,	'colS':29,	'colE':29},	{	'field':'wageJxComb1',	'title':'经合办绩效1',	'rowS':2,	'rowE':2,	'colS':30,	'colE':30},{	'field':'wageJxComb2',	'title':'经合办绩效2',	'rowS':2,	'rowE':2,	'colS':31,	'colE':31},{	'field':'wageJxSubstrate',	'title':'基层分院绩效',	'rowS':2,	'rowE':2,	'colS':32,	'colE':32},{	'field':'wageJxAircon',	'title':'空调班绩效',	'rowS':2,	'rowE':2,	'colS':33,	'colE':33},{	'field':'wageJxRadiology',	'title':'放射科介入',	'rowS':2,	'rowE':2,	'colS':34,	'colE':34},{	'field':'wageJxOperatroom',	'title':'手术室介入',	'rowS':2,	'rowE':2,	'colS':35,	'colE':35},{	'field':'wageJxYearlySalary',	'title':'年薪制人员绩效',	'rowS':2,	'rowE':2,	'colS':36,	'colE':36},{	'field':'supp4toCountryside',	'title':'分级诊疗办公室下乡补助',	'rowS':2,	'rowE':2,	'colS':37,	'colE':37},{	'field':'reward1',	'title':'奖励1',	'rowS':2,	'rowE':2,	'colS':38,	'colE':38},{	'field':'reward2',	'title':'奖励2',	'rowS':2,	'rowE':2,	'colS':39,	'colE':39},{	'field':'wageJxSanatorium',	'title':'疗养院绩效',	'rowS':2,	'rowE':2,	'colS':40,	'colE':40},{	'field':'wageJxTotal',	'title':'绩效工资合计',	'rowS':2,	'rowE':2,	'colS':41,	'colE':41}	]},{'field':'wagePayableTotal','title':'应发工资合计','rowS':1,'rowE':2,'colS':42,'colE':42}]},{'field':'扣款','title':'扣款','rowS':0,'rowE':0,'colS':43,'colE':53,'subs':[{'field':'社会保险','title':'社会保险','rowS':1,'rowE':1,'colS':43,'colE':49,'subs':[{	'field':'bxPension',	'title':'养老保险',	'rowS':2,	'rowE':2,	'colS':43,	'colE':43},	{	'field':'bxMedical',	'title':'医疗保险',	'rowS':2,	'rowE':2,	'colS':44,	'colE':44},{	'field':'bxUnemploy',	'title':'失业保险',	'rowS':2,	'rowE':2,	'colS':45,	'colE':45},{	'field':'bxSeriousIllness',	'title':'大病保险',	'rowS':2,	'rowE':2,	'colS':46,	'colE':46},{	'field':'bxHousfund',	'title':'住房公积金',	'rowS':2,	'rowE':2,	'colS':47,	'colE':47},{	'field':'bxAnnualCorpGold',	'title':'年企业金',	'rowS':2,	'rowE':2,	'colS':48,	'colE':48},{	'field':'bxTotal',	'title':'保险合计',	'rowS':2,	'rowE':2,	'colS':49,	'colE':49}	]},{'field':'cutWater2elect','title':'水电费','rowS':1,'rowE':2,'colS':50,'colE':50},{'field':'cutHygiene','title':'卫生费','rowS':1,'rowE':2,'colS':51,'colE':51},{'field':'cutArrears','title':'职工欠款','rowS':1,'rowE':2,'colS':52,'colE':52},{'field':'cutTotal','title':'扣款合计','rowS':1,'rowE':2,'colS':53,'colE':53}]},{'field':'taxableWage','title':'计税工资','rowS':0,'rowE':2,'colS':54,'colE':54},{'field':'taxIncomePersonal','title':'个税','rowS':0,'rowE':2,'colS':55,'colE':55},{'field':'netSalary','title':'实发工资','rowS':0,'rowE':2,'colS':56,'colE':56}{'field':'netTargetYearmonth','title':'工资月份','rowS':0,'rowE':2,'colS':57,'colE':57},{'field':'createTime','title':'导入时间','rowS':0,'rowE':2,'colS':58,'colE':58}]}";
		Integer count = JSONUtil.getLength(JSONObject.parseObject(tmp),null);
		System.out.println(count);
	}
}
