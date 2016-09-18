package com.interview.distance.domain;

import com.interview.distance.utils.FileUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gaolp on 2016/9/14.
 */
public class GraphResource implements IGraphResource {
    public List<String> getGraphResource() {
        String result = FileUtils.readGraphFile();
        return Arrays.asList(result.replaceAll(" ", "").split(","));
    }
}
