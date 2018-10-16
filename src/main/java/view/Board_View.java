package view;

import model.Board;
import model.Coordinate;
import model.piece.Piece;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Set;


public class Board_View extends JPanel {
    protected final Board board;
    protected final Grid_View[][] myGrid;
    private final int width;
    private final int height;

    /**
     * Constructor for Board_view class
     * @param board input board model
     */
    public Board_View(Board board) {
        setLayout(null);
        this.board = board;
        this.width = board.Board_Row;
        this.height = board.Board_Col;
        myGrid = new Grid_View[board.Board_Row][board.Board_Col];
        create_Board();
    }

    /**
     * Create the board and add view to it
     */
    protected void create_Board() {
        for (int x = 0; x < height; ++x) {
            for (int y = width - 1; y > -1; --y) {
                Grid_View cur_grid = new Grid_View(new Coordinate(x, y));
                if (board.Occpuied(x, y)) {
                    Piece_View piece_view = new Piece_View(board.get_Piece(x, y));
                    Piece cur_Piece = board.get_Piece(x, y);
                    cur_Piece.piece_view = piece_view;
                    cur_grid.piece_view = piece_view;
                    this.add(piece_view);
                }
                myGrid[x][y] = cur_grid;
            }
        }
        for (int x = 0; x < height; ++x) {
            for (int y = width - 1; y > -1; --y) {
                this.add(myGrid[x][y]);
            }
        }
    }

    /**
     * Add action listener to each grid
     * @param e target
     */
    public void select_Grid(ActionListener e) {
        for (int x = 0; x < height; ++x)
            for (int y = width - 1; y > -1; --y)
                myGrid[x][y].addActionListener(e);
    }

    /**
     * Add action listener to each valid piece
     * @param e target
     */
    public void select_Piece(ActionListener e) {
        for (int x = 0; x < height; ++x) {
            for (int y = width - 1; y > -1; --y) {
                if(myGrid[x][y].piece_view != null){
                    myGrid[x][y].piece_view.addActionListener(e);
                }
            }
        }
    }

    /**
     * Change icons for all the next valid coordinates in next move
     * @param NextCoordinate The next valid coordinates a piece can move into
     */
    public void next_Grid(Set<Coordinate> NextCoordinate){
        for (Coordinate coord : NextCoordinate) {
            myGrid[coord.x][coord.y].set_selected();
        }
    }

    /**
     * remove all coordinates that has the selected icon view
     */
    public void clear_Grid() {
        for (int x = 0; x < height; ++x)
            for (int y = width - 1; y > -1; --y)
                myGrid[x][y].set_unselected();
    }
}
