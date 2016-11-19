package com.wildcat.ui.hawk.header;

import com.vaadin.server.VaadinRequest;
import com.wildcat.ui.base.layout.BaseHeader;
import com.wildcat.ui.hawk.menubar.HawkMenuBar;

public class HawkHeader extends BaseHeader {

    @Override
    public void create(VaadinRequest vaadinRequest) {
        update(vaadinRequest);
    }

    @Override
    public void update(VaadinRequest vaadinRequest) {
        HawkMenuBar menuBar = new HawkMenuBar();
        menuBar.setWidth(100, Unit.PERCENTAGE);

        removeAllComponents();

        addComponent(menuBar);
    }
}
