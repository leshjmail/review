package com.interview.station.domain;

import com.interview.station.model.Bus;
import com.interview.station.model.Station;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gaolp on 2016/9/15.
 */
public class BusSimulation {

    public static final int BUS_NUM = 1;
    public static final int STATION_NUM = 10;
    private List<Bus> busList = new ArrayList<Bus>();
    private Set<Station> stationList = new HashSet<Station>();
    public static Map<Integer, String> stationMsg = new TreeMap<Integer, String>();
    public static int endStationCounter = 0;

    ExecutorService stationPool = Executors.newFixedThreadPool(10);
    ExecutorService busPool = Executors.newFixedThreadPool(10);
    ExecutorService showMsgPool = Executors.newFixedThreadPool(1);

    public void initBus() {
        for (int i = 0; i < BUS_NUM; i++) {
            Bus bus = new Bus();
            busList.add(bus);
        }
        for (int i = 1; i < BUS_NUM; i++) {
            busList.get(i - 1).setNextBus(busList.get(i));
            busList.get(i).setAwaitFlag(true);
            busList.get(i).setAwaitDistance(0);
        }
    }

    public void initStation() {
        for (int i = 0; i < STATION_NUM; i++) {
            stationList.add(new Station());
        }
    }

    public void simulation() {
        initBus();
        initStation();

        for (Station station : stationList) {
            stationPool.submit(new StationCollectDataThread(station));
            station.setBus(busList.get(0));
        }

        for (Bus bus : busList) {
            busPool.submit(new BusRunningThread(bus, stationList));
        }

        showMsgPool.submit(new StationShowMsgThread());

        stationPool.shutdown();
        busPool.shutdown();
        showMsgPool.shutdown();
    }
}
