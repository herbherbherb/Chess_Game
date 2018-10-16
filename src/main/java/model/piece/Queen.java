package model.piece;

import model.Coordinate;
import model.Player;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece{
    /**
     *The queen combines the power of the rook and bishop and
     * can move any number of squares along rank, file, or diagonal,
     * but it may not leap over other pieces.
     */
    public final int NextDirection[][] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public Queen(Player owner) {
        super(owner, "Queen");
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
