package model;

import model.piece.*;

public class Grid {
    protected Piece piece = null;

    /**
     * Getter method for Gird
     * @return piece
     */
    public Piece getPiece(){
        return this.piece;
    }

    /**
     * Setter method for Grid
     * @param other target piece to set
     */
    public void setPiece(Piece other){
        this.piece = other;
    }

    /**
     * Remove the current piece in current Grid
     */
    public void removePiece(){
        this.piece = null;
    }

    /**
     * Check if the Grid is empty
     * @return true if the Grid is empty
     */
    public boolean isEmpty(){
        return (this.piece == null);
    }

    @Override
    public String toString() {
        if(!this.isEmpty()) return this.piece.toString();
        return "";
    }
}
