package pkg01.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import graph.MyGraph;
import graph.MyNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir on 10/4/2023.
 */
public class Initializer {
    public static MyGraph loadFromFile(String fileName) {
        MyGraph rslt = null;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
            ObjectMapper mapper = new ObjectMapper();
            List<MyNode2> myNodes2 = mapper.readValue(jsonString, new TypeReference<List<MyNode2>>() {
            });
            myNodes2.sort((n1, n2) -> n1.value.compareTo(n2.value));
            List<MyNode> myNodes = new ArrayList<>();
            List<Integer> partNumbers = new ArrayList<>();
            for (int z1 = 0; z1 < myNodes2.size(); z1++) {
                myNodes.add(new MyNode(myNodes2.get(z1).childs, myNodes2.get(z1).value, myNodes2.get(z1).isBlocking));
                partNumbers.add(new Integer(myNodes2.get(z1).part));
            }
//            rslt.childs = myNodes.stream().map(n -> Mapper.mapNull(n)).collect(Collectors.toList());
            rslt = new MyGraph(myNodes, 4, partNumbers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslt;
    }
}
