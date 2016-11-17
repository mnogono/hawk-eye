package com.wildcat.ui.hawk.menubar;

import com.vaadin.server.Page;
import com.vaadin.ui.MenuBar;

public class HawkMenuBar extends MenuBar {
    public HawkMenuBar() {
        addItem("Dashboard", menuItem -> {

        });

        addItem("Methods", menuItem -> {
            Page.getCurrent().setLocation("/methods");
        });

        addItem("Calibration", menuItem -> {
            Page.getCurrent().setLocation("/calibrations");
        });

        MenuItem reports = addItem("Reports", null);
    }
}
