package model;

import controller.Game_Controller;
import model.piece.*;
import view.Board_View;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Board {
    public final int Board_Row;
    public final int Board_Col;
    protected Grid[][] board;
    public Set<Piece> active_Pieces = new HashSet<>();
    public boolean white_Turn;
    public Board_View board_view;
    public Stack<Movement> move_stack = new Stack<>();
    public Game_Controller game_controller;
    /**
     * Constructor for Board class using default 8 * 8 dimension
     */
    public Board(){
        this.Board_Col = 8;
        this.Board_Row = 8;
        this.white_Turn = true;
        board = new Grid[Board_Row][Board_Col];
        for(int row = 0; row < Board_Row; row++)
            for(int col = 0; col < Board_Col; col++)
                board[row][col] = new Grid();
    }

    /**
     * Constructor for Board class
     * @param Input_Row Specified number of rows
     * @param Input_Col Specified number pf columns
     */
    public Board(int Input_Row, int Input_Col){
        this.Board_Row = Input_Row;
        this.Board_Col = Input_Col;
        this.white_Turn = true;
        board = new Grid[Board_Row][Board_Col];
        for(int row = 0; row < Board_Row; row++)
            for(int col = 0; col < Board_Col; col++)
                board[row][col] = new Grid();
    }

    /**
     * Initialize empty board with conventional layout
     * @param black_Player
     * @param white_Player
     */
    public void Board_init(Player black_Player, Player white_Player, boolean has_special){
        this.Add_Piece(new Rook(black_Player), 0, 7);
        this.Add_Piece(new Knight(black_Player), 1, 7);
        this.Add_Piece(new Bishop(black_Player), 2, 7);
        this.Add_Piece(new Queen(black_Player), 3, 7);
        this.Add_Piece(new King(black_Player), 4, 7);
        this.Add_Piece(new Bishop(black_Player), 5, 7);
        this.Add_Piece(new Knight(black_Player), 6, 7);
        this.Add_Piece(new Rook(black_Player), 7, 7);
        for(int row = 0; row <= 7; row++){
            this.Add_Piece(new Pawn(black_Player), row, 6);
        }
        if(has_special){
            this.Add_Piece(new Elephant(black_Player), 2, 5);
            this.Add_Piece(new Cannon(black_Player), 5, 5);
        }

        this.Add_Piece(new Rook(white_Player), 0, 0);
        this.Add_Piece(new Knight(white_Player), 1, 0);
        this.Add_Piece(new Bishop(white_Player), 2, 0);
        this.Add_Piece(new Queen(white_Player), 3, 0);
        this.Add_Piece(new King(white_Player), 4, 0);
        this.Add_Piece(new Bishop(white_Player), 5, 0);
        this.Add_Piece(new Knight(white_Player), 6, 0);
        this.Add_Piece(new Rook(white_Player), 7, 0);
        for(int row = 0; row <= 7; row++){
            this.Add_Piece(new Pawn(white_Player), row, 1);
        }
        if(has_special){
            this.Add_Piece(new Elephant(white_Player), 2, 2);
            this.Add_Piece(new Cannon(white_Player), 5, 2);
        }
    }

    /**
     * Check if the inputs are valid coordinates
     * @param row input x coordinate
     * @param col input y coordinate
     * @return
     */
    public boolean Valid_Pos(int row, int col){
        if(row >= 0 && col >= 0 && row < Board_Row && col < Board_Col) return true;
        return false;
    }

    /**
     * Check if the coordinate is occupied by any pieces by any player
     * @param row target coordinate x
     * @param col target coordinate y
     */
    public boolean Occpuied(int row, int col){
        if(this.Valid_Pos(row, col) && !this.board[row][col].isEmpty()) return true;
        return false;
    }

    /**
     * @return the occupied piece @ coordinate (row, col)
     */
    public Piece Occpuied_Piece(int row, int col){
        if(this.Occpuied(row, col)) return this.board[row][col].piece;
        else return null;
    }

    /**
     * Designed specifically for Pawn movement,
     * helper function to check if pawn can capture diagonal opponent
     * @param piece Pawn piece
     * @param row target coordinate x
     * @param col target coordinate y
     */
    public boolean Occpuied_by_Oppo(Piece piece,  int row, int col){
        return (this.Occpuied(row,  col) && !piece.compareTo(this.Occpuied_Piece(row, col)));
    }


    /**
     * Designed for any pieces that move by direction
     * @param piece Bishop, Queen, Rook
     * @param row target coordinate x
     * @param col target coordinate y
     * @return
     */
    public boolean Occpuied_by_Friendly(Piece piece,  int row, int col){
        return (this.Occpuied(row,  col) && piece.compareTo(this.Occpuied_Piece(row, col)));
    }

    /**
     * Add a new piece to the board
     * @param target
     * @param row x coordinate to be placed in
     * @param col y coordinate to be placed in
     */
    public void Add_Piece(Piece target, int row, int col){
        if(!this.Valid_Pos(row, col) || target == null) return;
        this.active_Pieces.add(target);
        this.set_Piece(target, row, col);
    }

    /**
     * @return the piece @ coordinate (x, y)
     */
    public Piece get_Piece(int x, int y){
        return (this.Valid_Pos(x, y))?this.board[x][y].piece : null;
    }

    /**
     * Set piece on a specific coordinate
     * @param piece
     * @param x target coordinate x to set
     * @param y target coordinate y to set
     */
    public void set_Piece(Piece piece, int x, int y){
        if(!this.Valid_Pos(x, y) ) return;
        this.board[x][y].setPiece(piece);
        if(piece != null) {
            // Set piece coordinate
            piece.setPosition(x, y);
            piece.board = this;
        }
    }

    /**
     * Remove a given piece from the board
     * @param piece target piece to be removed
     */
    public void remove_Piece(Piece piece){
        if(piece == null) return;
        remove_Piece(piece.pos_x, piece.pos_y);
    }

    /**
     * Remove a given piece from the board
     * @param x target piece coordinate x
     * @param y target piece coordinate y
     */
    public void remove_Piece(int x,  int y){
        if(!this.Valid_Pos(x, y)) return;
        Piece piece = this.get_Piece(x, y);
        if(piece != null) {
            this.active_Pieces.remove(piece);
        }
        this.board[x][y].removePiece();
    }

    /**
     * Move piece to a new location, if the piece is pawn, set isFirstMove to False
     * If the target coordinate has opponent's piece, remove from global pieces set
     * @param piece
     * @param x target coordinate x
     * @param y target coordinate y
     */
    public void move(Piece piece, int x,  int y){
        Movement current = new Movement(this, new Coordinate(piece.pos_x, piece.pos_y), new Coordinate(x, y), this.get_Piece(x, y));
        this.move_stack.push(current);
        Piece target_piece = this.get_Piece(x, y);
        if(target_piece != null && target_piece.piece_view != null) {
            target_piece.piece_view.remove_View();
        }
        this.move_helper(piece, x, y);
        if(piece.name.equals("Pawn")){
            ((Pawn)piece).isFirstMove = false;
        }
        if(piece != null && piece.piece_view != null) {
            piece.piece_view.updateLocation();
        }
        this.white_Turn = !this.white_Turn;
    }

    /**
     * Helper function for move
     */
    public void move_helper(Piece piece, int x,  int y){
        if(piece == null) return;
        if(!this.canMove(piece, x, y))
            return;
        this.remove_Piece(piece);
        this.remove_Piece(x, y);
        this.set_Piece(piece, x, y);
        this.active_Pieces.add(piece);
    }

    /**
     * Helper function when player decide to restore his previous move.
     * Pop the top movement from the stack and execute.
     */
    public void undo(){
        if (move_stack.empty()) return;
        Movement moveAtTop = move_stack.pop();
        Coordinate dest = moveAtTop.dest;
        Coordinate starting = moveAtTop.starting;

        Piece current_piece = this.get_Piece(dest.x, dest.y);
        this.move_helper(current_piece, starting.x, starting.y);
        if(current_piece instanceof Pawn){
            ((Pawn) current_piece).isFirstMove = moveAtTop.isFirstMove;
        }
        Piece captured_piece = moveAtTop.captured_piece;
        this.set_Piece(captured_piece, dest.x, dest.y);
        if(captured_piece != null && !this.active_Pieces.contains(captured_piece)){
            this.active_Pieces.add(captured_piece);
        }
        this.white_Turn = !this.white_Turn;
        if(this.game_controller != null){
            if(current_piece != null && current_piece.piece_view != null) {
                current_piece.piece_view.updateLocation();
            }
            if(captured_piece != null && captured_piece.piece_view != null) {
                captured_piece.piece_view.updateLocation();
            }
            this.game_controller.player_turn = this.game_controller.player_turn.equals("White")?"Black":"White";
            this.game_controller.game_view.score_view.change_turn();
            this.game_controller.isChecked();
            this.game_controller.game_view.board_view.clear_Grid();
        }
    }

    /**
     * @param piece Current piece
     * @param x target position x coordinate
     * @param y target position y coordinate
     * @return whether piece can move to coordinate (x, y)
     */
    public boolean canMove(Piece piece, int x, int y){
        // If it is not a valid position, return false;
        if(!this.Valid_Pos(x, y)) return false;
        Piece target = this.get_Piece(x, y);
        //If the target grid is empty, return true
        if(target == null) return true;
        //If the current piece does NOT have the same color as target, return true
        else if(!piece.compareTo(target)) return true;
        else return false;
    }

    /**
     * @param player Given player
     * @return the king piece that belong to this player
     */
    public Piece find_king(Player player){
        if(player == null) return null;
        String color = player.isBlack?"Black":"White";
        for (Piece piece : this.active_Pieces) {
            if(piece.color.equals(color) && piece.name.equals("King"))
                return piece;
        }
        return null;
    }

    /**
     * Helper function to check if a given set contains a coordinate object by values
     * @param Next_Coordinate
     * @param target coordinate's value to be checked with
     * @return true if given set contains target coordinate by value
     */
    public boolean Set_Check(Set<Coordinate>Next_Coordinate, Coordinate target){
        if(target == null) return false;
        int first = target.x;
        int second = target.y;
        for(Coordinate cur: Next_Coordinate){
            if(cur.x == first && cur.y == second) return true;
        }
        return false;
    }

    /**
     * @param Next_Coordinate input set to be checked
     * @param first first coordinate of the target set
     * @param second second coordinate of the target set
     * @return true if Next_Coordinate equals target set by values
     */
    public boolean Set_Check(Set<Coordinate>Next_Coordinate, int first, int second){
        for(Coordinate cur: Next_Coordinate){
            if(cur.x == first && cur.y == second) return true;
        }
        return false;
    }

    /**
     * Check if the player is being checkmated
     * @param player
     * @return true is player's king is checked
     */
    public boolean CheckMate(Player player){
        if(player == null) return false;
        Piece player_king = find_king(player);
        if (player_king == null)
            throw new IllegalArgumentException("King cannot be null");
        Coordinate king_coordinate = new Coordinate(player_king.pos_x, player_king.pos_y);
        String opponent_color = player.isBlack?"White":"Black";
        for (Piece piece : this.active_Pieces) {
            if(piece.color.equals(opponent_color)
               && this.Set_Check(piece.NextCoordinate(false), king_coordinate)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the current player is in Stalemate, player is in stalemate if it hs not valid
     * action he/she can take without puting its own king in danger
     * @param player Current player to check
     * @return true if the player does not have any legal move left, hence, the player LOST!
     */
    public boolean in_Stalemate(Player player){
        if(player == null) return false;
        boolean ret = true;
        String color = player.isBlack?"Black":"White";
        Set<Piece> active_Pieces_Copy = this.active_Pieces.stream().collect(Collectors.toSet());
        for (Piece piece : active_Pieces_Copy){
            if(piece.color.equals(color) && piece.NextCoordinate(true).size() != 0){
                ret = false;
                break;
            }
        }
        return ret;
    }

    /**
     * This function checks if any self-move will result checkmate
     * @param piece piece to me moved
     * @param x target coordinate x
     * @param y target coordinate y
     * @return whether it is safe to make the move
     */
    public boolean valid_to_Move(Piece piece, int x, int y, boolean self_Round){
        if(piece == null || !this.canMove(piece, x, y)) return false;
        if(!self_Round) return true;

        //*** Otherwise, check if such movement puts own king is checkmate
        int prev_x = piece.pos_x; int prev_y = piece.pos_y;
        Player player = piece.player;
        Piece target_loc_piece = this.get_Piece(x, y);

        //check if it is safe to move to the new coordinate
        move_helper(piece, x, y);
        boolean is_Checked = CheckMate(player);
        move_helper(piece, prev_x, prev_y);
        if(target_loc_piece != null) {
            this.set_Piece(target_loc_piece, x, y);
            this.active_Pieces.add(target_loc_piece);
        }
        return !is_Checked;
    }
}