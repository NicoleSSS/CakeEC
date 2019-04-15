package com.mumu.cake.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * @ClassName: FontEcModule
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/18 21:40
 * @Version: 1.0
 */
public class FontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
