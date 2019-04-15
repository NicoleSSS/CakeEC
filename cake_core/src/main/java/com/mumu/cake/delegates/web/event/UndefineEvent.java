package com.mumu.cake.delegates.web.event;

import com.mumu.cake.util.log.CakeLogger;

/**
 * @ClassName: UndefineEvent
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 12:06
 * @Version: 1.0
 */
public class UndefineEvent extends Event{
    @Override
    public String execute(String params) {
        CakeLogger.e("UndefineEvent",params);
        return null;
    }
}
