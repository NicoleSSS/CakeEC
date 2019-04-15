package com.mumu.cake.delegates;

/**
 * @ClassName: CakeDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 9:00
 * @Version: 1.0
 */
public abstract class CakeDelegate extends PermissionCheckerDelegate{

    @SuppressWarnings("unchecked")
    public <T extends CakeDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
