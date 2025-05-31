package com.aport.strategy.file;

import com.aport.flight.Flight;
import com.aport.service.FlightService;
import java.io.*;
import java.util.List;

public class FlightFileStrategy implements FileStrategy {

    @Override
    public void save(String filePath) {
        FlightService flightService = FlightService.getInstance();
        List<Flight> flights = flightService.getFlights();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(flights);
            System.out.println("항공편 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public boolean load(String filePath) {
        FlightService flightService = FlightService.getInstance();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            List<Flight> flights = (List<Flight>) ois.readObject();
            for(Flight flight : flights) {
                flightService.addFlight(flight);
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
            return false;
        }
    }
}
