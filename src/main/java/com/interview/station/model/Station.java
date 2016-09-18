package com.interview.station.model;

/**
 * Created by gaolp on 2016/9/15.
 */
public class Station implements IStationInfo {
    private int no;
    private int position;
    private static int noCounter = 1;
    private Bus bus;

    public Station() {
        no = noCounter++;
        position = no * EACH_STATION_DISTANCE;
    }

    public int getNo() {
        return no;
    }

    public int getPosition() {
        return position;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String showMsg() {
        if (bus == null) {
            return "结束运营";
        }
        int curBusDistance = bus.getDistance();
        int curNo = curBusDistance / EACH_STATION_DISTANCE + (curBusDistance % EACH_STATION_DISTANCE == 0 ? 0 : 1);
        if (curNo < no) {
            return (no - curNo) + "站后到达";
        }

        int distancDiff = curBusDistance - position;
        if (distancDiff < 0) {
            return -distancDiff + "米后到达";
        } else if (distancDiff == 0) {
            return "已到站";
        }
        return "结束运营";
    }

    public void triggerNextBus() {
        if (bus == null) {
            return;
        }

        int curBusDistance = bus.getDistance();
        int distancDiff = bus.getDistance() - position;
        if (distancDiff >= 0) {
            bus = bus.getNextBus();
        }
    }
}
