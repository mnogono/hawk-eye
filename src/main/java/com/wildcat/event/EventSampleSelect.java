package com.wildcat.event;

import com.event.driven.util.Message;
import com.wildcat.db.data.model.Sample;

public abstract class EventSampleSelect implements Message{
    private Sample sample;

    public EventSampleSelect(Sample sample) {
        this.sample = sample;
    }

    public Sample getSample() {
        return sample;
    }
}
