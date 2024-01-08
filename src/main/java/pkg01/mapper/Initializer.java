package pkg01.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import graph.MyGraph;
import graph.MyNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            List<MyNode> myNodes = mapper.readValue(jsonString, new TypeReference<List<MyNode>>() {
            });
            myNodes.sort((n1, n2) -> n1.value.compareTo(n2.value));
//            rslt.childs = myNodes.stream().map(n -> Mapper.mapNull(n)).collect(Collectors.toList());
            rslt = new MyGraph(myNodes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rslt;
    }
}
