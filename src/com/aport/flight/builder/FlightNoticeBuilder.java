package com.aport.flight.builder;

import com.aport.flight.domain.FlightNotice;

public abstract class FlightNoticeBuilder {
    protected FlightNotice flightNotice;

    public FlightNotice getFlightNotice() {
        return flightNotice;
    }

    public FlightNoticeBuilder create(){
        flightNotice = new FlightNotice();
        return this;
    }

    public abstract FlightNoticeBuilder title();
    public abstract FlightNoticeBuilder message();
}
