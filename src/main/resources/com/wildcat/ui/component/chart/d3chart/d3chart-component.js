window.com_wildcat_ui_component_chart_d3chart_D3ChartComponent =
    function() {
        // Create the component
        var chart = new d3chart.Chart(this.getElement());

        // Handle changes from the server-side
        this.onStateChange = function() {
            chart.onStateChange(this.getState().value);
        };

        // Pass user interaction to the server-side
        /*
        var self = this;
        mycomponent.click = function() {
            self.onClick(mycomponent.getValue());
        };
        */
    };
