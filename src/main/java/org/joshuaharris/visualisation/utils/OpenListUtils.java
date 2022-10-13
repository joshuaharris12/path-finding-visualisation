package org.joshuaharris.visualisation.utils;

import javafx.util.Pair;
import org.joshuaharris.visualisation.representations.Square;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenListUtils {

    public static Pair<Square, Integer> getLowestCostSquare(Map<Square, Integer> costs) {
        Map.Entry<Square, Integer> lowestCostEntry = costs.entrySet()
                .stream()
                .sorted(new LowestCostComparator()).collect(Collectors.toList()).get(0);
        return new Pair(lowestCostEntry.getKey(), lowestCostEntry.getValue());
    }
}