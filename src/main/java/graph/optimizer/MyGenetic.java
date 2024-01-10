package graph.optimizer;

import graph.MyGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Amir on 11/28/2023.
 */
public class MyGenetic {
    private List<MyGraph> myGraph = new ArrayList<>();
    private Random random = new Random();
    private int maxGens = 4;

    public MyGenetic(MyGraph myGraph) {
        this.myGraph.add(myGraph);
    }

    public double getScore(int indx) throws Exception {
        double score = 500;
        //partition size balabce
        if (!myGraph.get(indx).checkSizeTelorance(0.05))
            throw new Exception("partition size balabce for:" + myGraph.get(indx).part);
        //max hops
        if (myGraph.get(indx).getMaxHop() > 4)
            throw new Exception("max hops is " + myGraph.get(indx).getMaxHop() + " for:" + myGraph.get(indx).part);
        //low total cut size
        //total cut size * total yal / number of partitions
        score -= myGraph.get(indx).totalCutSize() * myGraph.get(indx).getTotalYal() / myGraph.get(indx).partitionNumber;
        //cutsize balance
        //balance cut size = enheraf meyar
        score -= myGraph.get(indx).standardDeviationForTableCuts();
        return score;
    }

    public void setMaxGens(int maxGens) {
        this.maxGens = maxGens;
    }

    public void hazfGen() {

    }

    public void jahesh() {

    }

    public void addGen(MyGraph myGraph) {
        this.myGraph.add(myGraph);
    }

    public void crossover(MyGraph inp1, MyGraph inp2, MyGraph out1, MyGraph out2) {
        int gendCnt = inp1.nodes.size();
        int randomNumber = random.nextInt((1 << gendCnt) - 1) + 1;
    }
}
