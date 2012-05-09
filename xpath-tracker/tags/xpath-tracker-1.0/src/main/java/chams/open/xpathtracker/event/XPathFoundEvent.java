package chams.open.xpathtracker.event;

public class XPathFoundEvent {
    
    private String xpath;
    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    private int noOfChildElements;
    private int noOfComplexChildElements;
    private int noOfTextChildElements;

    public int getNoOfChildElements() {
        return noOfChildElements;
    }

    public void setNoOfChildElements(int noOfChildElements) {
        this.noOfChildElements = noOfChildElements;
    }

    public int getNoOfComplexChildElements() {
        return noOfComplexChildElements;
    }

    public void setNoOfComplexChildElements(int noOfChildComplexElements) {
        this.noOfComplexChildElements = noOfChildComplexElements;
    }

    public int getNoOfTextChildElements() {
        return noOfTextChildElements;
    }

    public void setNoOfTextChildElements(int noOfChildTextElements) {
        this.noOfTextChildElements = noOfChildTextElements;
    }

}
