package com.aport.flight.domain;

import java.io.Serializable;
import com.aport.common.Prototype;

public class Flight implements Serializable, Prototype<Flight> {
    private String flightNumber;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private int price;

    public Flight(String flightNumber, String departure, String arrival, String departureTime, String arrivalTime, int price) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public String getFlightInfo() {
        return String.format("%s: %s -> %s, %s ~ %s, %d원",
        		getFlightNumber(), getDeparture(), getArrival(), getDepartureTime(), getArrivalTime(), getPrice());
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDestination() {
        return arrival;
    }

    public void setDestination(String destination) {
        this.arrival = destination;
    }

    @Override
    public Flight copy() {
        return new Flight(flightNumber, departure, arrival, departureTime, arrivalTime, price);
    }
}
