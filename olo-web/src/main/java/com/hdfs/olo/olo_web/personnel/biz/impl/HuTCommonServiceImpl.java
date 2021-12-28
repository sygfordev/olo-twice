package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.ISysAreaBiz;
import com.hdfs.olo.olo_web.central.biz.ISysCityBiz;
import com.hdfs.olo.olo_web.central.biz.ISysProvinceBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.model.SysAreaModel;
import com.hdfs.olo.olo_web.central.model.SysCityModel;
import com.hdfs.olo.olo_web.central.model.SysProvinceModel;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosBranchBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosDepartBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosSecdepBiz;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosBranchModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosDepartModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosSecdepModel;
import com.hdfs.olo.olo_web.plugins.common.utils.ReflectionUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuTHeadItem;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;

@Service
public class HuTCommonServiceImpl implements IHuTCommonService {
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISysProvinceBiz sysProvinceBiz;
	@Autowired
	private ISysCityBiz sysCityBiz;
	@Autowired
	private ISysAreaBiz sysAreaBiz;
	@Autowired
	private IChPmHosBranchBiz chPmHosBranchBiz;
	@Autowired
	private IChPmHosDepartBiz chPmHosDepartBiz;
	@Autowired
	private IChPmHosSecdepBiz chPmHosSecdepBiz;
	
	//字典库数据
	private Map<String,List<Map<String,Object>>> dicts = null;
	private List<SysProvinceModel> pcaList = null;//省市县
	private List<ChPmHosBranchModel> hbpList = null;//支部科室
	
