package com.aport.common;

import com.aport.flight.domain.FlightNotice;

public interface Observer {
    void update(FlightNotice notice);
}
