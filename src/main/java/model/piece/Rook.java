package model.piece;

import java.util.HashSet;
import java.util.Set;
import model.Coordinate;
import model.Player;

public class Rook extends Piece {
    /**
     * The rook can move any number of squares along any rank or file,
     * but may not leap over other pieces.
     */

    public final int NextDirection[][] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public Rook(Player owner) {
        super(owner, "Rook");
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
