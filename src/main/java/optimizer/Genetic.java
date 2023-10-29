package optimizer;

import pkg01.MyNode;
import pkg01.MyPair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amir on 10/4/2023.
 */
public class Genetic {
    public Integer partitionNumber = 0;
    List<MyNode> partitions;

    public Genetic(List<MyNode> partitions) {
        this.partitions = partitions;
        for (MyNode n : partitions)
            if (n.part > partitionNumber) partitionNumber = n.part;
    }

    public static int getScore() {

        return 0;
    }

    public boolean checkSize(double telorance) {
        int maxSize = 0;
        Map<Integer, Integer> mapSizes = new HashMap<>();
        for (MyNode n : partitions)
            if (mapSizes.get(n.part) == null) {
                mapSizes.put(n.part, 1);
                if (1 > maxSize) maxSize = 1;
            } else {
                mapSizes.put(n.part, mapSizes.get(n.part) + 1);
                if (mapSizes.get(n.part) > maxSize) maxSize = mapSizes.get(n.part);
            }
        if (telorance * partitions.size() / mapSizes.size() < maxSize) return false;
        return true;
    }

    public double standardDeviationForTableCuts() {
        int[][] tableCut = new int[partitionNumber][partitionNumber];
        int totalCut = 0;
        //Map<MyPair, Integer> mapSizes = new HashMap<>();
        for (MyNode n : partitions)
            for (MyNode m : n.nodes)
                if (n.part != m.part) {
                    tableCut[n.part - 1][m.part - 1]++;
                }
        for (int t1 = 0; t1 < partitionNumber; t1++) {
            for (int t2 = 0; t2 < partitionNumber; t2++) {
                totalCut += tableCut[t1][t2];
                System.out.print(tableCut[t1][t2]);
                System.out.print('\t');
            }
            System.out.println();
        }
        double average = (double) totalCut / ((partitionNumber - 1) * (partitionNumber - 1));
        System.out.println("total cut = " + totalCut);
        System.out.println("average = " + average);
        double rslt = 0;
        for (int t1 = 0; t1 < partitionNumber; t1++)
            for (int t2 = 0; t2 < partitionNumber; t2++)
                if (t1 != t2) rslt += Math.abs(average - tableCut[t1][t2]);
        return rslt;
    }

    public int totalCutSize(Map<MyPair, Integer> inp) {
        int rslt = 0;
        for (Map.Entry<MyPair, Integer> entry : inp.entrySet()) {
            rslt += entry.getValue();
        }
        return rslt;
    }

    public int maxCutSize(Map<MyPair, Integer> inp) {
        int rslt = 0;
        for (Map.Entry<MyPair, Integer> entry : inp.entrySet()) {
            if (entry.getValue() > rslt) rslt = entry.getValue();
        }
        return rslt;
    }

    public double standardDeviation() {
        return 0;
    }
}
