package com.mumu.cake.delegates.bottom.icon;

/**
 * @ClassName: BottomTabBean
 * @Description: 首页底部TabBean
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 20:57
 * @Version: 1.0
 */
public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
