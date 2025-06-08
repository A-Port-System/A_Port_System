package com.aport.flight.domain;

import com.aport.common.Prototype;
import com.aport.common.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Serializable, Prototype<Flight> {
    private String flightNumber;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private int price;
    private boolean[] seats = new boolean[150];


    private List<Observer> observers = new ArrayList<>();
    private List<FlightNotice> flightNotices = new ArrayList<>();

    public Flight(String flightNumber, String departure, String arrival, String departureTime, String arrivalTime, int price) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public boolean[] getSeats() {
        return seats;
    }

    public void reserveSeat(int index) {
        if (index < 0 || index >= seats.length) throw new IllegalArgumentException("좌석 인덱스 오류");
        seats[index] = true;
    }

    public boolean isSeatTaken(int index) {
        return seats[index];
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

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(FlightNotice notice) {
        flightNotices.add(notice);
        for (Observer observer : observers) {
            observer.update(notice);
        }
    }

    public List<FlightNotice> getFlightNotices() {
        return flightNotices;
    }
}
