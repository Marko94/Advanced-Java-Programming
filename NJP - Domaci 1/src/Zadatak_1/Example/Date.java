package Zadatak_1.Example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Marko on 19.10.2016.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface Date {

}
