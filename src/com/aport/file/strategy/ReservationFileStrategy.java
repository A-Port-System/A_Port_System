package com.aport.file.strategy;

import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;

import java.io.*;
import java.util.List;

public class ReservationFileStrategy implements FileStrategy {

    @Override
    public void save(String filePath) {
        ReservationService reservationService = ReservationService.getInstance();
        List<Reservation> reservations = reservationService.getReservations();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(reservations);
            System.out.println("예약 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public boolean load(String filePath) {
        ReservationService reservationService = ReservationService.getInstance();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            List<Reservation> reservations = (List<Reservation>) ois.readObject();
            for (Reservation reservation : reservations) {
                reservationService.addReservation(reservation);
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
            return false;
        }
    }
}
