package org.example.Inits;

import org.example.CrudInitializer.CrudFactory;
import org.example.Main;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCInitilaizer {
    private  Map<Class<?>,List<Type>> GenericClass = new HashMap<>();

    public JDBCInitilaizer(){
      var datasets = filterInterfaces_ExtemdingCRudRep(getAllClasses());
      fetchGenerics(datasets);
        GenericClass.forEach((parentClass,genericList)->{
            Object clzz= (Object) CrudFactory.createRepo(GenericClass,parentClass);
                Class<?> clss = Main.class;
                try {
                    Field f = clss.getDeclaredField("inf");
                    f.set(clss.newInstance(), clzz);
                } catch (Exception e) {

                }
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