	/**
	 * 检测导入表头
	 * @param reader
	 * @param headerAlias
	 * @return
	 */
	@Override
	public Map<Boolean,Object> check4Header(ExcelReader reader,Map<String,String> headerAlias)
	{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		List<List<Object>> heads = reader.read(0, 0);
		if(null == heads || heads.size()<=0)
		{
			retMap.put(false,"未检测到有表头信息存在!");
			return retMap;
		}
		List<Object> eHeads = heads.get(0);
		StringBuffer defect = new StringBuffer();
		for(String key:headerAlias.keySet())
		{
			if(StringHelper.isNullOrEmpty(key))
				continue;
			boolean isExist = false;
			for(Object item:eHeads)
			{
				if(null == item || 
						!key.equals(item)) continue;
				isExist = true;
				break;
			}
			//若表头配置中的属性不存在，则加入返回信息
			if(!isExist) defect.append(defect.length()>0?","+key:key);
		}
		if(defect.length()>0)
			retMap.put(false,defect.toString().replace(",", "<br>")+"<br>请检查以上表头是否存在或存在空格!");
		else
			retMap.put(true,"检测表头通过!");
		return retMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<Boolean, Object> check4Import(ExcelReader reader, Map<String, String> headerAlias, Class clazz) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		if(null != headerAlias && headerAlias.size()>0)
			reader.setHeaderAlias(headerAlias);

		// 读取list时默认首个非空行为标题
		//List<List<Object>> read = reader.read();
		
		boolean isExistDictConfig = false;
		boolean isExistPCAConfig = false;
		boolean isExistMustConfig = false;
		boolean isExistHBPConfig = false;//是否存在支部科室配置
		//加载所有配置有字典的属性
		Map<String,String> dictMapping = new HashMap<String,String>();
		List<String> mustExists = new ArrayList<String>();//导入时必须存在项
		Map<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target,String>> 
		pcaMapping = new HashMap<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target,String>>();
		Map<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target,String>> 
		hbpMapping = new HashMap<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target,String>>();
		
		
		Field[] allFields = clazz.getDeclaredFields();
		for(Field item:allFields)
		{
			//获取实体配置中必须存在项
			isExistMustConfig = item.isAnnotationPresent(MustExist.class);
			MustExist exist = isExistMustConfig?item.getAnnotation(MustExist.class):null;
			if(isExistMustConfig && exist.value())
				mustExists.add(item.getName());
			//判断当前属性是否存在字典等配置
			isExistDictConfig = item.isAnnotationPresent(Dict.class);
			isExistPCAConfig = item.isAnnotationPresent(ProvCityArea.class);
			isExistHBPConfig = item.isAnnotationPresent(HosBranchDepart.class);
			if(!isExistDictConfig && !isExistPCAConfig && !isExistHBPConfig)continue;
			
			if(isExistDictConfig) {
				Dict dict = item.getAnnotation(Dict.class);
				dictMapping.put(item.getName(),StringHelper.isNullOrEmpty(dict.key())?item.getName():dict.key());
			}else if(isExistPCAConfig)
			{
				ProvCityArea pca = item.getAnnotation(ProvCityArea.class);
				Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target,String> 
				pcaConfig = new HashMap<com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target,String>();
				pcaConfig.put(pca.target(), pca.superPKey()+","+pca.superCKey());
				if(com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target.PROVINCE == pca.target() || 
						(com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target.CITY==pca.target() && !StringHelper.isNullOrEmpty(pca.superPKey())) ||
						(com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target.AREA==pca.target() && !StringHelper.isNullOrEmpty(pca.superPKey()) && !StringHelper.isNullOrEmpty(pca.superCKey())))
					pcaMapping.put(item.getName(),pcaConfig);
			}else
			{
				HosBranchDepart hbp = item.getAnnotation(HosBranchDepart.class);
				Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target,String> 
				hbpConfig = new HashMap<com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target,String>();
				hbpConfig.put(hbp.target(), hbp.superBKey()+","+hbp.superD1Key());
				if(com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target.BRANCH == hbp.target() || 
						(com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target.DEPART1==hbp.target() && !StringHelper.isNullOrEmpty(hbp.superBKey())) ||
						(com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target.DEPART2==hbp.target() && !StringHelper.isNullOrEmpty(hbp.superBKey()) && !StringHelper.isNullOrEmpty(hbp.superD1Key())))
					hbpMapping.put(item.getName(),hbpConfig);
			}
		}
		
		//校验导入必输项是否存在于导入表头中
		if(mustExists.size()>0) {
			boolean isAllExist4Must = true;
			for(String mItem:mustExists)
			{
				if(headerAlias.containsValue(mItem))
					continue;
				isAllExist4Must = false;
				break;
			}
			if(!isAllExist4Must) {
				retMap.put(false, "导入文件中缺失必输项!");
				return retMap;
			}
		}
		
		
		//若存在字典列，则加载所需字典配置
		if(dictMapping.values().size()>0) {
			List<String> itemKeys = new ArrayList<String>(dictMapping.values());
			dicts = dictBiz.queryItemListByCodes(itemKeys);
		}

		//读取文件中所有数据
		List<Map<String, Object>> readAll = reader.readAll();
		
		Map<String,Object> item = null;
		Map<Boolean,Object> rowObjVal = null;
		boolean isReadSucc = true;//是否全部读取成功
		StringBuffer errMsg = new StringBuffer();
		//最终封装的实体对象列表
		List<Object> modelList = new ArrayList<Object>();
		for (int i=0;i<readAll.size();i++) {
			item = readAll.get(i);
			rowObjVal = packageModel(clazz,item,i+1,dictMapping,pcaMapping,hbpMapping,mustExists);
			if(rowObjVal.containsKey(true))
			{
				modelList.add(rowObjVal.get(true));
				continue;
			}
			isReadSucc = false;
			Map<Integer,String> errMap = (Map<Integer,String>)rowObjVal.get(false);
			for(Integer colNum:errMap.keySet())
			{
				errMsg.append("第"+(i+1)+"行"+colNum+"列数据读取异常!");
			}
			break;
		}
		
		retMap.put(isReadSucc, isReadSucc?modelList:errMsg.toString());
		return retMap;
	}
	
