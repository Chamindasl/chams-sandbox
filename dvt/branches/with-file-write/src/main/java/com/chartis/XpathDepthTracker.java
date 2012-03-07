package com.chartis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.custommonkey.xmlunit.XpathNodeTracker;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XpathDepthTracker {

    private Map<Integer, List<ElementDetail>> resultByDepth = new HashMap<Integer, List<ElementDetail>>();
    private Map<String, List<ElementDetail>> resultByXpath = new HashMap<String, List<ElementDetail>>();
    
    public void traverseNodes(Node node, XpathNodeTracker xpTracker, int currentDepth) {
        xpTracker.visited(node);
        NodeList children = node.getChildNodes();
        int chlidElements = 0;
        if (children != null) {
            xpTracker.indent();
            for (int i = 0; i < children.getLength(); i++) {
                Node childNode = children.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    traverseNodes(childNode, xpTracker, currentDepth + 1);
                    chlidElements++;
                }
            }
            xpTracker.outdent();
        }
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            String xpathString = xpTracker.toXpathString();
            ElementDetail result = resultElement(currentDepth, chlidElements, xpathString, (Element)node);
            addToDepthResult(currentDepth, result);
            addToParentXp(xpathString, result);

        }

    }

    private void addToParentXp(String xpathString, ElementDetail result) {
        String withoutLastIndex = XmlUtils.withoutLastIndex(xpathString);
        List<ElementDetail>  currentElementDetialsByXp = resultByXpath.get(withoutLastIndex);
        if (currentElementDetialsByXp == null) {
            currentElementDetialsByXp = new ArrayList<ElementDetail>();
            resultByXpath.put(withoutLastIndex, currentElementDetialsByXp);
        }
        currentElementDetialsByXp.add(result);
    }

    private void addToDepthResult(int currentDepth, ElementDetail result) {
        List<ElementDetail>  currentElementDetials = resultByDepth.get(currentDepth);
        if (currentElementDetials == null) {
            currentElementDetials = new ArrayList<ElementDetail>();
            resultByDepth.put(currentDepth, currentElementDetials);
        }
        currentElementDetials.add(result);
    }

    private ElementDetail resultElement(int currentDepth, int chlidElements, String xpathString, Element element) {
        ElementDetail result = new ElementDetail();
        result.setDepth(currentDepth);
        result.setChildElements(chlidElements);
        result.setXpath(xpathString);
        result.setElement(element);
        return result;
    }

    public Map<Integer, List<ElementDetail>> getResultByDepth() {
        return resultByDepth;
    }

    public Map<String, List<ElementDetail>> getResultByXpath() {
        return resultByXpath;
    }
    
}
