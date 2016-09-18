package com.interview.station.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaolp on 2016/9/15.
 */
public class DelayUtils {
    public static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            System.out.println("There is an error when sleep 500ms.");
        }
    }

    public static void delayByTime(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (Exception e) {
            System.out.println("There is an error when sleep 500ms.");
        }
    }
}
