package com.hdfs.olo.olo_web.plugins.excel.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 字典 
 * @author huadf
 */
@Target(ElementType.FIELD)//该注解只能用在成员变量上
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {
    /**对应的字典项key**/
    String key() default "";
    /**转换类型**/
    Convert type() default Convert.VAL2KEY;
    /**该字典对应的中文属性（若无，则表示不存在）**/
    String cnField() default "";
    /**是否允许为空*/
    boolean nullAble() default true;
    
    public enum Convert {
  	  KEY2VAL,
  	  VAL2KEY
    }
}
