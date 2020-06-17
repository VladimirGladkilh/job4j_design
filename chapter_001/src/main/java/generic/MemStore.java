package generic;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
       int index = getIndexById(id);
       if (index >= 0) {
           this.mem.set(index, model);
           return true;
       }
       return false;
    }

    @Override
    public boolean delete(String id) {
        int index = getIndexById(id);
        if (index >= 0) {
            this.mem.remove(index);
            return true;
        }
        return false;

    }

    @Override
    public T findById(String id) {
        for (T mems : this.mem) {
            if (mems.getId().equals(id)) {
                return mems;
            }
        }
        return null;
    }

    private int getIndexById(String id) {
        int index = 0;
        for (T mems : this.mem) {
            if (mems.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
