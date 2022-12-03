package carsharing.dao;

import java.util.List;

public interface Dao<E, K> {
    public List<E> getAll();
    public E get(K k);
    public void update(E e);
    public void del(E e);
}
