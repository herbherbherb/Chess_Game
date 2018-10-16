package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class PlayerTest {

    @Test
    @DisplayName("check toString() methods")
    void check_toString() {
        Player new_player1 = new Player("Herbert");
        assertEquals(new_player1.toString(), "Herbert");
        Player new_player2= new Player("");
        assertTrue(Arrays.asList(new_player2.white_Fake_Name).contains(new_player2.toString()));
        Player new_player3= new Player(null);
        assertTrue(Arrays.asList(new_player3.white_Fake_Name).contains(new_player2.toString()));
        Player new_player4= new Player();
        assertTrue(Arrays.asList(new_player4.white_Fake_Name).contains(new_player2.toString()));
    }

    @Test
    @DisplayName("check isBlack")
    void check_isBlack() {
        Player new_player1 = new Player("Herbert", false);
        assertFalse(new_player1.isBlack);
    }

    @Test
    @DisplayName("check set_Color")
    void check_set_Color() {
        Player new_player1 = new Player("Herbert");
        new_player1.set_Color(false);
        assertFalse(new_player1.isBlack);
    }

}