package org.joshuaharris.visualisation.utils;

import org.joshuaharris.visualisation.representations.Square;

import java.util.Comparator;
import java.util.Map;

public class LowestCostComparator implements Comparator<Map.Entry<Square,Integer>> {

    @Override
    public int compare(Map.Entry<Square, Integer> o1, Map.Entry<Square, Integer> o2) {
        return Integer.compare(o1.getValue(), o2.getValue());
    }

}
