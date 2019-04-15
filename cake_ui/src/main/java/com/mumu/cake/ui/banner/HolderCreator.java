package com.mumu.cake.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @ClassName: HolderCreator
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 21:24
 * @Version: 1.0
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder> {

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
