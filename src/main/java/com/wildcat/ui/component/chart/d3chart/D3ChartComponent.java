package com.wildcat.ui.component.chart.d3chart;

import com.vaadin.annotations.JavaScript;
import com.wildcat.ui.component.chart.ChartComponent;
import com.wildcat.ui.component.chart.ChartState;

@JavaScript({"d3chart.js", "d3chart-component.js"})
public class D3ChartComponent extends ChartComponent {
    @Override
    protected ChartState getState() {
        return (ChartState) super.getState();
    }
}
