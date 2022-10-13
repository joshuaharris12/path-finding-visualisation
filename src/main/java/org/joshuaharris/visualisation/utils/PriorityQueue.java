package org.joshuaharris.visualisation.utils;


import org.joshuaharris.visualisation.representations.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//public class PriorityQueue<T> {
//    private List<Pair> elements;
//
//    public PriorityQueue() {
//        this.elements = new ArrayList<>();
//    }
//
//    public int size() {
//        return this.elements.size();
//    }
//
//    public Pair<T, Integer> remove(T element) throws Exception {
//        AtomicInteger i = new AtomicInteger(0);
//
//        List<Integer> matchingIndices = this.elements.stream()
//                .filter(p -> p.getKey().equals(element))
//                .map(p -> i.getAndIncrement())
//                .collect(Collectors.toList());
//
//        boolean exists = matchingIndices.size() > 0;
//
//        if (exists) {
//            int indexToRemove = matchingIndices.get(0);
//            return this.elements.remove(indexToRemove);
//        }
//
//        throw new Exception("Element not found");
//    }
//
//    public void add(T element, int weight) {
//        Pair<T, Integer> newPair = new Pair<>(element, weight);
//
//        List<Pair> matching = this.elements.stream()
//                .filter(p -> p.getKey().equals(element) && weight < (int) p.getValue())
//                .collect(Collectors.toList());
//
//        if (matching.isEmpty()) {
//            this.elements.add(newPair);
//        } else {
//            Pair<T, Integer> matchingPair = matching.get(0);
//
//            if (weight < matchingPair.getValue()) {
//                matchingPair.setValue(weight);
//            }
//        }
//    }
//
//    public boolean isEmpty() {
//        return this.elements.size() == 0;
//    }
//
//    public Pair<T, Integer> pop() throws Exception {
//        if (this.elements.isEmpty()) throw new Exception("Priority Queue is empty");
//
//       Pair<T, Integer> smallestPair = this.elements.stream()
//                .min(Comparator.comparingInt(p -> (int) p.getValue())).get();
//
//       this.elements.remove(smallestPair);
//       return smallestPair;
//    }
//}

public class PriorityQueue {
    private List<Node> elements;


    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public int size() {
        return this.elements.size();
    }

    public void add(Node node) {
        boolean alreadyExists = this.elements.contains(node);
       if (!alreadyExists) {
           this.elements.add(node);
       }
    }

    public Node pop() {
        Node smallestNode = this.elements.stream().min(Comparator.comparing(Node::computeF)).get();
        this.elements.remove(smallestNode);
        return smallestNode;
    }
}
