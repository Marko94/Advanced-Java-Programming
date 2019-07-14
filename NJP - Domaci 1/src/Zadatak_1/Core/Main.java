package Zadatak_1.Core;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Marko on 19.10.2016.
 */
public class Main {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        ExampleObject e = new ExampleObject(12);

        Writer t = new Writer();

        Attributes at = new Attributes();

        at.store(e);
//        System.out.println(at.findByPrimaryKey(e));
        at.remove(e);
    }
}
