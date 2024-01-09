package pkg01.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir on 10/3/2023.
 */
public class MyNode2 {
    public Integer value;
    public boolean isBlocking = false;
    public Integer part;
    public List<Integer> childs = new ArrayList<>();

    public MyNode2() {
    }

    public MyNode2(List<Integer> childs, int value, Integer part, boolean isBlocking) {
        this.childs = childs;
        this.value = value;
        this.part = part;
        this.isBlocking = isBlocking;
    }
}
