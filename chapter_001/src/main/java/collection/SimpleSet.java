package collection;

import java.util.*;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleArray<E> container = new SimpleArray<E>();

    private boolean find(E model) {
            for (E mod : this.container) {
                if (Objects.equals(mod, model)) {
                    return true;
                }
            }
            return false;
    }
    public void add(E model) {
        if (model !=null && !find(model)) {
            this.container.add(model);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return this.container.iterator();
    }
    public boolean checkIndex(int index) {
        return this.container.chechIndex(index);
    }
}
