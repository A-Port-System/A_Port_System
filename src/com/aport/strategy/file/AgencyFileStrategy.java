package com.aport.strategy.file;

import com.aport.user.Agency;
import com.aport.user.User;
import com.aport.service.UserService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AgencyFileStrategy implements FileStrategy {

    @Override
    public void save(String filePath) {
        UserService userService = UserService.getInstance();
        Map<String, User> userMap = userService.getUserMap();
        List<User> agencies = new ArrayList<>(userMap.values());

        agencies.removeIf(user -> !(user instanceof Agency));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(agencies);
            System.out.println("대행사 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public boolean load(String filePath) {
        UserService userService = UserService.getInstance();
        Map<String, User> userMap = userService.getUserMap();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            List<Agency> agencies = (List<Agency>) ois.readObject();
            for (Agency agency : agencies) {
                userMap.put(agency.getId(), agency);
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
            return false;
        }
    }
}
