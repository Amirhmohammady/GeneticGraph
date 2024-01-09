package graph.optimizer;

import graph.MyGraph;

import java.util.Random;

/**
 * Created by Amir on 11/28/2023.
 */
public class MyGenetic {
    private MyGraph myGraph;
    private Random random = new Random();

    public MyGenetic(MyGraph myGraph) {
        this.myGraph = myGraph;
    }

    public double getScore() {
        double score = 500;
        //partition size balabce
        if (!myGraph.checkSizeTelorance(0.05)) return -1;
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

    public void crossover(MyGraph inp1, MyGraph inp2, MyGraph out1, MyGraph out2) {
        int gendCnt = inp1.nodes.size();
        int randomNumber = random.nextInt((1 << gendCnt) - 1) + 1;
    }
}
