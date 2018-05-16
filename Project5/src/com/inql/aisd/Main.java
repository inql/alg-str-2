package com.inql.aisd;

import com.inql.aisd.disjointset.DisjointSetsUtil;
import com.inql.aisd.disjointset.Node;
import com.inql.aisd.graph.Graph;
import com.inql.aisd.kruskal.Kruskal;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        disjoinSetsTest();
        if(args.length==0){
            kruskalTest();
        }else if(args.length==1){
            File file = new File(args[0]);
            String path = file.getAbsolutePath();
            try {
                kruskalFromFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disjoinSetsTest(){
        Node[] nodes = new Node[9];
        for (int i = 0; i < 9; i++) {
            nodes[i] = DisjointSetsUtil.makeSet(i+1);
        }
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[0]),DisjointSetsUtil.findSet(nodes[1]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[2]),DisjointSetsUtil.findSet(nodes[3]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[4]),DisjointSetsUtil.findSet(nodes[3]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[0]),DisjointSetsUtil.findSet(nodes[4]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[5]),DisjointSetsUtil.findSet(nodes[6]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[7]),DisjointSetsUtil.findSet(nodes[8]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[5]),DisjointSetsUtil.findSet(nodes[7]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[6]),DisjointSetsUtil.findSet(nodes[3]));

        for (Node node :
                nodes) {
            System.out.println(node);
        }
    }

    public static void kruskalTest(){
        Graph graph = new Graph();
        Kruskal kruskal = new Kruskal();
        for(int i =0; i<5; i++)
        {
            graph.addVerticle(i);
        }
        graph.addEdge(0,1,1);
        graph.addEdge(0,4,2);
        graph.addEdge(1,2,5);
        graph.addEdge(1,3,2);
        graph.addEdge(1,4,1);
        graph.addEdge(2,3,7);
        graph.addEdge(3,4,3);
        kruskal.kruskal(graph);
        System.out.println(kruskal.kruskalList);
    }

    public static void kruskalFromFile(String pathToFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile));
        String line = "";
        List<int[]> dataList = new ArrayList<>();
        while((line=bufferedReader.readLine())!=null){
            dataList.add(Arrays.stream(line.split(";")).mapToInt(Integer::parseInt).toArray());
        }
        bufferedReader.close();
        Graph graph = new Graph();
        Kruskal kruskal = new Kruskal();
        for (int[] data :
                dataList) {
            graph.addVerticle(data[0]);
            graph.addVerticle(data[1]);
            graph.addEdge(data[0],data[1],data[2]);
        }
        kruskal.kruskal(graph);
        System.out.println(kruskal.kruskalList);
    }
}
