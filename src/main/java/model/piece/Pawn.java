package model.piece;

import java.util.HashSet;
import java.util.Set;
import model.Coordinate;
import model.Player;


public class Pawn extends Piece{
    /**
     * The pawn may move forward to the unoccupied square immediately
     * in front of it on the same file; or on its first move it may
     * advance two squares along the same file provided both squares
     * are unoccupied; or it may move to a square occupied by an opponent's
     * piece which is diagonally in front of it on an adjacent file, capturing that piece.
     */
    public boolean isFirstMove = true;
    public Pawn(Player owner) {
        super(owner, "Pawn");
    }

    /**
     * Add target position to output if it is ok to move in the next round
     * @param next_x target coordinate x
     * @param next_y target coordinate y
     */
    public void Pawn_Next_Location(int next_x, int next_y, Set<Coordinate>output, boolean self_Round){
        if(!Valid_Pos(next_x, next_y)) return;
        if(this.board.valid_to_Move(this, next_x, next_y, self_Round)){
            Coordinate valid_coordinate = new Coordinate(next_x, next_y);
            output.add(valid_coordinate);
        }
    }

    /**
     * Pawn is a little complicated, and thus, it has its own function definition
     * Assume white player always start at the bottom and black player always starts at the top
     * @param self_Round
     * @return
     */
    @Override
    public Set<Coordinate> NextCoordinate(boolean self_Round) {
        Set<Coordinate> output = new HashSet<>();
        if(this.Valid_Pos()){
            int y_offset = (this.isBlack())?-1:1;
            int next_x = this.pos_x;
            int next_y = this.pos_y + y_offset;
            if(!this.board.Occpuied_by_Oppo(this, next_x, next_y))
                Pawn_Next_Location(next_x, next_y, output, self_Round);
            if(isFirstMove && !this.board.Occpuied(next_x, next_y)){
                next_y += y_offset;
                if(!this.board.Occpuied_by_Oppo(this, next_x, next_y))
                    Pawn_Next_Location(next_x, next_y, output, self_Round);
            }
            //Check if it can capture 2 diagonals place
            next_y = this.pos_y + y_offset;
            next_x = this.pos_x + 1;
            if(this.board.Occpuied_by_Oppo(this, next_x, next_y))
                Pawn_Next_Location(next_x, next_y, output, self_Round);
            next_x = this.pos_x - 1;
            if(this.board.Occpuied_by_Oppo(this, next_x, next_y))
                Pawn_Next_Location(next_x, next_y, output, self_Round);
            return output;
        }
        else{
            return output;
        }

    }

}
