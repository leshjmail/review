package com.interview.station.domain;

import com.interview.station.model.Bus;
import com.interview.station.model.IStationInfo;
import com.interview.station.model.Station;
import com.interview.station.utils.DelayUtils;

import java.util.Set;

/**
 * Created by gaolp on 2016/9/15.
 */
public class BusRunningThread implements Runnable {
    private Bus bus = null;
    private Set<Station> stationList = null;

    public BusRunningThread(Bus bus, Set<Station> stationList) {
        this.bus = bus;
        this.stationList = stationList;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            DelayUtils.delay();
            boolean arriveStation = bus.drive();
            if (arriveStation) {
                DelayUtils.delayByTime(1000 * 2L);
                for (Station station : stationList) {
                    station.triggerNextBus();
                }
                if (bus.getNextBus() != null) {
                    bus.getNextBus().setAwaitFlag(false);
                }
            }
            if (bus.getDistance() >= IStationInfo.EACH_STATION_DISTANCE * BusSimulation.STATION_NUM) {
                for (Station station : stationList) {
                    station.triggerNextBus();
                }
                break;
            }
        }
    }
}
