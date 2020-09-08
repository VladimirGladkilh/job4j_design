import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;

public class SimpleCacheHashMap  {

    private HashMap map = new HashMap();

    public Object get(Object key) {
        SoftReference softRef = (SoftReference) map.get(key);
        if (softRef == null) {
            return null;
        }
        return softRef.get();
    }

    public Object insert(Object key, Object value) {
        SoftReference softRef = (SoftReference) map.put(key, new SoftReference(value));
        if (softRef == null) {
            return null;
        }
        Object oldValue = softRef.get();
        softRef.clear();
        return oldValue;
    }

    public Object delete(Object key) {
        SoftReference softRef = (SoftReference) map.remove(key);
        if (softRef == null) {
            return null;
        }
        Object oldValue = softRef.get();
        softRef.clear();
        return oldValue;
    }
}
