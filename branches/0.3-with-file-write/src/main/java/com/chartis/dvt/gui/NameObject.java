package com.chartis.dvt.gui;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class NameObject {

    private String name;
    private Object object;

    public void setName(String name) {
        this.name = name;
    }
    public NameObject(String name, Object object) {
        this.name = name;
        this.object = object;
    }
    public String getName() {
        return name;
    }
    public Object getObject() {
        return object;
    }
    @Override
    public String toString() {
        return name;
    }
    
}
