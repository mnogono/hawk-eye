package com.wildcat.ui.dashboard;

import com.wildcat.db.data.model.Sample;
import com.wildcat.ui.base.layout.AnyVerticalLayout;
import com.wildcat.ui.dashboard.listener.SampleSelectListener;

public class SampleChartLayout extends AnyVerticalLayout implements SampleSelectListener {
    private Sample sample;

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void onSampleSelect(Sample sample) {
        this.sample = sample;
        update();
    }
}
