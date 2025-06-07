package com.aport.user.domain;

import com.aport.common.Observer;
import com.aport.flight.domain.FlightNotice;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Observer {
    private List<FlightNotice> flightNotices = new ArrayList<>();

    private Customer(Builder builder) {
        super(builder);
    }

    public static Customer of(Builder builder) {
        return new Customer(builder);
    }
    @Override
    public void update(FlightNotice notice) {
        flightNotices.add(notice);
    }

    public List<FlightNotice> getFlightNotices() {
        return flightNotices;
    }
}