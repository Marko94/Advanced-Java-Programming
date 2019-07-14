package Zadatak_2.Core;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Marko on 20.10.2016.
 */
public class Main {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        ExampleBean e = new ExampleBean();
        Injector i = new Injector();
        i.checkConstructor(e);
    }
}
