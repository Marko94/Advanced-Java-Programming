package Zadatak_1.Core;

import Zadatak_1.Example.AfterStore;
import Zadatak_1.Example.BeforeRemove;
import Zadatak_1.Example.Date;
import Zadatak_1.Example.Key;

/**
 * Created by Marko on 19.10.2016.
 */

public class ExampleObject {
    @Key
    int val;

    String tempString;

    private double tempDouble;

    @Date
    Dates date;

    public float tempFloat;

    public ExampleObject(int val) {
        super();
        this.val = val;
    }

    @AfterStore

    public void add(){
        System.err.println("Added");
    }

    @BeforeRemove
    public void remove(){
        System.err.println("Removed");
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getTempString() {
        return tempString;
    }

    public void setTempString(String tempString) {
        this.tempString = tempString;
    }

    public double getTempDouble() {
        return tempDouble;
    }

    public void setTempDouble(double tempDouble) {
        this.tempDouble = tempDouble;
    }

    public float getTempFloat() {
        return tempFloat;
    }

    public void setTempFloat(float tempFloat) {
        this.tempFloat = tempFloat;
    }
}
