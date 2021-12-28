package com.hdfs.olo.autocoding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;

import com.hdfs.olo.autocoding.common.Column;
import com.hdfs.olo.autocoding.common.Table;
import com.hdfs.olo.autocoding.utils.CamelCaseUtils;
import com.hdfs.olo.autocoding.utils.FileHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class EasyCodeApplication {

    private Logger logger = Logger.getLogger(this.getClass());
    private Properties properties;

    /**
     * 读取配置文件
     *
     * @throws Exception
     */
    public EasyCodeApplication() throws Exception {
        properties = new Properties();
        String fileDir = this.getClass().getClassLoader().getResource("generator.xml").getFile();
        properties.loadFromXML(new FileInputStream(fileDir));
    }

    /**
     * 解析数据表
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public Table parseTable(String tableName) throws Exception {
        String driverName = properties.getProperty("jdbc.driver");
        String userName = properties.getProperty("jdbc.username");
        String userPwd = properties.getProperty("jdbc.password");
        String dbURL = properties.getProperty("jdbc.url");

        String catalog = properties.getProperty("jdbc.catalog");
        String schema = properties.getProperty("jdbc.schema");
        schema = schema == null ? userName : schema;//"%"
        String column = "%";

        logger.debug("driver>>" + driverName);
        logger.debug("url>>" + dbURL);
        logger.debug("name>>" + userName);
        logger.debug("password>>" + userPwd);
        logger.debug("catalog>>" + catalog);
        logger.debug("schema>>" + schema);

        Class.forName(driverName);
        Connection conn = java.sql.DriverManager.getConnection(dbURL, userName, userPwd);
        DatabaseMetaData dmd = conn.getMetaData();

        List<Column> pkColumns = new ArrayList<Column>();
        ResultSet pkrs = dmd.getPrimaryKeys(catalog, schema, tableName);
        while (pkrs.next()) {
            Column c = new Column();
            String name = pkrs.getString("COLUMN_NAME");
            c.setName(CamelCaseUtils.toCamelCase(name));
            c.setDbName(name);
            pkColumns.add(c);
        }
        
        ResultSet rs = dmd.getColumns(catalog, schema, tableName, column);
        List<Column> columns = new ArrayList<Column>();
        while (rs.next()) {
            Column c = new Column();
            c.setLabel(rs.getString("REMARKS"));
            String name = rs.getString("COLUMN_NAME");
            c.setName(CamelCaseUtils.toCamelCase(name));
            c.getNameUpper();
            c.setDbName(name);
            String dbType = rs.getString("TYPE_NAME");
            int columnSize = rs.getInt("COLUMN_SIZE");
            if (dbType.equals("TINYINT") && columnSize > 1) {
                c.setType("Integer");
            } else if (dbType.equals("TINYINT") && columnSize == 1) {
                c.setType("Boolean");
            } else if (dbType.equals("DECIMAL") && columnSize == 1) {
                c.setType("BigDecimal");
            } else {
                String type = properties.getProperty(dbType);
                c.setType(type == null ? "String" : type);
            }
            c.setDbType(dbType);

            c.setLength(rs.getInt("COLUMN_SIZE"));
            c.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
            c.setNullable(rs.getBoolean("NULLABLE"));
            
            columns.add(c);
        }
        
        conn.close();

        Table t = new Table();
        String prefiex = properties.getProperty("tableRemovePrefixes");
        String name = tableName;
        if (prefiex != null && !"".equals(prefiex)) {
            name = tableName.split(prefiex)[0];
        }
        t.setName(CamelCaseUtils.toCamelCase(name));
        t.setDbName(tableName);
        t.setColumns(columns);
        t.setPkColumns(pkColumns);
        return t;
    }

    /**
     * <p>Discription:[生成映射文件和实体类]</p>
     * Created on 2019年4月4日
     *
     * @param tableName       要声称映射文件和实体类的表名称
     * @param tableDescAndCat 表描述
     * @throws Exception
     */
    public void gen(String tableName, String tableDescAndCat, String id, String modelId) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);

        String outRoot = properties.getProperty("outRoot");

        //当输出地址为null时，文件放到桌面
        if (null == outRoot || "".contentEquals(outRoot)) {
            File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
            outRoot = desktopDir.getAbsolutePath() + "/Desktop/EasyCodeDemo";
        }

        String basePackage = properties.getProperty("basePackage");
        String modulesPack = properties.getProperty("modulesPack");
        String utils4Pack = properties.getProperty("utils4Pack");
        String invokeRoot = properties.getProperty("invokeRoot4Controller");
        //获取当前日期
        SimpleDateFormat sm_date = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sm_year = new SimpleDateFormat("yyyy年");

        //将首字母转为大写
        StringBuffer buffer = new StringBuffer();
        String namePart1 = modelId.substring(0, 1).toUpperCase();
        String namePart2 = modelId.substring(1);
        buffer.append(namePart1 + namePart2);

        System.out.println(buffer);

        Map<String, Object> root = new HashMap<String, Object>();
        Table t = this.parseTable(tableName);
        t.setTableDesc(tableDescAndCat.split("_")[0]);
        root.put("table", t);
        root.put("className", t.getNameUpper());
        root.put("classNameLower", t.getName());
        root.put("primaryKey", id);
        root.put("modelId", modelId);
        root.put("modelIdFirstUpper", buffer);
        
        root.put("package", basePackage);
        root.put("package4Pro", basePackage.substring(0, basePackage.lastIndexOf(".")));
        root.put("modules", modulesPack);
        root.put("utils4Pack", utils4Pack);
        root.put("invokeRoot",invokeRoot);
        
        root.put("date", sm_date.format(new Date()));
        root.put("year", sm_year.format(new Date()));

        root.put("author", "huadf");
        root.put("email", "jeenry@126.com");
        root.put("website", "xxxx");

        String templateDir = this.getClass().getClassLoader().getResource("templates").getPath();

        File tdf = new File(templateDir);
        List<File> files = FileHelper.findAllFile(tdf);

        for (File f : files) {
            String parentDir = "";
            if (f.getParentFile().compareTo(tdf) != 0) {
                parentDir = f.getParent().split("templates")[1];
            }
            cfg.setClassForTemplateLoading(this.getClass(), "/templates" + parentDir);

            Template template = cfg.getTemplate(f.getName());
            template.setEncoding("UTF-8");
            String parentFileDir = FileHelper.genFileDir(parentDir, root);
            parentFileDir = parentFileDir.replace(".", "/").replace("\\", "/");
            boolean isJavaType = f.getName().indexOf("_")>=0?false:true;
            String targetName = null;
            if(isJavaType) {
            	targetName = FileHelper.genFileDir(f.getName(), root).replace(".ftl", ".java");
            }else {
            	String[] ftlName = f.getName().substring(0, f.getName().indexOf(".")).split("_");
            	parentFileDir += ("/"+t.getName());
            	targetName = f.getName().replace(".ftl", "."+ftlName[1]).replace("_"+ftlName[1], "");
            	if("js".equals(ftlName[1]))
            		targetName = FileHelper.genFileDir(f.getName(), root).replace(".ftl", "."+ftlName[1]).replace("_"+ftlName[1], "");
            }
            System.out.println(targetName);

            File newFile = FileHelper.makeFile(outRoot + parentFileDir + "/" + targetName);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
            template.process(root, out);
            logger.debug("已生成文件：" + outRoot + parentFileDir + "/" + targetName);
        }
    }

    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();
        EasyCodeApplication g = new EasyCodeApplication();
        Map<String, String> map = new HashMap<String, String>();
