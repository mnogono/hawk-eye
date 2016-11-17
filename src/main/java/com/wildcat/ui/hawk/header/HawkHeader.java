package com.wildcat.ui.hawk.header;

import com.wildcat.ui.base.layout.BaseHeader;
import com.wildcat.ui.hawk.menubar.HawkMenuBar;

public class HawkHeader extends BaseHeader {

    @Override
    public void create() {
        update();
    }

    @Override
    public void update() {
        HawkMenuBar menuBar = new HawkMenuBar();
        menuBar.setWidth(100, Unit.PERCENTAGE);

        removeAllComponents();

        addComponent(menuBar);
    }
}
