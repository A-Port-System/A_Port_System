package com.aport.app;
import com.aport.file.service.FileService;
import com.aport.user.service.UserService;
import com.aport.user.state.UserState;
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
        File dataDir = new File("data");

        if (!dataDir.exists()) {
            boolean created = dataDir.mkdirs();
            if (created) {
                System.out.println("data 폴더가 존재하지 않아 새로 생성했습니다.");
            } else {
                System.err.println("data 폴더를 생성하지 못했습니다.");
                return;
            }
        }

        FileService fileService = FileService.getInstance();
        fileService.load();
    }


    private static void printHeader() {
        System.out.println("\n=== A Port 시스템 ===");
    }
}