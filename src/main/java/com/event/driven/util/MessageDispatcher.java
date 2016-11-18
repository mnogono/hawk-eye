package com.event.driven.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessageDispatcher implements DynamicRouter<Message> {
    Map<Class<? extends Message>, List<Channel<Message>>> handlers;

    static private MessageDispatcher instance;

    static public MessageDispatcher getInstance() {
        if (instance == null) {
            instance = new MessageDispatcher();
        }

        return instance;
    }

    private MessageDispatcher() {
        handlers = new HashMap<>();
    }

    @Override
    public void dispatch(Message message) {
        List<Channel<Message>> messageHandlers = handlers.get(message.getType());
        if (messageHandlers == null) {
            return;
        }

        for (Channel<Message> handler : messageHandlers) {
            handler.dispatch(message);
        }
    }

    @Override
    public void registerChannel(Class<? extends Message> type, Channel<? extends Message> channel) {
        if (channel == null) {
            throw new NullPointerException("channel can't be null");
        }

        List<Channel<Message>> messageHandlers = handlers.get(type);

        if (messageHandlers == null) {
            messageHandlers = new LinkedList<>();
            handlers.put(type, messageHandlers);
        }

        messageHandlers.add((Channel<Message>) channel);
    }
}
