package com.event.driven.util;

public interface Message {
    Class<? extends Message> getType();
}
