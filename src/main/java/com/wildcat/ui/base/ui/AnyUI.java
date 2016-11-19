package com.wildcat.ui.base.ui;

import com.vaadin.server.VaadinRequest;

public interface AnyUI {
    void create(VaadinRequest vaadinRequest);

    void update(VaadinRequest vaadinRequest);
}
