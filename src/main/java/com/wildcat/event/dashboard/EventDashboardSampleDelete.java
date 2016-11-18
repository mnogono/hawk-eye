package com.wildcat.event.dashboard;

import com.event.driven.util.Message;
import com.wildcat.db.data.model.Sample;
import com.wildcat.event.EventSampleDelete;

public class EventDashboardSampleDelete extends EventSampleDelete {
    public EventDashboardSampleDelete(Sample sample) {
        super(sample);
    }

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
