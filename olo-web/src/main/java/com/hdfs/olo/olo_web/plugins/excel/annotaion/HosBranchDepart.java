package com.hdfs.olo.olo_web.plugins.excel.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict.Convert;
/**
 * 支部科室等信息
 * @author huadf
 */
@Target(ElementType.FIELD)//该注解只能用在成员变量上
@Retention(RetentionPolicy.RUNTIME)
public @interface HosBranchDepart {
	/**转换类型**/
    Convert type() default Convert.VAL2KEY;
    /**目标：支部**/
    Target target() default Target.BRANCH;
    /**上级字段-支部字段**/
    String superBKey() default "";
    /**上级字段-一级科室字段**/
    String superD1Key() default "";
    /**该字典对应的中文属性（若无，则表示不存在）**/
    String cnField() default "";
    /**是否允许为空*/
    boolean nullAble() default true;
    
    public enum Target {
  	  BRANCH,
  	  DEPART1,
  	  DEPART2
    }
}