	/**
	 * 根据行数据加工对象实体
	 * @param clazz	对象类
	 * @param rowMap	行数据
	 * @param rowNum	行号
	 * @return
	 */
	@Override
	@SuppressWarnings({ "static-access"})
	public Map<Boolean,Object> packageModel(Class<?> clazz,Map<String,Object> rowMap,int rowNum
			,Map<String,String> dictMapping,Map<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target,String>> pcaMapping,
			Map<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target,String>> hbpMapping,
			List<String> mustExists)
	{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		Map<Integer,String> errMap = new HashMap<Integer,String>();
		Object obj = null;
		try {
			obj = clazz.forName(clazz.getName()).newInstance();
		} catch (Exception e1) {
			errMap.put(-1, "对象创建失败");
			e1.printStackTrace();
			retMap.put(false, errMap);
			return retMap;
		}
		int i = 0;
		for(String key:rowMap.keySet())
		{
			i ++;
			if(StringHelper.isNullOrEmpty(key))continue;
			try {
				key = key.trim();
				
				Field f = ReflectionUtil.getDeclaredField(obj.getClass(),key);
				if (null == f) continue;
				
				//判断是否为必输项，若是，则进行非空校验
				if(mustExists.size()>0 && mustExists.contains(key)
						&& StringHelper.isNullOrEmpty(rowMap.get(key)+""))
				{
					errMap.put(i, "必输项为空");
					break;
				}
				
				
				//匹配字典
				if(f.isAnnotationPresent(Dict.class))
				{
					if(null == dictMapping) continue;
					String dictKey = dictMapping.get(f.getName());
					if(!dicts.containsKey(dictKey)) 
						continue;
					String cnVal = null;
					boolean seted = false;
					for(Map<String,Object> item:dicts.get(dictKey))
					{
						if(!item.get("item_val").equals(rowMap.get(key)))
							continue;
						rowMap.put(key, item.get("item_key"));
						cnVal = item.get("item_val")+"";
						seted = true;
						break;
					}
					//若未找到对应的字典，则跳过
					if(!seted) continue;
					//校验对应的字典中文  属性是否存在，若存在，则将中文置入
					Dict dict = f.getAnnotation(Dict.class);
					String cnFieldVal = StringHelper.isNullOrEmpty(dict.cnField())?(key+"Cn"):dict.cnField();
					Field fcn = ReflectionUtil.getDeclaredField(obj.getClass(),cnFieldVal);
					if(null != fcn)
						ReflectionUtil.setFieldValByType(obj, fcn, cnVal);
				} else //匹配省市县
				if(f.isAnnotationPresent(ProvCityArea.class))
				{
					if(null == pcaMapping || !pcaMapping.containsKey(f.getName())) continue;
					boolean seted = false;
					Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target,String> pitem = pcaMapping.get(f.getName());
					com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target target = null;
					String provName = null;//对应实体的属性名称
					String cityName = null;//对应实体的属性名称
					for(com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target t:pitem.keySet())
					{
						target = t;
						String[] array = pitem.get(t).split(",");
						provName = array.length>0?array[0]:null;
						cityName = array.length>1?array[1]:null;
						break;
					}
					
					String cnVal = null;
					Integer provNo = null;
					SysProvinceModel prov = null;
					List<SysCityModel> citys = null;
					switch(target)
					{
					case PROVINCE:
						if(null == pcaList || pcaList.size()<=0) 
							pcaList = sysProvinceBiz.queryList(new SysProvinceModel());
						for(SysProvinceModel item:pcaList)
						{
							if(!item.getProvName().contentEquals(""+rowMap.get(key)))
								continue;
							rowMap.put(key, item.getProvNo());
							cnVal = item.getProvName();
							seted = true;
							break;
						}
						break;
					case CITY:
						if(null == pcaList || pcaList.size()<=0) break;
						provNo = (Integer)ReflectionUtil.getFieldValue(obj,provName);
						if(null == provNo) break;
						for(SysProvinceModel item:pcaList)
						{
							if(item.getProvNo() != provNo) continue;
							prov = item;
							break;
						}
						if(null == prov) break;
						if(prov.getCityList().size() == 0) {
							SysCityModel model = new SysCityModel();
							model.setProvNo(provNo);
							citys = sysCityBiz.queryList(model);
							for(SysProvinceModel item:pcaList)
							{
								if(item.getProvNo() != provNo) continue;
								item.setCityList(citys);
								break;
							}
						}else {
							citys = prov.getCityList();
						}
							
						if(null == citys || citys.size()<=0) break;
						for(SysCityModel item:citys)
						{
							if(!item.getCityName().contentEquals(""+rowMap.get(key)))
								continue;
							rowMap.put(key, item.getCityNo());
							cnVal = item.getCityName();
							seted = true;
							break;
						}	
						break;
					case AREA:
						if(null == pcaList || pcaList.size()<=0) break;
						provNo = (Integer)ReflectionUtil.getFieldValue(obj,provName);
						Integer cityNo = (Integer) ReflectionUtil.getFieldValue(obj, cityName);
						if(null == provNo || null == cityNo) break;
						
						List<SysAreaModel> areaList = null;
						outLoop:
						for(SysProvinceModel item:pcaList)
						{
							if(item.getProvNo() != provNo) continue;
							for(SysCityModel city:item.getCityList())
							{
								if(city.getCityNo().intValue()!= cityNo.intValue()) continue;
								areaList = city.getAreaList();
								if(null == areaList || areaList.size()<=0)
								{
									SysAreaModel area = new SysAreaModel();
									area.setCityNo(cityNo);
									areaList = sysAreaBiz.queryList(area);
									city.setAreaList(areaList);
								}
								break outLoop;
							}
							break;
						}
						if(null == areaList || areaList.size()<=0) break;
						
						for(SysAreaModel item:areaList)
						{
							if(!item.getAreaName().contentEquals(""+rowMap.get(key)))
								continue;
							rowMap.put(key, item.getAreaNo());
							cnVal = item.getAreaName();
							seted = true;
							break;
						}	
						break;
					}
					//若未找到对应的省市县，则跳过
					if(!seted) continue;
					
					//校验对应的省市县中文  属性是否存在，若存在，则将中文置入
					ProvCityArea pca = f.getAnnotation(ProvCityArea.class);
					String cnFieldVal = StringHelper.isNullOrEmpty(pca.cnField())?(key+"Cn"):pca.cnField();
					Field fcn = ReflectionUtil.getDeclaredField(obj.getClass(),cnFieldVal);
					if(null != fcn)
						ReflectionUtil.setFieldValByType(obj, fcn, cnVal);
				}else //匹配支部科室
				if(f.isAnnotationPresent(HosBranchDepart.class))
				{
					if(null == hbpMapping || !hbpMapping.containsKey(f.getName())) continue;
					boolean seted = false;
					Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target,String> bitem = hbpMapping.get(f.getName());
					com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target target = null;
					String branchName = null;//对应实体的属性名称
					String depart1Name = null;//对应实体的属性名称
					for(com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target t:bitem.keySet())
					{
						target = t;
						String[] array = bitem.get(t).split(",");
						branchName = array.length>0?array[0]:null;
						depart1Name = array.length>1?array[1]:null;
						break;
					}
					
					String cnVal = null;
					Integer branchNo = null;
					ChPmHosBranchModel branch = null;
					List<ChPmHosDepartModel> depart1s = null;
					switch(target)
					{
					case BRANCH:
						if(null == hbpList || hbpList.size()<=0) 
							hbpList = chPmHosBranchBiz.queryList(new ChPmHosBranchModel());
						for(ChPmHosBranchModel item:hbpList)
						{
							if(!item.getHbhName().contentEquals(""+rowMap.get(key)))
								continue;
							rowMap.put(key, item.getHbhNo());
							cnVal = item.getHbhName();
							seted = true;
							break;
						}
						break;
					case DEPART1:
						if(null == hbpList || hbpList.size()<=0) break;
						branchNo = (Integer)ReflectionUtil.getFieldValue(obj,branchName);
						if(null == branchNo) break;
						for(ChPmHosBranchModel item:hbpList)
						{
							if(item.getHbhNo() != branchNo) continue;
							branch = item;
							break;
						}
						if(null == branch) break;
						if(branch.getSubList().size() == 0) {
							ChPmHosDepartModel model = new ChPmHosDepartModel();
							model.setHbhNo(branchNo);
							depart1s = chPmHosDepartBiz.queryList(model);
							for(ChPmHosBranchModel item:hbpList)
							{
								if(item.getHbhNo() != branchNo) continue;
								item.setSubList(depart1s);
								break;
							}
						}else {
							depart1s = branch.getSubList();
						}
							
						if(null == depart1s || depart1s.size()<=0) break;
						for(ChPmHosDepartModel item:depart1s)
						{
							if(!item.getHdpName().contentEquals(""+rowMap.get(key)))
								continue;
							rowMap.put(key, item.getHdpNo());
							cnVal = item.getHdpName();
							seted = true;
							break;
						}	
						break;
					case DEPART2:
						if(null == hbpList || hbpList.size()<=0) break;
						branchNo = (Integer)ReflectionUtil.getFieldValue(obj,branchName);
						Integer dep1No = (Integer) ReflectionUtil.getFieldValue(obj, depart1Name);
						if(null == branchNo || null == dep1No) break;
						
						List<ChPmHosSecdepModel> dep2List = null;
						outLoop:
						for(ChPmHosBranchModel item:hbpList)
						{
							if(item.getHbhNo() != branchNo) continue;
							for(ChPmHosDepartModel dep1:item.getSubList())
							{
								if(dep1.getHdpNo().intValue()!= dep1No.intValue()) continue;
								dep2List = dep1.getSubList();
								if(null == dep2List || dep2List.size()<=0)
								{
									ChPmHosSecdepModel dep2 = new ChPmHosSecdepModel();
									dep2.setHdpNo(dep1No);
									dep2List = chPmHosSecdepBiz.queryList(dep2);
									dep1.setSubList(dep2List);
								}
								break outLoop;
							}
							break;
						}
						if(null == dep2List || dep2List.size()<=0) break;
						
						for(ChPmHosSecdepModel item:dep2List)
						{
							if(!item.getHsdName().contentEquals(""+rowMap.get(key)))
								continue;
							rowMap.put(key, item.getHsdNo());
							cnVal = item.getHsdName();
							seted = true;
							break;
						}	
						break;
					}
					//若未找到对应的支部科室，则跳过
					if(!seted) continue;
					
					//校验对应的支部科室中文  属性是否存在，若存在，则将中文置入
					HosBranchDepart hbp = f.getAnnotation(HosBranchDepart.class);
					String cnFieldVal = StringHelper.isNullOrEmpty(hbp.cnField())?(key+"Cn"):hbp.cnField();
					Field fcn = ReflectionUtil.getDeclaredField(obj.getClass(),cnFieldVal);
					if(null != fcn)
						ReflectionUtil.setFieldValByType(obj, fcn, cnVal);
				}
				ReflectionUtil.setFieldValByType(obj, f, rowMap.get(key));
			}catch(Exception e)
			{
				//errMap.put(i, "数据异常");//列数据异常不再对外传递
				logger.error("导入数据文件中[第"+rowNum+"行"+i+"列]出现异常！error："+e.getMessage());
			}
		}
		if(errMap.size()>0)
			retMap.put(false, errMap);
		else
			retMap.put(true, obj);
		return retMap;
	}

