package view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Source: https://stackoverflow.com/questions/11687527/how-to-set-transparent-png-to-jbutton
 */

public class Button_View extends JButton {
    protected final BufferedImage bufferImage;
    protected ImageIcon bufferIcon;
    protected ImageIcon bufferIconSelected;
    private final int width;
    private final int height;

    /**
     * Constructor for one button view instance
     * @param image raw image for the button
     * @param width desire to scale to width
     * @param height desire to scale to height
     * @param isPieceType true if the button is a instance of piece, otherwise false
     */
    public Button_View(Image image, int width, int height, boolean isPieceType){
        this.bufferImage = this.scale_Image(image, width, height);
        this.width = width;
        this.height = height;
        this.bufferIcon = new ImageIcon(this.bufferImage);
        if(isPieceType){this.bufferIconSelected = this.bufferIcon;}
        this.setIcon(this.bufferIcon);
        this.setDisabledIcon(this.bufferIcon);
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(getPreferredSize());
        this.setContentAreaFilled(false);
        this.setLayout(null);
        this.setBorderPainted(false);
        this.setOpaque(false);
    }

    /**
     * Set bufferIconSelected when it is selected
     * @param image image when the grid is selected
     */
    public void selected_BufferIcon(Image image) {
        BufferedImage bufferImagSelected = this.scale_Image(image, this.width, this.height);
        this.bufferIconSelected = new ImageIcon(bufferImagSelected);
    }

    /**
     * When the button is selected, switch to selected icon
     */
    public void set_selected(){
        setIcon(this.bufferIconSelected);
    }

    /**
     * When the button is unselected, switch back to normal icon
     */
    public void set_unselected(){
        setIcon(this.bufferIcon);
    }

    /**
     * Scale the buffer image to fit the into the tiles
     * @param image
     * @param width
     * @param height
     * @return
     */
    public BufferedImage scale_Image(Image image, int width, int height) {
        BufferedImage temp_Image = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D cursor = temp_Image.createGraphics();
        cursor.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        cursor.drawImage(image, 0, 0, width, height, null);
        cursor.dispose();
        return temp_Image;
    }

    /**
     * Convert the tile coordinates on the board in model into position to place on the display in view
     * @param x Coordinate x on board, 0 <= x < 7
     * @param y Coordinate y on board, 0 <= y < 7
     */
    protected void convert_Coordinate(int x, int y) {
        setLocation(50 * x, 365 - 50 * y );
    }
}
