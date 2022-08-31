package org.joshuaharris.visualisation.algorithms;//import org.joshuaharris.graph.search.recusive.Node;


import org.joshuaharris.visualisation.representations.Graph;
import org.joshuaharris.visualisation.representations.Square;

import java.util.*;

public class DepthFirstSearch {

//    public static List<Node<String>> recursive(Node<String> start, Node<String> target) {
//        if (start.equals(target)) {
//            List<Node<String>> toReturn = new ArrayList<>();
//            toReturn.add(start);
//            return toReturn;
//        }
//
//        List<Node<String>> neighbours = start.getNeighbours();
//
//        for (Node<String> v : neighbours) {
//            List<Node<String>> path = recursive(v, target);
//
//            if (!path.isEmpty()) {
//                path.add(0, v);
//                return path;
//            }
//        }
//        return Arrays.asList(null, null);
//    }


    public static List<Square> findPath(Graph graph, Square start, Square target) {
        if (start.equals(target)) {
            System.out.println("Start == end");
            return Arrays.asList(start);
        }

        List<Square> frontier = new ArrayList<>();
        frontier.add(start);

        HashSet<Square> explored = new HashSet<>();
        HashMap<Square, Square> memory = new HashMap<>(); // <child, parent>

        memory.put(start, null);


        while (!frontier.isEmpty()) {
            Square u = frontier.remove(frontier.size()-1);
            explored.add(u);
            System.out.println(String.format("Frontier [%s]", u));

            List<Square> neighbours = graph.getNeighbours(u);
            System.out.println(String.format("Current: %s,    neighbours: [%s]",u.toString(),neighbours.toString()));
            for (int i = 0; i < neighbours.size(); ++i) {
                Square v = neighbours.get(i);

                memory.put(v, u);

                if (!explored.contains(v) && !frontier.contains(v)) {
                    if (v.equals(target)) {

                        List<Square> path = reconstructPath(start, target, memory);
                        return path;
                    }
                    frontier.add(v);
                }

            }
        }

        return Arrays.asList();
    }

    private static List<Square> reconstructPath(Square start, Square end, HashMap<Square, Square> memory) {
        List<Square> path = new ArrayList<>();
        Square u = end;

        System.out.println(String.format("Memory: %s", memory));


        while (u != null) {
            path.add(u);
            u = memory.get(u);
        }
        return path;
    }

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
