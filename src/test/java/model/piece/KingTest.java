package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;

class KingTest {
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
    @DisplayName("check NextCoordinate() methods")
    void check_NextCoordinate() {
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate_1 = black_King.NextCoordinate(true);

        assertEquals(black_King.name, "King");
        assertTrue(board.Set_Check(Next_Coordinate_1, 3, 7));
        assertTrue(board.Set_Check(Next_Coordinate_1, 5, 7));
        assertTrue(board.Set_Check(Next_Coordinate_1, 4, 6));
        assertTrue(board.Set_Check(Next_Coordinate_1, 3, 6));
        assertEquals(Next_Coordinate_1.size(), 5);

        board.move(black_King, 4, 1);
        Set<Coordinate> Next_Coordinate_2 = black_King.NextCoordinate(true);
        assertFalse(board.Set_Check(Next_Coordinate_2, 3, 0));
        assertFalse(board.Set_Check(Next_Coordinate_2, 5, 1));
        assertTrue(board.Set_Check(Next_Coordinate_2, 4, 0));
        assertTrue(board.Set_Check(Next_Coordinate_2, 5, 2));
        assertEquals(Next_Coordinate_2.size(),  4);
    }
}