import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ChessController {
    private GUI view;
    private int currentPlayer;
    public static int row = 7;
    public static int column = 6;
    public static JButton[][] buttonsall = new JButton[column][row]; // JButton for each tile (42)
    private static Map<String, PointPiece> piecesMap = new HashMap<>(); // Map to store information about each piece
    private JButton selectedButton = null;

    public ChessController(GUI view) {
        this.view = view;
        this.currentPlayer = 0;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % 2; // Toggle between 0 and 1 (player 1 and player 2)
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void initializePieces() {
        File piecesFolder = new File("pieces"); // Create a File object for the "pieces" folder
        
        // Add pieces at initial places
        // blue pieces
        piecesMap.put("blue_point0", new PointPiece("blue_point0", new File(piecesFolder, "blue_PointPiece.png"), 1, 0, 0));
        piecesMap.put("blue_point1", new PointPiece("blue_point1", new File(piecesFolder, "blue_PointPiece.png"), 1, 1, 0));
        piecesMap.put("blue_point2", new PointPiece("blue_point2", new File(piecesFolder, "blue_PointPiece.png"), 1, 2, 0));
        piecesMap.put("blue_point3", new PointPiece("blue_point3", new File(piecesFolder, "blue_PointPiece.png"), 1, 3, 0));
        piecesMap.put("blue_point4", new PointPiece("blue_point4", new File(piecesFolder, "blue_PointPiece.png"), 1, 4, 0));
        piecesMap.put("blue_point5", new PointPiece("blue_point5", new File(piecesFolder, "blue_PointPiece.png"), 1, 5, 0));
        piecesMap.put("blue_point6", new PointPiece("blue_point6", new File(piecesFolder, "blue_PointPiece.png"), 1, 6, 0));
        piecesMap.put("blue_plus0", new PointPiece("blue_plus0", new File(piecesFolder, "blue_PlusPiece.png"), 0, 0, 0));
        piecesMap.put("blue_plus1", new PointPiece("blue_plus1", new File(piecesFolder, "blue_PlusPiece.png"), 0, 6, 0));
        piecesMap.put("blue_hourglass0", new PointPiece("blue_hourglass0", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 1, 0));
        piecesMap.put("blue_hourglass1", new PointPiece("blue_hourglass1", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 5, 0));
        piecesMap.put("blue_time0", new PointPiece("blue_time0", new File(piecesFolder, "blue_TimePiece.png"), 0, 2, 0));
        piecesMap.put("blue_time1", new PointPiece("blue_time1", new File(piecesFolder, "blue_TimePiece.png"), 0, 4, 0));
        piecesMap.put("blue_sun", new PointPiece("blue_sun", new File(piecesFolder, "blue_SunPiece.png"), 0, 3, 0));
        
        // yellow pieces
        piecesMap.put("yellow_point0", new PointPiece("yellow_point0", new File(piecesFolder, "yellow_PointPiece.png"), 4, 0, 1));
        piecesMap.put("yellow_point1", new PointPiece("yellow_point1", new File(piecesFolder, "yellow_PointPiece.png"), 4, 1, 1));
        piecesMap.put("yellow_point2", new PointPiece("yellow_point2", new File(piecesFolder, "yellow_PointPiece.png"), 4, 2, 1));
        piecesMap.put("yellow_point3", new PointPiece("yellow_point3", new File(piecesFolder, "yellow_PointPiece.png"), 4, 3, 1));
        piecesMap.put("yellow_point4", new PointPiece("yellow_point4", new File(piecesFolder, "yellow_PointPiece.png"), 4, 4, 1));
        piecesMap.put("yellow_point5", new PointPiece("yellow_point5", new File(piecesFolder, "yellow_PointPiece.png"), 4, 5, 1));
        piecesMap.put("yellow_point6", new PointPiece("yellow_point6", new File(piecesFolder, "yellow_PointPiece.png"), 4, 6, 1));
        piecesMap.put("yellow_plus0", new PointPiece("yellow_plus0", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 0, 1));
        piecesMap.put("yellow_plus1", new PointPiece("yellow_plus1", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 6, 1));
        piecesMap.put("yellow_hourglass0", new PointPiece("yellow_hourglass0", new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 1, 1));
        piecesMap.put("yellow_hourglass1", new PointPiece("yellow_hourglass1", new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 5, 1));
        piecesMap.put("yellow_time0", new PointPiece("yellow_time0", new File(piecesFolder, "yellow_TimePiece.png"), 5, 2, 1));
        piecesMap.put("yellow_time1", new PointPiece("yellow_time1", new File(piecesFolder, "yellow_TimePiece.png"), 5, 4, 1));
        piecesMap.put("yellow_sun", new PointPiece("yellow_sun", new File(piecesFolder, "yellow_SunPiece.png"), 5, 3, 1)); 

    }

    public void printPiecesMap() {
        for (Map.Entry<String, PointPiece> entry : piecesMap.entrySet()) {
            String key = entry.getKey();
            PointPiece value = entry.getValue();
            System.out.println("Key: " + key + ", Piece: " + value.getName() +
                    ", X: " + value.getX() + ", Y: " + value.getY());
        }
    }

    public void initializeButtons(JPanel panel) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttonsall[i][j] = new JButton();
                buttonsall[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                buttonsall[i][j].setPreferredSize(new Dimension(64, 64)); // Set preferred size here
                buttonsall[i][j].addActionListener(new ButtonClickListener(i, j));
                panel.add(buttonsall[i][j]);
    
                // Check if there is a piece at this position
                PointPiece piece = getPieceAtPosition(i, j);
                if (piece != null) {
                    view.setIconForButton(buttonsall[i][j], piece.getImagePath(), piece.getName());
                }
            }
        }
    }

    private PointPiece getPieceAtPosition(int x, int y) {
        for (PointPiece piece : piecesMap.values()) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        return null;
    }

    private void updatepieces(JButton selectedButton, int newx, int newy) {
        // Assuming that the action command of the button corresponds to the piece name
        String name = selectedButton.getActionCommand(); 
    
        // Retrieve the piece from the piecesMap using the name
        PointPiece piece = piecesMap.get(name);

        System.out.println("Information");
    
        if (piece != null) {
            File file = piece.getImagePath(); 
            int oldx = piece.getX(); 
            int oldy = piece.getY();
            int player = piece.getPlayer();

            // remove the existing
            piecesMap.remove(name);
            
            // put the new one
            piecesMap.put(name, new PointPiece(name, file, newx, newy, player)); 

            // Update the piece's coordinates with the new values
            //piece.xCoordinate = newx;
            //piece.yCoordinate = newy;
    
            // Now you have the attributes for the specific selectedButton
            System.out.println("Name: " + name);
            System.out.println("File: " + file);
            System.out.println("X: " + newx);
            System.out.println("Y: " + newy);
            System.out.println("Player: " + player);
        }
    }

    public class ButtonClickListener implements ActionListener {
        private int x; 
        private int y; 

        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            // Check if it's the correct player's turn
            if (selectedButton == null && getPieceAtPosition(x, y) != null) {
                selectedButton = clickedButton;
            } else if (selectedButton != null && getPieceAtPosition(x, y) == null) {
                System.out.println("\nSelected button\n");
                System.out.println("x: " + x);
                System.out.println("y: " + y);
                
                // Exchange icons between the selected button and the clicked button
                Icon tempIcon = selectedButton.getIcon();
                selectedButton.setIcon(clickedButton.getIcon());
                clickedButton.setIcon(tempIcon);

                updatepieces(selectedButton, x, y);

                // Reset the selectedButton to null after the exchange
                selectedButton = null;
            
            } else {
                selectedButton = null;
            }
        }
    }
}

