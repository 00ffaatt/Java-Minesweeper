package com.example;

import java.util.*;

public class Minefield {
    private Cell[][] aCells;
    private final ArrayList<Position> aAllPositions = new ArrayList<>();
    private MinefieldStatus aMinefieldStatus;

    public Minefield(int pRows, int pColumns, int pMines) {
        assert pRows > 0 && pColumns > 0 && pMines > 0;
        assert pMines < pRows * pColumns;
        aCells = new Cell[pRows][pColumns];
        aMinefieldStatus = MinefieldStatus.NOT_CLEARED;
        initialize();
        placeMines(pMines);
    }

    private void initialize() {
        for (int row = 0; row < aCells.length; row++) {
            for (int column = 0; column < aCells[0].length; column++) {
                aCells[row][column] = new Cell();
                aAllPositions.add(new Position(row, column));
            }
        }
    }

    public List<Position> getAllPositions() {
        return Collections.unmodifiableList(aAllPositions);
    }

    private Cell getCell(Position pPosition) {
        return aCells[pPosition.getRow()][pPosition.getColumn()];
    }

    public boolean isMined(Position pPosition) {
        return getCell(pPosition).isMined();
    }

    public boolean isRevealed(Position pPosition) {
        return getCell(pPosition).isRevealed();
    }

    public boolean isMarked(Position pPosition) {
        return getCell(pPosition).isMarked();
    }

    public void reveal(Position pPosition) {
        assert pPosition != null;
        Cell cell = getCell(pPosition);

        if (cell.isMined()) {
            revealAll();
        } else {
            cell.reveal();
            autoReveal(pPosition);
        }
    }

    public void toggleMark(Position pPosition) {
        getCell(pPosition).toggleMark();
    }

    private void placeMines(int pNumberOfMines) {
        List<Position> positions = aAllPositions;
        Collections.shuffle(positions);
        for (int i = 0; i < pNumberOfMines; i++) {
            this.getCell(positions.get(i)).mine();
        }
    }

    public MinefieldStatus getStatus() {
        return aMinefieldStatus;
    }

    public int getNumberOfMinesLeft() {
        int total = 0;
        for (Cell[] cells : aCells) {
            for (Cell cell : cells) {
                if (cell.isMined()) {
                    total++;
                }
            }
        }
        return total;
    }

    private int getNumberOfRows() {
        return aCells.length;
    }

    private int getNumberOfColumns() {
        return aCells[0].length;
    }

    private void revealAll() {
        for (Position position : aAllPositions) {
            getCell(position).reveal();
        }
    }

    private List<Position> getNeighbours(Position pPosition) {
        final List<Position> neighbours = new ArrayList<>();
        for (int row = Math.max(0, pPosition.getRow() - 1); row <= Math.min(getNumberOfRows() - 1,
                pPosition.getRow() + 1); row++) {
            for (int column = Math.max(0, pPosition.getColumn() - 1); column <= Math.min(getNumberOfColumns() - 1,
                    pPosition.getColumn() + 1); column++) {
                final Position position = new Position(row, column);
                if (!position.equals(pPosition)) {
                    neighbours.add(position);
                }
            }
        }
        return neighbours;
    }

    public int getNumberOfMinedNeighbours(Position pPosition) {
        return internalGetNumberOfNeighbours(pPosition);
    }

    private int internalGetNumberOfNeighbours(Position pPosition) {
        int total = 0;
        for (Position neighbour : getNeighbours(pPosition)) {
            if (getCell(neighbour).isMined()) {
                total++;
            }
        }
        return total;
    }

    private void autoReveal(Position pPosition) {
        if (internalGetNumberOfNeighbours(pPosition) == 0) {
            for (Position neighbour : getNeighbours(pPosition)) {
                if (getCell(neighbour).isHidden() && !getCell(neighbour).isMarked()) {
                    getCell(neighbour).reveal();
                    autoReveal(neighbour);
                }
            }
        }
    }

}