package pkg01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir on 10/3/2023.
 */
public class MyNode2 {
    public MyNode2() {
    }

    public MyNode2(List<Integer> nodes, int value, Integer part, boolean isBlocking) {
        this.nodes = nodes;
        this.value = value;
        this.part = part;
        this.isBlocking = isBlocking;
    }

    public Integer value;
    public boolean isBlocking = false;
    public Integer part;
    public List<Integer> nodes = new ArrayList<>();
}
