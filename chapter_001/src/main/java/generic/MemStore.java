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
        T mems = findById(id);
        if (mems != null) {
            int index = this.mem.indexOf(mems);
            this.mem.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        /*T mems = findById(id);
        if (mems != null) {
            return this.mem.remove(mems);
        }*/
        return this.mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        for (T mems : this.mem){
            if (mems.getId().equals(id)) {
                return mems;
            }
        }
        return null;
    }


}
