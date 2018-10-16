package model.piece;

import static org.junit.jupiter.api.Assertions.*;
import model.Board;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Set;


class CannonTest {
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
        Piece black_Cannon = new Cannon(black_player);
        Piece black_Knight = new Knight(black_player);
        Piece black_Pawn = new Rook(black_player);
        Piece black_Queen = new Queen(black_player);
        Piece white_Queen = new Queen(white_player);
        Piece white_Rook = new Rook(white_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        Piece white_Pawn = new Pawn(white_player);
        board.Add_Piece(black_Cannon, 2, 2);
        board.Add_Piece(black_Knight, 2, 3);
        board.Add_Piece(black_Pawn, 4, 2);
        board.Add_Piece(black_Queen, 1, 2);
        board.Add_Piece(white_Queen, 2, 4);
        board.Add_Piece(white_Pawn, 2, 5);
        board.Add_Piece(white_Rook, 7, 2);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate = black_Cannon.NextCoordinate(true);
        assertEquals(black_Cannon.name, "Cannon");
        assertTrue(board.Set_Check(Next_Coordinate, 2, 4));
        assertTrue(board.Set_Check(Next_Coordinate, 7, 2));
        assertEquals(Next_Coordinate.size(),  2);
        this.board.move(white_Queen, 2, 6);
        Next_Coordinate = black_Cannon.NextCoordinate(true);
        assertTrue(board.Set_Check(Next_Coordinate, 2, 5));
        assertEquals(Next_Coordinate.size(),  2);
    }

    @Test
    @DisplayName("check NextCoordinate_checkmate() methods")
    void check_NextCoordinate_checkmate() {
        Piece black_Cannon = new Cannon(black_player);
        Piece black_Knight = new Knight(black_player);
        Piece black_Rook = new Rook(black_player);
        Piece black_Queen = new Queen(black_player);
        Piece white_Queen = new Queen(white_player);
        Piece white_Rook = new Rook(white_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        Piece white_Pawn = new Pawn(white_player);
        board.Add_Piece(black_Cannon, 5, 7);
        board.Add_Piece(black_Knight, 2, 3);
        board.Add_Piece(black_Rook, 5, 2);
        board.Add_Piece(black_Queen, 1, 2);
        board.Add_Piece(white_Queen, 5, 0);
        board.Add_Piece(white_Pawn, 2, 5);
        board.Add_Piece(white_Rook, 7, 7);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        Set<Coordinate> Next_Coordinate = black_Cannon.NextCoordinate(true);
        assertEquals(black_Cannon.name, "Cannon");
        assertEquals(Next_Coordinate.size(),  0);
        this.board.move(white_Rook, 7, 5);
        Next_Coordinate = black_Cannon.NextCoordinate(true);
        assertEquals(Next_Coordinate.size(),  1);
    }
}