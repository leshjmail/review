package com.interview.distance.domain;

import com.interview.distance.model.GraphBuilder;
import com.interview.distance.model.Node;
import com.interview.distance.model.PathInfo;

import java.util.*;

/**
 * Created by gaolp on 2016/9/14.
 */
public class DistanceCalculation implements IDistanceCalculation {
    static GraphBuilder gb = new GraphBuilder();
    static Map<String, Node> nodeMap = new HashMap<String, Node>();

    static {
        nodeMap = gb.build();
    }
    public List<PathInfo> path(String startNodeName, String endNodeName) {
        if (startNodeName == null || endNodeName == null) {
            return new ArrayList<PathInfo>(); // don't calculate distance
        }
        Node curNode = nodeMap.get(startNodeName);
        List<PathInfo> pathList = new ArrayList<PathInfo>();
        Stack<Node> nodeStack = new Stack<Node>();
        Stack<String> pathStack = new Stack<String>();
        Set<String> accessSet = new HashSet<String>();

        for (Map.Entry<Node, Integer> entry : curNode.getChild().entrySet()) {
            nodeStack.push(entry.getKey());
        }

        pathStack.push(startNodeName);
        while (!nodeStack.empty()) {

            Node node = nodeStack.get(nodeStack.size() - 1);

            if (!accessSet.contains(node.getName())) {
                pathStack.push(node.getName());
                accessSet.add(node.getName());
                if (node.getName().equals(endNodeName)) {
                    pathList.add(createPathInfo(pathStack));
                    nodeStack.pop();
                    accessSet.remove(node.getName());
                    pathStack.pop();
                } else {
                    for (Map.Entry<Node, Integer> entry : node.getChild().entrySet()) {
                        Node subNode = entry.getKey();
                        if (!accessSet.contains(subNode.getName())) {
                            nodeStack.push(entry.getKey());
                        }
                    }
                }
            } else if (accessSet.contains(node.getName())) {
                pathStack.pop();
                nodeStack.pop();
                accessSet.remove(node.getName());
            }
        }

        return pathList;
    }

    private PathInfo createPathInfo(Stack<String> pathStack) {
        PathInfo pathInfo = new PathInfo();
        for (String nodeName : pathStack) {
            pathInfo.addNode(nodeMap.get(nodeName));
        }
        return pathInfo;
    }

    public int distance(String... nodeName) {
        if (nodeName == null || nodeName.length < 2) {
            return -1; // don't calculate distance
        }
        String headNodeName = nodeName[0];
        Node curNode = nodeMap.get(headNodeName);
        int distance = 0;
        for (int i = 1; i < nodeName.length; i++) {
            String nextNodeName = nodeName[i];
            Node nextNode = nodeMap.get(nextNodeName);
            if (curNode.hasChild(nextNode)) {
                distance += curNode.getChildDistance(nextNode);
                curNode = nextNode;
            } else {
                distance = 0; // the path don't exist
                break;
            }
        }
        return distance;
    }
}
