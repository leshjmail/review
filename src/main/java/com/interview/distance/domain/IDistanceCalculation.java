package com.interview.distance.domain;

import com.interview.distance.model.PathInfo;

import java.util.List;

/**
 * Created by gaolp on 2016/9/14.
 */
public interface IDistanceCalculation {
    int distance(String... nodeName);

    List<PathInfo> path(String startNodeName, String endNodeName);
}
