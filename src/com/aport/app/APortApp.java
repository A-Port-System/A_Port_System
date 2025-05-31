package com.aport.app;
import com.aport.common.UserState;
import com.aport.file.service.FileService;
import com.aport.file.strategy.AgencyFileStrategy;
import com.aport.file.strategy.CustomerFileStrategy;
import com.aport.file.strategy.FileStrategy;
import com.aport.file.strategy.FlightFileStrategy;
import com.aport.file.strategy.OfficerFileStrategy;
import com.aport.file.strategy.ReservationFileStrategy;
import com.aport.user.service.UserService;

import java.io.File;
import java.util.Scanner;

public class APortApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        setup();
        while (true) {
            printHeader();
            UserState state = UserService.getInstance().getState();
            state.handleMenu();
        }
    }

    private static void setup() {
        InputUtil.setScanner(scanner);
        FileService fileService = FileService.getInstance();
        for(File file : new File("data").listFiles()){
            System.out.println("파일 로드: " + file.getName());
            String name = file.getName();
            FileStrategy fileStrategy = null;
            if (name.startsWith("customer")) {
                fileStrategy = new CustomerFileStrategy();
            } else if (name.startsWith("officer")) {
                fileStrategy = new OfficerFileStrategy();
            } else if (name.startsWith("agency")) {
                fileStrategy = new AgencyFileStrategy();
            } else if (name.startsWith("flight")) {
                fileStrategy = new FlightFileStrategy();
            } else if (name.startsWith("reservation")) {
                fileStrategy = new ReservationFileStrategy();
            } 
            fileService.setStrategy(fileStrategy);
            fileService.load(file.getAbsolutePath());
        }
    }

    private static void printHeader() {
        System.out.println("\n=== A Port 시스템 ===");
    }
}