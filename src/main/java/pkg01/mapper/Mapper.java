package pkg01.mapper;

import graph.MyGraph;
import graph.MyNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Amir on 10/3/2023.
 */
public class Mapper {
    /*public static MyNode map(MyGraph mg, MyNode2 inp) {
        return new MyNode(mapListInteger(mg, inp.childs), inp.value, inp.part, inp.isBlocking);
    }*/

    /*public static MyNode mapNull(MyNode2 inp) {
        return new MyNode(null, inp.value, inp.part, inp.isBlocking);
    }*/

    /*public static MyNode2 map(MyNode inp) {
        return new MyNode2(mapListMyNode(inp.childs), inp.value, inp.part, inp.isBlocking);
    }*/

    public static List<Integer> mapListMyNode(List<MyNode> inp) {
        return inp.stream().map(n -> n.value).collect(Collectors.toList());
    }

    public static List<MyNode> mapListInteger(MyGraph mg, List<Integer> inp) {
        return inp.stream().map(n -> getById(mg, n)).collect(Collectors.toList());
    }

    public static MyNode getById(MyGraph mg, Integer inp) {
        for (MyNode m : mg.nodes)
            if (m.value.equals(inp)) return m;
        return null;
    }
}
