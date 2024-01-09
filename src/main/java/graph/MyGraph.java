package graph;

import pkg01.utils.Pointer;

import java.util.*;

/**
 * Created by Amir on 10/4/2023.
 */
public class MyGraph {
    //public List<MyNode> allNodes = new ArrayList<>();
    public static Integer partitionNumber = 0;
    public static List<MyNode> nodes = new ArrayList<>();
    private static int totalYal = 0;
    public List<Integer> part = new ArrayList<>();

    public MyGraph(List<MyNode> nodes,Integer partitionNumber) {
        this.nodes = nodes;
        this.partitionNumber = partitionNumber;
        for (MyNode n : nodes) {
            //if (n.part > partitionNumber) partitionNumber = n.part;
            totalYal += n.childs.size();
        }
    }

    public MyGraph(MyGraph inp) {
        part = new ArrayList<>(inp.nodes.size());
        for (int t1 = 0; t1 < inp.nodes.size(); t1++) {
            part.add(new Integer(0));
            part.set(t1, inp.part.get(t1));
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
        for (Integer n : part)
            if (mapSizes.get(n) == null) {
                mapSizes.put(n, 1);
                if (1 > maxSize) maxSize = 1;
            } else {
                mapSizes.put(n, mapSizes.get(n) + 1);
                if (mapSizes.get(n) > maxSize) maxSize = mapSizes.get(n);
            }
        double avgTelorance = (1 + telorance) * nodes.size() / mapSizes.size();
        int avgInt = nodes.size() / mapSizes.size() + 1;
        avgTelorance = Math.max(avgTelorance, avgInt);
        if (avgTelorance < maxSize) return false;
        return true;
    }

    public double standardDeviationForTableCuts() {
        int[][] tableCut = new int[partitionNumber][partitionNumber];
        int totalCut = 0;
        //Map<MyPair, Integer> mapSizes = new HashMap<>();
        for (int n = 0; n < nodes.size(); n++)
            for (Integer m : nodes.get(n).childs)
                if (!part.get(n).equals(part.get(m - 1))) {
                    tableCut[part.get(n)-1][part.get(m - 1) - 1]++;
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
        for (int m = 0; m < part.size(); m++) {
            for (Integer n : nodes.get(m).childs) {
                if (part.get(m) != part.get(n - 1)) rslt++;
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
        for (int n = 0; n < nodes.size(); n++) {
            if (nodes.get(n).isBlocking) {
                List<Integer> nodeList = new ArrayList<>();
                nodeList.add(n);
                checkNextChildForMaxHops(nodeList, maxHop);
            }
        }
        return maxHop.getValue();
    }

    private void checkNextChildForMaxHops(List<Integer> list, Pointer<Integer> maxHop) {
        for (Integer n : nodes.get(list.get(list.size() - 1)).childs) {
            int t1;
            Integer hops = 0;
            int lastPartition = part.get(list.get(0));
            for (t1 = 0; t1 < list.size(); t1++) {
                if (lastPartition != part.get(list.get(t1))) {
                    hops++;
                    lastPartition = part.get(list.get(t1));
                }
                if (n - 1 == t1) break;
            }
            //if n not be in list{
            if (!(t1 < list.size())) {
                //if n is blocking{
                if (nodes.get(n - 1).isBlocking) {
                    if (lastPartition != part.get(n - 1)) hops++;
                    if (hops > maxHop.getValue()) maxHop.setValue(hops);
                    System.out.print("==>>hop: ");
                    for (Integer t2 : list) {
                        System.out.print(nodes.get(t2).value + " ");
                    }
                    System.out.println(nodes.get(n - 1).value);
                    System.out.println(" hops:" + hops);
                } else {
                    list.add((n - 1));
                    checkNextChildForMaxHops(list, maxHop);
                    //remove the last element
                    list.remove(list.size() - 1);
                }
            }
        }
    }
}
