package model.piece;

import static org.junit.jupiter.api.Assertions.*;

import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

class PieceTest {
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
    @DisplayName("check Constructor methods")
    void check_Constructor() {
        Piece Knight = new Knight(black_player);
        assertEquals(Knight.name, "Knight");
        assertEquals(Knight.color, "Black");
        assertEquals(Knight.player, black_player);
    }

    @Test
    @DisplayName("check setPosition() methods")
    void check_setPosition() {
        Piece Knight = new Knight(black_player);
        Knight.setPosition(4, 5);
        assertEquals(Knight.pos_x, 4);
        assertEquals(Knight.pos_y, 5);
    }

    @Test
    @DisplayName("check isBlack() methods")
    void check_isBlack() {
        Piece Knight = new Knight(black_player);
        assertTrue(Knight.isBlack());
    }

    @Test
    @DisplayName("check compareTo() methods")
    void check_compareTo() {
        Piece black_Knight = new Knight(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        assertTrue(black_King.compareTo(black_Knight));
        assertFalse(black_King.compareTo(white_King));
    }

    @Test
    @DisplayName("check Valid_Pos() methods")
    void check_Valid_Pos() {
        Piece Knight = new Knight(black_player);
        board.set_Piece(Knight, 3, 5);
        assertEquals(Knight.pos_x, 3);
        assertEquals(Knight.pos_y, 5);
        assertTrue(Knight.Valid_Pos());
        assertTrue(Knight.Valid_Pos(3, 5));
        Piece Rook = new Rook(white_player);
        board.set_Piece(Rook, 0, 8);
        assertFalse(Rook.Valid_Pos());
        assertFalse(Rook.Valid_Pos(0, 8));
    }

    @Test
    @DisplayName("check Pos_In_Direction() methods")
    void check_Pos_In_Direction() {
        Piece black_Rook = new Rook(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_Rook, 2, 5);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        int NextDirection[][] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Set<Coordinate> Next_Coordinate = black_Rook.Pos_In_Direction(NextDirection, true);
        assertTrue(board.Set_Check(Next_Coordinate, 2, 4));
        assertTrue(board.Set_Check(Next_Coordinate, 2, 1));
        assertTrue(board.Set_Check(Next_Coordinate, 7, 5));
        assertTrue(board.Set_Check(Next_Coordinate, 3, 5));
        assertEquals(Next_Coordinate.size(),  14);
    }

    @Test
    @DisplayName("check Pos_In_Location() methods")
    void check_Pos_In_Location() {
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_King, 7, 7);
        board.Add_Piece(white_King, 4, 0);
        int NextLocation[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Set<Coordinate> Next_Coordinate = black_King.Pos_In_Location(NextLocation, true);
        assertTrue(board.Set_Check(Next_Coordinate, 6, 6));
        assertTrue(board.Set_Check(Next_Coordinate, 6, 7));
        assertTrue(board.Set_Check(Next_Coordinate, 7, 6));
        assertEquals(Next_Coordinate.size(),  3);
    }

    @Test
    @DisplayName("check toString() methods")
    void check_toString(){
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(black_King, 7, 7);
        board.Add_Piece(white_King, 4, 0);
        assertEquals(black_King.toString(), "Black King @ (7,7)");
        assertEquals(white_King.toString(), "White King @ (4,0)");
    }

}