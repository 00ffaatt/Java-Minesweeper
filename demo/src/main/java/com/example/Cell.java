package com.example;

public class Cell {
    private enum CellInteraction {
        HIDDEN, MARKED, REVEALED
    }

    private boolean aIsMined;
    private CellInteraction aInteractionStatus = CellInteraction.HIDDEN;

    public Cell() {
    }

    public boolean isHidden() {
        return aInteractionStatus != CellInteraction.REVEALED;
    }

    public boolean isRevealed() {
        return aInteractionStatus == CellInteraction.REVEALED;
    }

    public boolean isMarked() {
        return aInteractionStatus == CellInteraction.MARKED;
    }

    public boolean isMined() {
        return aIsMined;
    }

    public void reveal() {
        aInteractionStatus = CellInteraction.REVEALED;
    }

    public void toggleMark() {
        assert isHidden();
        if (aInteractionStatus == CellInteraction.MARKED) {
            aInteractionStatus = CellInteraction.HIDDEN;
        } else {
            aInteractionStatus = CellInteraction.MARKED;
        }
    }

    public void mine() {
        aIsMined = true;
    }

}
