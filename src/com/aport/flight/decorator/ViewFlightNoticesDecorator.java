package com.aport.flight.decorator;

import com.aport.common.Decorator;
import com.aport.common.command.Command;
import com.aport.flight.domain.FlightNotice;
import java.util.List;
import com.aport.user.domain.Customer;
import com.aport.user.service.UserService;

public class ViewFlightNoticesDecorator extends Decorator {

    public ViewFlightNoticesDecorator(Command command) {
        super(command);
    }

    @Override
    public Object execute() {
        view();
        return super.execute();
    }

    public void view() {
        System.out.println("\n=== 항공편 공지사항 목록 ===");
        Customer currentUser = (Customer) UserService.getInstance().getCurrentUser();
        if (currentUser == null) {
            System.out.println("[오류] 현재 사용자가 없습니다. 로그인 후 다시 시도하세요.");
            return;
        }
        List<FlightNotice> notices = currentUser.getFlightNotices();
        if (notices == null || notices.isEmpty()) {
            System.out.println("등록된 공지사항이 없습니다.");
            return;
        }
        for (int i = 0; i < notices.size(); i++) {
            System.out.println((i + 1) + ". " + notices.get(i).getTitle());
            System.out.println("   내용: " + notices.get(i).getMessage());
        }
    }
    
}
