import java.util.*;

class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T> {

    protected T key;
    protected Map<K, V> map;

    KeyableMap(T key, Map<K, V> map) {
        this.key = key;
        this.map = map;
    }

    KeyableMap(T key) {
        this.key = key;
        map = new HashMap<>();
    }

    @Override
    public T getKey() {
        return this.key;
    }

    public V get(K key) {
        return map.get(key);
    }

    public KeyableMap<T, K, V> put(V item) {
        this.map.put(item.getKey(), item);
        return this;
    }

    @Override
    public String toString() {
        return (this.key + ": " + map.values()).replace("[","{").replace("]","}");
    }
}
