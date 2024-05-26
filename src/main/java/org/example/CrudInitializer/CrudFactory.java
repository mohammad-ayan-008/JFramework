package org.example.CrudInitializer;

import org.example.CrudRepository;

import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudFactory {
    private static List<Class<?>> interfaceClasses = new ArrayList<>();

    public static  <T,ID> CrudRepository<T,ID> createRepo(Map<Class<?>,List<Type>> GenericClass , Class<?> infce){
        interfaceClasses.add(infce);
        return (CrudRepository<T, ID>)
                Proxy.newProxyInstance(
                        CrudRepository.class.getClassLoader(),
                        interfaceClasses.toArray(new Class<?>[0]),
                        new CrudInitializer<>(GenericClass)
                );
    }

}
