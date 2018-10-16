package model.piece;
import java.util.HashSet;
import java.util.Set;
import model.Board;
import model.Coordinate;
import model.Player;
import view.Piece_View;

public abstract class Piece {

    public Board board;
    public int pos_x;
    public int pos_y;
    public final Player player;
    public final String color;
    public final String name;
    public Piece_View piece_view;

    /**
     * Abstract class constructor for generic Piece class
     * @param player
     * @param name
     */
    public Piece(Player player, String name){
        if(player == null) throw new IllegalArgumentException("Player is invalid (null)!");
        this.player = player;
        this.color =  (player.isBlack)?"Black":"White";
        this.name = name;
    }

    public abstract Set<Coordinate> NextCoordinate(boolean self_Round);

    /**
     * Set the position for piece
     * @param pos_x
     * @param pos_y
     */
    public void setPosition(int pos_x, int pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public boolean isBlack(){
        return (this.color.equals("Black"))?true:false;
    }

    /**
     * Compare if 2 pieces have the same color
     * @param other piece to be compared with in term of color
     * @return true if both pieces have the same color, false otherwise
     */
    public boolean compareTo(Piece other){
        return (this.color.equals(other.color))?true:false;
    }

    public boolean Valid_Pos(){
        if(this.board == null) return false;
        int row = this.pos_x; int col = this.pos_y;
        if(row >= 0 && col >= 0 && row < this.board.Board_Row && col < this.board.Board_Col) return true;
        return false;
    }

    public boolean Valid_Pos(int row, int col){
        if(this.board == null) return false;
        if(row >= 0 && col >= 0 && row < this.board.Board_Row && col < this.board.Board_Col) return true;
        return false;
    }

    /**
     * Select all available coordinates in the
     * @param NextDirection
     * @return
     */
    public Set<Coordinate> Pos_In_Direction(int [][] NextDirection, boolean self_Round){
        Set<Coordinate> output = new HashSet<>();
        for(int current[]: NextDirection){
            int x_offset = current[0];
            int y_offset = current[1];
            int next_x = this.pos_x;
            int next_y = this.pos_y;
            while(true){
                next_x += x_offset;
                next_y += y_offset;
                if(!Valid_Pos(next_x, next_y) || this.board.Occpuied_by_Friendly(this, next_x, next_y)) break;
                if(this.board.valid_to_Move(this, next_x, next_y, self_Round)){
                    Coordinate valid_coordinate = new Coordinate(next_x, next_y);
                    output.add(valid_coordinate);
                    if(this.board.Occpuied_by_Oppo(this, valid_coordinate.x, valid_coordinate.y))
                        break;
                }
            }
        }
        return output;
    }

    public Set<Coordinate> Pos_In_Location(int [][] NextLocation, boolean self_Round){
        Set<Coordinate> output = new HashSet<>();
        for(int current[]: NextLocation) {
            int next_x = this.pos_x + current[0];
            int next_y = this.pos_y + current[1];
            if(!Valid_Pos(next_x, next_y)) continue;
            if(this.board.valid_to_Move(this, next_x, next_y, self_Round)){
                Coordinate valid_coordinate = new Coordinate(next_x, next_y);
                output.add(valid_coordinate);
            }
        }
        return output;
    }

    /**
     * toString() method to print out given piece's information
     * @return string
     */
    @Override
    public String toString()
    {return this.color + " " + this.name + " @ ("
            + this.pos_x + "," + this.pos_y + ")";}
}
