package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {
    @BeforeEach
    public void initEach(){
        System.out.println("CoordinateTest");
    }

    @Test
    @DisplayName("check toString() methods")
    void check_toString() {
        Coordinate new_coord1 = new Coordinate(1, 2);
        assertEquals(new_coord1.toString(), "(1,2)");
    }

    @Test
    @DisplayName("check equals() methods")
    void check_equals() {
        Coordinate new_coord1 = new Coordinate(1, 2);
        Coordinate new_coord2 = new Coordinate(1, 2);
        Coordinate new_coord3 = new Coordinate(2, 3);
        Player new_player1 =  new Player();
        assertTrue(new_coord1.equals(new_coord2));
        assertFalse(new_coord1.equals(new_coord3));
        assertFalse(new_coord1.equals(new_player1));
    }
}