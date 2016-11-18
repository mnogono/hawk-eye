package com.event.driven.util;

public interface Channel<E extends Message> {
    void dispatch(E message);
}
