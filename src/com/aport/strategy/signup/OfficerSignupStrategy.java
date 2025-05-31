package com.aport.strategy.signup;

import com.aport.app.InputUtil;
import com.aport.service.FileService;
import com.aport.service.UserService;
import com.aport.strategy.file.OfficerFileStrategy;
import com.aport.strategy.file.FileStrategy;
import com.aport.user.Officer;
import com.aport.user.User;

import java.io.File;
import java.util.Map;

public class OfficerSignupStrategy implements SignupStrategy {

    @Override
    public void signUp() {
        Map<String, User> userMap = UserService.getInstance().getUserMap();

        String id = InputUtil.readLine("아이디(이메일): ");
        if (userMap.containsKey(id)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return;
        }
        String password = InputUtil.readLine("비밀번호: ");
        String name = InputUtil.readLine("이름: ");
        String department = InputUtil.readLine("부서: ");
        String position = InputUtil.readLine("직위: ");
        String officeNumber = InputUtil.readLine("사무실 번호: ");
        String phoneNumber = InputUtil.readLine("전화번호: ");
        String email = InputUtil.readLine("이메일: ");
        String hireDate = InputUtil.readLine("입사일: ");
        boolean isAdmin = InputUtil.readLine("관리자 여부(Y/N): ").equalsIgnoreCase("Y");

        User user = new Officer.Builder()
            .username(id)
            .password(password)
            .name(name)
            .department(department)
            .position(position)
            .officeNumber(officeNumber)
            .phoneNumber(phoneNumber)
            .email(email)
            .hireDate(hireDate)
            .isAdmin(isAdmin)
            .build();
        userMap.put(id, user);

        FileStrategy fileStrategy = new OfficerFileStrategy();
        FileService fileService = FileService.getInstance(fileStrategy);
        fileService.save(new File("data/officer_data.dat").getAbsolutePath());
        System.out.println("회원가입 완료!");
    }
}