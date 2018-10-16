package model;

import static jdk.nashorn.api.scripting.ScriptObjectMirror.identical;
import static org.junit.jupiter.api.Assertions.*;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

import model.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

class BoardTest {
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
    @DisplayName("check Board_init() methods")
    void check_Board_init() {
        board.Board_init(black_player, white_player, false);
        assertEquals(board.find_king(white_player).pos_x, board.find_king(black_player).pos_x);
        assertEquals(board.active_Pieces.size(), 32);
        assertTrue(board.get_Piece(3, 7) instanceof Queen);
        assertTrue(board.get_Piece(5, 1) instanceof Pawn);
        for(int row = 0; row <= 7; ++row){
            assertTrue(board.get_Piece(row, 0).getClass().equals(board.get_Piece(row, 7).getClass()));
            assertTrue(board.get_Piece(row, 1).getClass().equals(board.get_Piece(row, 6).getClass()));
        }

        this.initEach();
        board.Board_init(black_player, white_player, true);
        assertEquals(board.find_king(white_player).pos_x, board.find_king(black_player).pos_x);
        assertEquals(board.active_Pieces.size(), 36);
        assertTrue(board.get_Piece(5, 5) instanceof Cannon);
        assertTrue(board.get_Piece(2, 2) instanceof Elephant);
        Set<Coordinate> coord = board.get_Piece(2, 2).NextCoordinate(true);
//         System.out.println(coord.size());

    }

    @Test
    @DisplayName("check Board() methods")
    void check_Board() {
       board = new Board(8, 16);
       assertEquals(board.Board_Row, 8);
       assertEquals(board.Board_Col, 16);
       Piece black_Pawn = new Pawn(black_player);
       board.set_Piece(black_Pawn, 4, 14);
       assertEquals(board.get_Piece(4, 14), black_Pawn);
       Piece black_King = new King(black_player);
       board.Add_Piece(black_King, 4, 15);
       assertEquals(board.find_king(black_player), black_King);
    }


    @Test
    @DisplayName("check Valid_Pos() methods")
    void check_Valid_Pos() {
        assertFalse(board.Valid_Pos(0, 9));
        assertFalse(board.Valid_Pos(9, 9));
        assertFalse(board.Valid_Pos(0, 8));
        assertFalse(board.Valid_Pos(8, 0));
        assertFalse(board.Valid_Pos(-1, 7));
        assertTrue(board.Valid_Pos(1, 7));
        assertTrue(board.Valid_Pos(0, 6));
    }

    @Test
    @DisplayName("check Add_Piece() methods")
    void check_Add_Piece() {
        Piece Knight = new Knight(black_player);
        board.Add_Piece(Knight, 4, 6);
        assertTrue(board.active_Pieces.contains(Knight));
        assertEquals(Knight.board, board);
        assertEquals(Knight.pos_x, 4);
        assertEquals(Knight.pos_y, 6);

    }

    @Test
    @DisplayName("check get_Piece() methods")
    void check_get_Piece() {
        Piece Knight = new Knight(black_player);
        board.Add_Piece(Knight, 4, 6);
        assertEquals(Knight, board.get_Piece(4, 6));
    }

    @Test
    @DisplayName("check Occpuied() methods")
    void check_Occpuied() {
        Piece Knight = new Knight(black_player);
        Piece Rook = new Rook(white_player);
        board.Add_Piece(Knight, 4, 6);
        board.Add_Piece(Rook, -1, 8);
        assertTrue(board.Occpuied(4, 6));
        assertFalse(board.Occpuied(-1, 8));
    }

    @Test
    @DisplayName("check Occpuied_by_Oppo() methods")
    void check_Occpuied_by_Oppo() {
        Piece Knight = new Knight(black_player);
        Piece King = new King(black_player);
        Piece Rook = new Rook(white_player);
        board.Add_Piece(King, 4, 7);
        board.Add_Piece(Knight, 4, 6);
        board.Add_Piece(Rook, 3, 6);
        assertTrue(board.Occpuied_by_Oppo(Knight, 3, 6));
        assertFalse(board.Occpuied_by_Oppo(Knight, 4, 7));
    }

