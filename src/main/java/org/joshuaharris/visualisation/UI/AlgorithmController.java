package org.joshuaharris.visualisation.UI;

import org.joshuaharris.visualisation.algorithms.DepthFirstSearch;
import org.joshuaharris.visualisation.exceptions.InvalidButtonIdException;
import org.joshuaharris.visualisation.representations.AlgorithmType;
import org.joshuaharris.visualisation.representations.Graph;
import org.joshuaharris.visualisation.representations.Square;

import java.util.List;

public class AlgorithmController {

    public static List<Square> performAlgorithm(AlgorithmType type, Graph graph, Square start, Square target) {
        switch (type) {
            case DFS:
                return DepthFirstSearch.find(graph, start, target);
            default:
                System.out.println(type);
                throw new InvalidButtonIdException("Invalid Algorithm Type provided");
        }
    }
}
