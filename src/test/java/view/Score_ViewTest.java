package view;

import static org.junit.jupiter.api.Assertions.*;

import model.Board;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class Score_ViewTest {
    Board board;
    Player black_player;
    Player white_player;

    @BeforeEach
    public void initEach(){
        board = new Board();
        black_player = new Player("Herbert", true);
        white_player = new Player("Jim", false);
    }

    @Test
    @DisplayName("check message() methods")
    void check_message() {
        Score_View sv = new Score_View(board, black_player, white_player);
        sv.set_message("Checkmate!!!");
        assertEquals(sv.message.getText(), "Checkmate!!!");
        sv.reset_message();
        assertEquals(sv.message.getText(), "");

    }

    @Test
    @DisplayName("check change_turn() methods")
    void check_change_turn() {
        Score_View sv = new Score_View(board, black_player, white_player);
        assertTrue(sv.board.white_Turn);
        assertEquals(sv.turn.getText(), "Turn: Jim");
        board.white_Turn = false;
        sv.change_turn();
        assertEquals(sv.turn.getText(), "Turn: Herbert");
    }

    @Test
    @DisplayName("check update_score() methods")
    void check_update_score() {
        Score_View sv = new Score_View(board, black_player, white_player);
        assertEquals(sv.black_Score.getText(), "Herbert : 0");
        black_player.game_score = 100;
        sv.update_score();
        assertEquals(sv.black_Score.getText(), "Herbert : 100");
    }
}