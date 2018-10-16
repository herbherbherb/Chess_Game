package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class Info_View extends JFrame {
    /**
     * Source: https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
     */
    public JLabel white_Player_Field = new JLabel("White Player's Name: ");
    public JLabel black_Player_Field = new JLabel("Black Player's Name: ");
    public JLabel board_Row_Field = new JLabel("Number of Board Rows: ");
    public JLabel board_Col_Field = new JLabel("Number of Board Columns: ");
    public JLabel special_piece_Field = new JLabel("Include Special Pieces: ");


    public JTextField white_Player_Name = new JTextField(20);
    public JTextField black_Player_Name = new JTextField(20);

    public JSpinner board_Row = new JSpinner();
    public JSpinner board_Col = new JSpinner();

    public JSpinner special_piece;

    public JButton buttonLogin = new JButton("Start Game");

    public Info_View() {
        super("Chess Game");

        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        //===============================================
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(white_Player_Field, constraints);

        constraints.gridx = 1;
        white_Player_Name.setText("");
        newPanel.add(white_Player_Name, constraints);
        //===============================================

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(black_Player_Field, constraints);

        constraints.gridx = 1;
        black_Player_Name.setText("");
        newPanel.add(black_Player_Name, constraints);
        //===============================================

        board_Row.setValue(Integer.valueOf(8));
        board_Col.setValue(Integer.valueOf(8));
        //===============================================
        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(board_Row_Field, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(board_Row, constraints);

        Dimension d_Row = board_Row.getPreferredSize();
        d_Row.width = 100;
        board_Row.setPreferredSize(d_Row);
        //===============================================
        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(board_Col_Field, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        newPanel.add(board_Col, constraints);

        Dimension d_Col = board_Col.getPreferredSize();
        d_Col.width = 100;
        board_Col.setPreferredSize(d_Col);
        //===============================================
        SpinnerListModel listModel = new SpinnerListModel(new String[] { "Yes","No"});
        special_piece = new JSpinner(listModel);
        special_piece.setValue("No");
        constraints.gridx = 0;
        constraints.gridy = 4;
        newPanel.add(special_piece_Field, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        newPanel.add(special_piece, constraints);

        Dimension d_special = special_piece.getPreferredSize();
        d_special.width = 100;
        special_piece.setPreferredSize(d_special);
        //===============================================

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);

        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Pre-Game Info"));
        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * When user press start button to start the game
     * @param e ActionListener
     */
    public void press_Start(ActionListener e) {
        this.buttonLogin.addActionListener(e);
    }
}
