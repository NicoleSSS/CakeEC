package com.mumu.cake.util.callback;

import java.util.WeakHashMap;

/**
 * @ClassName: CallbackManager
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 12:18
 * @Version: 1.0
 */
public class CallbackManager {

    private static final WeakHashMap<Object,IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class Holder{
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance(){
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback){
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag){
        return CALLBACKS.get(tag);
    }
}
