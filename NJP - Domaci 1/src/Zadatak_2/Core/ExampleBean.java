package Zadatak_2.Core;

import Zadatak_2.ManagedBean;

/**
 * Created by Marko on 20.10.2016.
 */
public class ExampleBean {
    @ManagedBean
    AnotherBean anotherBean;

    public AnotherBean getAnotherBean() {
        return anotherBean;
    }

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }
}
