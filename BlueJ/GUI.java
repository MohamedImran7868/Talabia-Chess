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
    private JPanel board = new JPanel();
    private JLabel turn = new JLabel("Turn: RED");
    private JLabel swap = new JLabel("Move before swap: 4");
    private JPanel p;

    GUI() {
        
        p = new JPanel(new GridLayout(3, 1));
        frame.add(p);
        frame.setSize(300,250);
        controller = new ChessController(this);

        JLabel Welcome = new JLabel("Welcome To Talabia Chess");
        JButton Start = new JButton("Start Game");
        JButton load = new JButton("Load Game");

        ImageIcon icon = new ImageIcon("Horse.jpg");
        JPanel startpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel loadpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel welcomepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.setBackground(new Color (0, 255, 255));
        welcomepanel.setBackground(new Color (0, 255, 255));
        startpanel.setBackground(new Color (0, 255, 255));
        loadpanel.setBackground(new Color (0, 255, 255));

        Start.setFocusable(false);
        load.setFocusable(false);

        welcomepanel.add(Welcome, BorderLayout.CENTER);
        startpanel.add(Start, BorderLayout.CENTER);
        loadpanel.add(load, BorderLayout.CENTER);

        Welcome.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Start.setFont(new Font("Arial", Font.BOLD, 20));
        load.setFont(new Font("Arial", Font.BOLD, 20));

        p.add(welcomepanel, BorderLayout.NORTH);
        p.add(startpanel, BorderLayout.CENTER);
        p.add(loadpanel, BorderLayout.SOUTH);

        Start.addActionListener(new Start(true));
        load.addActionListener(new Load());
        
         
        Start.setBackground(new Color(0,255,0));
        load.setBackground(new Color(0, 255, 0));
        Start.setForeground(Color.WHITE);
        load.setForeground(Color.WHITE);

        // Settings frame
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setResizable(true);
        frame.setVisible(true);
    }

    // Start button action listener
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

            JPanel tab = new JPanel(new GridLayout(1, 3));
            JButton save = new JButton("Save game");
            JPanel savePanel = new JPanel();
            JPanel turnPanel = new JPanel();
            JPanel swapPanel = new JPanel();

            save.setFocusable(false);
            tab.setLayout(new BorderLayout());
            tab.setPreferredSize(new Dimension(600, 20));
            save.setPreferredSize(new Dimension(100, 15));
            board.setLayout(new GridLayout(6, 7));

            if (initializePieces) {
                controller.initializePieces();
            }
            controller.initializeButtons(board);
            //controller.changeturntotext();
            setTurn();
            setswap();

            savePanel.add(save);
            turnPanel.add(turn);
            swapPanel.add(swap);
            tab.add(savePanel, BorderLayout.WEST);
            tab.add(turnPanel, BorderLayout.CENTER);
            tab.add(swapPanel, BorderLayout.EAST);

            frame.add(tab, BorderLayout.NORTH);
            frame.add(board, BorderLayout.CENTER);
            frame.setVisible(true);

            save.addActionListener(new Save());
        }
    }

    // Save button action listener
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

    // Load button action listener
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

    // Sets the icon for button
    public void setIconForButton(JButton button, File imageFile, String name) {
        try {
            
            BufferedImage image = ImageIO.read(imageFile);

            // Scale the image to fit the button
            Image newImage = image.getScaledInstance(85, 85, java.awt.Image.SCALE_SMOOTH);

            // Create a new ImageIcon with the scaled image
            ImageIcon icon = new ImageIcon(newImage);

            // Set the new icon and action command for the button
            button.setIcon(icon);
            button.setActionCommand(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Display Game over
    public void gameover() {
        JOptionPane.showMessageDialog(frame, "GAME ENDS. " + controller.getplayer() + " WINS.", "GAME OVER!!!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // Display who's turn
    public void setTurn() {
        turn.setText("Turn: " + controller.getplayer());
    }
    
    // Display how many moves left for swap
    public void setswap() {
        swap.setText("Move before swap: " + controller.getturn());
    }
}
