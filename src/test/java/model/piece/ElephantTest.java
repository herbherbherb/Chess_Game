package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;

class XiangTest {
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
        Piece black_Elephant = new Elephant(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_Elephant, 3, 4);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate = black_Elephant.NextCoordinate(true);

        assertEquals(black_Elephant.name, "Elephant");
        assertTrue(board.Set_Check(Next_Coordinate, 1, 6));
        assertTrue(board.Set_Check(Next_Coordinate, 1, 2));
        assertTrue(board.Set_Check(Next_Coordinate, 5, 6));
        assertTrue(board.Set_Check(Next_Coordinate, 5, 2));
        assertEquals(Next_Coordinate.size(), 4);

        Piece black_Elephant2 = new Elephant(black_player);
        Piece white_Rook = new Rook(white_player);
        board.Add_Piece(black_Elephant2, 1, 7);
        board.Add_Piece(white_Rook, 0, 7);
        assertEquals(black_Elephant2.NextCoordinate(true).size(), 0);

        Piece black_Elephant3 = new Elephant(black_player);
        board.Add_Piece(black_Elephant3, 8, 8);
        assertEquals(black_Elephant3.NextCoordinate(true).size(), 0);
    }
}