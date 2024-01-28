import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class GUI {
    private static JFrame frame = new JFrame("Talabia Chess");
    private ChessController controller;
    public JPanel board = new JPanel();
    public JLabel turn = new JLabel("Turn: YELLOW");
    private JPanel p;

    GUI() {
        
        p = new JPanel(new GridLayout(3, 1));
        frame.add(p);
        frame.setSize(300,400);
        controller = new ChessController(this);
        JButton Start = new JButton("Start Game");
        JButton load = new JButton("Load Game");
        JButton option = new JButton("Options");
        
        JPanel startpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel loadpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel optionpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.setBackground(new Color (34, 139, 34));
        startpanel.setBackground(new Color (34, 139, 34));
        loadpanel.setBackground(new Color (34, 139, 34));
        optionpanel.setBackground(new Color (34, 139, 34));

        Start.setFocusable(false);
        load.setFocusable(false);
        option.setFocusable(false);

        startpanel.add(Start);
        loadpanel.add(load);
        optionpanel.add(option);

        Start.setFont(new Font("Arial", Font.BOLD, 20));
        load.setFont(new Font("Arial", Font.BOLD, 20));
        option.setFont(new Font("Arial", Font.BOLD, 20));

        p.add(startpanel, BorderLayout.NORTH);
        p.add(loadpanel, BorderLayout.CENTER);
        p.add(optionpanel, BorderLayout.SOUTH);

        Start.addActionListener(new Start(true));
        load.addActionListener(new Load());
        
         
        Start.setBackground(new Color(0,0,128));
        load.setBackground(new Color(0, 0, 128));
        option.setBackground(new Color(0, 0, 128));
        Start.setForeground(Color.YELLOW);
        load.setForeground(Color.YELLOW);
        option.setForeground(Color.YELLOW);
        //option.addActionListener(new Option());

        // Settings frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setResizable(true);
        frame.setVisible(true);

        
    }

    public class Start implements ActionListener {
        private boolean initializePieces;

        public Start(boolean initializePieces) {
            this.initializePieces = initializePieces;
        }

        public void actionPerformed(ActionEvent e) {
            // Remove the existing panel before adding a new one
            frame.getContentPane().removeAll();
            frame.repaint();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setResizable(true);

            JPanel tab = new JPanel();
            JButton save = new JButton("Save game");
            JPanel optionPanel = new JPanel(new GridLayout(1, 3));

            save.setFocusable(false);
            tab.setLayout(new BorderLayout());
            tab.setPreferredSize(new Dimension(600, 20));
            save.setPreferredSize(new Dimension(100, 15));
            board.setLayout(new GridLayout(6, 7));

            if (initializePieces) {
                controller.initializePieces();
            }
            controller.initializeButtons(board);
            controller.printPiecesMap();
            controller.changeturntotext();

            optionPanel.add(save);
            optionPanel.add(turn, BorderLayout.EAST);
            tab.add(optionPanel, BorderLayout.WEST);

            frame.add(tab, BorderLayout.NORTH);
            frame.add(board, BorderLayout.CENTER);
            frame.setVisible(true);

            save.addActionListener(new Save());
        }
    }

    public void setIconForButton(JButton button, File imageFile, String name) {
        try {
            // Get the canonical file path to handle case sensitivity
            String canonicalPath = imageFile.getCanonicalPath();
            File canonicalFile = new File(canonicalPath);
            
            BufferedImage image = ImageIO.read(canonicalFile);

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

    public class Save implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            while (true) {
                // Prompt the user to enter the file name
                String fileName = JOptionPane.showInputDialog(frame, "Enter file name (without extension):");

                if (fileName == null) {
                    // If the user cancels or enters an empty name, break the loop
                    break;
                }

                if (fileName != null && !fileName.trim().isEmpty()) {
                    
                    // Create a 'save' folder if it doesn't exist
                    File saveFolder = new File("save");
                    if (!saveFolder.exists()) {
                        saveFolder.mkdir();
                    }

                    // Save the file in the 'save' folder
                    File fileToSave = new File(saveFolder, fileName.trim() + ".txt");

                    // Call the saveGame method in the ChessController
                    controller.save(fileToSave.getAbsolutePath());
                    break;
                } else {
                    // If the file doesn't exist, show an error message
                    JOptionPane.showMessageDialog(frame, "Please enter filename.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public class Load implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            while (true) {
                // Prompt the user to enter the file name
                String fileName = JOptionPane.showInputDialog(frame, "Enter file name (without extension):");
    
                if (fileName == null) {
                    // If the user cancels or enters an empty name, break the loop
                    break;
                }
    
                // Load the file in the 'save' folder
                File fileToLoad = new File("save", fileName.trim() + ".txt");
    
                if (fileToLoad.exists()) {
                    // If the file exists, load the game and break the loop
                    controller.load(fileToLoad.getAbsolutePath());
                    new Start(false).actionPerformed(null);
                    break;
                } else {
                    // If the file doesn't exist, show an error message
                    JOptionPane.showMessageDialog(frame, "Invalid filename. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public ChessController getController() {
        return controller;
    }

    public void gameover() {
        JOptionPane.showMessageDialog(frame, "GAME ENDS. " + controller.winner() + " WINS.", "GAME OVER!!!", JOptionPane.INFORMATION_MESSAGE);
    }
}
