package com.wildcat.event.dashboard;

import com.event.driven.util.Message;
import com.wildcat.db.data.model.Sample;
import com.wildcat.event.EventSampleSelect;

public class EventDashboardSampleSelect extends EventSampleSelect {

    public EventDashboardSampleSelect(Sample sample) {
        super(sample);
    }

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
