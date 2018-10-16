package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;

class QueenTest {
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
        Piece black_Queen = new Queen(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_Queen, 3, 1);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate = black_Queen.NextCoordinate(true);

        assertEquals(black_Queen.name, "Queen");
        assertTrue(board.Set_Check(Next_Coordinate, 2, 0));
        assertTrue(board.Set_Check(Next_Coordinate, 0, 1));
        assertTrue(board.Set_Check(Next_Coordinate, 1, 3));
        assertTrue(board.Set_Check(Next_Coordinate, 4, 0));
        assertEquals(Next_Coordinate.size(), 23);
    }
}