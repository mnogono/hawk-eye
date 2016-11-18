package com.wildcat.ui.dashboard;

import com.event.driven.util.Channel;
import com.event.driven.util.MessageDispatcher;
import com.wildcat.db.data.model.Sample;
import com.wildcat.event.dashboard.EventDashboardSampleDelete;
import com.wildcat.event.dashboard.EventDashboardSampleSelect;
import com.wildcat.ui.base.layout.AnyVerticalLayout;


public class SampleChartLayout extends AnyVerticalLayout {
    private class DashboardSampleSelectHandler implements Channel<EventDashboardSampleSelect> {
        @Override
        public void dispatch(EventDashboardSampleSelect message) {
            sample = message.getSample();
        }
    }

    private class DashboardSampleDeleteHandler implements Channel<EventDashboardSampleDelete> {
        @Override
        public void dispatch(EventDashboardSampleDelete message) {
            sample = message.getSample();
        }
    }

    private Sample sample;

    public SampleChartLayout() {
        DashboardSampleDeleteHandler dashboardSampleDeleteHandler = new DashboardSampleDeleteHandler();
        DashboardSampleSelectHandler dashboardSampleSelectHandler = new DashboardSampleSelectHandler();

        MessageDispatcher.getInstance().registerChannel(EventDashboardSampleSelect.class, dashboardSampleSelectHandler);
        MessageDispatcher.getInstance().registerChannel(EventDashboardSampleDelete.class, dashboardSampleDeleteHandler);
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }
}
