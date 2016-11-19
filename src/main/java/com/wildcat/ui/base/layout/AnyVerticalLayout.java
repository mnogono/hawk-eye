package com.wildcat.ui.base.layout;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class AnyVerticalLayout extends VerticalLayout implements AnyLayout {
    @Override
    public void create(VaadinRequest vaadinRequest) {
        for (int i = 0; i < getComponentCount(); ++i) {
            Component component = getComponent(i);
            if (component instanceof AnyLayout) {
                ((AnyLayout) component).create(vaadinRequest);
            }
        }
    }

    @Override
    public void update(VaadinRequest vaadinRequest) {
        for (int i = 0; i < getComponentCount(); ++i) {
            Component component = getComponent(i);
            if (component instanceof AnyLayout) {
                ((AnyLayout) component).update(vaadinRequest);
            }
        }
    }
}
