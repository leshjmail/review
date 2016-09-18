package com.interview.distance.domain;

import com.interview.distance.model.PathInfo;

import java.util.List;

/**
 * Created by gaolp on 2016/9/14.
 */
public interface IDistanceView {
    String printDistance(int distance);

    String printPath(List<PathInfo> pathInfoList);
}
