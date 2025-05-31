package com.aport.strategy.file;

import com.aport.user.Customer;
import com.aport.user.User;
import com.aport.service.UserService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerFileStrategy implements FileStrategy {

    @Override
    public void save(String filePath) {
        // UserService에서 모든 사용자 정보를 가져옴
        UserService userService = UserService.getInstance();
        Map<String, User> userMap = userService.getUserMap();
        List<User> customers = new ArrayList<>(userMap.values());

        // 필터링하여 Customer 객체만 추출
        customers.removeIf(user -> !(user instanceof Customer));
        
        // 파일에 저장
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(customers);
            System.out.println("고객 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public boolean load(String filePath) {
        UserService userService = UserService.getInstance();
        Map<String, User> userMap = userService.getUserMap();
        // 파일에서 고객 데이터를 로드

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            List<User> customers = (List<User>) ois.readObject();
            for (User user : customers) {
                Customer customer = (Customer) user;
                userMap.put(customer.getId(), customer);
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
            return false;
        }
    }
}