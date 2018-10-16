package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;

class KnightTest {
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
        Piece black_Knight = new Knight(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_Knight, 3, 2);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate = black_Knight.NextCoordinate(true);

        assertEquals(black_Knight.name, "Knight");
        assertTrue(board.Set_Check(Next_Coordinate, 2, 0));
        assertTrue(board.Set_Check(Next_Coordinate, 1, 3));
        assertTrue(board.Set_Check(Next_Coordinate, 4, 4));
        assertTrue(board.Set_Check(Next_Coordinate, 5, 1));
        assertEquals(Next_Coordinate.size(), 8);

        Piece black_Knight2 = new Knight(black_player);
        board.Add_Piece(black_Knight2, 7, 8);
        assertEquals(black_Knight2.NextCoordinate(true).size(), 0);
    }
}