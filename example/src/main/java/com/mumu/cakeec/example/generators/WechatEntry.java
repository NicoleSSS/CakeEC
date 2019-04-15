package com.mumu.cakeec.example.generators;

import com.mumu.cake.annotations.EntryGenerator;
import com.mumu.cake.wechat.template.WXEntryTemplate;

/**
 * @ClassName: WechatEntry
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 17:54
 * @Version: 1.0
 */
@EntryGenerator(
        packageName = "com.mumu.cakeec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WechatEntry {
}
