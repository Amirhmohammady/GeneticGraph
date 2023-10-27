package pkg01.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pkg01.MyNode;
import pkg01.MyNode2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Amir on 10/4/2023.
 */
public class Initializer {
    public static void loadFromFile(String fileName) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("grapgh.txt")));
            ObjectMapper mapper = new ObjectMapper();
            List<MyNode2> myNodes2 = mapper.readValue(jsonString, new TypeReference<List<MyNode2>>() {
            });
            MyNode.allNodes = myNodes2.stream().map(n -> Mapper.mapNull(n)).collect(Collectors.toList());
            for (MyNode2 n : myNodes2) {
                MyNode myNode = Mapper.getById(n.value);
                myNode.nodes = Mapper.mapListInteger(n.nodes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
