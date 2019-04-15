package com.mumu.cake.util.callback;

import androidx.annotation.NonNull;

/**
 * @ClassName: IGlobalCallback
 * @Description:
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 12:18
 * @Version: 1.0
 */
public interface IGlobalCallback<T> {

    void executeCallback(@NonNull T args);
}
