package Zadatak_1.Core;

import Zadatak_1.Example.AfterStore;
import Zadatak_1.Example.BeforeRemove;
import Zadatak_1.Example.Date;
import Zadatak_1.Example.Key;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Marko on 19.10.2016.
 */

public class Attributes {
    HashMap<Integer, Object> keyMap = new HashMap<>();
    public Attributes() {

    }
    public void store(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Class obj = object.getClass();

        Field[] fields = obj.getDeclaredFields();
        boolean added = false;
        for (Field field : fields) {
//            System.out.println(field);

            Annotation[] annotations = field.getDeclaredAnnotations();
//            for (Annotation annotation : annotations) {
//                System.out.println(annotation);
//            }

            if(field.isAnnotationPresent(Key.class)){
                field.setAccessible(true);
                int val = field.getInt(object);
                if(val!=0){
                    System.out.println("Key: "+val);
                    keyMap.put(val, object);
                    added=true;
                }
            }
            if(added){
                if(field.isAnnotationPresent(Date.class)){
                    try {
                        Class<?> cf=field.getType();
                        Object bj= cf.newInstance();
                        field.set(object, bj);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (added){
            Method[] methods = obj.getDeclaredMethods() ;
            for (Method metod : methods) {
//                System.out.println(metod);

                Annotation[] annotations = metod.getDeclaredAnnotations();
//                for (Annotation annotation : annotations) {
//                    System.out.println(annotation);
//                }

                if(metod.isAnnotationPresent(AfterStore.class)){
                    metod.setAccessible(true);
                    metod.invoke(object);
//                    metod.invoke(object, "Rand");
                }
            }
        }
    }

    public void remove(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
        if(findByPrimaryKey(object)!=null){
            int val = (int) findByPrimaryKey(object);

            Class obj = object.getClass();

            Method[] methods = obj.getDeclaredMethods() ;
            for (Method metod : methods) {
//                System.out.println(metod);

                Annotation[] annotations = metod.getDeclaredAnnotations();
//                for (Annotation annotation : annotations) {
//                    System.out.println(annotation);
//                }
                if(metod.isAnnotationPresent(BeforeRemove.class)){
                    metod.setAccessible(true);
                    metod.invoke(object);
//                    metod.invoke(object, "Random");
                }
            }
            keyMap.remove(val);
        }
    }

    public Object findByPrimaryKey(Object key) throws IllegalArgumentException, IllegalAccessException{
        Class obj = key.getClass();

        Field[] fields = obj.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field);

            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

            if(field.isAnnotationPresent(Key.class)){
                field.setAccessible(true);
                int val = field.getInt(key);
                return (Integer)val;
            }
        }
        return null;
    }
}