	@Override
	public void export(HttpServletResponse response, String fileName, List<?> data, HuToolHead headInfo) throws Exception {
        ExcelWriter bigWriter = ExcelUtil.getBigWriter();
        //bigWriter.writeHeadRow(new LinkedList());
        
        List<HuTHeadItem> heads = headInfo.getHeads();
        int lastCol = -1;
//        for (int i = 0; i < heads.size(); i++) {
//            if (null != heads.get(i).getSubs() && heads.get(i).getSubs().size()>0) {
//                for (int j = 0; j < heads.get(i).getSubs().size(); j++) {
//                    bigWriter.addHeaderAlias(heads.get(i).getSubs().get(j).getField(), heads.get(i).getSubs().get(j).getTitle());
//                    bigWriter.setColumnWidth(lastCol++, 30);
//                }
//            }else
//            {
//            	bigWriter.addHeaderAlias(heads.get(i).getField(), heads.get(i).getTitle());
//            	bigWriter.setColumnWidth(lastCol++, 30);
//            }
//        }
        lastCol = addHeadAlias(bigWriter,heads,-1);
        //根据需要合并添加第一列表头
        int titleNeedRowNum = 1;
        bigWriter.merge(0, 0, 0, lastCol, headInfo.gethTitle(), true);
        Map<String,Object> signMap = headInfo.getSign();
        if(null != signMap && signMap.size()>0)
        {
        	StringBuffer signMsg = new StringBuffer();
        	for(String signKey:signMap.keySet())
        	{
        		if(signMsg.length()>0) signMsg.append("\t");
        		signMsg.append(signKey+":"+signMap.get(signKey));
        	}
        	bigWriter.merge(1, 1, 0, lastCol, signMsg, true);
        	titleNeedRowNum++;
        }
        Integer headRank = writeHead(bigWriter,heads,titleNeedRowNum,null);
        if(null == headRank || headRank<=0) throw new Exception("表头信息异常!");
        //暂时先固定表头层级深度
        //if(headRank>3) headRank = 3;
        // 只导出配置好的列名
        bigWriter.setCurrentRow((headRank)+titleNeedRowNum);
        bigWriter.setOnlyAlias(true);
        bigWriter.write(data);
        StyleSet styleSet = bigWriter.getStyleSet();
        styleSet.setWrapText();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setHeader("filename", URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName+".xlsx", "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        bigWriter.flush(out, true);
        bigWriter.close();
        IoUtil.close(out);
    }
	
	/**
	 * 设置表头别名，用于加载数据
	 * @param bigWriter
	 * @param heads
	 * @param lastCol
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer addHeadAlias(ExcelWriter bigWriter,List<HuTHeadItem> heads,Integer lastCol) throws Exception
	{
		if(null == bigWriter || null == heads || heads.size()<=0) 
			throw new Exception("head or writer is null");
		if(null == lastCol) lastCol = -1;
		for (int i = 0; i < heads.size(); i++) {
            if (null != heads.get(i).getSubs() && heads.get(i).getSubs().size()>0) {
            	lastCol = this.addHeadAlias(bigWriter, heads.get(i).getSubs(), lastCol);
            }else
            {
            	bigWriter.addHeaderAlias(heads.get(i).getField(), heads.get(i).getTitle());
            	bigWriter.setColumnWidth(lastCol++, 30);
            }
        }
		return lastCol;
	}
	
	/**
	 * 写入表头，并返回表头层级
	 * @param bigWriter
	 * @param heads
	 * @param ignoreRowSize
	 * @param headRank 表头层级
	 * @return 表头层级
	 */
	@Override
	public int writeHead(ExcelWriter bigWriter,List<HuTHeadItem> heads,Integer ignoreRowSize,Integer headRank)
	{
		if(null == headRank) 
			headRank = 0;
		boolean sameLevel = false;//层级判断维度
		HuTHeadItem item = null;
		Integer subDeep = null;
		List<Integer> subDeeps = new ArrayList<Integer>();
        for(int i=0;i<heads.size();i++)
        {
        	item = heads.get(i);
        	item.setRowS(item.getRowS()+ignoreRowSize);
        	item.setRowE(item.getRowE()+ignoreRowSize);
        	logger.info("设置单元格：行:"+item.getRowS()+"-"+item.getRowE()+" 列："+item.getColS()+"-"+item.getColE()+" title："+item.getTitle());
        	if(item.getRowS() != item.getRowE() || item.getColS() != item.getColE())
        		bigWriter.merge(item.getRowS(), item.getRowE(), item.getColS(), item.getColE(), item.getTitle(),false);
        	else
        		bigWriter.writeCellValue(item.getColS(), item.getRowS(), item.getTitle());
        	if(!sameLevel) {
        		headRank++;
        		sameLevel = true;
        	}
        	if(null == item.getSubs() || item.getSubs().size()<=0) continue;
        	subDeep = writeHead(bigWriter,item.getSubs(),ignoreRowSize,headRank);
        	subDeeps.add(subDeep);
        	//headRank++;
        }
        subDeeps.add(headRank);
        return Collections.max(subDeeps);
	}
}
