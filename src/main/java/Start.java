import controller.Game_Controller;
import javax.swing.*;

/**
 * A function that user can directly run to start the game,
 * it calls the controller constructor and create a new instance of controller
 */
public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game_Controller());
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Board board = new Board();
//                Player black_player = new Player("Herbert", true);
//                Player white_player = new Player("Jim", false);
//                board.Board_init(black_player, white_player, false);
//                new Info_View().setVisible(true);
//                new Game_View(board, black_player, white_player).setVisible(true);
//            }
//        });
    }
}
