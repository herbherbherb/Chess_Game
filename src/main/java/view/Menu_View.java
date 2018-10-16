package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu_View extends JMenuBar {
    private JButton new_Game = new JButton("New Game");
    private JButton redo_Button = new JButton("Restore");
    private JButton forfeit_Button = new JButton("Resign");

    public Menu_View(){
        /**
         * Source: https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
         */
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(7, 7, 7, 7);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new_Game, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(redo_Button, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(forfeit_Button, constraints);
    }

    /**
     * Add callback listener when new_Game button is pressed
     * @param e
     */
    public void new_Game_Action(ActionListener e) {
        this.new_Game.addActionListener(e);
    }

    /**
     * Add callback listener when restore_Button is pressed
     * @param e
     */
    public void restore_Button_Action(ActionListener e) {
        this.redo_Button.addActionListener(e);
    }

    /**
     * Add callback listener when forfeit_Button is pressed
     * @param e
     */
    public void forfeit_Button_Action(ActionListener e) {
        this.forfeit_Button.addActionListener(e);
    }
}
