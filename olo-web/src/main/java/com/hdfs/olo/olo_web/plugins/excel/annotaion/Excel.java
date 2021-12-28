package com.hdfs.olo.olo_web.plugins.excel.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 是否支持Excel
 * @author huadf
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
	/**是否支持excel**/
    boolean isSupport() default false;
    /**是否允许为空*/
    boolean nullAble() default true;
}
