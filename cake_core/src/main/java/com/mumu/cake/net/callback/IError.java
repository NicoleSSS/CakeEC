package com.mumu.cake.net.callback;

/**
 * @ClassName: IError
 * @Description: 错误回调
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 10:46
 * @Version: 1.0
 */
public interface IError {

    void onError(int code, String msg);
}
