package chams.open.xpathtracker.event;

public class PrintListener implements XPathFoundListener{

    public void notify(final XPathFoundEvent e) {
        System.out.println(String.format("xpath: %s, NoOfChildElements: %d, NoOfTextChildElements: %d, NoOfComplexChildElements(): %d, "
                , e.getXpath(), e.getNoOfChildElements(), e.getNoOfTextChildElements(), e.getNoOfComplexChildElements()));
    }

}
