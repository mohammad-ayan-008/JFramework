package org.example.Inits;

import org.example.Annotations.AutoConfig;
import org.example.CrudInitializer.CrudFactory;
import org.example.Main;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCInitilaizer {
    private  Map<Class<?>,List<Type>> GenericClass = new HashMap<>();
    private  Map<String,Field> fields = new HashMap<>();

    public JDBCInitilaizer(){
      var allclasses = getAllClasses();
      setupFileds(allclasses);
      var datasets = filterInterfaces_ExtemdingCRudRep(allclasses);
      fetchGenerics(datasets);
        GenericClass.forEach((parentClass,genericList)->{
            Object clzz= (Object) CrudFactory.createRepo(GenericClass,parentClass);
            System.out.println(parentClass);
            Field field = fields.get(parentClass.getName());
            if (field != null){
                try {
                    field.set(field.getDeclaringClass().newInstance(),clzz);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(field);

        });

    }

    private void setupFileds(List<Class<?>> list) {
       list.parallelStream()
               .forEach(clas->{
                    Arrays.stream(clas.getDeclaredFields())
                            .filter(x->x.isAnnotationPresent(AutoConfig.class))
                            .forEach(field -> {
                                fields.put(field.getType().getName(),field);
                            });
               });
    }

    private void fetchGenerics(List<Class<?>> clzz){
          clzz.stream()
                  .forEach(superClass->{
                      Arrays.asList(superClass.getGenericInterfaces())
                      .forEach(sets ->{
                              ParameterizedType typ = (ParameterizedType) sets;
                              GenericClass.put(superClass,Arrays.stream(typ.getActualTypeArguments()).toList());
                          });
                  });
    }


    private List<Class<?>> getAllClasses(){
        Reflections reff = new Reflections("org.example",new SubTypesScanner(false));
        return  reff.getSubTypesOf(Object.class).stream().toList();
    }

    private List<Class<?>> filterInterfaces_ExtemdingCRudRep(List<Class<?>> list){
        return list.parallelStream().filter(HelperFunctions::isInterface).filter(HelperFunctions::isExtendingCrudRepo).toList();
    }


}
