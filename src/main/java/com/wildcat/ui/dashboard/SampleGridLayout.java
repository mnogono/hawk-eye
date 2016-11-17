package com.wildcat.ui.dashboard;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.ui.base.layout.AnyVerticalLayout;
import com.wildcat.ui.dashboard.listener.SampleSelectListener;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
//import org.springframework.context.ApplicationContext;

public class SampleGridLayout extends AnyVerticalLayout {
    List<SampleSelectListener> sampleSelectListeners = new LinkedList<>();



//    private ProgressIndicator progress;

//    public DragDropHtml5ToFromDesktopExample() {
//        addComponent(new Label(
//                "Drag text from a desktop application or an image file "
//                        + "from the file system to the drop box below.<br/> "
//                        + "Dragging files requires an HTML5 capable browser "
//                        + "like Firefox, Safari or Chrome.<br/> "
//                        + "You can also drag the drop box to a desktop "
//                        + "application, getting either HTML or plain text "
//                        + "depending on the application.", Label.CONTENT_XHTML));
//
//        CssLayout dropPane = new CssLayout();
//        dropPane.setWidth("200px");
//        dropPane.setHeight("200px");
//        dropPane.addStyleName("image-drop-pane");
//
//        SampleDropBox dropBox = new SampleDropBox(dropPane);
//        dropBox.setSizeUndefined();
//
//        Panel panel = new Panel(dropBox);
//        panel.setSizeUndefined();
//        panel.addStyleName("no-vertical-drag-hints");
//        panel.addStyleName("no-horizontal-drag-hints");
//        addComponent(panel);
//
//        progress = new ProgressIndicator();
//        progress.setIndeterminate(true);
//        progress.setVisible(false);
//        addComponent(progress);
//    }
//
//    @Override
//    public void attach() {
//        super.attach();
//
//        // warn the user if the browser does not support file drop
//
//
//        ApplicationContext context =  getApplication().getContext();
//        if (context instanceof AbstractWebApplicationContext) {
//            WebBrowser webBrowser = ((AbstractWebApplicationContext) context)
//                    .getBrowser();
//            // FF
//            boolean supportsHtml5FileDrop = webBrowser.isFirefox()
//                    && (webBrowser.getBrowserMajorVersion() >= 4 || (webBrowser
//                    .getBrowserMajorVersion() == 3 && webBrowser
//                    .getBrowserMinorVersion() >= 6));
//            if (!supportsHtml5FileDrop) {
//                /*
//                 * pretty much all chromes and safaris are new enough
//                 */
//
//                supportsHtml5FileDrop = webBrowser.isChrome()
//                        || webBrowser.isSafari()
//                        && webBrowser.getBrowserMajorVersion() > 4;
//            }
//            if (!supportsHtml5FileDrop) {
//                getWindow()
//                        .showNotification(
//                                "Image file drop is only supported on Firefox 3.6+, Chrome 6+, and Safari 5+. "
//                                        + "Text can be dropped into the box on other browsers.",
//                                Notification.TYPE_WARNING_MESSAGE);
//            }
//        }
//    }

    private SampleGrid sampleGrid;

    @Override
    public void create() {

        sampleGrid = new SampleGrid();
        sampleGrid.addItemSetChangeListener(new Container.ItemSetChangeListener() {
            @Override
            public void containerItemSetChange(Container.ItemSetChangeEvent event) {
                System.out.println("containerItemSetChange");
            }
        });
        sampleGrid.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                ObjectId id = (ObjectId) event.getProperty().getValue();
                Sample sample = null;
                if (id != null) {
                    sample = DbClient.getOperations().findById(id, Sample.class);
                }

                System.out.println(sample.getName());

                for (SampleSelectListener listener : sampleSelectListeners) {
                    listener.onSampleSelect(sample);
                }

            }
        });

        sampleGrid.create();

        setSizeFull();
        addComponent(sampleGrid);

        Panel panel = new Panel();
        panel.setWidth(250, Unit.PIXELS);
        panel.setHeight(250, Unit.PIXELS);
        panel.addStyleName("no-vertical-drag-hints");
        panel.addStyleName("no-horizontal-drag-hints");

        SampleDropBox dropBox = new SampleDropBox(panel);

        addComponent(dropBox);

        //addComponent(dropBox);
    }

    @Override
    public void update() {
        sampleGrid.update();
    }

    public void addListener(SampleSelectListener sampleSelectListener) {
        sampleSelectListeners.add(sampleSelectListener);
    }
}
