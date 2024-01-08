package graph;

import pkg01.utils.Pointer;

import java.util.*;

/**
 * Created by Amir on 10/4/2023.
 */
public class MyGraph {
    //public List<MyNode> allNodes = new ArrayList<>();
    public static Integer partitionNumber = 0;
    public List<MyNode> nodes = new ArrayList<>();
    private int totalYal = 0;

    public MyGraph(List<MyNode> nodes) {
        this.nodes = nodes;
        for (MyNode n : nodes) {
            if (n.part > partitionNumber) partitionNumber = n.part;
            totalYal += n.childs.size();
        }
    }

    public MyGraph(MyGraph inp) {
        //partitionNumber = inp.partitionNumber;
        totalYal = inp.totalYal;
        //copy childs
        nodes = new ArrayList<>(inp.nodes.size());
        for (int t1 = 0; t1 < inp.nodes.size(); t1++) {
            nodes.add(new MyNode());
            nodes.get(t1).value = inp.nodes.get(t1).value;
            nodes.get(t1).isBlocking = inp.nodes.get(t1).isBlocking;
            nodes.get(t1).part = inp.nodes.get(t1).part;
            for (int t2 = 0; t2 < inp.nodes.get(t1).childs.size(); t2++) {
                nodes.get(t1).childs.add(inp.nodes.get(t1).childs.get(t2));
            }
        }
    }

    /*static MyGraph copyMyGraph(MyGraph inp){

    }*/

    public int getTotalYal() {
        return totalYal;
    }

    public boolean checkSizeTelorance(double telorance) {
        int maxSize = 0;
        Map<Integer, Integer> mapSizes = new HashMap<>();
        for (MyNode n : nodes)
            if (mapSizes.get(n.part) == null) {
                mapSizes.put(n.part, 1);
                if (1 > maxSize) maxSize = 1;
            } else {
                mapSizes.put(n.part, mapSizes.get(n.part) + 1);
                if (mapSizes.get(n.part) > maxSize) maxSize = mapSizes.get(n.part);
            }
        if (telorance * nodes.size() / mapSizes.size() < maxSize) return false;
        return true;
    }

    public double standardDeviationForTableCuts() {
        int[][] tableCut = new int[partitionNumber][partitionNumber];
        int totalCut = 0;
        //Map<MyPair, Integer> mapSizes = new HashMap<>();
        for (MyNode n : nodes)
            for (Integer m : n.childs)
                if (n.part != nodes.get(m - 1).part) {
                    tableCut[n.part - 1][nodes.get(m - 1).part - 1]++;
                }
        for (int t1 = 0; t1 < partitionNumber; t1++) {
            for (int t2 = 0; t2 < partitionNumber; t2++) {
                totalCut += tableCut[t1][t2];
                System.out.print(tableCut[t1][t2]);
                System.out.print('\t');
            }
            System.out.println();
        }
        double average = (double) totalCut / ((partitionNumber - 1) * partitionNumber);
        System.out.println("total cut = " + totalCut);
        System.out.println("average = " + average);
        double rslt = 0;
        for (int t1 = 0; t1 < partitionNumber; t1++)
            for (int t2 = 0; t2 < partitionNumber; t2++)
                if (t1 != t2) rslt += Math.abs(average - tableCut[t1][t2]);
        return rslt;
    }

    public int totalCutSize() {
        int rslt = 0;
        for (MyNode m : nodes) {
            for (Integer n : m.childs) {
                if (m.part != nodes.get(n - 1).part) rslt++;
            }
        }
        /*for (Map.Entry<MyPair, Integer> entry : inp.entrySet()) {
            rslt += entry.getValue();
        }*/
        return rslt;
    }

    /*public int maxCutSize(Map<MyPair, Integer> inp) {
        int rslt = 0;
        for (Map.Entry<MyPair, Integer> entry : inp.entrySet()) {
            if (entry.getValue() > rslt) rslt = entry.getValue();
        }
        return rslt;
    }*/

    public int getMaxHop() {
        Pointer<Integer> maxHop = new Pointer<>(new Integer(0));
        for (MyNode n : this.nodes) {
            if (n.isBlocking) {
                List<MyNode> nodeList = new ArrayList<>();
                nodeList.add(n);
                checkNextChildForMaxHops(nodeList, maxHop);
            }
        }
        return maxHop.getValue();
    }

    private void checkNextChildForMaxHops(List<MyNode> list, Pointer<Integer> maxHop) {
        for (Integer n : list.get(list.size() - 1).childs) {
            int t1;
            Integer hops = 0;
            int lastPartition = list.get(0).part;
            for (t1 = 0; t1 < list.size(); t1++) {
                if (lastPartition != list.get(t1).part) {
                    hops++;
                    lastPartition = list.get(t1).part;
                }
                if (nodes.get(n - 1) == list.get(t1)) break;
            }
            //if n not be in list{
            if (!(t1 < list.size())) {
                //if n is blocking{
                if (nodes.get(n - 1).isBlocking) {
                    if (lastPartition != nodes.get(n - 1).part) hops++;
                    if (hops > maxHop.getValue()) maxHop.setValue(hops);
                    System.out.print("==>>hop: ");
                    for (MyNode t2 : list) {
                        System.out.print(t2.value + " ");
                    }
                    System.out.println(nodes.get(n - 1).value);
                    System.out.println(" hops:" + hops);
                } else {
                    list.add(nodes.get(n - 1));
                    checkNextChildForMaxHops(list, maxHop);
                    //remove the last element
                    list.remove(list.size() - 1);
                }
            }
        }
    }
}