//        map.put("ch_pm_station", "用工及岗位表");
//        map.put("ch_pm_edu_head", "学历信息头表");
//        map.put("ch_pm_edu_info", "学历信息表");
//        map.put("ch_pm_posit_head", "职务信息头表");
//        map.put("ch_pm_posit_info", "职务信息表");
//        map.put("ch_pm_title_head", "职称信息头表");
//        map.put("ch_pm_title_info", "职称信息表");
//        map.put("ch_pm_skills_head", "技能等级(技术工种)信息头表");
//        map.put("ch_pm_skills_info", "技能等级（技术工种）表");
//        map.put("ch_pm_spec_profe", "特殊工种");
//        map.put("ch_pm_contract_info", "合同信息");
//        map.put("ch_pm_work_expe_info", "工作经历信息");
//        map.put("ch_pm_incdec_info", "增减情况");
//        map.put("ch_pm_family_member_info", "家庭成员");
//        map.put("ch_pm_dossier_info", "人事档案");
//        map.put("ch_pm_other_info", "其他信息");
//        map.put("ch_pm_file_info", "文件信息");
//        map.put("ch_pm_trans_info", "调动信息");
//        map.put("ch_sa_adjust_salary", "调资表");
//        map.put("ch_sa_adjust_formula", "调资-公式表");
//        map.put("ch_sa_adjust_record", "调资记录表");
//        map.put("ch_sa_payslip", "工资条");
//        map.put("ch_sa_wechat_token", "微信访问Token");
//        map.put("ch_sa_wechat_account", "微信访问账户");
//        map.put("ch_social_info", "社保信息");
//        map.put("ch_social_record", "社保导入记录");
        map.put("ch_pm_alarm_set", "警报设置");
        map.put("ch_pm_alarm_msg", "警报信息");
        

        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            //设置数据库主键字段
            g.gen(e.getKey(), e.getValue(), "id", "id");
        }
        System.out.println("-------------------模版文件生成完毕，时间：" + (System.currentTimeMillis() - time) + "毫秒 ----------------!!!");
    }
}