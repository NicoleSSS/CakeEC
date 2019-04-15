package com.mumu.cakeec.example.generators;

import com.mumu.cake.annotations.PayEntryGenerator;
import com.mumu.cake.wechat.template.WXPayEntryTemplate;

/**
 * @ClassName: WechatPayEntry
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 17:53
 * @Version: 1.0
 */
@PayEntryGenerator(
        packageName = "com.mumu.cakeec.example",
        payTemplate = WXPayEntryTemplate.class
)
public interface WechatPayEntry {
}
