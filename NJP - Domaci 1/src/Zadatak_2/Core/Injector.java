package Zadatak_2.Core;

import Zadatak_2.ManagedBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Marko on 20.10.2016.
 */
public class Injector {
    public Injector() {

    }

    public void checkConstructor(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Class obj = object.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
//            System.out.println(field.getName());
            if(field.isAnnotationPresent(ManagedBean.class))
                field.setAccessible(true);
                System.out.println(field.getName());
        }
    }
}