    @Test
    @DisplayName("check Occpuied_by_Friendly() methods")
    void check_Occpuied_by_Friendly() {
        Piece Knight = new Knight(black_player);
        Piece King = new King(black_player);
        Piece Rook = new Rook(white_player);
        board.Add_Piece(King, 4, 7);
        board.Add_Piece(Knight, 4, 6);
        board.Add_Piece(Rook, 3, 6);
        assertFalse(board.Occpuied_by_Friendly(Knight, 3, 6));
        assertFalse(board.Occpuied_by_Friendly(Knight, 7, 6));
        assertTrue(board.Occpuied_by_Friendly(Knight, 4, 7));
    }

    @Test
    @DisplayName("check Occpuied_Piece() methods")
    void check_Occpuied_Piece() {
        Piece Knight = new Knight(black_player);
        Piece Rook = new Rook(white_player);
        board.Add_Piece(Knight, 4, 6);
        board.Add_Piece(Rook, -1, 8);
        assertEquals(board.Occpuied_Piece(4, 6), Knight);
        assertNull(board.Occpuied_Piece(-1, 8));
    }

    @Test
    @DisplayName("check remove_Piece() methods")
    void check_remove_Piece() {
        Piece Knight = new Knight(black_player);
        board.Add_Piece(Knight, 4, 6);
        board.remove_Piece(4, 6);
        assertFalse(board.active_Pieces.contains(Knight));
        assertNull(board.get_Piece(4, 6));
    }

    @Test
    @DisplayName("check move methods")
    void check_move() {
        Piece black_Knight = new Knight(black_player);
        Piece black_Rook = new Rook(black_player);
        Piece white_Rook = new Rook(white_player);
        Piece white_Pawn = new Pawn(white_player);
        board.Add_Piece(black_Knight, 4, 5);
        board.Add_Piece(black_Rook, 4, 6);
        board.Add_Piece(white_Rook, 4, 7);
        board.Add_Piece(white_Pawn, 3, 3);

        //Move black_knight more to adjacent black_Rook:
        assertTrue(board.white_Turn);
        board.move(black_Knight, 4, 6);
        assertEquals(board.get_Piece(4, 5), black_Knight);
        assertEquals(board.get_Piece(4, 6), black_Rook);
        assertFalse(board.white_Turn);
        board.move(black_Knight, 4, 7);
        assertTrue(board.white_Turn);
        assertNull(board.get_Piece(4, 5));
        assertEquals(board.get_Piece(4, 7), black_Knight);
        assertEquals(board.get_Piece(4, 6), black_Rook);
        assertTrue(board.active_Pieces.contains(black_Knight));
        assertTrue(board.active_Pieces.contains(black_Rook));
        assertFalse(board.active_Pieces.contains(white_Rook));

        //Move Pawn to a new location:
        assertTrue(((Pawn)white_Pawn).isFirstMove);
        board.move(white_Pawn, 3, 5);
        assertFalse(((Pawn)white_Pawn).isFirstMove);
    }

    @Test
    @DisplayName("check find_king() methods")
    void check_find_king() {
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        Piece black_Knight = new Knight(black_player);

        board.Add_Piece(black_King, 4, 6);
        board.Add_Piece(white_King, 2, 6);
        board.Add_Piece(black_Knight, 3, 5);
        assertEquals(board.find_king(black_player), black_King);
        assertEquals(board.find_king(white_player), white_King);
        assertNotEquals(board.find_king(black_player), black_Knight);
        board.remove_Piece(4, 6);
        board.remove_Piece(2, 6);
        assertNull(board.find_king(black_player));
        assertNull(board.find_king(white_player));
    }

