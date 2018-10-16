package controller;
import model.Board;
import model.Player;
import model.Coordinate;
import model.piece.*;
import view.Game_View;
import view.Grid_View;
import view.Info_View;
import view.Piece_View;
import java.util.Set;
import java.util.Stack;

public class Game_Controller implements Runnable {
    public Game_View game_view;
    public Board board;
    public Player black_player;
    public Player white_player;
    public String player_turn = "White";
    public Piece selected_piece = null;
    public Set<Coordinate> NextCoord;
    private boolean special_piece;
    public boolean game_is_over = false;
    private boolean has_not_resigned = true;

    /**
     * Helper function that scraps player's information from info panel and initialize the game
     */
    private void init() {
        Info_View info_view = new Info_View();
        info_view.press_Start(e -> {
            this.board = new Board((int)info_view.board_Row.getValue(), (int)info_view.board_Col.getValue());
            this.board.game_controller = this;
            special_piece = info_view.special_piece.getValue().equals("No")?false:true;
            this.black_player = new Player(info_view.black_Player_Name.getText(), true);
            this.white_player = new Player(info_view.white_Player_Name.getText(), false);
            board.Board_init(black_player, white_player, special_piece);
            info_view.setVisible(false);
            info_view.dispose();
            this.game_view = new Game_View(board, black_player, white_player);
            this.start();
        });
    }

    /**
     * Action when user press the new_Game button on Menu_View
     */
    protected void restart(){
        int width = this.board.Board_Row;
        int height = this.board.Board_Col;
        this.board = new Board(width, height);
        this.board.game_controller = this;
        this.board.move_stack.clear();
        this.board.active_Pieces.clear();
        this.board.Board_init(black_player, white_player, this.special_piece);
        this.game_view.restart(board, this.black_player, this.white_player);
        this.game_is_over = false;
        this.has_not_resigned = true;
        this.selected_piece = null;
        this.start();
    }

    /**
     * Action when user press forfeit_Button on Menu_View
     */
    protected void resign(){
        if(!this.has_not_resigned){return;}
        if(this.game_is_over){
            this.game_view.score_view.set_message("Game is over, no can do!");
        }
        else{
            this.has_not_resigned = false;
            Player oppo_player = this.player_turn.equals("White")?this.black_player:this.white_player;
            oppo_player.game_score = oppo_player.game_score + 1;
            this.game_view.score_view.update_score();
            String prompt = oppo_player + " WIN!!!";
            this.game_view.score_view.set_message(prompt);
            this.game_is_over = true;
        }
    }

    /**
     * Key function that implement the main game logic when the game is actually running
     */
    public void start(){
        player_turn = "White";
        /**
         * Adding action listener when player select a piece on the board
         */
        this.game_view.board_view.select_Piece(e ->{
            this.game_view.board_view.clear_Grid();
            if(this.game_is_over) return;
            Piece cur_piece = ((Piece_View) e.getSource()).piece;
            if(cur_piece.color.equals(player_turn)){
                this.selected_piece = cur_piece;
                this.NextCoord = this.selected_piece.NextCoordinate(true);
                if(this.NextCoord.isEmpty()){
                    this.game_view.score_view.set_message("NO Valid Coordinate to Move");
                    this.selected_piece = null;
                }
                else{
                    this.game_view.board_view.next_Grid(this.NextCoord);
                    this.game_view.score_view.reset_message();
                }
            }
            else if(!cur_piece.color.equals(player_turn) && this.selected_piece != null){
                if(this.board.Set_Check(this.NextCoord, cur_piece.pos_x, cur_piece.pos_y)){
                    this.move(cur_piece.pos_x, cur_piece.pos_y);
                }
                else
                    this.game_view.score_view.set_message("Cannot not move to there!");
            }
            else if(!cur_piece.color.equals(player_turn) && this.selected_piece == null){
                Player cur_player = player_turn.equals("White")?white_player:black_player;
                String prompt = cur_player.Player_Name + "'s Ture!";
                this.game_view.score_view.set_message(prompt);
            }
            else{
                this.game_view.score_view.set_message("Invalid Selection!");
            }
        });

        /**
         * Adding action listener when player select a tile on the board
         */
        this.game_view.board_view.select_Grid(e -> {
            if(this.game_is_over) return;
            Grid_View cur_grid = ((Grid_View) e.getSource());
            if(this.selected_piece != null){
                if(this.board.Set_Check(this.NextCoord, cur_grid.coordinate.x, cur_grid.coordinate.y)){
                    this.move(cur_grid.coordinate.x, cur_grid.coordinate.y);
                }
                else
                    this.game_view.score_view.set_message("Invalid Selection!");
            }
            else{
                this.game_view.score_view.set_message("Invalid Selection!");
            }
        });

        this.game_view.menu_view.restore_Button_Action(e -> this.board.undo());
        this.game_view.menu_view.new_Game_Action(e -> this.restart());
        this.game_view.menu_view.forfeit_Button_Action(e -> this.resign());
    }

    /**
     * Key function that implement the logic when player moves a piece on board to another tile, connects the
     * model (actual game logic) and view (JPanel).
     * @param x Targeted row coordinate
     * @param y Targeted col coordinate
     */
    protected void move(int x, int y){
        this.game_view.board_view.clear_Grid();
        this.board.move(this.selected_piece, x, y);

        if(!this.game_Over()){
            this.player_turn = this.player_turn.equals("White")?"Black":"White";
            this.game_view.score_view.change_turn();
            this.selected_piece = null;
            this.isChecked();
        }
        else {
            this.game_is_over = true;
        }
    }

    /**
     * Helper function when to set the view (prompt message) when a player is under checkmate
     */
    public void isChecked(){
        Player cur_player = this.player_turn.equals("White")?this.white_player:this.black_player;
        if(this.board.CheckMate(cur_player)){
            String prompt = cur_player.Player_Name + " is Checked!!!";
            this.game_view.score_view.set_message(prompt);
        }
        else{
            this.game_view.score_view.reset_message();
        }
    }

    /**
     * Key function that determines action to take to view when the game is over. Every move will call this function
     * to check if the preceding move results "Game Over"
     * @return True if game is over, false otherwise
     */
    protected boolean game_Over(){
        Player current_player = this.player_turn.equals("White")?white_player:black_player;
        Player oppo_player = this.player_turn.equals("White")?black_player:white_player;
//        System.out.println("current player: " + current_player.Player_Name);
        if(this.board.CheckMate(oppo_player)){
            String prompt = oppo_player.Player_Name + " is in CHECKMATE!";
            if(this.board.in_Stalemate(oppo_player)){
                prompt = current_player + " WIN!!!";
                this.game_view.score_view.set_message(prompt);
                current_player.game_score = current_player.game_score + 1;
                this.game_view.score_view.update_score();
                this.game_is_over = true;
                return true;
            }
            else{
                this.game_view.score_view.set_message(prompt);
                return false;
            }
        }
        /**
         * Stalemate is a situation in the game of chess where the player whose
         * turn it is to move is not in check but has no legal move.
         */
        else if(this.board.in_Stalemate(oppo_player)){
            String prompt = "In STALEMATE!";
            this.game_view.score_view.set_message(prompt);
            current_player.game_score = current_player.game_score + 1;
            oppo_player.game_score = oppo_player.game_score + 1;
            this.game_view.score_view.update_score();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        init();
    }
}
