package view;
import model.Coordinate;
import javax.swing.*;

/**
 * Source: https://stackoverflow.com/questions/10084787/displaying-an-imageicon
 */

public class Grid_View extends Button_View {
    private static final int width = 50;
    private static final int height = 50;
    public final Coordinate coordinate;
    public Piece_View piece_view;

    /**
     * Constructor for Grid view
     * Grid view is view for tiles, either white or orange and change icon
     * when it becomes a valid tiles for a piece to move into.
     * @param coordinate
     */
    public Grid_View(Coordinate coordinate) {
        // odd position has white color, even position has black color

        super(new ImageIcon(model.Board.class.getResource("/"
                + ((coordinate.x + coordinate.y) % 2 == 1 ? "white" : "black") + "_Board"
                + ".png")).getImage(), width, height, false);
        this.coordinate = coordinate;
        this.convert_Coordinate(coordinate.x, coordinate.y);
        this.selected_BufferIcon(new ImageIcon(model.Board.class.getResource("/"
                + ((coordinate.x + coordinate.y) % 2 == 1 ? "white" : "black") + "_Board_Selected"
                + ".png")).getImage());
    }
}