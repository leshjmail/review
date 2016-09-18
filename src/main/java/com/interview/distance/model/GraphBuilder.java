package com.interview.distance.model;

import com.interview.distance.domain.GraphResource;
import com.interview.distance.domain.IGraphResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaolp on 2016/9/14.
 */
public class GraphBuilder {
    IGraphResource graphResource = new GraphResource();

    public Map<String, Node> build() {
        Map<String, Node> map = new HashMap<String, Node>();
        List<String> graphNodeList = graphResource.getGraphResource();
        for (String node : graphNodeList) {
            // validate resource data
            if (node.length() != 3) {
                continue;
            }
            String startNodeName = String.valueOf(node.charAt(0));
            String endNodeName = String.valueOf(node.charAt(1));
            int distance = 0;
            try {
                distance = Integer.parseInt(String.valueOf(node.charAt(2)));
            } catch (Exception e) {
                continue;
            }

            if (!map.containsKey(startNodeName)) {
                map.put(startNodeName, new Node(startNodeName));
            }
            if (!map.containsKey(endNodeName)) {
                map.put(endNodeName, new Node(endNodeName));
            }
            Node startNode = map.get(startNodeName);
            Node endNode = map.get(endNodeName);
            startNode.getChild().put(endNode, distance);
        }

        for (Map.Entry<String, Node> entry : map.entrySet()) {
            for (Map.Entry<Node, Integer> entryNextNode : entry.getValue().getChild().entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue().getName() + "->" + entryNextNode.getKey().getName() + ",dis=" + entryNextNode.getValue());
            }
        }


        return map;
    }
}
