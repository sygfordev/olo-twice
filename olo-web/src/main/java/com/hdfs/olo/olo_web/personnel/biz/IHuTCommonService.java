package com.hdfs.olo.olo_web.personnel.biz;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuTHeadItem;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;

public interface IHuTCommonService {

	/**
	 * 检测导入表头
	 * @param reader
	 * @param headerAlias
	 * @return
	 */
	public Map<Boolean,Object> check4Header(ExcelReader reader,Map<String,String> headerAlias);
	
	/**
	 * 导入前对数据进行校验
	 * @param reader
	 * @param headerAlias
	 * @param clazz
	 * @return
	 */
	public Map<Boolean,Object> check4Import(ExcelReader reader,Map<String,String> headerAlias,Class clazz);
	
	/**
	 * 导出
	 * @param response
	 * @param fileName
	 * @param data
	 * @param headInfo
	 * @throws IOException
	 */
	public void export(HttpServletResponse response, String fileName, List<?> data, HuToolHead headInfo) throws Exception;
	
	/**
	 * 根据数据封装实体类
	 * @param clazz
	 * @param rowMap
	 * @param rowNum
	 * @param dictMapping
	 * @param pcaMapping
	 * @param hbpMapping
	 * @param mustExists
	 * @return
	 */
	public Map<Boolean,Object> packageModel(Class<?> clazz,Map<String,Object> rowMap,int rowNum
			,Map<String,String> dictMapping,Map<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target,String>> pcaMapping,
			Map<String,Map<com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target,String>> hbpMapping,
			List<String> mustExists);
	
	/**
	 * 设置表头别名，用于加载数据
	 * @param bigWriter
	 * @param heads
	 * @param lastCol
	 * @return
	 * @throws Exception
	 */
	public Integer addHeadAlias(ExcelWriter bigWriter,List<HuTHeadItem> heads,Integer lastCol) throws Exception;
	
	/**
	 * 写入表头，并返回表头层级
	 * @param bigWriter
	 * @param heads
	 * @param ignoreRowSize
	 * @param headRank 表头层级
	 * @return 表头层级
	 */
	public int writeHead(ExcelWriter bigWriter,List<HuTHeadItem> heads,Integer ignoreRowSize,Integer headRank);
}
