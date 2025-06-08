package com.aport.flight.builder;

import com.aport.flight.domain.FlightNotice;

public class FlightNoticeDirector {
    private FlightNoticeBuilder flightNoticeBuilder;

    public void setFlightNoticeBuilder(FlightNoticeBuilder flightNoticeBuilder) {
        this.flightNoticeBuilder = flightNoticeBuilder;
    }

    public FlightNotice construct(){
        flightNoticeBuilder.create()
                .title()
                .message();
        return flightNoticeBuilder.getFlightNotice();
    }
}
