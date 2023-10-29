import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import optimizer.Genetic;
import pkg01.MyNode;
import pkg01.MyNode2;
import pkg01.MyPair;
import pkg01.mapper.Initializer;
import pkg01.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Initializer.loadFromFile("grapgh.txt");
        for (MyNode n : MyNode.allNodes)
            System.out.println(n);
        Genetic genetic = new Genetic(MyNode.allNodes);
        System.out.println("standardDeviationForTableCuts = " + genetic.standardDeviationForTableCuts());
        /*Map<MyPair, Integer> integerMap = genetic.cutSize();
        for (Map.Entry<MyPair, Integer> entry : integerMap.entrySet())
            System.out.println("" + entry.getKey() + ":" + entry.getValue());*/
    }
}