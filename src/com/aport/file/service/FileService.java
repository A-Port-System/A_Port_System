package com.aport.file.service;

import com.aport.flight.service.FlightService;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.user.service.UserService;
import java.io.*;
import java.util.*;

public class FileService {
    private static FileService instance;
    private String filePath = "data/data.ser"; // 기본 파일 경로

    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    public void save() {
        Map<String, Object> all = new HashMap<>();
        all.put("users", UserService.getInstance());
        all.put("reservations", ReservationService.getInstance());
        all.put("flights", FlightService.getInstance());
        all.put("reservation_counter", Reservation.getReservationCounter());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(all);
            oos.close();
            System.out.println("데이터가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();  // 또는 로깅/에러 처리
        }
    }

    public boolean load() {
        if (!new File(filePath).exists()) {
            return false; // 파일이 없으면 초기화 실패
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            @SuppressWarnings("unchecked")
            Map<String, Object> all = (Map<String, Object>) ois.readObject();
            UserService.setInstance((UserService) all.get("users"));
            ReservationService.setInstance((ReservationService) all.get("reservations"));
            FlightService.setInstance((FlightService) all.get("flights"));
            Reservation.setReservationCounter((Integer) all.get("reservation_counter"));
            ois.close();
            System.out.println("데이터가 성공적으로 로드되었습니다.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();  // 또는 로깅/에러 처리
            return false;
        }
        return true;
    }
}
