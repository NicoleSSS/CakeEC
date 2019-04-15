package com.mumu.cake.delegates.bottom.image;

/**
 * @ClassName: BottomTabBean
 * @Description: 首页底部TabBean
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 20:57
 * @Version: 1.0
 */
public final class ImageBottomTabBean {

    private final int IMAGE_NORM;
    private final int IMAGE_FORCE;
    private final CharSequence TITLE;

    public ImageBottomTabBean(int imageNorm, int imageForce, CharSequence title) {
        this.IMAGE_NORM = imageNorm;
        this.IMAGE_FORCE = imageForce;
        this.TITLE = title;
    }

    public int getImageNorm() {
        return IMAGE_NORM;
    }

    public int getImageForce(){
        return IMAGE_FORCE;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
