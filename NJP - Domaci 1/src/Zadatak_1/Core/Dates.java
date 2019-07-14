package Zadatak_1.Core;

import java.util.Date;

/**
 * Created by Marko on 19.10.2016.
 */

public class Dates {
    Date date;

    public Dates() {
        date = new Date();
        System.out.println("Date: " + date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
