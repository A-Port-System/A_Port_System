package com.aport.user.strategy;

import com.aport.app.InputUtil;
import com.aport.file.service.FileService;
import com.aport.user.domain.User;
import com.aport.user.domain.UserType;
import com.aport.user.service.UserService;
import java.util.Map;

public class AgencySignupStrategy implements SignupStrategy {

    @Override
    public void signUp() {
        Map<String, User> userMap = UserService.getInstance().getUserMap();

        String id = InputUtil.readLine("아이디(이메일): ");
        if (userMap.containsKey(id)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return;
        }
        String password = InputUtil.readLine("비밀번호: ");
        String agencyName = InputUtil.readLine("에이전시명: ");
        String contactPhone = InputUtil.readLine("담당자 연락처: ");
        String contactEmail = InputUtil.readLine("담당자 이메일: ");

        User user = new User.Builder()
    	    .userType(UserType.AGENCY)
            .id(id)
            .password(password)
            .agencyName(agencyName)
            .phoneNumber(contactPhone)
            .email(contactEmail)
            .build();
        userMap.put(id, user);

        FileService fileService = FileService.getInstance();
        fileService.save();

        System.out.println("회원가입 완료!");
    }
}