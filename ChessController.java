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
        piecesMap.put("blue_point0", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 0, 0));
        piecesMap.put("blue_point1", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 1, 0));
        piecesMap.put("blue_point2", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 2, 0));
        piecesMap.put("blue_point3", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 3, 0));
        piecesMap.put("blue_point4", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 4, 0));
        piecesMap.put("blue_point5", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 5, 0));
        piecesMap.put("blue_point6", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 6, 0));
        piecesMap.put("blue_plus0", new PointPiece("blue_plus", new File(piecesFolder, "blue_PlusPiece.png"), 0, 0, 0));
        piecesMap.put("blue_plus1", new PointPiece("blue_plus", new File(piecesFolder, "blue_PlusPiece.png"), 0, 6, 0));
        piecesMap.put("blue_hourglass0", new PointPiece("blue_hourglass", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 1, 0));
        piecesMap.put("blue_hourglass1", new PointPiece("blue_hourglass", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 5, 0));
        piecesMap.put("blue_time0", new PointPiece("blue_time", new File(piecesFolder, "blue_TimePiece.png"), 0, 2, 0));
        piecesMap.put("blue_time1", new PointPiece("blue_time", new File(piecesFolder, "blue_TimePiece.png"), 0, 4, 0));
        piecesMap.put("blue_sun", new PointPiece("blue_sun", new File(piecesFolder, "blue_SunPiece.png"), 0, 3, 0));
        
        // yellow pieces
        piecesMap.put("yellow_point0", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 0, 1));
        piecesMap.put("yellow_point1", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 1, 1));
        piecesMap.put("yellow_point2", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 2, 1));
        piecesMap.put("yellow_point3", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 3, 1));
        piecesMap.put("yellow_point4", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 4, 1));
        piecesMap.put("yellow_point5", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 5, 1));
        piecesMap.put("yellow_point6", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 6, 1));
        piecesMap.put("yellow_plus0", new PointPiece("yellow_plus", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 0, 1));
        piecesMap.put("yellow_plus1", new PointPiece("yellow_plus", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 6, 1));
        piecesMap.put("yellow_hourglass0", new PointPiece("yellow_hourglass", new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 1, 1));
        piecesMap.put("yellow_hourglass1", new PointPiece("yellow_hourglass", new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 5, 1));
        piecesMap.put("yellow_time0", new PointPiece("yellow_time", new File(piecesFolder, "yellow_TimePiece.png"), 5, 2, 1));
        piecesMap.put("yellow_time1", new PointPiece("yellow_time", new File(piecesFolder, "yellow_TimePiece.png"), 5, 4, 1));
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

    private PointPiece getPieceAtPosition(int xCoordinate, int yCoordinate) {
        for (PointPiece piece : piecesMap.values()) {
            if (piece.xCoordinate == xCoordinate && piece.yCoordinate == yCoordinate) {
                return piece;
            }
        }
        return null;
    }

    private PointPiece getPieceAtPosition(JButton button) {
        int x = -1;
        int y = -1;

        // Find the coordinates of the button
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (buttonsall[i][j] == button) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        return getPieceAtPosition(x, y);
    }

    private boolean ismovevalid (PointPiece selectedPiece, int x, int y) {

        String name = selectedPiece.name ;
        int ydistance = y - selectedPiece.yCoordinate;
        int xdistance = x - selectedPiece.xCoordinate;

        System.out.println("Name: " + name);
        System.out.println("X: " + selectedPiece.xCoordinate);
        System.out.println("Y: " + selectedPiece.yCoordinate);
        System.out.println("DX: " + xdistance);
        System.out.println("DY: " + ydistance);
        
        switch (name) {
            case "blue_point":
                // Blue Point piece

                if ((xdistance == 1 || xdistance == 2) && ydistance == 0)
                {
                    return true;
                }
                else {
                    return false;
                }
            case "yellow_point":
                // Yellow Point piece

                if ((xdistance == -1 || xdistance == -2) && ydistance == 0)
                {
                    return true;
                }
                else {
                    return false;
                }
                
            case "blue_hourglass":
            case "yellow_hourglass":
                // Hourglass piece
                if ((Math.abs(xdistance) == 1 && Math.abs(ydistance) == 2) || (Math.abs(xdistance) == 2 && Math.abs(ydistance) == 1))
                {
                    return true;
                }
                else {
                    return false;
                }
                
            case "blue_time":
            case "yellow_time":
                // Time piece
                if (Math.abs(xdistance) == Math.abs(ydistance))
                {
                    return true;
                }
                else {
                    return false;
                }
                
            case "blue_plus":
            case "yellow_plus":
                // Plus piece
                if (xdistance == 0 || ydistance == 0)
                {
                    return true;
                }
                else {
                    return false;
                }
                
            case "blue_sun":
            case "yellow_sun":
                // Sun piece
                if ((Math.abs(xdistance) == 1 && ydistance ==0) || (Math.abs(ydistance) == 1 && xdistance ==0) || (Math.abs(ydistance) == 1 && Math.abs(xdistance) == 1))
                {
                    return true;
                }
                else {
                    return false;
                }
            default:
                return false;
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
            JButton clickedButton = buttonsall[x][y];

            // Check if the clicked button has a piece
            PointPiece clickedPiece = getPieceAtPosition(x, y);

            if (clickedPiece != null) {
                // If a piece is clicked, store it as the selected piece
                selectedButton = clickedButton;
            } else if (selectedButton != null) {
                // If a button without a piece is clicked and a piece is selected,
                // move the piece to the clicked button
                PointPiece selectedPiece = getPieceAtPosition(selectedButton);

                if (ismovevalid(selectedPiece, x, y)) {
                    // Update the piecesMap with the new coordinates
                    selectedPiece.xCoordinate = x;
                    selectedPiece.yCoordinate = y;

                    // Update the view with the new icon for the clicked button
                    view.setIconForButton(clickedButton, selectedPiece.getImagePath(), selectedPiece.getName());

                    // Clear the icon for the selected button
                    selectedButton.setIcon(null);

                    // Clear the selected button
                    selectedButton = null;
                } else {
                    return;
                }

                
            }
        }
    }
}

