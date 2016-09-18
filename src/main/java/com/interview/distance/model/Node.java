package com.interview.distance.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaolp on 2016/9/14.
 */
public class Node {
    private String name;
    private Map<Node, Integer> child = new HashMap<Node, Integer>();

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, Integer> getChild() {
        return child;
    }

    public void setChild(Map<Node, Integer> child) {
        this.child = child;
    }

    public boolean hasChild(Node node) {
        return child.containsKey(node);
    }

    public Integer getChildDistance(Node node) {
        return child.get(node);
    }
}
