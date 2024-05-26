package org.example;

import java.util.List;

public interface CrudRepository <T,ID>{
    T save(T element);
    boolean deleteAll();
    boolean deleteByID();
    List<T> findAll(Class<?> cls);
}
