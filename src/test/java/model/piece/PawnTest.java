package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;

class PawnTest {
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
        Piece black_Pawn = new Pawn(black_player);
        Piece white_Pawn = new Pawn(white_player);
        Piece white_Pawn2 = new Pawn(white_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_Pawn, 4, 6);
        board.Add_Piece(white_Pawn, 4, 1);
        board.Add_Piece(white_Pawn2, 5, 3);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);

        Set<Coordinate> black_Next_Coordinate = black_Pawn.NextCoordinate(true);
        assertEquals(black_Pawn.name, "Pawn");
        assertTrue(board.Set_Check(black_Next_Coordinate, 4, 5));
        assertTrue(board.Set_Check(black_Next_Coordinate, 4, 4));
        assertEquals(black_Next_Coordinate.size(), 2);

        board.move(black_Pawn, 4, 4);
        board.move(white_Pawn, 4, 3);
        black_Next_Coordinate = black_Pawn.NextCoordinate(true);
        assertTrue(board.Set_Check(black_Next_Coordinate, 5, 3));
        assertEquals(black_Next_Coordinate.size(), 1);

        board.remove_Piece(4, 3);
        black_Next_Coordinate = black_Pawn.NextCoordinate(true);
        assertTrue(board.Set_Check(black_Next_Coordinate, 5, 3));
        assertTrue(board.Set_Check(black_Next_Coordinate, 4, 3));
        assertFalse(board.Set_Check(black_Next_Coordinate, 4, 2));
        assertEquals(black_Next_Coordinate.size(), 2);    }
}