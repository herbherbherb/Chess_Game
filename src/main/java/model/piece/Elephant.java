package model.piece;

import java.util.HashSet;
import java.util.Set;
import model.Coordinate;
import model.Player;

public class Elephant extends Piece{
    /**
     * The knight moves to any of the closest squares that are not on the same rank,
     * file, or diagonal, thus the move forms an "L"-shape: two squares vertically
     * and one square horizontally, or two squares horizontally and one square vertically.
     * The knight is the only piece that can leap over other pieces.
     */
    public final int NextLocation[][] = {{-2, 2}, {-2, -2}, {2, 2}, {2, -2}};
    public Elephant(Player owner) {
        super(owner, "Elephant");
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
