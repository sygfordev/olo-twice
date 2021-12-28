package com.hdfs.olo.olo_web.plugins.excel.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 导入时必须存在项
 * @author huadf
 */
@Target(ElementType.FIELD)//
@Retention(RetentionPolicy.RUNTIME)
public @interface MustExist {
    /**对应的字典项key**/
    boolean value() default true;
}
