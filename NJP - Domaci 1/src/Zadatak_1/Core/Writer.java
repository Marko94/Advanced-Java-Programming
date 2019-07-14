package Zadatak_1.Core;

import Zadatak_1.Example.AfterStore;
import Zadatak_1.Example.BeforeRemove;
import Zadatak_1.Example.Date;
import Zadatak_1.Example.Key;

/**
 * Created by Marko on 19.10.2016.
 */

public class Writer {
    @Key
    int a;

    @Date
    Dates c;

    String temp;

    public Writer() {
        a=1;
        temp ="Something";
    }

    @AfterStore
    public String toString(String a) {
        System.out.println("AfterStore");
        return a;
    }

    @BeforeRemove
    public void vrednost(String t){
        System.out.println("BeforeRemove");
        toString(t);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
