package com.interview.station.utils;

import java.util.Random;

/**
 * Created by gaolp on 2016/9/15.
 */
public class RandomUtils {
    private static Random ra = new Random();

    public static int randomInt(int limit) {
        return ra.nextInt(limit) + 1;
    }
}
