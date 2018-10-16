package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;

class BishopTest {
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
        Piece black_Bishop = new Bishop(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_Bishop, 3, 0);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate = black_Bishop.NextCoordinate(true);

        assertEquals(black_Bishop.name, "Bishop");
        assertTrue(board.Set_Check(Next_Coordinate, 0, 3));
        assertTrue(board.Set_Check(Next_Coordinate, 2, 1));
        assertTrue(board.Set_Check(Next_Coordinate, 5, 2));
        assertTrue(board.Set_Check(Next_Coordinate, 7, 4));
        assertEquals(Next_Coordinate.size(), 7);
    }
}