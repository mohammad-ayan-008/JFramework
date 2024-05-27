package org.example;

import java.util.List;

public interface CrudRepository <T,ID>{
    T save(T element);
    boolean deleteAll();
    boolean deleteByObject(T cls);
    List<T> findAll(Class<?> cls);
}
