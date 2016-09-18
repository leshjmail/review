package com.interview.distance.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaolp on 2016/9/15.
 */
public class PathInfo {
    private static final String PATH_SEP = "->";
    private List<Node> nodes = new ArrayList<Node>();

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public String path() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(PATH_SEP).append(node.getName());
        }
        return sb.substring(PATH_SEP.length());
    }

    public String[] pathName() {
        String[] pathNameArr = new String[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            pathNameArr[i] = nodes.get(i).getName();
        }
        return pathNameArr;
    }
}
