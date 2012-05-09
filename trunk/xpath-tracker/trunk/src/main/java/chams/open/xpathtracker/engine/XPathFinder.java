package chams.open.xpathtracker.engine;

import java.util.List;

import org.custommonkey.xmlunit.XpathNodeTracker;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import chams.open.xpathtracker.event.XPathFoundEvent;
import chams.open.xpathtracker.event.XPathFoundListener;

public class XPathFinder {

    private List<? extends XPathFoundListener> listeners;

    public List<? extends XPathFoundListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<? extends XPathFoundListener> listeners) {
        this.listeners = listeners;
    }

    public void find(final Document doc) {
        traverseNodes(doc, new XpathNodeTracker());
    }

    private void traverseNodes(final Node n, final XpathNodeTracker tracker) {
        NodeList children = n.getChildNodes();

        if (children != null) {
            for (int i = 0; i < children.getLength(); i++) {
                tracker.indent();
                Node childNode = children.item(i);
                // here would be a good place to put your application logic
                // and do something base upon node type
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    tracker.visited(childNode);
                    notifyListeners(childNode, tracker.toXpathString());
                }
                traverseNodes(childNode, tracker);
                tracker.outdent();

            }
        }
    }

    private void notifyListeners(final Node childNode, final String xpath) {
        final XPathFoundEvent event = new XPathFoundEvent();
        event.setXpath(xpath);
        event.setNoOfComplexChildElements(noOfChildComplexElements(childNode));
        event.setNoOfChildElements(noOfChildElements(childNode));
        event.setNoOfTextChildElements(noOfChildTextElements(childNode));
        for (final XPathFoundListener listener : listeners) {
            listener.notify(event);
        }
    }

    private boolean hasChildElements(final Node node) {
        if (node.hasChildNodes()) {
            NodeList nl = node.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node c = nl.item(i);
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                    return true;
                }
            }
        }
        return false;
    }

    private int noOfChildElements(final Node node) {
        if (node.hasChildNodes()) {
            NodeList nl = node.getChildNodes();
            int j = 0;
            for (int i = 0; i < nl.getLength(); i++) {
                Node c = nl.item(i);
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                    ++j;
                }
            }
            return j;
        }
        return 0;
    }

    private int noOfChildTextElements(final Node node) {
        if (node.hasChildNodes()) {
            NodeList nl = node.getChildNodes();
            int j = 0;
            for (int i = 0; i < nl.getLength(); i++) {
                Node c = nl.item(i);
                if (c.getNodeType() == Node.ELEMENT_NODE && !hasChildElements(c)) {
                    ++j;
                }
            }
            return j;
        }
        return 0;
    }

    private int noOfChildComplexElements(final Node node) {
        if (node.hasChildNodes()) {
            NodeList nl = node.getChildNodes();
            int j = 0;
            for (int i = 0; i < nl.getLength(); i++) {
                Node c = nl.item(i);
                if (hasChildElements(c)) {
                    ++j;
                }
            }
            return j;
        }
        return 0;
    }

}
