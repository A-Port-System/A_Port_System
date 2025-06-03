package com.aport.file.strategy;

import com.aport.user.domain.Officer;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OfficerFileStrategy implements FileStrategy {

    @Override
    public void save(String filePath) {
        UserService userService = UserService.getInstance();
        Map<String, User> userMap = userService.getUserMap();
        List<User> officers = new ArrayList<>(userMap.values());

        officers.removeIf(user -> !(user instanceof Officer));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(officers);
            System.out.println("직원 데이터가 저장되었습니다.");
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
            List<Officer> officers = (List<Officer>) ois.readObject();
            for (Officer officer : officers) {
                userMap.put(officer.getId(), officer);
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
            return false;
        }
    }
}