    @Test
    @DisplayName("check NextCoordinate() methods")
    void check_NextCoordinate() {
        Piece bishop = new Bishop(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        board.Add_Piece(bishop, 4, 4);
        board.Add_Piece(black_King, 4, 0);
        board.Add_Piece(white_King, 4, 7);
        Set<Coordinate> Next_Coordinate = bishop.NextCoordinate(true);

        assertTrue(board.Set_Check(Next_Coordinate, 0, 0));
        assertTrue(board.Set_Check(Next_Coordinate, 1, 7));
        assertTrue(board.Set_Check(Next_Coordinate, 3, 3));
        assertTrue(board.Set_Check(Next_Coordinate, 6, 2));
        assertTrue(board.Set_Check(Next_Coordinate, 7, 7));
        assertEquals(Next_Coordinate.size(), 13);
    }

    @Test
    @DisplayName("check NextCoordinate() methods with checkmate condition")
    void check_NextCoordinate_checkmate() {
        Piece black_Bishop = new Bishop(black_player);
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        Piece white_Rook = new Rook(white_player);
        board.Add_Piece(black_Bishop, 3, 7);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        board.Add_Piece(white_Rook, 2, 7);
        Set<Coordinate> Next_Coordinate = black_Bishop.NextCoordinate(true);
        assertEquals(Next_Coordinate.size(), 0);
    }

    @Test
    @DisplayName("check undo() methods")
    void check_undo() {
        board.Board_init(black_player, white_player, false);
        Piece white_Pawn = board.get_Piece(2, 1);
        Piece black_Pawn = board.get_Piece(3, 6);
        board.move(white_Pawn, 2, 3);
        assertEquals(board.move_stack.size(), 1);
        assertFalse(((Pawn)white_Pawn).isFirstMove);
        board.undo();
        assertTrue(((Pawn)white_Pawn).isFirstMove);
        assertEquals(white_Pawn.pos_x, 2);
        assertEquals(white_Pawn.pos_y, 1);
        assertEquals(board.board[2][1].getPiece(), white_Pawn);
        assertNull(board.board[2][3].getPiece());
        board.move(white_Pawn, 2, 3);
        board.move(black_Pawn, 3, 4);
        assertTrue(board.Set_Check(white_Pawn.NextCoordinate(true), 3, 4));
        board.move(white_Pawn, 3, 4);
        assertFalse(board.active_Pieces.contains(black_Pawn));
        assertEquals(board.board[3][4].getPiece(), white_Pawn);
        board.undo();
        assertFalse(((Pawn)white_Pawn).isFirstMove);
        assertEquals(board.board[3][4].getPiece(), black_Pawn);
        assertTrue(board.active_Pieces.contains(black_Pawn));
        board.undo();
        board.undo();
        assertTrue(((Pawn)white_Pawn).isFirstMove);
        assertEquals(white_Pawn.pos_x, 2);
        assertEquals(white_Pawn.pos_y, 1);
        assertEquals(board.board[2][1].getPiece(), white_Pawn);
        assertNull(board.board[2][3].getPiece());
    }

    @Test
    @DisplayName("check CheckMate() methods")
    void check_CheckMate() {
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        Piece white_Pawn = new Pawn(white_player);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 0);
        board.Add_Piece(white_Pawn, 5, 6);
        assertTrue(board.CheckMate(black_player));

    }

    @Test
    @DisplayName("check in_Stalemate1() methods")
    void check_in_Stalemate1() {
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        Piece white_Queen = new Queen(white_player);
        board.Add_Piece(black_King, 4, 7);
        board.Add_Piece(white_King, 4, 5);
        board.Add_Piece(white_Queen, 7, 7);
        assertTrue(board.in_Stalemate(black_player));
        assertTrue(board.CheckMate(black_player));
        assertFalse(board.in_Stalemate(white_player));
    }

    @Test
    @DisplayName("check in_Stalemate2() methods")
    void check_in_Stalemate2() {
        Piece black_King = new King(black_player);
        Piece white_King = new King(white_player);
        Piece white_Pawn = new Pawn(white_player);
        board.Add_Piece(black_King, 0, 7);
        board.Add_Piece(white_King, 1, 5);
        board.Add_Piece(white_Pawn, 2, 6);
        assertTrue(board.in_Stalemate(black_player));
        assertFalse(board.CheckMate(black_player));
        assertFalse(board.in_Stalemate(white_player));
    }
}