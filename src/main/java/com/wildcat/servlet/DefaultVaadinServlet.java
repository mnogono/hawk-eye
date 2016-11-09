package com.wildcat.servlet;

import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "Default Vaadin Servlet", value = {"/VAADIN/*"}, asyncSupported = true)
public class DefaultVaadinServlet extends VaadinServlet {}
