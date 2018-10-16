package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;

class RookTest {
    Board board;
    Player black_player;
    Player white_player;
    Piece black_King;
    Piece white_King;

    @BeforeEach
    public void initEach(){
        board = new Board();
        black_player = new Player("Herbert", true);
        white_player = new Player("Jim", false);
    }

    @Test
    @DisplayName("check NextCoordinate() methods")
    void check_NextCoordinate() {
        Piece black_Rook = new Rook(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_Rook, 3, 0);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate = black_Rook.NextCoordinate(true);

        assertEquals(black_Rook.name, "Rook");
        assertTrue(board.Set_Check(Next_Coordinate, 0, 0));
        assertTrue(board.Set_Check(Next_Coordinate, 4, 0));
        assertFalse(board.Set_Check(Next_Coordinate, 7, 0));
        assertTrue(board.Set_Check(Next_Coordinate, 3, 7));
        assertEquals(Next_Coordinate.size(),  11);
    }
}