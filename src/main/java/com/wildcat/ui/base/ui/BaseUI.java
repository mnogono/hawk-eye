package com.wildcat.ui.base.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.wildcat.ui.base.layout.AnyLayout;
import com.wildcat.ui.base.ui.AnyUI;

/**
 * Base 3 layout UI header, content and footer
 * override createHeader and createFooter function if needed
 * invoke function init when need to create ui
 */
public abstract class BaseUI extends UI implements AnyUI {
    protected AnyLayout header;
    protected AnyLayout footer;
    protected AnyLayout content;

    /**
     * main function to start build UI
     */
    public void create(VaadinRequest vaadinRequest) {
        header = createHeader(vaadinRequest);
        header.create(vaadinRequest);

        content = createContent(vaadinRequest);
        content.create(vaadinRequest);

        footer = createFooter(vaadinRequest);
        footer.create(vaadinRequest);

        VerticalLayout verticalContent = new VerticalLayout();
        verticalContent.setSizeFull();

        verticalContent.addComponent(header);
        verticalContent.addComponent(content);
        verticalContent.addComponent(footer);

        verticalContent.setExpandRatio(content, 1f);

        setContent(verticalContent);
    }

    @Override
    public void update(VaadinRequest vaadinRequest) {
        header.update(vaadinRequest);
        content.update(vaadinRequest);
        footer.update(vaadinRequest);
    }

    public abstract AnyLayout createHeader(VaadinRequest vaadinRequest);

    public abstract AnyLayout createContent(VaadinRequest vaadinRequest);

    public abstract AnyLayout createFooter(VaadinRequest vaadinRequest);
}
