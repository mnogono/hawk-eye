package com.wildcat.ui.dashboard;

import com.event.driven.util.MessageDispatcher;
import com.vaadin.ui.*;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.event.dashboard.EventDashboardSampleDelete;
import com.wildcat.event.dashboard.EventDashboardSampleSelect;
import com.wildcat.ui.base.layout.AnyVerticalLayout;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
import java.util.function.ObjIntConsumer;
//import org.springframework.context.ApplicationContext;

public class SampleGridLayout extends AnyVerticalLayout {
    private SampleGrid sampleGrid;
    private Button buttonSampleImport;
    private Button buttonSampleDelete;

    @Override
    public void create() {
        sampleGrid = new SampleGrid();
        sampleGrid.addValueChangeListener(event -> {
            ObjectId id = (ObjectId) event.getProperty().getValue();
            Sample sample = null;
            if (id != null) {
                sample = DbClient.getOperations().findById(id, Sample.class);
            }

            MessageDispatcher.getInstance().dispatch(new EventDashboardSampleSelect(sample));
        });

        sampleGrid.create();


        buttonSampleImport = new Button("Import", (event) -> {

        });
        buttonSampleImport.setImmediate(true);

        buttonSampleDelete = new Button("Delete", (event) -> {
            ObjectId id = (ObjectId) sampleGrid.getValue();
            if (id == null) {
                return;
            }

            Sample sample = DbClient.getOperations().findById(id, Sample.class);
            if (sample != null) {
                //TODO show messsage to delete sample
            }

            MessageDispatcher.getInstance().dispatch(new EventDashboardSampleDelete(sample));
        });

        HorizontalLayout sampleGridButtonsLayout = new HorizontalLayout(buttonSampleImport, buttonSampleDelete);
        sampleGridButtonsLayout.setWidthUndefined();

        setSizeFull();
        addComponent(sampleGridButtonsLayout);
        addComponent(sampleGrid);

        setExpandRatio(sampleGrid, 1f);

//        Panel panel = new Panel();
//        panel.setWidth(250, Unit.PIXELS);
//        panel.setHeight(250, Unit.PIXELS);
//        panel.addStyleName("no-vertical-drag-hints");
//        panel.addStyleName("no-horizontal-drag-hints");
//
//        SampleDropBox dropBox = new SampleDropBox(panel);

//        addComponent(dropBox);
    }

    @Override
    public void update() {
        sampleGrid.update();
    }
}
