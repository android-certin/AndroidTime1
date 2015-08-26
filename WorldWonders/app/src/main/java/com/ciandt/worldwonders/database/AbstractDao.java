package com.ciandt.worldwonders.database;

import java.util.List;

/**
 * Created by jfranco on 8/24/15.
 */
public interface AbstractDao<T> {
    List<T> getAll();
    T getById(int id);
    List<T> search(String word);
    boolean insert(T object);
    boolean update(T object);
    boolean delete(T object);
    void close();
}
