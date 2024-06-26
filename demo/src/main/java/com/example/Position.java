package com.example;

public class Position {
    private int aRowIndex;
    private int aColumnIndex;

    public Position(int pRowIndex, int pColumnIndex) {
        assert pRowIndex >= 0 && pColumnIndex >= 0;
        aRowIndex = pRowIndex;
        aColumnIndex = pColumnIndex;
    }

    public int getRow() {
        return aRowIndex;
    }

    public int getColumn() {
        return aColumnIndex;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + aColumnIndex;
        result = prime * result + aRowIndex;
        return result;
    }

    public boolean equals(Object pObject) {
        if (this == pObject) {
            return true;
        } else if (pObject == null) {
            return false;
        } else if (this.getClass() != pObject.getClass()) {
            return false;
        }

        Position other = (Position) pObject;
        return this.aColumnIndex == other.aColumnIndex && this.aRowIndex == other.aRowIndex;
    }

    public String toString() {
        return String.format("(r=%d, c=%d)", aRowIndex, aColumnIndex);
    }
}
