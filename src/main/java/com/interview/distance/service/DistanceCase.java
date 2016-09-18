package com.interview.distance.service;

import com.interview.distance.domain.DistanceCalculation;
import com.interview.distance.domain.DistanceView;
import com.interview.distance.domain.IDistanceCalculation;
import com.interview.distance.domain.IDistanceView;
import com.interview.distance.model.PathInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gaolp on 2016/9/14.
 */
public class DistanceCase {
    public static void main(String[] args) {
        IDistanceCalculation distanceCalculation = new DistanceCalculation();
        IDistanceView distanceView = new DistanceView();

        printLog("路线 B-C-D的距离:" + distanceView.printDistance(distanceCalculation.distance("B", "C", "D")));
        printLog("路线 A-E-B-C-D的距离:" + distanceView.printDistance(distanceCalculation.distance("A", "E", "B", "C", "D")));
        printLog("路线 A-C-B的距离:" + distanceView.printDistance(distanceCalculation.distance("A", "C", "B")));

        List<PathInfo> pathInfoList = distanceCalculation.path("C", "C");
        Iterator<PathInfo> pathInfoIter = pathInfoList.iterator();
        List<PathInfo> pathInfoListForPath = new ArrayList<PathInfo>();
        while (pathInfoIter.hasNext()) {
            PathInfo pathInfo = pathInfoIter.next();
            if (pathInfo.getNodes().size() - 1 <= 3) {
                pathInfoListForPath.add(pathInfo);
            }
        }
        printLog("以C站点开始终点到达C站点的最多经过3个站点的路线列表:" + distanceView.printPath(pathInfoListForPath));

        List<PathInfo> a2cPathInfoList = distanceCalculation.path("A", "C");
        Iterator<PathInfo> a2cPathInfoListIter = a2cPathInfoList.iterator();
        PathInfo a2cMinPath = null;
        int minDistance = Integer.MAX_VALUE;
        while (a2cPathInfoListIter.hasNext()) {
            PathInfo pathInfo = a2cPathInfoListIter.next();
            int distance = distanceCalculation.distance(pathInfo.pathName());
            if (distance < minDistance) {
                minDistance = distance;
                a2cMinPath = pathInfo;
            }
        }
        printLog("从A站到C站的最短距离:" + minDistance + "(" + a2cMinPath.path() + ")");

        List<PathInfo> b2bPathInfoList = distanceCalculation.path("B", "B");
        Iterator<PathInfo> b2bPathInfoListIter = b2bPathInfoList.iterator();
        PathInfo b2bMinPath = null;
        minDistance = Integer.MAX_VALUE;
        while (b2bPathInfoListIter.hasNext()) {
            PathInfo pathInfo = b2bPathInfoListIter.next();
            int distance = distanceCalculation.distance(pathInfo.pathName());
            if (distance < minDistance) {
                minDistance = distance;
                b2bMinPath = pathInfo;
            }
        }
        printLog("从B站到B站的最短距离:" + minDistance + "(" + b2bMinPath.path() + ")");

        pathInfoIter = pathInfoList.iterator();
        List<PathInfo> pathInfoListForNumber = new ArrayList<PathInfo>();
        while (pathInfoIter.hasNext()) {
            PathInfo pathInfo = pathInfoIter.next();
            int distance = distanceCalculation.distance(pathInfo.pathName());
            if (distance <= 30) {
                pathInfoListForNumber.add(pathInfo);
            }
        }
        printLog("从C站到C站在30公里的距离内可能的路线个数:" + distanceView.printPath(pathInfoListForNumber));

    }

    static void printLog(String msg) {
        System.out.println(msg + "\n");
    }
}
