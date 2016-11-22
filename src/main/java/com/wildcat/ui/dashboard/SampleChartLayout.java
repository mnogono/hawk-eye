package com.wildcat.ui.dashboard;

import ChartDirector.Chart;
import ChartDirector.XYChart;
import com.event.driven.util.Channel;
import com.event.driven.util.MessageDispatcher;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.wildcat.db.data.model.Sample;
import com.wildcat.event.dashboard.EventDashboardSampleDelete;
import com.wildcat.event.dashboard.EventDashboardSampleSelect;
import com.wildcat.ui.base.layout.AnyVerticalLayout;
import com.wildcat.ui.image.source.SampleImageSource;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class SampleChartLayout extends AnyVerticalLayout {
    private class DashboardSampleSelectHandler implements Channel<EventDashboardSampleSelect> {
        private VaadinRequest vaadinRequest;

        @Override
        public void dispatch(EventDashboardSampleSelect event) {
//            sample = message.getSample();
//
//            // The data for the line chart
//            double[] data = {30, 28, 40, 55, 75, 68, 54, 60, 50, 62, 75, 65, 75, 91, 60, 55, 53, 35, 50,
//                    66, 56, 48, 52, 65, 62};
//
//            // The labels for the line chart
//            String[] labels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
//                    "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
//
//            // Create a XYChart object of size 250 x 250 pixels
//            XYChart c = new XYChart(250, 250);
//
//            // Set the plotarea at (30, 20) and of size 200 x 200 pixels
//            c.setPlotArea(30, 20, 200, 200);
//
//            // Add a line chart layer using the given data
//            c.addLineLayer(data);
//
//            // Set the labels on the x axis.
//            c.xAxis().setLabels(labels);
//
//            // Display 1 out of 3 labels on the x-axis.
//            c.xAxis().setLabelStep(3);
//
//            //java.awt.Image image = c.makeImage();
//
//            //c.makeSession(vaadinRequest.getWrappedSession().getSe)
//
//            HttpServletRequest httpServletRequest = ((VaadinServletRequest) vaadinRequest.getService().getCurrentRequest()).getHttpServletRequest();
//            String uuid = UUID.randomUUID().toString();
//            String imageUrl = c.makeSession(httpServletRequest, uuid, Chart.PNG);
//
//            labelImage.setValue("<img src=\"/chart-image?" + imageUrl + "></img>");
//            labelImage.markAsDirty();

            Sample sample = event.getSample();
//            ((SampleImageSource)((StreamResource) image.getSource()).getStreamSource()).setSample(sample);
//            image.markAsDirty();

            imageLayout.removeAllComponents();

            String uuid = UUID.randomUUID().toString();
            StreamResource streamResource = new StreamResource(new SampleImageSource(sample), uuid);
            image = new Image(uuid, streamResource);
            image.setImmediate(true);
            //image.markAsDirty();
            imageLayout.addComponent(image);
        }

        public void setVaadinRequest(VaadinRequest vaadinRequest) {
            this.vaadinRequest = vaadinRequest;
        }
    }

    private class DashboardSampleDeleteHandler implements Channel<EventDashboardSampleDelete> {
        @Override
        public void dispatch(EventDashboardSampleDelete message) {
            sample = message.getSample();
        }
    }

    private Sample sample;
    //private Label labelImage;
    //private Embedded embeddedSampleImage;
    private Image image;
    private VerticalLayout imageLayout;

    public SampleChartLayout() {
        DashboardSampleDeleteHandler dashboardSampleDeleteHandler = new DashboardSampleDeleteHandler();
        DashboardSampleSelectHandler dashboardSampleSelectHandler = new DashboardSampleSelectHandler();

        MessageDispatcher.getInstance().registerChannel(EventDashboardSampleSelect.class, dashboardSampleSelectHandler);
        MessageDispatcher.getInstance().registerChannel(EventDashboardSampleDelete.class, dashboardSampleDeleteHandler);
    }

    @Override
    public void create(VaadinRequest vaadinRequest) {
        //image = new Image("chart", new StreamResource());
        //image.setImmediate(true);
        //image.setSizeFull();
        //StreamResource streamResource = new StreamResource();


        //labelImage = new Label("<img></img>", ContentMode.HTML);
        //labelImage.setImmediate(true);
        imageLayout = new VerticalLayout();
        addComponent(imageLayout);
    }

    @Override
    public void update(VaadinRequest vaadinRequest) {

    }
}
