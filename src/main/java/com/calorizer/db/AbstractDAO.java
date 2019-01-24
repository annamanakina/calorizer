package com.calorizer.db;

import com.calorizer.items.Entity;
import java.util.List;


public abstract class AbstractDAO<K, T extends Entity> {
    abstract List<T> getAll();
    abstract T getById(K id);
    abstract boolean delete (K id);
    abstract boolean delete (T object);
    abstract boolean create (T object);
    abstract T update(T object);

}
