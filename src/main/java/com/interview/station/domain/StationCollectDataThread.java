package com.interview.station.domain;

import com.interview.station.model.Station;
import com.interview.station.utils.DelayUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaolp on 2016/9/15.
 */
public class StationCollectDataThread implements Runnable {
    private Station station = null;

    public StationCollectDataThread(Station station) {
        this.station = station;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            DelayUtils.delay();
            String msg = station.showMsg();
            BusSimulation.stationMsg.put(station.getNo(), msg + (station.getBus() != null ? ",车号=" + station.getBus().getNo() : ""));
            if (station.getBus() == null) {
                msg = station.showMsg();
                BusSimulation.endStationCounter++;
                BusSimulation.stationMsg.put(station.getNo(), msg + (station.getBus() != null ? ",车号=" + station.getBus().getNo() : ""));
                break;
            }
        }
    }

    private void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            System.out.println("There is an error when sleep 500ms.");
        }
    }
}
