package model;

import model.piece.Pawn;
import model.piece.Piece;

public class Movement {
    protected Board board;
    protected Coordinate starting;
    protected Coordinate dest;
    protected Piece captured_piece;
    protected boolean isFirstMove;
    /**
     * Constructor for Movement instance
     * @param board Board element
     * @param starting The starting coordinate the piece from
     * @param dest The destination coordinate that the piece will be moved to
     * @param captured_piece If the destination coordinate has occupied piece and captured by this
     */
    public Movement(Board board, Coordinate starting, Coordinate dest, Piece captured_piece){
        this.board = board;
        this.starting = starting;
        this.dest = dest;
        this.captured_piece = captured_piece;
        Piece current_piece = board.get_Piece(starting.x, starting.y);
        if(current_piece != null && current_piece instanceof  Pawn){
            this.isFirstMove = ((Pawn) current_piece).isFirstMove;
        }
    }
}
