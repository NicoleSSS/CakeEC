package com.mumu.cake.net.callback;

/**
 * @ClassName: IRequest
 * @Description: 网络请求加载圈
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 10:47
 * @Version: 1.0
 */
public interface IRequest {
    void onRequestStart();
    void onRequestEnd();
}
