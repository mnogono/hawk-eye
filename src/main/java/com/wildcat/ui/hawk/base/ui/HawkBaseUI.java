package com.wildcat.ui.hawk.base.ui;

import com.vaadin.server.VaadinRequest;
import com.wildcat.ui.base.layout.AnyLayout;
import com.wildcat.ui.base.ui.BaseUI;
import com.wildcat.ui.hawk.footer.HawkFooter;
import com.wildcat.ui.hawk.header.HawkHeader;

public abstract class HawkBaseUI extends BaseUI {
    @Override
    public AnyLayout createHeader(VaadinRequest vaadinRequest) {
        return new HawkHeader();
    }

    @Override
    public AnyLayout createFooter(VaadinRequest vaadinRequest) {
        return new HawkFooter();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        create(vaadinRequest);
    }

    @Override
    protected void refresh(VaadinRequest vaadinRequest) {
        update(vaadinRequest);
    }
}
