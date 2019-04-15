package com.mumu.cake.ui.launcher;


import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @ClassName: LauncherHolderCreator
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/12 17:18
 * @Version: 1.0
 */
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
