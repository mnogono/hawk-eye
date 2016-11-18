package com.wildcat.ui.base.layout;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class AnyVerticalLayout extends VerticalLayout implements AnyLayout {
    @Override
    public void create() {
        for (int i = 0; i < getComponentCount(); ++i) {
            Component component = getComponent(i);
            if (component instanceof AnyLayout) {
                ((AnyLayout) component).create();
            }
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < getComponentCount(); ++i) {
            Component component = getComponent(i);
            if (component instanceof AnyLayout) {
                ((AnyLayout) component).update();
            }
        }
    }
}
