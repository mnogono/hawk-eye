package com.event.driven.util;

public interface DynamicRouter<E extends Message> {
    /**
     * register new channel
     * @param type
     * @param channel
     */
    void registerChannel(Class<? extends Message> type, Channel<? extends Message> channel);

    void dispatch(E message);
}
