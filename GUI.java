import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class GUI {
    private static JFrame frame = new JFrame("Talabia Chess");
    private ChessController controller;
    public JPanel board = new JPanel();
    public JLabel turn = new JLabel("Turn: YELLOW");

    GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        controller = new ChessController(this);

        JPanel tab = new JPanel();
        JButton save = new JButton("Save game");
        JPanel optionPanel = new JPanel(new GridLayout(1, 3));

        tab.setLayout(new BorderLayout());
        tab.setPreferredSize(new Dimension(600, 20));
        save.setPreferredSize(new Dimension(100, 15));
        board.setLayout(new GridLayout(6, 7));

        controller.initializePieces();
        controller.initializeButtons(board);

        optionPanel.add(save);
        optionPanel.add(turn, BorderLayout.EAST);
        tab.add(optionPanel, BorderLayout.WEST);

        frame.add(tab, BorderLayout.NORTH);
        frame.add(board, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void setIconForButton(JButton button, File imageFile, String name) {
        try {
            BufferedImage image = ImageIO.read(imageFile);

            // Scale the image to fit the button
            Image newImage = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);

            // Create a new ImageIcon with the scaled image
            ImageIcon icon = new ImageIcon(newImage);

            // Set the new icon and action command for the button
            button.setIcon(icon);
            button.setActionCommand(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChessController getController() {
        return controller;
    }

    public void gameover() {
        JOptionPane.showMessageDialog(frame, "GAME ENDS", "GAME OVER!!!", JOptionPane.INFORMATION_MESSAGE);
    }
}
