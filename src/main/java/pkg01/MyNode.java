package pkg01;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Amir on 10/3/2023.
 */
public class MyNode {
    public static List<MyNode> allNodes = new ArrayList<>();

    @JsonIgnoreProperties({"value"})
    public Integer value;
    public boolean isBlocking = false;
    public Integer part;
    public List<MyNode> nodes = new ArrayList<>();

    public MyNode(List<MyNode> nodes, int value, Integer part, boolean isBlocking) {
        this();
        this.nodes = nodes;
        this.value = value;
        this.part = part;
        this.isBlocking = isBlocking;
    }

    public MyNode() {
        allNodes.add(this);
    }

    @Override
    public String toString() {
        return "\"MyNode\":{" +
                "\"value\":" + value +
                ", \"isBlocking\":" + isBlocking +
                ", \"nodes\":[" + nodes.stream().map(n -> "{\"id\":" + String.valueOf(n.value) + "}").collect(Collectors.joining(",")) +
                "]}";
    }
}
