package com.chartis;

public class ElementDetail {

    private int depth;
    private int childElements;
    private String xpath;

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public int getChildElements() {
        return childElements;
    }
    public void setChildElements(int childElements) {
        this.childElements = childElements;
    }
    public String getXpath() {
        return xpath;
    }
    public void setXpath(String xpath) {
        this.xpath = xpath;
    }
    @Override
    public String toString() {
        return "ElementDetail [depth=" + depth + ", childElements=" + childElements + ", xpath=" + xpath + "]";
    }
    
}
