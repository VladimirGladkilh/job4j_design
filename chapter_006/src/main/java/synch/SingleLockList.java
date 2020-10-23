package synch;

import collection.DynamicStore;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final DynamicStore<T> list = new DynamicStore<>();
    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }


    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized DynamicStore<T> copy(DynamicStore<T> list) {
        DynamicStore<T> newList = new DynamicStore<>();
        list.forEach(t -> newList.add(t));
        return newList;
    }
}