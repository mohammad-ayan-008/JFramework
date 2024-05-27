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
    public Initializer in;
    public CrudInitializer(Map<Class<?>, List<Type>> genericClass) {
        try {
            in = Initializer.getInstance(
                   "mongodb+srv://mohammadayanafaq:nObewC7P2y067XSY@cluster0.bwktxd8.mongodb.net/test?retryWrites=true&w=majority&appName=Cluster0",
                   "MyDatabase");
        } catch (Exception e) {
           e.printStackTrace();
        }
        GenericClass = genericClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mname = method.getName();
        if (mname.startsWith("find") && mname.contains("By")) {
                        System.out.println(mname);
                        Class<?> clzz=method.getReturnType();
                        if (clzz.isAnnotationPresent(Entity.class)) {
                            String Collection = clzz.getAnnotation(Entity.class).collection();
                            String name = mname.substring(mname.indexOf("By") + 2).toLowerCase();
                            Object value_param = args[0];
                            try {
                                System.out.println("entered");
                                clzz.getDeclaredField(name);
                                Class c = method.getDeclaringClass();

                                String data = in.FindBy(Collection, name, value_param);
                                System.out.println(data);
                              //
                                 return new Gson().fromJson(data, clzz);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();

                            }
                        }
        }
        if (mname.equals("deleteByObject")){
            Class<?> clas =(Class<?>) args[0].getClass();
            System.out.println(clas.getName());
            if (clas.isAnnotationPresent(Entity.class)) {
                Field f = clas.getDeclaredField("id");
                f.setAccessible(true);
                Object value_param=f.get(args[0]);
                System.out.println(value_param);
                return in.DeleteByID(clas.getAnnotation(Entity.class).collection(),"id",value_param);
            }else{
                System.out.println(clas.getName()+" Not an Entity Class");
                return false;
            }
        }
        if (mname.equals("save")) {
            Object Clzz = args[0];
            Class<?> clazz = Clzz.getClass();
            System.out.println(clazz.getName());
            if (clazz.isAnnotationPresent(Entity.class)) {
                String collection = clazz.getAnnotation(Entity.class).collection();
                String json = new Gson().toJson(Clzz);
                Document doc = Document.parse(json);

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
                String data =in.findALL(clas.getAnnotation(Entity.class).collection());
                Type type =new TypeToken<List<?>>(){}.getType();
                List<?> clz = new Gson().fromJson(data,type);
                return clz;
            }else{
                System.out.println(clas.getName()+" Not an Entity Class");
            }
        }

        return null;
        }

    }
