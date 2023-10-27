package pkg01;

/**
 * Created by Amir on 10/6/2023.
 */
public class MyPair {
    Integer i1,i2;

    public MyPair(Integer i1, Integer i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPair myPair = (MyPair) o;

        if (!i1.equals(myPair.i1)) return false;
        return i2.equals(myPair.i2);

    }

    @Override
    public int hashCode() {
        int result = i1.hashCode();
        result = 31 * result + i2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MyPair{" +
                "i1=" + i1 +
                ", i2=" + i2 +
                '}';
    }
}
