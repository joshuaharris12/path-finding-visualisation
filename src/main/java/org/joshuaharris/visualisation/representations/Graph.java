package org.joshuaharris.visualisation.representations;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private SquareType[][] grid;
    private int height;
    private int width;

    public Graph(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new SquareType[height][width];

        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                grid[row][col] = SquareType.SPACE;
            }
        }
    }

    public void setWall(Square square) {
        int row = square.getRow();
        int col = square.getCol();

        if (isValidSquare(square)) {
            grid[row][col] = SquareType.WALL;
        }
    }

    public void removeWall(Square square) {
        int row = square.getRow();
        int col = square.getCol();

        if (isValidSquare(square)) {
            grid[row][col] = SquareType.SPACE;
        }
    }

    public boolean isWall(Square square) {
        int row = square.getRow();
        int col = square.getCol();

        if (isValidSquare(square)) {
            return grid[row][col].equals(SquareType.WALL);
        }
        return true;
    }

    public boolean isValidSquare(Square square) {
        int row = square.getRow();
        int col = square.getCol();

        return (row >= 0) && (row < height) && (col >= 0) && (col < width);
    }

    public List<Square> getNeighbours(Square square) {
        List<Square> neighbours = new ArrayList<>();

        Square upper = new Square(square.getCol(), square.getRow()-1);
        if (isValidSquare(upper) && !isWall(upper)) neighbours.add(upper);

        Square lower = new Square(square.getCol(), square.getRow()+1);
        if (isValidSquare(lower) && !isWall(lower)) neighbours.add(lower);

        Square left = new Square(square.getCol()-1, square.getRow());
        if (isValidSquare(left) && !isWall(left)) neighbours.add(left);

        Square right = new Square(square.getCol()+1, square.getRow());
        if (isValidSquare(right) && !isWall(right)) neighbours.add(right);

        return neighbours;
    }

    @Override
    public String toString() {
        String toReturn = "";

        for (int row = 0; row < height; ++row) {
            String rowStr = "";
            for (int col = 0; col < width; ++col) {
                rowStr += String.format("%5s",grid[row][col].toString().substring(0,1));
            }
            toReturn += String.format("%s\n", rowStr);
        }
        return toReturn;
    }

    public void refresh() {
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                grid[row][col] = SquareType.SPACE;
            }
        }
    }

}


