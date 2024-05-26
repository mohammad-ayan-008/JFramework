package org.example.CrudInitializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bson.Document;
import org.example.Annotations.Entity;
import org.example.TEst.Client;
import org.example.TEst.USER;
import org.example.mongo.Initializer;

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
                        String name = mname.substring(mname.indexOf("By") + 2).toLowerCase();
                        System.out.println(name);
                        try {
                            clzz.getDeclaredField(name);
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        return;
                    });

        if (mname.equals("save")) {
            Object Clzz = args[0];
            Class<?> clazz = Clzz.getClass();
            System.out.println(clazz.getName());
            if (clazz.isAnnotationPresent(Entity.class)) {
                String collection = clazz.getAnnotation(Entity.class).collection();
                String json = new Gson().toJson(Clzz);
                Document doc = Document.parse(json);
                Initializer in = Initializer.getInstance(
                        "DATABASE",
                        "MyDatabase");

                in.save(collection, doc);
                System.out.println(Clzz.toString());
            } else {
                System.out.println("not an Entity Class");
            }
            return Clzz;
        }
        if (mname.equals("findAll")) {
            Class<?> clas =(Class<?>) args[0];
            System.out.println(clas.getName());
            if (clas.isAnnotationPresent(Entity.class)) {
                Initializer in = Initializer.getInstance(
                        "DATABASE",
                        "MyDatabase");
                String data =in.findALL(clas.getAnnotation(Entity.class).collection());
                Type type =new TypeToken<List<?>>(){}.getType();
                List<?> clz = new Gson().fromJson(data,type);
                return clz;
            }else{
                System.out.println("lol");
            }
        }
        return null;
        }

    }
