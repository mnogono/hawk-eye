package com.wildcat.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;


public class LoginUI extends UI {
    @WebServlet(name = "LoginUI Servlet", value = {"/login/*"}, asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = LoginUI.class)
    public static class LoginUIServlet extends VaadinServlet {}

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new VerticalLayout(new Label("Simple Vaadin Application")));
    }
}
