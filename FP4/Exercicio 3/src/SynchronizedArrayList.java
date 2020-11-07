import java.util.ArrayList;

public class SynchronizedArrayList {
    private ArrayList list = new ArrayList();

    public synchronized void add(Object o) {
        list.add(o);
    }

    public synchronized ArrayList get() {
        return list;
    }
}