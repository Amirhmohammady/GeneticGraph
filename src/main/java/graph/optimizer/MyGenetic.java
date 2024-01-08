package graph.optimizer;

import graph.MyGraph;

/**
 * Created by Amir on 11/28/2023.
 */
public class MyGenetic {
    private MyGraph myGraph;

    public MyGenetic(MyGraph myGraph) {
        this.myGraph = myGraph;
    }

    public double getScore() {
        double score = 500;
        //partition size balabce
        if (myGraph.checkSizeTelorance(0.05)) return -1;
        //max hops
        if (myGraph.getMaxHop() > 4) return -1;
        //low total cut size
        //total cut size * total yal / number of partitions
        score -= myGraph.totalCutSize() * myGraph.getTotalYal() / myGraph.partitionNumber;
        //cutsize balance
        //balance cut size = enheraf meyar
        score -= myGraph.standardDeviationForTableCuts();
        return score;
    }
}
