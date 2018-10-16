package model;

import static org.junit.jupiter.api.Assertions.*;
import model.piece.Pawn;
import model.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class GridTest {
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
    @DisplayName("check All methods")
    void check_All() {
        Piece pawn = new Pawn(black_player);
        board.set_Piece(pawn, 3, 5);
        assertEquals(board.get_Piece(3, 5), pawn);
        board.board[3][5].removePiece();
        assertNull(board.board[3][5].getPiece());
        assertTrue(board.board[3][5].isEmpty());
    }

    @Test
    @DisplayName("check toString() methods")
    void check_toString() {
        Piece pawn = new Pawn(black_player);
        board.set_Piece(pawn, 3, 5);
        assertEquals(board.board[3][5].toString(), "Black Pawn @ (3,5)");
        assertEquals(board.board[4][6].toString(), "");
    }

}