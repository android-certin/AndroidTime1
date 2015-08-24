package com.ciandt.worldwonders.database;

import java.util.List;

/**
 * Created by jfranco on 8/24/15.
 */
public interface Dao<T> {
    public List<T> getAll();
    public T getById(int id);
    public List<T> search(String word);
    public boolean insert(T object);
    public boolean update(T object);
    public boolean delete(T object);
    public void close();
}
