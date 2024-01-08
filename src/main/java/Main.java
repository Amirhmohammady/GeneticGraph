import graph.MyGraph;
import graph.MyNode;
import graph.optimizer.MyGenetic;
import pkg01.mapper.Initializer;

public class Main {
    public static void main(String[] args) {
        MyGraph mg = Initializer.loadFromFile("grapgh.txt");
        for (MyNode n : mg.nodes)
            System.out.println(n);
        System.out.println("standardDeviationForTableCuts = " + mg.standardDeviationForTableCuts());
        System.out.println("total cut size: " + mg.totalCutSize());
        System.out.println("===>>hops:\n" + mg.getMaxHop());
        /*Map<MyPair, Integer> integerMap = genetic.cutSize();
        for (Map.Entry<MyPair, Integer> entry : integerMap.entrySet())
            System.out.println("" + entry.getKey() + ":" + entry.getValue());*/
        MyGenetic myGenetic = new MyGenetic(mg);
        System.out.println("score : " + myGenetic.getScore());
        MyGraph mg2 = new MyGraph(mg);
        MyGenetic myGenetic2 = new MyGenetic(mg2);
        System.out.println("score mg2: " + myGenetic2.getScore());
    }
}