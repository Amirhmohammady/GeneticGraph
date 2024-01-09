package graph;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Amir on 10/3/2023.
 */
public class MyNode {
    //public static List<MyNode> allNodes = new ArrayList<>();

    @JsonIgnoreProperties({"value"})
    public Integer value;
    public boolean isBlocking = false;
    //public Integer part;
    public List<Integer> childs = new ArrayList<>();

    public MyNode(List<Integer> childs, int value, boolean isBlocking) {
        this();
        this.childs = childs;
        this.value = value;
        //this.part = part;
        this.isBlocking = isBlocking;
    }

    public MyNode() {
        //allNodes.add(this);
    }

    @Override
    public String toString() {
        return "\"MyNode\":{" +
                "\"value\":" + value +
                //"\"partition\":" + part +
                ", \"isBlocking\":" + isBlocking +
                ", \"childs\":[" + childs.stream().map(n -> "{\"id\":" + String.valueOf(n) + "}").collect(Collectors.joining(",")) +
                "]}";
    }
}
