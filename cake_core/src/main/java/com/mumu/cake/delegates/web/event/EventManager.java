package com.mumu.cake.delegates.web.event;

import java.util.HashMap;

import androidx.annotation.NonNull;

/**
 * @ClassName: EventManager
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 11:56
 * @Version: 1.0
 */
public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager(){
    }

    private static class Holder{
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance(){
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event){
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action){
       final Event event = EVENTS.get(action);
       if(event == null){
            return new UndefineEvent();
       }
       return event;
    }
}
