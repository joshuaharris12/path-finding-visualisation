package org.joshuaharris.visualisation.representations;

public class Node {

    private Square square;
    private int gVal;
    private int hVal;


    public Node(Square square) {
        this.square = square;
        this.gVal = 0;
        this.hVal = 0;
    }

    public Square getSquare() { return this.square; }
    public int g() { return this.gVal; }
    public int h() { return this.hVal; }
    public int computeF() { return (this.g() + this.hVal); }

    public void setG(int gVal) { this.gVal = gVal; }
    public void setH(int hVal) { this.hVal = hVal; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{Square=");
        sb.append(square.toString());
        sb.append(" h-value=");
        sb.append(this.hVal);
        sb.append(" g-value=");
        sb.append(this.gVal);
        sb.append("}");
        return sb.toString();
    }
}
