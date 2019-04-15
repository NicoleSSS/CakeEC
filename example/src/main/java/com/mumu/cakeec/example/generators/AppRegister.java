package com.mumu.cakeec.example.generators;

import com.mumu.cake.annotations.AppRegisterGenerator;
import com.mumu.cake.wechat.template.AppRegisterTemplate;

/**
 * @ClassName: AppRegister
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 17:59
 * @Version: 1.0
 */
@AppRegisterGenerator(
        packageName = "com.mumu.cakeec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
