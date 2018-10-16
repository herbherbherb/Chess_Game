package model.piece;

import java.util.HashSet;
import java.util.Set;
import model.Coordinate;
import model.Player;

public class King extends Piece{
    /**
     * The king moves one square in any direction.
     */
    public final int NextLocation[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public King(Player owner) {
        super(owner, "King");
    }

    @Override
    public Set<Coordinate> NextCoordinate(boolean self_Round) {
        if(this.Valid_Pos()){
            return this.Pos_In_Location(NextLocation, self_Round);
        }
        else{
            Set<Coordinate> output = new HashSet<>();
            return output;
        }

    }
}
