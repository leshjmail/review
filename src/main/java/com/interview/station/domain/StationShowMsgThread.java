package com.interview.station.domain;

import com.interview.station.utils.DelayUtils;

import java.util.Map;

/**
 * Created by gaolp on 2016/9/15.
 */
public class StationShowMsgThread implements Runnable {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            DelayUtils.delay();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Integer, String> entry : BusSimulation.stationMsg.entrySet()) {
                sb.append("站台" + entry.getKey()).append(":").append(entry.getValue()).append("\n");
            }
            System.out.println(sb.toString());
            if (BusSimulation.endStationCounter == BusSimulation.STATION_NUM) {
                DelayUtils.delay();
                break;
            }
        }
    }
}
