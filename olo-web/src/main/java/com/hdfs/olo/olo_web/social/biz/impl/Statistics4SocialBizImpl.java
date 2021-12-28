package com.hdfs.olo.olo_web.social.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.MoneyConvert;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuTHeadItem;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.social.biz.IStatistics4SocialBiz;
import com.hdfs.olo.olo_web.social.mapper.Statistics4SocialMapper;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import cn.hutool.poi.excel.WorkbookUtil;

@DataSource("social")
@Service("statistics4SocialBiz")
public class Statistics4SocialBizImpl implements IStatistics4SocialBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private Statistics4SocialMapper statistics4SocialMapper;
	@Autowired
	private IHuTCommonService huTCommonService;
	
	/**
	 * 养老保险按月汇总
	 * @param start
	 * @param end
	 * @param type  100:养老  101：医疗  102：失业  103：工伤
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_10x(String start,String end,int type) throws Exception{
		if(100 != type && 101 != type && 102 != type && 103 != type)
			throw new Exception("社保汇总：未指定请求汇总类型或汇总类型不在有效范围内！");
		
		if(StringHelper.isNullOrEmpty(start) && StringHelper.isNullOrEmpty(end)) 
			throw new Exception(100!=type?101!=type?102!=type?"工伤":"失业":"医疗":"养老"
					+"保险按月汇总,缺少日期区间！");
		List<Map<String,Object>> datas = null;
		switch(type)
		{
		case 100://养老
			datas = statistics4SocialMapper.st4pen(start,end);
			break;
		case 101://医疗
			datas = statistics4SocialMapper.st4medi(start,end);
			break;
		case 102://失业
			datas = statistics4SocialMapper.st4unemp(start,end);
			break;
		case 103://工伤
			datas = statistics4SocialMapper.st4injury(start,end);
			break;
		}
		if(null == datas || datas.size() == 0) return new ArrayList<Map<String,Object>>();
		String socialYmonth = null;
		//累计征缴
		BigDecimal amount4AddUp = null;
		BigDecimal amount4AddUpLaohe = null;
		BigDecimal amount4AddUpLaoheComp = null;
		BigDecimal amount4AddUpLaowu = null;
		BigDecimal amount4AddUpLaowuComp = null;
		BigDecimal amount4AddUpRecap = null;
		BigDecimal amount4AddUpRecapLaohe = null;
		BigDecimal amount4AddUpRecapLaowu = null;
		//累计征缴小计
		BigDecimal amount4AddUpTotal = null;
		BigDecimal amount4AddUpLaoheTotal = null;
		BigDecimal amount4AddUpLaoheCompTotal = null;
		BigDecimal amount4AddUpLaowuTotal = null;
		BigDecimal amount4AddUpLaowuCompTotal = null;
		BigDecimal amount4AddUpRecapTotal = null;
		BigDecimal amount4AddUpRecapLaoheTotal = null;
		BigDecimal amount4AddUpRecapLaowuTotal = null;
		Map<String,Object> item = null;
		for(int i=0;i<datas.size();i++)
		{
			item = datas.get(i);
			socialYmonth = (String)item.get("social_ymonth");
			if("TOTAL".equals(socialYmonth)) {
				item.put("amount_addup", amount4AddUpTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("amount_addup_laohe", amount4AddUpLaoheTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("amount_addup_laohe_comp", amount4AddUpLaoheCompTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("amount_addup_laowu", amount4AddUpLaowuTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("amount_addup_laowu_comp", amount4AddUpLaowuCompTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("amount_addup_recap", amount4AddUpRecapTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("amount_addup_recap_laohe", amount4AddUpRecapLaoheTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("amount_addup_recap_laowu", amount4AddUpRecapLaowuTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
				item.put("rate_addup", "100%");
				item.put("social_ymonth", "合计");
				break;
			}
			//累计征缴
			amount4AddUp =(null == amount4AddUp?new BigDecimal(0):amount4AddUp).add((BigDecimal)item.get("amount"));
			amount4AddUpLaohe = (null == amount4AddUpLaohe?new BigDecimal(0):amount4AddUpLaohe).add((BigDecimal)item.get("amount_laohe"));
			amount4AddUpLaoheComp = (null == amount4AddUpLaoheComp?new BigDecimal(0):amount4AddUpLaoheComp).add((BigDecimal)item.get("amount_laohe_comp"));
			amount4AddUpLaowu = (null == amount4AddUpLaowu?new BigDecimal(0):amount4AddUpLaowu).add((BigDecimal)item.get("amount_laowu"));
			amount4AddUpLaowuComp = (null == amount4AddUpLaowuComp?new BigDecimal(0):amount4AddUpLaowuComp).add((BigDecimal)item.get("amount_laowu_comp"));
			amount4AddUpRecap = (null == amount4AddUpRecap?new BigDecimal(0):amount4AddUpRecap).add(new BigDecimal(String.valueOf(item.get("amount_recap"))));
			amount4AddUpRecapLaohe = (null == amount4AddUpRecapLaohe?new BigDecimal(0):amount4AddUpRecapLaohe).add(new BigDecimal(String.valueOf(item.get("amount_recap_laohe"))));
			amount4AddUpRecapLaowu = (null == amount4AddUpRecapLaowu?new BigDecimal(0):amount4AddUpRecapLaowu).add(new BigDecimal(String.valueOf(item.get("amount_recap_laowu"))));
			
			item.put("amount_addup", amount4AddUp.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("amount_addup_laohe", amount4AddUpLaohe.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("amount_addup_laohe_comp", amount4AddUpLaoheComp.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("amount_addup_laowu", amount4AddUpLaowu.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("amount_addup_laowu_comp", amount4AddUpLaowuComp.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("amount_addup_recap", amount4AddUpRecap.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("amount_addup_recap_laohe", amount4AddUpRecapLaohe.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("amount_addup_recap_laowu", amount4AddUpRecapLaowu.setScale(2, BigDecimal.ROUND_HALF_UP));
			item.put("rate_addup", "100%");
			
			//累计征缴小计
			amount4AddUpTotal =(null == amount4AddUpTotal?new BigDecimal(0):amount4AddUpTotal).add(amount4AddUp);
			amount4AddUpLaoheTotal = (null == amount4AddUpLaoheTotal?new BigDecimal(0):amount4AddUpLaoheTotal).add(amount4AddUpLaohe);
			amount4AddUpLaoheCompTotal = (null == amount4AddUpLaoheCompTotal?new BigDecimal(0):amount4AddUpLaoheCompTotal).add(amount4AddUpLaoheComp);
			amount4AddUpLaowuTotal = (null == amount4AddUpLaowuTotal?new BigDecimal(0):amount4AddUpLaowuTotal).add(amount4AddUpLaowu);
			amount4AddUpLaowuCompTotal = (null == amount4AddUpLaowuCompTotal?new BigDecimal(0):amount4AddUpLaowuCompTotal).add(amount4AddUpLaowuComp);
			amount4AddUpRecapTotal = (null == amount4AddUpRecapTotal?new BigDecimal(0):amount4AddUpRecapTotal).add(amount4AddUpRecap);
			amount4AddUpRecapLaoheTotal = (null == amount4AddUpRecapLaoheTotal?new BigDecimal(0):amount4AddUpRecapLaoheTotal).add(amount4AddUpRecapLaohe);
			amount4AddUpRecapLaowuTotal = (null == amount4AddUpRecapLaowuTotal?new BigDecimal(0):amount4AddUpRecapLaowuTotal).add(amount4AddUpRecapLaowu);
		}
		return datas;
	}
	
	/**
	 * 养老保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_100(String start,String end) throws Exception{
		return load4_10x(start,end,100);
	}
	/**
	 * 医疗保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_101(String start,String end) throws Exception{
		return load4_10x(start,end,101);
	}
	
	/**
	 * 失业保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_102(String start,String end) throws Exception{
		return load4_10x(start,end,102);
	}
	
	/**
	 * 工伤保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_103(String start,String end) throws Exception{
		return load4_10x(start,end,103);
	}
	
	private static final String[] array104K;
	private static final String[] array104V;
	private static final Map<Integer,String> spanMap;
	static {
		String str = "size,base,comp,comp_recap,pers,pers_recap,serious,total,"
				+ "size_formal,base_formal,comp_formal,pers_formal,serious_formal,total_formal,"
				+ "size_formalz,base_formalz,comp_formalz,pers_formalz,serious_formalz,total_formalz,"
				+ "size_formalj,base_formalj,comp_formalj,pers_formalj,serious_formalj,total_formalj,"
				+ "size_formal1,base_formal1,comp_formal1,pers_formal1,serious_formal1,total_formal1,"
				+ "size_formal1z,base_formal1z,comp_formal1z,pers_formal1z,serious_formal1z,total_formal1z,"
				+ "size_formal1j,base_formal1j,comp_formal1j,pers_formal1j,serious_formal1j,total_formal1j,"
				+ "size_qiri,base_qiri,comp_qiri,pers_qiri,serious_qiri,total_qiri,"
				+ "size_qirix,base_qirix,comp_qirix,pers_qirix,serious_qirix,total_qirix,"
				+ "size_qiriz,base_qiriz,comp_qiriz,pers_qiriz,serious_qiriz,total_qiriz,"
				+ "size_qirij,base_qirij,comp_qirij,pers_qirij,serious_qirij,total_qirij,"
				+ "size_qiri1x,base_qiri1x,comp_qiri1x,pers_qiri1x,serious_qiri1x,total_qiri1x,"
				+ "size_qiri1z,base_qiri1z,comp_qiri1z,pers_qiri1z,serious_qiri1z,total_qiri1z,"
				+ "size_qiri1j,base_qiri1j,comp_qiri1j,pers_qiri1j,serious_qiri1j,total_qiri1j,"
				+ "size_zhongx,base_zhongx,comp_zhongx,pers_zhongx,serious_zhongx,total_zhongx,"
				+ "size_zhongxz,base_zhongxz,comp_zhongxz,pers_zhongxz,serious_zhongxz,total_zhongxz,"
				+ "size_zhongxj,base_zhongxj,comp_zhongxj,pers_zhongxj,serious_zhongxj,total_zhongxj,"
				+ "size_jcht,base_jcht,comp_jcht,pers_jcht,serious_jcht,total_jcht,"
				+ "size_jchtzx,base_jchtzx,comp_jchtzx,pers_jchtzx,serious_jchtzx,total_jchtzx,"
				+ "size_jchtqr,base_jchtqr,comp_jchtqr,pers_jchtqr,serious_jchtqr,total_jchtqr";
		String str2 = "人数,缴费基数,单位应缴金额,单位实缴金额,个人应缴金额,个人实缴金额,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计,"
				+ "人数,缴费基数,单位缴纳,个人缴纳,大病保险,小计";
		array104K = str.split(",");
		array104V = str2.split(",");
		//spanMap = new HashMap<Integer,String>();
		spanMap = new TreeMap<Integer, String>(new MapKeyComparator());
		spanMap.put(0, "总计");
		spanMap.put(8, "正式");
		spanMap.put(14, "总院正式");
		spanMap.put(20, "基层正式");
		spanMap.put(26, "正式1");
		spanMap.put(32, "总院正式1");
		spanMap.put(38, "基层正式1");
		spanMap.put(44, "启日合计");
		spanMap.put(50, "启日小计");
		spanMap.put(56, "总院启日");
		spanMap.put(62, "基层启日");
		spanMap.put(68, "启日1小计");
		spanMap.put(74, "总院启日1");
		spanMap.put(80, "基层启日1");
		spanMap.put(86, "众鑫合计");
		spanMap.put(92, "总院众鑫");
		spanMap.put(98, "基层众鑫");
		spanMap.put(104, "基层合同合计");
		spanMap.put(110, "基层合同众鑫");
		spanMap.put(116, "基层合同启日");
	}
	
	static class MapKeyComparator implements Comparator<Integer>{
	    @Override
	    public int compare(Integer str1, Integer str2) {
	        return str1.compareTo(str2);
	    }
	}
	/**
	 * 用工形式应缴汇总
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_104(ChSocialInfoExtModel model) throws Exception{
		if(null == model || (StringHelper.isNullOrEmpty(model.getStartYear())))
			throw new Exception("统计年份为空!");
		
		List<Map<String,Object>> datas = statistics4SocialMapper.st4Modality(model);
		if(null == datas || datas.size()<=0) return new ArrayList<>();
		List<Map<String,Object>> retDatas = new ArrayList<Map<String,Object>>();
		Map<String,Object> kvMap = new HashMap<String,Object>();
		//行数
		Integer rowsnum = null;
		for(Map<String,Object> item:datas)
		{
			kvMap.put(String.valueOf(item.get("social_ymonth")), item);
			if(null == rowsnum)rowsnum = item.size()-1;
		}
		//初始化行数据
		String modality = null;//填充用工形式
		for(int i=0;i<rowsnum;i++) {
			Map<String,Object> item = new HashMap<String,Object>();
			if(spanMap.containsKey(i))modality = spanMap.get(i);
			item.put("modality", modality);
			retDatas.add(i, item);
		}
		//封装每一行的数据
		for(int i=0;i<array104K.length;i++) {
			retDatas.get(i).put("stkey", array104V[i]);
			//for(int j=0;j<rowsnum;j++) {
				Map<String,Object> item = null;
				String ymonth = null;
				for(int k=1;k<=12;k++) {
					ymonth = model.getStartYear()+"-"+(k<10?("0"+k):k);
					item = (Map<String,Object>)kvMap.get(ymonth);
					if(null == item) {
						retDatas.get(i).put(ymonth, "-");
					}else {
						retDatas.get(i).put(ymonth, cvt2String(item.get(array104K[i])));
					}
				}
			//}
		}
		return retDatas;
	}
	
	private String cvt2String(Object val) {
		if(val instanceof BigDecimal)
			return ((BigDecimal)val).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		else if(val instanceof Long)
			return ((Long)val).longValue()+"";
		else 
			return String.valueOf(val);
	}
	
	
	/**
	 * 用工形式应缴汇总导出
	 * @param response
	 * @param title
	 * @param datas
	 * @param head
	 * @throws Exception
	 */
	@Override
	public void export104(HttpServletResponse response,String title,List<Map<String,Object>> datas,HuToolHead head)throws Exception{
		Workbook wb = WorkbookUtil.createBook(true);//(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
		Sheet sheet = WorkbookUtil.getOrCreateSheet(wb, "用工形式应缴汇总");
		ExcelWriter bigWriter = new BigExcelWriter(sheet);
        //bigWriter.writeHeadRow(new LinkedList());
        
        List<HuTHeadItem> heads = head.getHeads();
        int lastCol = huTCommonService.addHeadAlias(bigWriter,heads,-1);
		
        //根据需要合并添加第一列表头
        int titleNeedRowNum = 1;
        bigWriter.merge(0, 0, 0, lastCol, head.gethTitle(), true);
        
        //去除数据中的用工形式数据
        for(Map<String,Object> item:datas) {
        	item.put("modality","");
        }
        
        Integer headRank = huTCommonService.writeHead(bigWriter,heads,titleNeedRowNum,null);
        if(null == headRank || headRank<=0) throw new Exception("表头信息异常!");
        //暂时先固定表头层级深度
        //if(headRank>3) headRank = 3;
        // 只导出配置好的列名
        bigWriter.setCurrentRow((headRank)+titleNeedRowNum);
        bigWriter.setOnlyAlias(true);
        bigWriter.write(datas);
        
        //写入行合并（左侧导航）
        Integer startIndex = null;
        String startContent = null;
        int indx = 0;
        for(Integer key:spanMap.keySet())
        {
        	indx ++;
        	if(null == startIndex) {
        		startIndex = key;
        		startContent = spanMap.get(key);
        		continue;
        	}
        	bigWriter.merge(startIndex+2, key+2-1, 0, 0, startContent, false);
        	startIndex = key;
        	startContent = spanMap.get(key);
        	if(indx<spanMap.size()) continue;
        	bigWriter.merge(startIndex+2, datas.size()+1, 0, 0, startContent, false);
        }
        
        
        StyleSet styleSet = bigWriter.getStyleSet();
        styleSet.setWrapText();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setHeader("filename", URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(title+".xlsx", "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        bigWriter.flush(out, true);
        bigWriter.close();
        IoUtil.close(out);
	}
	
	
	/**
	 * 社保参保人员月统计
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_200(String start,String end)throws Exception
	{
		if(StringHelper.isNullOrEmpty(start) && StringHelper.isNullOrEmpty(end))
			throw new Exception("统计区间为空!");
		List<Map<String,Object>> datas = statistics4SocialMapper.st4Insured(start, end);
		if(null == datas || datas.size()<=0) return new ArrayList<>();
		boolean isExistStart = StringHelper.isNullOrEmpty(start)?false:true;
		Map<String,Object> item = null;
		Long size4Laohe = null;
		Long size4Laowu = null;
		Long size4Retire = null;
		Long sizeTotal = null;
		String socialYmonth = null;
		
		String ymonth = null;
		if(isExistStart) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateTimeHelper.strToDate(start, "yyyy-MM"));
			//calendar.add(Calendar.MONTH, -1);//calendar在获取月份时，本身就少了一个月
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			month = month == 0?12:month;
			ymonth = year+"-"+(month<10?"0"+month:month);
		}
		
		Iterator<Map<String,Object>> it = datas.iterator();
		while(it.hasNext()) {
			item = it.next();
			socialYmonth = (String)item.get("social_ymonth");
			//设置期初数据
			
			item.put("size_laohe_bf", null!=size4Laohe?size4Laohe:"-");
			item.put("size_laowu_bf", null!=size4Laowu?size4Laowu:"-");
			item.put("size_retire_bf", null!=size4Retire?size4Retire:"-");
			item.put("size_total_bf", null!=sizeTotal?sizeTotal:"-");
			
			size4Laohe = Long.valueOf(String.valueOf(item.get("size_laohe")));
			size4Laowu = Long.valueOf(String.valueOf(item.get("size_laowu")));
			size4Retire = Long.valueOf(String.valueOf(item.get("size_retire")));
			sizeTotal = new BigDecimal(String.valueOf(item.get("size_total"))).longValue();
			
			if(isExistStart && ymonth.equals(socialYmonth)) it.remove();
		}
		return datas;
	}
	
	/**
	 * 年度社保征缴明细表
	 * @param year
	 * @param mo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_201(String year,List<String> modalitys)throws Exception
	{
		if(StringHelper.isNullOrEmpty(year)) throw new Exception("统计年份为空!");
		List<Map<String,Object>> datas = null;
		String modalityStr = null != modalitys && modalitys.size()>0?String.join(",", modalitys):null;
		datas = statistics4SocialMapper.st4zhengjiaoYear(year, modalitys,modalityStr);
		return datas;
	}
	
	/**
	 * 社保计划表
	 * @param startMonth
	 * @param endMonth
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_202(ChSocialInfoExtModel model)throws Exception{
		if(null == model || (StringHelper.isNullOrEmpty(model.getStartMonth()) && StringHelper.isNullOrEmpty(model.getEndMonth())))
			throw new Exception("统计区间为空!");
		List<Map<String,Object>> datas = statistics4SocialMapper.st4socialplan(model);
		if(null == datas || datas.size()<=0) return datas;
		Map<String,Object> statis = datas.get(datas.size()-1);
		String tmp = String.valueOf(statis.get("card_no"));
		if(!"合计".equals(tmp)) return datas;
		statis.put("size", datas.size()-1);
		statis.put("name", "合计");
		statis.put("card_no", "-");
		statis.put("work_area_cn", "-");
		statis.put("hos_depart_1level_cn", "-");
		statis.put("hos_depart_2level_cn", "-");
		return datas;
	}
}
