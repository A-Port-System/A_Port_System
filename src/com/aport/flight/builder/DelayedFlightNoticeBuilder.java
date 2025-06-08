package com.aport.flight.builder;

public class DelayedFlightNoticeBuilder extends FlightNoticeBuilder{
    @Override
    public FlightNoticeBuilder title() {
        flightNotice.setTitle("항공편 지연 안내");
        return this;
    }

    @Override
    public FlightNoticeBuilder message() {
        flightNotice.setMessage("항공편이 지연되었습니다. 공항 모니터에서 업데이트를 확인하시기 바랍니다.");
        return this;
    }
}
