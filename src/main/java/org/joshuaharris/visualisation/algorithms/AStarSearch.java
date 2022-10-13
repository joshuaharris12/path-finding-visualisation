package org.joshuaharris.visualisation.algorithms;

import org.joshuaharris.visualisation.representations.Node;
import org.joshuaharris.visualisation.representations.Square;
import org.joshuaharris.visualisation.representations.Graph;
import org.joshuaharris.visualisation.utils.DistanceCalculator;
import org.joshuaharris.visualisation.utils.PriorityQueue;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class AStarSearch {

    public static List<Node> find(Graph graph, Square start, Square target) {
        PriorityQueue openList = new PriorityQueue();
        Set<Square> closedList = new HashSet<>();
        Node startNode = new Node(start);
        startNode.setG(0);
        startNode.setH(DistanceCalculator.calculateManhattanDistance(start, target));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node q = openList.pop();

            List<Square> successors = graph.getNeighbours(q.getSquare());

            for (Square successor : successors) {
                if (successor.equals(target)) break;

                Node newNode = new Node(successor);

            }

        }
    }


}