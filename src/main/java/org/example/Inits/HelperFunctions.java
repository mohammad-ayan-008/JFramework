package org.example.Inits;

import org.example.CrudRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class HelperFunctions {
    public static boolean  isInterface(Class<?> clzz){
        return clzz.isInterface();
    }

    public static boolean isExtendingCrudRepo(Class<?> clz){
        return !Arrays.asList(clz.getInterfaces())
                .parallelStream()
                .filter(x->x.equals(CrudRepository.class)).toList().isEmpty();
    }

    public static boolean isparamType(Type typ){
        return typ instanceof ParameterizedType;
    }

    public static boolean isRawValid(Type typ,Class<?> tinterface){
        ParameterizedType type = (ParameterizedType) typ;
        Type raw = type.getRawType();
        return raw instanceof Class<?> && tinterface.isAssignableFrom((Class<?>) raw);
    }

    public static boolean isClass(Class<?> clzz){
        return  (clzz.isInterface() && clzz.isEnum() && clzz.isAnnotation());
    }
}
