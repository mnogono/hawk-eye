package com.wildcat.ui.dashboard;

import com.vaadin.ui.Table;
import com.wildcat.db.data.model.Sample;
import com.wildcat.db.mongodb.DbClient;
import com.wildcat.ui.base.ui.AnyUI;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.tylproject.vaadin.addon.MongoContainer;

import java.net.UnknownHostException;

public class SampleGrid extends Table implements AnyUI {
    MongoContainer<Sample> container;

    protected MongoContainer.Builder<Sample> mongoBuilder() throws UnknownHostException {
        MongoOperations mongoOperations = new MongoTemplate(DbClient.get(), DbClient.dbName);

        return MongoContainer.Builder
                .forEntity(Sample.class, mongoOperations)
                .withProperty("name");
    }

    @Override
    public void create() {
        setSelectable(true);
        setSizeFull();

        try {
            container = mongoBuilder().build();
            setContainerDataSource(container);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        container.refresh();
    }
}
