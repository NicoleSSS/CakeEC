package com.mumu.cake.app;

import com.mumu.cake.util.storage.CakePreference;

/**
 * @ClassName: AccountManager
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 14:26
 * @Version: 1.0
 */
public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登录后调用
     * @param state
     */
    public static void setSignState(boolean state){
        CakePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static boolean isSignIn(){
        return CakePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNoSignIn();
        }
    }

}
