package org.example.CrudInitializer;

import org.example.TEst.Client;
import org.example.TEst.USER;

import java.lang.reflect.*;
import java.util.*;

public class CrudInitializer<T,ID> implements InvocationHandler {
    private Map<Class<?>, List<Type>> GenericClass = new HashMap<>();

    public CrudInitializer(Map<Class<?>, List<Type>> genericClass) {
        GenericClass = genericClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mname = method.getName();
        if (mname.startsWith("findBy"))

            GenericClass.values()
                    .forEach(arrayVal -> {
                        System.out.println(mname);
                        Class<?> clzz = (Class<?>) arrayVal.get(0);
                        String name = mname.substring(mname.indexOf("By")+2).toLowerCase();
                        System.out.println(name);
                        try {
                            clzz.getDeclaredField(name);
                        } catch (NoSuchFieldException e) {
                           e.printStackTrace();
                        }
                        return;
                    });
        return null;
        }

    }
