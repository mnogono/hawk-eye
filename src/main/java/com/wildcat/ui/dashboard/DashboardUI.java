package com.wildcat.ui.dashboard;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.wildcat.ui.base.layout.AnyHorizontalLayout;
import com.wildcat.ui.base.layout.AnyLayout;
import com.wildcat.ui.base.layout.AnyVerticalLayout;
import com.wildcat.ui.base.ui.BaseUI;
import com.wildcat.ui.component.chart.ChartComponent;
import com.wildcat.ui.component.chart.d3chart.D3ChartComponent;
import com.wildcat.ui.hawk.base.ui.HawkBaseUI;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import java.net.UnknownHostException;

//@PreserveOnRefresh
public class DashboardUI extends HawkBaseUI {
    @WebServlet(name = "DashboardUI Servlet", value = {"/dashboard/*"}, asyncSupported = true)
    //@ServletSecurity(@HttpConstraint(rolesAllowed={"tomcat"}))
    @VaadinServletConfiguration(productionMode = false, ui = DashboardUI.class)
    public static class DashboardUIServlet extends VaadinServlet {}

//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//        //super.init(vaadinRequest);
//
//        VerticalLayout content = new VerticalLayout();
//        content.setSizeFull();
//
//        Button buttonA  = new Button("button a");
//        DragAndDropWrapper dragAndDropWrapperButtonA = new DragAndDropWrapper(buttonA);
//        dragAndDropWrapperButtonA.setDragStartMode(DragAndDropWrapper.DragStartMode.WRAPPER);
//
//        content.addComponent(dragAndDropWrapperButtonA);
//
//        content.addComponent(new Label("asd"));
//
//        Panel drop = new Panel("Panel");
//        drop.setWidth(100, Unit.PIXELS);
//        drop.setHeight(100, Unit.PIXELS);
//
//        content.addComponent(drop);
//
//
//
//        //SampleDropBox dropBox = new SampleDropBox(drop);
//
//        setContent(content);
//
//
//    }

//    @Override
//    protected void refresh(VaadinRequest request) {
//        //super.refresh(request);
//    }

    @Override
    public AnyLayout createContent(VaadinRequest vaadinRequest) {

        SampleGridLayout sampleGridLayout = new SampleGridLayout();
        SampleChartLayout sampleChartLayout = new SampleChartLayout();

        //sampleGridLayout.addListener(sampleChartLayout);

        AnyVerticalLayout layout = new AnyVerticalLayout();
        layout.addComponent(sampleGridLayout);
        layout.addComponent(sampleChartLayout);

        ChartComponent chartComponent = new D3ChartComponent();
        layout.addComponent(chartComponent);

        //sampleGridLayout.create();

        //content.addComponent(sampleGridLayout);
        /*
        try {
            SampleGrid sampleGrid = new SampleGrid();
            sampleGrid.setSizeFull();
            sampleGrid.create();
            //sampleGrid.update();

            content.setSizeFull();

            content.addComponent(sampleGrid);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        */

        return layout;
    }
}
