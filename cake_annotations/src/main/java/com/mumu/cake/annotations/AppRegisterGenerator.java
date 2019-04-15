package com.mumu.cake.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: AppRegisterGenerator
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 16:25
 * @Version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AppRegisterGenerator {

    String packageName();

    Class<?> registerTemplate();
}
