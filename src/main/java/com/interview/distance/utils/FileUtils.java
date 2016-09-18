package com.interview.distance.utils;

import java.io.*;

/**
 * Created by gaolp on 2016/9/14.
 */
public class FileUtils {
    public static final String GRAPH_RESOURCE = "graph.txt";

    public static String readGraphFile() {
        String result = "";
        InputStreamReader read = null;
        try {
//            File file=new File(GRAPH_RESOURCE);
            ClassLoader classLoader = FileUtils.class.getClassLoader();
            File file = new File(classLoader.getResource(GRAPH_RESOURCE).getFile());
            if (file.isFile() && file.exists()) {
                read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    result = lineTxt;
                    break;
                }
            } else {
                System.out.println("The resource " + GRAPH_RESOURCE + " doesn't exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is an error when read " + GRAPH_RESOURCE + ", the detailed message is " + e.getMessage());
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    System.out.println("There is an error when close steam of " + GRAPH_RESOURCE + ", the detailed message is" + e.getMessage());
                }
            }
        }
        return result;
    }
}
