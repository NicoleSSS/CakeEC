package com.mumu.cake.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @ClassName: EcIcons
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 15:38
 * @Version: 1.0
 */
public enum EcIcons implements Icon {
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
