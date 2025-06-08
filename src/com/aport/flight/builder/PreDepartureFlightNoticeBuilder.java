package com.aport.flight.builder;

public class PreDepartureFlightNoticeBuilder extends FlightNoticeBuilder {
    @Override
    public FlightNoticeBuilder title() {
        flightNotice.setTitle("탑승 전 안내");
        return this;
    }

    @Override
    public FlightNoticeBuilder message() {
        flightNotice.setMessage("탑승 전 필요한 절차를 확인하시기 바랍니다. 탑승구에서의 대기 시간을 줄이기 위해 미리 준비해 주세요.");
        return this;
    }
}
