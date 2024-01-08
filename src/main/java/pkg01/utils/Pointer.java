package pkg01.utils;

/**
 * Created by Amir on 1/6/2024.
 */
public class Pointer<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Pointer(T value){
        this.value = value;
    }
    /*public Pointer()throws IllegalAccessException, InstantiationException {
        Class<T> t1 = null;
        this.value = t1.newInstance();
    }*/
}
