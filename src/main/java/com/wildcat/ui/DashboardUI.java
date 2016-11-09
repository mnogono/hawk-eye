package com.wildcat.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;

public class DashboardUI extends UI {

    @WebServlet(name = "DashboardUI Servlet", value = {"/dashboard/*"}, asyncSupported = true)
    @ServletSecurity(@HttpConstraint(rolesAllowed={"tomcat"}))
    @VaadinServletConfiguration(productionMode = false, ui = DashboardUI.class)
    public static class DashboardUIServlet extends VaadinServlet {}

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Button logout = new Button("logout", (e) -> {
            getSession().getSession().invalidate();
        });

        setContent(new VerticalLayout(new Label("Dashboard"), logout));
    }
}
