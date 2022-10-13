package org.joshuaharris.visualisation.algorithms;

import org.joshuaharris.visualisation.representations.Graph;
import org.joshuaharris.visualisation.representations.Square;

import java.util.*;

public class BreadthFirstSearch {

    public static List<Square> find(Graph graph, Square start, Square target) {
        HashSet<Square> visited = new HashSet<>();
        HashMap<Square, Square> memory = new HashMap<>();
        ArrayList<Square> openList = new ArrayList<>();
        openList.add(start);
        memory.put(start, null);

        while (!openList.isEmpty()) {
            System.out.println(String.format("Openlist: %s", openList.toString()));
            Square current = openList.remove(0);
            visited.add(current);

            List<Square> neighbours = graph.getNeighbours(current);

            for (Square neighbour: neighbours) {
                if (!visited.contains(neighbour)) {
                    memory.put(neighbour, current);
                    if (neighbour.equals(target)) {
                        return constructPathFromMemory(memory, target);
                    } else {
                        openList.add(neighbour);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private static List<Square> constructPathFromMemory(HashMap<Square, Square> memory, Square target) {
        List<Square> path = new ArrayList<>();
        Square current = target;

        while (current != null) {
            path.add(current);
            current = memory.get(current);
        }
        return path;
    }
}
