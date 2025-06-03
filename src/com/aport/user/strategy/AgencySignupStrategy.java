package com.aport.user.strategy;

import com.aport.app.InputUtil;
import com.aport.file.service.FileService;
import com.aport.file.strategy.AgencyFileStrategy;
import com.aport.file.strategy.FileStrategy;

import java.util.Map;

import com.aport.user.domain.Agency;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;

import java.io.*;

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
        String name = InputUtil.readLine("이름: ");
        String agencyCode = InputUtil.readLine("에이전시 코드: ");
        String agencyName = InputUtil.readLine("에이전시명: ");
        String agencyType = InputUtil.readLine("에이전시 유형: ");
        String contactPerson = InputUtil.readLine("담당자명: ");
        String contactPhone = InputUtil.readLine("담당자 연락처: ");
        String contactEmail = InputUtil.readLine("담당자 이메일: ");
        String address = InputUtil.readLine("주소: ");
        String registrationNumber = InputUtil.readLine("사업자등록번호: ");
        boolean isPartner = InputUtil.readLine("파트너 여부(Y/N): ").equalsIgnoreCase("Y");

        User user = new Agency.Builder()
            .username(id)
            .password(password)
            .name(name)
            .agencyCode(agencyCode)
            .agencyName(agencyName)
            .agencyType(agencyType)
            .contactPerson(contactPerson)
            .contactPhone(contactPhone)
            .contactEmail(contactEmail)
            .address(address)
            .registrationNumber(registrationNumber)
            .isPartner(isPartner)
            .build();
        userMap.put(id, user);

        FileStrategy fileStrategy = new AgencyFileStrategy();
        FileService fileService = FileService.getInstance(fileStrategy);
        fileService.save(new File("data/agency_data.dat").getAbsolutePath());

        System.out.println("회원가입 완료!");
    }
}