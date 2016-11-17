package com.wildcat.ui.base.ui;

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
    public void create() {
        header = createHeader();
        header.create();

        content = createContent();
        content.create();

        footer = createFooter();
        footer.create();

        VerticalLayout verticalContent = new VerticalLayout();
        verticalContent.setSizeFull();

        verticalContent.addComponent(header);
        verticalContent.addComponent(content);
        verticalContent.addComponent(footer);

        verticalContent.setExpandRatio(content, 1f);

        setContent(verticalContent);
    }

    @Override
    public void update() {
        header.update();
        content.update();
        footer.update();
    }

    public abstract AnyLayout createHeader();

    public abstract AnyLayout createContent();

    public abstract AnyLayout createFooter();
}
