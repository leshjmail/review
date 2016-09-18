package com.interview.distance.domain;

import com.interview.distance.model.PathInfo;

import java.util.List;

/**
 * Created by gaolp on 2016/9/14.
 */
public class DistanceView implements IDistanceView {
    public String printDistance(int distance) {
        if (distance <= 0) {
            return "无";
        } else {
            return String.valueOf(distance);
        }
    }

    public String printPath(List<PathInfo> pathInfoList) {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (PathInfo pathInfo : pathInfoList) {
            sb.append("\n").append(counter).append(") ").append(pathInfo.path());
        }
        return sb.toString();
    }
}
