package kiss;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findInList(value, comparator, true);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findInList(value, comparator, false);
    }

    static <T> T findInList(List<T> value, Comparator<T> comparator, boolean findMax) {
        checkList(value);
        T returnValue = value.get(0);
        int findFlag = findMax ? 1 : -1;
        for (T val : value) {
            returnValue = (comparator.compare(returnValue,val) * findFlag <= 0) ? val : returnValue;
        }
        return returnValue;
    }
    static <T> void checkList(List<T> value) {
        if (value.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("List have zero size");
        }
    }
}