package org.joshuaharris.visualisation.algorithms;//import org.joshuaharris.graph.search.recusive.Node;

import org.joshuaharris.visualisation.representations.Graph;
import org.joshuaharris.visualisation.representations.Square;

import java.util.*;

public class DepthFirstSearch {

    public static List<Square> find(Graph graph, Square start, Square target) {
        HashSet<Square> visited = new HashSet<>();
        HashMap<Square, Square> memory = new HashMap<>();
        Stack<Square> stack = new Stack<>();
        stack.push(start);
        memory.put(start, null);

        while (!stack.isEmpty()) {
            Square current = stack.pop();
            visited.add(current);

            List<Square> neighbours = graph.getNeighbours(current);

            for (Square neighbour: neighbours) {
                if (!visited.contains(neighbour)) {
                    memory.put(neighbour, current);
                    if (neighbour.equals(target)) {
                        return constructPathFromMemory(memory, target);
                    } else {
                        stack.push(neighbour);
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