package com.wildcat.event;

import com.wildcat.db.data.model.Sample;

public abstract class EventSampleDelete extends EventSampleSelect {
    public EventSampleDelete(Sample sample) {
        super(sample);
    }
}
