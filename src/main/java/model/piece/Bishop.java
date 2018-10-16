package model.piece;

import java.util.HashSet;
import java.util.Set;
import model.Coordinate;
import model.Player;

public class Bishop extends Piece{
    /**
     * The bishop can move any number of squares diagonally,
     * but may not leap over other pieces.
     */
    public final int NextDirection[][] = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public Bishop(Player owner) {
        super(owner, "Bishop");
    }

    @Override
    public Set<Coordinate> NextCoordinate(boolean self_Round) {
        if(this.Valid_Pos()){
            return this.Pos_In_Direction(NextDirection, self_Round);
        }
        else{
            Set<Coordinate> output = new HashSet<>();
            return output;
        }

    }
}
