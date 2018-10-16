package view;

import model.piece.Piece;
import javax.swing.*;
import java.awt.*;

public class Piece_View extends Button_View {
    private static final int width = 50;
    private static final int height = 50;

    public final Piece piece;

    public Piece_View(Piece piece) {
        super(getImage(piece), width, height, true);
        this.piece = piece;
        updateLocation();
    }

    public void updateLocation() {
        this.setVisible(true);
        convert_Coordinate(piece.pos_x, piece.pos_y);
    }

    /**
     * Given a piece, get the corresponding image icon
     * @param piece
     * @return
     */
    static private Image getImage(Piece piece) {
        String image_Name = "/" + (piece.isBlack() ? "black" : "white") + "_" + piece.name + ".png";
        return new ImageIcon(model.Board.class.getResource(image_Name)).getImage();
    }

    /**
     * When add a new piece to board, set the view to visible
     */
    public void add_View() {
        this.setVisible(true);
        this.updateLocation();
    }

    /**
     * When remove the piece from the board, set the view to invisible
     */
    public void remove_View() {
        this.setVisible(false);
    }
}
