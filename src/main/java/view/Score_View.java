package view;
import model.Board;
import model.Player;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Score_View extends JPanel {
    protected JLabel black_Score;
    protected JLabel white_Score;
    protected JLabel turn;
    public JLabel message;
    private Player black_Player;
    private Player white_Player;
    public Board board;

    /**
     * Constructor for score view, this is the area that displays player's information (Name, game color)
     * whose turn and prompt message when players make interaction with game
     * @param board
     * @param black_Player
     * @param white_Player
     */
    public Score_View(Board board, Player black_Player, Player white_Player){
        /**
         * Source: https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
         */
        this.black_Player = black_Player; this.white_Player = white_Player;
        this.board = board;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(7, 12, 7, 12);

        black_Score = new JLabel(black_Player.Player_Name + " : " + black_Player.game_score);
        EmptyBorder border = new EmptyBorder(5, 5, 5, 5);
        LineBorder black_line = new LineBorder(Color.orange, 3, true);
        CompoundBorder black_compound = new CompoundBorder(black_line, border);
        black_Score.setBorder(black_compound);

        white_Score = new JLabel(white_Player.Player_Name + " : " + white_Player.game_score);
        LineBorder white_line = new LineBorder(Color.white, 3, true);
        CompoundBorder white_compound = new CompoundBorder(white_line, border);
        white_Score.setBorder(white_compound);

        String player_turn = (board.white_Turn)?white_Player.Player_Name:black_Player.Player_Name;
        turn = new JLabel("Turn: " + player_turn);
        turn.setForeground(Color.white);


        constraints.gridx = 0;
        constraints.gridy = 0;
        add(black_Score, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(white_Score, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(turn, constraints);
        constraints.gridx = 3;
        constraints.gridy = 0;
        message = new JLabel("");
//        Font font = new Font("Courier", Font.BOLD,12);
//        message.setFont(font);
        add(message, constraints);
        this.setBackground(Color.pink);
    }

    /**
     * Message when player performs invalid action
     * @param new_message
     */
    public void set_message(String new_message){
        this.message.setText(new_message);
        this.message.setForeground (Color.red);
    }

    /**
     * Reset action message to empty if it is valid
     */
    public void reset_message(){
//        Font f = this.message.getFont();
//        this.message.setFont(f.deriveFont(f.getStyle() | ~Font.BOLD));
        this.message.setText("");
        this.message.setForeground (Color.black);
    }

    /**
     * Update the game's turn
     */
    public void change_turn(){
//        System.out.println(this.board.white_Turn+"ddd");
        String player_turn = (this.board.white_Turn)?white_Player.Player_Name:black_Player.Player_Name;
        turn.setText("Turn: " + player_turn);
        Color cur_color = (board.white_Turn)?Color.white:Color.orange;
        turn.setForeground(cur_color);
    }

    /**
     * Manually set player's turn
     */
    public void set_turn(String player_color){
        String player_turn = (player_color.equals("White"))?white_Player.Player_Name:black_Player.Player_Name;
        turn.setText("Turn: " + player_turn);
    }

    /**
     * Update the game score
     */
    public void update_score(){
        this.black_Score.setText(black_Player.Player_Name + " : " + black_Player.game_score);
        this.white_Score.setText(white_Player.Player_Name + " : " + white_Player.game_score);
    }
}
