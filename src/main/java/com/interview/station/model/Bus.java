package com.interview.station.model;

import com.interview.station.utils.DelayUtils;
import com.interview.station.utils.RandomUtils;

/**
 * Created by gaolp on 2016/9/15.
 */
public class Bus implements IStationInfo {
    private int no;
    private int distance;
    private static int noCounter = 1;
    private Bus nextBus = null;
    private boolean awaitFlag = false;
    private int awaitDistance = 0;

    public Bus() {
        no = noCounter++;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Bus getNextBus() {
        return nextBus;
    }

    public void setNextBus(Bus nextBus) {
        this.nextBus = nextBus;
    }

    public boolean isAwaitFlag() {
        return awaitFlag;
    }

    public void setAwaitFlag(boolean awaitFlag) {
        this.awaitFlag = awaitFlag;
    }

    public int getAwaitDistance() {
        return awaitDistance;
    }

    public void setAwaitDistance(int awaitDistance) {
        this.awaitDistance = awaitDistance;
    }

    public synchronized boolean drive() {
        while (awaitFlag && distance == awaitDistance) {
            DelayUtils.delay();
        }
        boolean arriveStation = false;
        int oldStationNo = distance / EACH_STATION_DISTANCE;
        distance += RandomUtils.randomInt(20) + 1;
        int newStationNo = distance / EACH_STATION_DISTANCE;
        if (newStationNo != oldStationNo) {
            distance = newStationNo * EACH_STATION_DISTANCE;
            arriveStation = true;
            if (nextBus != null) {
                nextBus.awaitFlag = true;
                nextBus.awaitDistance = oldStationNo * EACH_STATION_DISTANCE;
            }
        }
        return arriveStation;
    }
}
