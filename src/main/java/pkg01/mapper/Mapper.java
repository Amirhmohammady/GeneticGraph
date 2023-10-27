package pkg01.mapper;

import pkg01.MyNode;
import pkg01.MyNode2;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Amir on 10/3/2023.
 */
public class Mapper {
    public static MyNode map(MyNode2 inp) {
        return new MyNode(mapListInteger(inp.nodes), inp.value, inp.part, inp.isBlocking);
    }

    public static MyNode mapNull(MyNode2 inp) {
        return new MyNode(null, inp.value, inp.part, inp.isBlocking);
    }

    public static MyNode2 map(MyNode inp) {
        return new MyNode2(mapListMyNode(inp.nodes), inp.value, inp.part, inp.isBlocking);
    }

    public static List<Integer> mapListMyNode(List<MyNode> inp) {
        return inp.stream().map(n -> n.value).collect(Collectors.toList());
    }

    public static List<MyNode> mapListInteger(List<Integer> inp) {
        return inp.stream().map(n -> getById(n)).collect(Collectors.toList());
    }

    public static MyNode getById(Integer inp) {
        for (MyNode m : MyNode.allNodes)
            if (m.value.equals(inp)) return m;
        return null;
    }
}
