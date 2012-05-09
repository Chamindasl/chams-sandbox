package chams.open.xpathtracker.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHAMINDA.AMARASINGHE
 * 
 */
public class ListHolderListener implements XPathFoundListener {

    private List<XPathFoundEvent> xPathFoundEvents = new ArrayList<XPathFoundEvent>();;

    public void notify(final XPathFoundEvent e) {
        xPathFoundEvents.add(e);
    }

    public List<XPathFoundEvent> getXPathFoundEvents() {
        return xPathFoundEvents;
    }

}
