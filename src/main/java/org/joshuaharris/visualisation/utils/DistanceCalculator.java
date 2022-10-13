package org.joshuaharris.visualisation.utils;

import org.joshuaharris.visualisation.representations.Square;


public class DistanceCalculator {

    public static int calculateManhattanDistance(Square from, Square to) {
        return Math.abs(from.getCol() - to.getCol()) + Math.abs(from.getRow() - to.getRow());
    }
}
