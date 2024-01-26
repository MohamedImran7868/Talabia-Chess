import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChessController {
    private GUI view;
    private PointPiece selectedPiece;
    private int currentPlayer;
    public static int row = 7;
    public static int column = 6;
    public static JButton[][] buttonsall = new JButton[column][row]; // JButton for each tile (42)
    private static Map<String, PointPiece> piecesMap = new HashMap<>(); // Map to store information about each piece
    private JButton selectedButton = null;
    private int turn = 0;

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

    public void changeturntotext() {
        String player = null;
        if (currentPlayer == 0) {
            player = "YELLOW";
        } else {
            player = "BLUE";
        }

        view.turn.setText("Turn: " + player);
    }

    public void initializePieces() {
        File piecesFolder = new File("pieces"); // Create a File object for the "pieces" folder
        
        // Add pieces at initial places
        // blue pieces
        piecesMap.put("blue_point0", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 0, 1, "alive"));
        piecesMap.put("blue_point1", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 1, 1, "alive"));
        piecesMap.put("blue_point2", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 2, 1, "alive"));
        piecesMap.put("blue_point3", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 3, 1, "alive"));
        piecesMap.put("blue_point4", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 4, 1, "alive"));
        piecesMap.put("blue_point5", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 5, 1, "alive"));
        piecesMap.put("blue_point6", new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 6, 1, "alive"));
        piecesMap.put("blue_plus0", new PointPiece("blue_plus", new File(piecesFolder, "blue_PlusPiece.png"), 0, 0, 1, "alive"));
        piecesMap.put("blue_plus1", new PointPiece("blue_plus", new File(piecesFolder, "blue_PlusPiece.png"), 0, 6, 1, "alive"));
        piecesMap.put("blue_hourglass0", new PointPiece("blue_hourglass", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 1, 1, "alive"));
        piecesMap.put("blue_hourglass1", new PointPiece("blue_hourglass", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 5, 1, "alive"));
        piecesMap.put("blue_time0", new PointPiece("blue_time", new File(piecesFolder, "blue_TimePiece.png"), 0, 2, 1, "alive"));
        piecesMap.put("blue_time1", new PointPiece("blue_time", new File(piecesFolder, "blue_TimePiece.png"), 0, 4, 1, "alive"));
        piecesMap.put("blue_sun", new PointPiece("blue_sun", new File(piecesFolder, "blue_SunPiece.png"), 0, 3, 1, "alive"));
        
        // yellow pieces
        piecesMap.put("yellow_point0", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 0, 0, "alive"));
        piecesMap.put("yellow_point1", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 1, 0, "alive"));
        piecesMap.put("yellow_point2", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 2, 0, "alive"));
        piecesMap.put("yellow_point3", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 3, 0, "alive"));
        piecesMap.put("yellow_point4", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 4, 0, "alive"));
        piecesMap.put("yellow_point5", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 5, 0, "alive"));
        piecesMap.put("yellow_point6", new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 6, 0, "alive"));
        piecesMap.put("yellow_plus0", new PointPiece("yellow_plus", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 0, 0, "alive"));
        piecesMap.put("yellow_plus1", new PointPiece("yellow_plus", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 6, 0, "alive"));
        piecesMap.put("yellow_hourglass0", new PointPiece("yellow_hourglass", new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 1, 0, "alive"));
        piecesMap.put("yellow_hourglass1", new PointPiece("yellow_hourglass", new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 5, 0, "alive"));
        piecesMap.put("yellow_time0", new PointPiece("yellow_time", new File(piecesFolder, "yellow_TimePiece.png"), 5, 2, 0, "alive"));
        piecesMap.put("yellow_time1", new PointPiece("yellow_time", new File(piecesFolder, "yellow_TimePiece.png"), 5, 4, 0, "alive"));
        piecesMap.put("yellow_sun", new PointPiece("yellow_sun", new File(piecesFolder, "yellow_SunPiece.png"), 5, 3, 0, "alive")); 

    }

    public void printPiecesMap() {
        for (Map.Entry<String, PointPiece> entry : piecesMap.entrySet()) {
            String key = entry.getKey();
            PointPiece value = entry.getValue();
            System.out.println("Key: " + key + ", Piece: " + value.getName() +
                    ", X: " + value.getX() + ", Y: " + value.getY() + ", Status: " + value.getStatus());
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
            if (piece.xCoordinate == xCoordinate && piece.yCoordinate == yCoordinate && piece.status == "alive") {
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

    private void swapPiece() {
        File piecesFolder = new File("pieces");
        
        for (PointPiece piece : piecesMap.values()) {
            int x = 0;
            int y = 0;

            if (piece.status.equals("alive") && piece.name.equals("blue_plus")) {
                piece.setName("blue_time");
                piece.setImagePath(new File(piecesFolder, "blue_TimePiece.png"));

                x = piece.getX();
                y = piece.getY();

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), piece.getName());
                
            }

            else if (piece.status.equals("alive") && piece.name.equals("blue_time")) {
                piece.setName("blue_plus");
                piece.setImagePath(new File(piecesFolder, "blue_PlusPiece.png"));

                x = piece.getX();
                y = piece.getY();

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), piece.getName());
                
            }

            else if (piece.status.equals("alive") && piece.name.equals("yellow_plus")) {
                piece.setName("yellow_time");
                piece.setImagePath(new File(piecesFolder, "yellow_TimePiece.png"));

                x = piece.getX();
                y = piece.getY();

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), piece.getName());
                
            }

            else if (piece.status.equals("alive") && piece.name.equals("yellow_time")) {
                piece.setName("yellow_plus");
                piece.setImagePath(new File(piecesFolder, "yellow_PlusPiece.png"));

                x = piece.getX();
                y = piece.getY();

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), piece.getName());
                
            }
        }
    }

    // Movement Logic
    private boolean ismovevalid (int x, int y) {

        String name = selectedPiece.name ;
        int ydistance = y - selectedPiece.yCoordinate;
        int xdistance = x - selectedPiece.xCoordinate;
        boolean gotpiece = false;

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
                    if (xdistance == 2) {
                        // Check if there are any pieces
                        for (int i = selectedPiece.xCoordinate + 1; i < x; i++) {
                            PointPiece checkPiece = getPieceAtPosition(i, y);
                            if (checkPiece != null) {
                                gotpiece = true;
                            }
                        }
    
                        if (!gotpiece) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    } 
                }
                else {
                    return false;
                }
            case "yellow_point":
                // Yellow Point piece

                if ((xdistance == -1 || xdistance == -2) && ydistance == 0)
                {
                    if (xdistance == -2) {
                        // Check if there are any pieces
                        for (int i = x; i < selectedPiece.xCoordinate; i++) {
                            PointPiece checkPiece = getPieceAtPosition(i, y);
                            if (checkPiece != null) {
                                gotpiece = true;
                            }
                        }
    
                        if (!gotpiece) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    } 
                }
                else {
                    return false;
                }
                
            case "blue_hourglass":
            case "yellow_hourglass":
                // Hourglass piece can skip over pieces
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
                    if (Math.abs(xdistance) > 1) {
                        // When the targeted piece coordinate is smaller than selected Piece Coordinate
                        if (x < selectedPiece.xCoordinate && y < selectedPiece.yCoordinate) {
                            // Check if there are any pieces
                            for (int i = x; i < selectedPiece.xCoordinate; i++) {
                                for (int j = y; j < selectedPiece.yCoordinate; j++) {
                                    int idistance = Math.abs(i - selectedPiece.xCoordinate);
                                    int jdistance = Math.abs(j - selectedPiece.yCoordinate);

                                    if (idistance == jdistance) {
                                        PointPiece checkPiece = getPieceAtPosition(i, j);
                                        if (checkPiece != null) {
                                            gotpiece = true;
                                        }
                                    }
                                }
                                
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        } // When the targeted piece coordinate is bigger than selected Piece Coordinate
                        else if (x > selectedPiece.xCoordinate && y > selectedPiece.yCoordinate) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.xCoordinate + 1; i < x; i++) {
                                for (int j = selectedPiece.yCoordinate; j < y; j++) {
                                    int idistance = Math.abs(i - selectedPiece.xCoordinate);
                                    int jdistance = Math.abs(j - selectedPiece.yCoordinate);

                                    if (idistance == jdistance) {
                                        PointPiece checkPiece = getPieceAtPosition(i, j);
                                        if (checkPiece != null) {
                                            gotpiece = true;
                                        }
                                    }
                                }
                                
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        } // When the targeted piece xcoordinate is smaller than selected Piece xCoordinate and vice versa for ycoordinate
                        else if (x < selectedPiece.xCoordinate && y > selectedPiece.yCoordinate) {
                            // Check if there are any pieces
                            for (int i = x; i < selectedPiece.xCoordinate; i++) {
                                for (int j = selectedPiece.yCoordinate; j < y; j++) {
                                    int idistance = Math.abs(i - selectedPiece.xCoordinate);
                                    int jdistance = Math.abs(j - selectedPiece.yCoordinate);

                                    if (idistance == jdistance) {
                                        PointPiece checkPiece = getPieceAtPosition(i, j);
                                        if (checkPiece != null) {
                                            gotpiece = true;
                                        }
                                    }
                                }
                                
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        } // When the targeted piece ycoordinate is smaller than selected Piece yCoordinate and vice versa for xcoordinate
                        else if (x > selectedPiece.xCoordinate && y < selectedPiece.yCoordinate) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.xCoordinate; i < x; i++) {
                                for (int j = y; j < selectedPiece.yCoordinate; j++) {
                                    int idistance = Math.abs(i - selectedPiece.xCoordinate);
                                    int jdistance = Math.abs(j - selectedPiece.yCoordinate);

                                    if (idistance == jdistance) {
                                        PointPiece checkPiece = getPieceAtPosition(i, j);
                                        if (checkPiece != null) {
                                            gotpiece = true;
                                        }
                                    }
                                }
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else {
                        return true;
                    }
                }
                else {
                    return false;
                }
                
            case "blue_plus":
            case "yellow_plus":
                // Plus piece
                if (xdistance == 0 || ydistance == 0)
                {
                    if (xdistance == 0) {
                        // When the targeted piece ycoordinate is smaller than selected Piece yCoordinate
                        if (y < selectedPiece.yCoordinate) {
                            // Check if there are any pieces
                            for (int i = y; i < selectedPiece.yCoordinate; i++) {
                                int idistance = Math.abs(i - selectedPiece.yCoordinate);

                                if (idistance == 0) {
                                    PointPiece checkPiece = getPieceAtPosition(x, i);
                                    if (checkPiece != null) {
                                        gotpiece = true;
                                    }
                                }
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        } // When the targeted piece ycoordinate is bigger than selected Piece yCoordinate
                        else if (y > selectedPiece.yCoordinate) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.yCoordinate + 1; i < y; i++) {
                                int idistance = Math.abs(i - selectedPiece.yCoordinate);

                                if (idistance == 0) {
                                    PointPiece checkPiece = getPieceAtPosition(x, i);
                                    if (checkPiece != null) {
                                        gotpiece = true;
                                    }
                                }
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else if (ydistance == 0) {
                        // When the targeted piece xcoordinate is smaller than selected Piece xCoordinate
                        if (x < selectedPiece.xCoordinate) {
                            // Check if there are any pieces
                            for (int i = x; i < selectedPiece.xCoordinate; i++) {
                                int idistance = Math.abs(i - selectedPiece.xCoordinate);

                                if (idistance == 0) {
                                    PointPiece checkPiece = getPieceAtPosition(i, y);
                                    if (checkPiece != null) {
                                        gotpiece = true;
                                    }
                                }
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        } // When the targeted piece xcoordinate is bigger than selected Piece xCoordinate
                        else if (x > selectedPiece.xCoordinate) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.xCoordinate + 1; i < x; i++) {
                                int idistance = Math.abs(i - selectedPiece.xCoordinate);

                                if (idistance == 0) {
                                    PointPiece checkPiece = getPieceAtPosition(i, y);
                                    if (checkPiece != null) {
                                        gotpiece = true;
                                    }
                                }
                            }
        
                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
                else {
                    return false;
                }
                
            case "blue_sun":
            case "yellow_sun":
                // Sun piece
                if ((Math.abs(xdistance) == 1 && ydistance ==0) || (Math.abs(ydistance) == 1 && xdistance == 0) || (Math.abs(ydistance) == 1 && Math.abs(xdistance) == 1))
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

    private void capturepiece(PointPiece clickedPiece, JButton clickedButton) {

        PointPiece targetPiece = getPieceAtPosition(clickedButton);

        if (targetPiece != null ) {
            // Capture the piece
            String name = targetPiece.getName();
            if (name == "blue_sun" || name == "yellow_sun") {
                System.out.println("WINCaptured: " + name);
                targetPiece.status = "dead";
                view.gameover();

            } else {
                System.out.println("Captured: " + name);
                targetPiece.status = "dead";
            }
        }
    }

    public void save(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Current Player: " + currentPlayer);
            for (Map.Entry<String, PointPiece> entry : piecesMap.entrySet()) {
                String key = entry.getKey();
                PointPiece value = entry.getValue();
                writer.write("Key: " + key + ", Piece: " + value.getName() +
                        ", X: " + value.getX() + ", Y: " + value.getY() + ", Status: " + value.getStatus() +"\n");
            }
            System.out.println("Pieces information saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class saveclicklistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

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

            //  
            if (clickedPiece != null && clickedPiece.getPlayer() == currentPlayer) {
                // If a piece is clicked, store it as the selected piece
                selectedButton = clickedButton;
                System.out.println("yes");
            } else if (selectedButton != null) {
                selectedPiece = getPieceAtPosition(selectedButton);

                if (ismovevalid(x, y) ) {

                    capturepiece(clickedPiece, clickedButton);

                    // Update the piecesMap with the new coordinates
                    selectedPiece.xCoordinate = x;
                    selectedPiece.yCoordinate = y;

                    System.out.println("NewX: " + selectedPiece.xCoordinate);
                    System.out.println("NewY: " + selectedPiece.yCoordinate);
                    System.out.println("Player: " + currentPlayer);

                    // Update the view with the new icon for the clicked button
                    view.setIconForButton(clickedButton, selectedPiece.getImagePath(), selectedPiece.getName());

                    // Clear the icon for the selected button
                    selectedButton.setIcon(null);

                    // Clear the selected button
                    selectedButton = null;

                    if (turn == 3){
                        // for swap piece
                        swapPiece();
                        turn = 0;
                    } else {
                        turn++;
                    }

                    save("save.txt");
                    switchPlayer();
                    changeturntotext();
                } else {
                    return;
                }
            }
        }
    }
}

