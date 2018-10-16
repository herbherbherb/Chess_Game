package view;
import model.Board;
import model.Player;
import javax.swing.*;
import java.awt.*;

/**
 * This is the GUI view for main game panel
 */
public class Game_View extends JFrame {
    public Board_View board_view;
    public Menu_View menu_view;
    public Score_View score_view;
    public JPanel main_Panel;

    /**
     * Constructor for Game_View class
     * @param board Game Board
     * @param black_Player
     * @param white_Player
     */
    public Game_View(Board board, Player black_Player, Player white_Player){
        /**
         * Source: https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
         */
        super("Chess Game");
        setSize(53 * board.Board_Row, 70 * board.Board_Col);
        setLayout(new BorderLayout());
        this.menu_view = new Menu_View();
        add(menu_view, BorderLayout.NORTH);

        main_Panel = new JPanel(new BorderLayout());
        this.score_view = new Score_View(board, black_Player, white_Player);
        main_Panel.add(score_view, BorderLayout.NORTH);

        this.board_view = new Board_View(board);
        board.board_view = board_view;
        main_Panel.add(this.board_view, BorderLayout.CENTER);

        add(main_Panel);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Re-initialize Score_View and Board_View
     * @param board
     * @param black_player
     * @param white_player
     */
    public void restart(Board board, Player black_player, Player white_player){
        this.main_Panel.remove(this.score_view);
        this.score_view = new Score_View(board, black_player, white_player);
        this.main_Panel.add(score_view, BorderLayout.NORTH);
        this.main_Panel.remove(this.board_view);
        this.board_view = new Board_View(board);
        board.board_view = board_view;
        this.main_Panel.add(this.board_view, BorderLayout.CENTER);
        this.main_Panel.revalidate();
        this.main_Panel.repaint();
    }
}