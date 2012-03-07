package com.chartis;

import static com.chartis.XmlUtils.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

public class Mapper {

    public Mapper(final Document source2, final Document target2, final XpathDepthTracker sourceTracker,
            final XpathDepthTracker targetTracker) {
        this.source = source2;
        this.target = target2;
        this.sourceTracker = sourceTracker;
        this.targetTracker = targetTracker;
    }

    private Document source;
    private Document target;
    private XpathDepthTracker sourceTracker;
    private XpathDepthTracker targetTracker;
    private Map<String, String> sourceToTarget = new HashMap<String, String>();
    private Map<String, String> targetToSource = new HashMap<String, String>();

    public void map() {
        Map<Integer, List<ElementDetail>> sourceDepthMap = sourceTracker.getResultByDepth();
        for (int i = 1; i <= sourceDepthMap.size(); i++) {
            for (ElementDetail e : sourceDepthMap.get(i)) {
                mapSourceToTarget(e.getXpath());
            }
        }
    }

    private void mapSourceToTarget(String xpath) {
        String t = null;
        String parentXp = parentXpath(xpath);
        String targetParent = sourceToTarget.get(parentXp);
        if (targetParent == null) {
            targetParent = "";
        }
        String name = removeIndex(lastName(xpath));

        String targetName = targetParent + name;
        List<ElementDetail> matchingList = targetTracker.getResultByXpath().get(targetName);
        t = matchingXpath(xpath, targetName, matchingList);
        if (t != null) {
            if (targetToSource.get(t) == null) {
                targetToSource.put(t, xpath);
                sourceToTarget.put(xpath, t);
            } else {
                System.out.println(" Found Conflict " + xpath);
                sourceToTarget.put(xpath, null);
            }
        } else {
            System.out.println("Count not find a matching for " + xpath);
            sourceToTarget.put(xpath, t);
        }
    }

    private String matchingXpath(String xpath, String targetName, List<ElementDetail> matchingList) {
        String t = null;
        if (matchingList != null && matchingList.size() != 0) {
            if (matchingList.size() == 1) {
                System.out.println(" Found exact matching for " + xpath + " in " + targetName + "[1]");
                t = targetName + "[1]";
            } else {
                String matchingXpath = null;
                for (ElementDetail e : matchingList) {
                    if (targetToSource.get(e.getXpath()) == null) {
                        matchingXpath = e.getXpath();
                        break;
                    }
                }
                System.out.println(" Found " + matchingList.size() + " matchings for " + xpath + ", selected "
                        + matchingXpath);
                t = matchingXpath;
            }
        }
        return t;
    }

    public Map<String, String> getSourceToTarget() {
        return sourceToTarget;
    }

    public Map<String, Integer> getResult(String xpath) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        return result;
        
    }
}
