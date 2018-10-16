package model;
import java.util.Random;

/**
 * Class for actual game players
 */

public class Player {
    public String Player_Name = null;
    public boolean isBlack = true;
    public int game_score = 0;
    protected final String[] white_Fake_Name = new String[]{"Arie","Mariam","John",
            "Ankit","Josh","Herbert","Danny", "Walker","Tom", "Mahesh","Joshi", "Young","Canvas"};
    protected final String[] black_Fake_Name = new String[]{"Jasmin","Florida","Phuong",
            "Leana","Teodoro","Pinkie","Annis","Carmella", "Adam", "Trump", "Obama", "Harrison", "Hillary"};
    protected final int length = white_Fake_Name.length;
    private Random rand = new Random();

    public Player() {
        this.Player_Name = white_Fake_Name[rand.nextInt(length)];
    }

    public Player(String name){
        this.Player_Name = (name == null || name.replaceAll("\\t\\n\\s+","") == null || name.replaceAll("\\t\\n\\s+","").equals(""))? white_Fake_Name[rand.nextInt(length)]:name;
    }
    
    /**
     * @param name    Player's Name
     * @param isBlack Player's Chosen Color
     */
    public Player(String name, boolean isBlack){
        if(isBlack)
            this.Player_Name = (name == null || name.replaceAll("\\t\\n\\s+","") == null || name.replaceAll("\\t\\n\\s+","").equals(""))? white_Fake_Name[rand.nextInt(length)]:name;
        else
            this.Player_Name = (name == null || name.replaceAll("\\t\\n\\s+","") == null || name.replaceAll("\\t\\n\\s+","").equals(""))? black_Fake_Name[rand.nextInt(length)]:name;
        this.isBlack = isBlack;
    }

    /**
     * Set Player to "White" or Black, for GUI purpose
     * @param isBlack
     */
    public void set_Color(boolean isBlack){
        this.isBlack = isBlack;
    }


    @Override
    public String toString() {
        return this.Player_Name;
    }
}
