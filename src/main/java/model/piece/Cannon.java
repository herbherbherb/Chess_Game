package model.piece;

import java.util.HashSet;
import java.util.Set;
import model.Coordinate;
import model.Player;

public class Cannon  extends Piece{
    /**
     * The cannon can move any number of squares along any rank or file,
     * but in order to move,  it has to leap over other pieces and capture.
     */

    public final int NextDirection[][] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public Cannon(Player owner) {
        super(owner, "Cannon");
    }
    @Override
    public Set<Coordinate> NextCoordinate(boolean self_Round) {
        if(this.Valid_Pos()){
            Set<Coordinate> output = new HashSet<>();
            Set<Coordinate> rowSet = new HashSet<>();
            for(int current[]: NextDirection) {
                int number_in_dir = 0;
                int x_offset = current[0];
                int y_offset = current[1];
                int next_x = this.pos_x + x_offset;
                int next_y = this.pos_y + y_offset;
                while(Valid_Pos(next_x, next_y)){
                    if(this.board.Occpuied(next_x, next_y)){
                        number_in_dir = number_in_dir + 1;
                        //If it is not the first piece in direction and have a different color than cannon's color
                        if(number_in_dir == 2 && !this.compareTo(this.board.Occpuied_Piece(next_x, next_y))){
                            Coordinate possible_coordinate = new Coordinate(next_x, next_y);
                            rowSet.add(possible_coordinate);
                        }
                    }
                    next_x += x_offset;
                    next_y += y_offset;
                }
            }
            for(Coordinate possible: rowSet){
                if(this.board.valid_to_Move(this, possible.x, possible.y, self_Round)){
                    output.add(possible);
                }
            }
            return output;
        }
        else{
            Set<Coordinate> output = new HashSet<>();
            return output;
        }

    }

}
