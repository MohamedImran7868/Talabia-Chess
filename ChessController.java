import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChessController {
    private GUI view;
    private PointPiece selectedPiece;
    private int currentPlayer;
    private static int row = 7;
    private static int column = 6;
    private static JButton[][] buttonsall = new JButton[column][row]; // JButton for each tile (42)
    private static Map<String, PointPiece> piecesMap = new HashMap<>(); // Map to store information about each piece
    private Map<JButton, Color> originalButtonColors = new HashMap<>();
    private JButton selectedButton = null;
    private int turn = 0;
    private String player;


    public ChessController(GUI view) {
        this.view = view;
        this.currentPlayer = 0;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % 2; // Toggle between 0 and 1 (player 1 and player 2)
    }

    public void changeturntotext() {
        if (currentPlayer == 0) {
            player = "YELLOW";
        } else {
            player = "BLUE";
        }
    }

    public String getplayer() {
        return player;
    }

    public void initializePieces() {
        File piecesFolder = new File("Pieces"); // Create a File object for the "pieces" folder

        // Add pieces at initial places
        // blue pieces
        piecesMap.put("blue_point0",
                new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 0, 1, "alive"));
        piecesMap.put("blue_point1",
                new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 1, 1, "alive"));
        piecesMap.put("blue_point2",
                new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 2, 1, "alive"));
        piecesMap.put("blue_point3",
                new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 3, 1, "alive"));
        piecesMap.put("blue_point4",
                new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 4, 1, "alive"));
        piecesMap.put("blue_point5",
                new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 5, 1, "alive"));
        piecesMap.put("blue_point6",
                new PointPiece("blue_point", new File(piecesFolder, "blue_PointPiece.png"), 1, 6, 1, "alive"));
        piecesMap.put("blue_plus0",
                new PointPiece("blue_plus", new File(piecesFolder, "blue_PlusPiece.png"), 0, 0, 1, "alive"));
        piecesMap.put("blue_plus1",
                new PointPiece("blue_plus", new File(piecesFolder, "blue_PlusPiece.png"), 0, 6, 1, "alive"));
        piecesMap.put("blue_hourglass0",
                new PointPiece("blue_hourglass", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 1, 1, "alive"));
        piecesMap.put("blue_hourglass1",
                new PointPiece("blue_hourglass", new File(piecesFolder, "blue_HourglassPiece.png"), 0, 5, 1, "alive"));
        piecesMap.put("blue_time0",
                new PointPiece("blue_time", new File(piecesFolder, "blue_TimePiece.png"), 0, 2, 1, "alive"));
        piecesMap.put("blue_time1",
                new PointPiece("blue_time", new File(piecesFolder, "blue_TimePiece.png"), 0, 4, 1, "alive"));
        piecesMap.put("blue_sun",
                new PointPiece("blue_sun", new File(piecesFolder, "blue_SunPiece.png"), 0, 3, 1, "alive"));

        // yellow pieces
        piecesMap.put("yellow_point0",
                new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 0, 0, "alive"));
        piecesMap.put("yellow_point1",
                new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 1, 0, "alive"));
        piecesMap.put("yellow_point2",
                new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 2, 0, "alive"));
        piecesMap.put("yellow_point3",
                new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 3, 0, "alive"));
        piecesMap.put("yellow_point4",
                new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 4, 0, "alive"));
        piecesMap.put("yellow_point5",
                new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 5, 0, "alive"));
        piecesMap.put("yellow_point6",
                new PointPiece("yellow_point", new File(piecesFolder, "yellow_PointPiece.png"), 4, 6, 0, "alive"));
        piecesMap.put("yellow_plus0",
                new PointPiece("yellow_plus", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 0, 0, "alive"));
        piecesMap.put("yellow_plus1",
                new PointPiece("yellow_plus", new File(piecesFolder, "yellow_PlusPiece.png"), 5, 6, 0, "alive"));
        piecesMap.put("yellow_hourglass0", new PointPiece("yellow_hourglass",
                new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 1, 0, "alive"));
        piecesMap.put("yellow_hourglass1", new PointPiece("yellow_hourglass",
                new File(piecesFolder, "yellow_Hourglasspiece.png"), 5, 5, 0, "alive"));
        piecesMap.put("yellow_time0",
                new PointPiece("yellow_time", new File(piecesFolder, "yellow_TimePiece.png"), 5, 2, 0, "alive"));
        piecesMap.put("yellow_time1",
                new PointPiece("yellow_time", new File(piecesFolder, "yellow_TimePiece.png"), 5, 4, 0, "alive"));
        piecesMap.put("yellow_sun",
                new PointPiece("yellow_sun", new File(piecesFolder, "yellow_SunPiece.png"), 5, 3, 0, "alive"));

    }

    public void initializeButtons(JPanel panel) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttonsall[i][j] = new JButton();
                buttonsall[i][j].setFocusable(false);
                buttonsall[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                buttonsall[i][j].setPreferredSize(new Dimension(64, 64)); // Set preferred size here
                buttonsall[i][j].addActionListener(new ButtonClickListener(i, j));

                Color originalColor = (i + j) % 2 == 0 ? Color.WHITE : Color.GRAY;
                // Store the original color for each button
                originalButtonColors.put(buttonsall[i][j], originalColor);

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
            if (piece.getX() == xCoordinate && piece.getY() == yCoordinate && "alive".equals(piece.getStatus())) {
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
            int x = piece.getX();
            int y = piece.getY();
            String name = piece.getName();
            String status = piece.getStatus();

            if (status.equals("alive") && name.equals("blue_plus")) {
                piece.setName("blue_time");
                piece.setImagePath(new File(piecesFolder, "blue_TimePiece.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }

            else if (status.equals("alive") && name.equals("blue_time")) {
                piece.setName("blue_plus");
                piece.setImagePath(new File(piecesFolder, "blue_PlusPiece.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }

            else if (status.equals("alive") && name.equals("yellow_plus")) {
                piece.setName("yellow_time");
                piece.setImagePath(new File(piecesFolder, "yellow_TimePiece.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }

            else if (status.equals("alive") && name.equals("yellow_time")) {
                piece.setName("yellow_plus");
                piece.setImagePath(new File(piecesFolder, "yellow_PlusPiece.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }
        }
    }

    // Movement Logic
    private boolean ismovevalid(int x, int y) {

        String name = selectedPiece.getName();
        int ydistance = y - selectedPiece.getY();
        int xdistance = x - selectedPiece.getX();
        boolean gotpiece = false;

        switch (name) {
            case "blue_point":
            case "yellow_pointflipped":

                // Blue Point piece

                if ((xdistance == 1 || xdistance == 2) && ydistance == 0) {
                    if (xdistance == 2) {
                        // Check if there are any pieces
                        for (int i = selectedPiece.getX() + 1; i < x; i++) {
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
                } else {
                    return false;
                }
            case "yellow_point":
            case "blue_pointflipped":
                // Yellow Point piece

                if ((xdistance == -1 || xdistance == -2) && ydistance == 0) {
                    if (xdistance == -2) {
                        // Check if there are any pieces
                        for (int i = x + 1; i < selectedPiece.getX(); i++) {
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
                } else {
                    return false;
                }

            case "blue_hourglass":
            case "yellow_hourglass":
                // Hourglass piece can skip over pieces
                if ((Math.abs(xdistance) == 1 && Math.abs(ydistance) == 2)
                        || (Math.abs(xdistance) == 2 && Math.abs(ydistance) == 1)) {
                    return true;
                } else {
                    return false;
                }

            case "blue_time":
            case "yellow_time":
                // Time piece
                if (Math.abs(xdistance) == Math.abs(ydistance)) {
                    if (Math.abs(xdistance) > 1) {
                        // When the targeted piece coordinate is smaller than selected Piece Coordinate
                        if (x < selectedPiece.getX() && y < selectedPiece.getY()) {
                            // Check if there are any pieces
                            for (int i = x + 1; i < selectedPiece.getX(); i++) {
                                for (int j = y + 1; j < selectedPiece.getY(); j++) {
                                    int idistance = Math.abs(i - selectedPiece.getX());
                                    int jdistance = Math.abs(j - selectedPiece.getY());

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
                        else if (x > selectedPiece.getX() && y > selectedPiece.getY()) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.getX() + 1; i < x; i++) {
                                for (int j = selectedPiece.getY(); j < y; j++) {
                                    int idistance = Math.abs(i - selectedPiece.getX());
                                    int jdistance = Math.abs(j - selectedPiece.getY());

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
                        } // When the targeted piece xcoordinate is smaller than selected Piece
                          // xCoordinate and vice versa for ycoordinate
                        else if (x < selectedPiece.getX() && y > selectedPiece.getY()) {
                            // Check if there are any pieces
                            for (int i = x; i < selectedPiece.getX(); i++) {
                                for (int j = selectedPiece.getY(); j < y; j++) {
                                    int idistance = Math.abs(i - selectedPiece.getX());
                                    int jdistance = Math.abs(j - selectedPiece.getY());

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
                        } // When the targeted piece ycoordinate is smaller than selected Piece
                          // yCoordinate and vice versa for xcoordinate
                        else if (x > selectedPiece.getX() && y < selectedPiece.getY()) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.getX(); i < x; i++) {
                                for (int j = y; j < selectedPiece.getY(); j++) {
                                    int idistance = Math.abs(i - selectedPiece.getX());
                                    int jdistance = Math.abs(j - selectedPiece.getY());

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
                } else {
                    return false;
                }

            case "blue_plus":
            case "yellow_plus":
                // Plus piece
                if (xdistance == 0 || ydistance == 0) {
                    if (xdistance == 0) {
                        // When the targeted piece ycoordinate is smaller than selected Piece
                        // yCoordinate
                        if (y < selectedPiece.getY()) {
                            // Check if there are any pieces
                            for (int i = y + 1; i < selectedPiece.getY(); i++) {

                                PointPiece checkPiece = getPieceAtPosition(x, i);
                                if (checkPiece != null) {
                                    gotpiece = true;
                                }
                            }

                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        } // When the targeted piece ycoordinate is bigger than selected Piece yCoordinate
                        else if (y > selectedPiece.getY()) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.getY() + 1; i < y; i++) {

                                PointPiece checkPiece = getPieceAtPosition(x, i);
                                if (checkPiece != null) {
                                    gotpiece = true;
                                }
                            }

                            if (!gotpiece) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else if (ydistance == 0) {
                        // When the targeted piece xcoordinate is smaller than selected Piece
                        // xCoordinate
                        if (x < selectedPiece.getX()) {
                            // Check if there are any pieces
                            for (int i = x + 1; i < selectedPiece.getX(); i++) {

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
                        } // When the targeted piece xcoordinate is bigger than selected Piece xCoordinate
                        else if (x > selectedPiece.getX()) {
                            // Check if there are any pieces
                            for (int i = selectedPiece.getX() + 1; i < x; i++) {
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
                        }
                    }
                } else {
                    return false;
                }

            case "blue_sun":
            case "yellow_sun":
                // Sun piece
                if ((Math.abs(xdistance) == 1 && ydistance == 0) || (Math.abs(ydistance) == 1 && xdistance == 0)
                        || (Math.abs(ydistance) == 1 && Math.abs(xdistance) == 1)) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    private void capturepiece(JButton clickedButton) {

        PointPiece targetPiece = getPieceAtPosition(clickedButton);

        if (targetPiece != null) {
            // Capture the piece
            String name = targetPiece.getName();
            if (name == "blue_sun" || name == "yellow_sun") {
                targetPiece.setStatus("dead");
                view.gameover();

            } else {
                targetPiece.setStatus("dead");
            }
        }
    }

    private void rotatepoint() {
        File piecesFolder = new File("pieces");

        for (PointPiece piece : piecesMap.values()) {
            int x = piece.getX();
            int y = piece.getY();
            String name = piece.getName();
            String status = piece.getStatus();

            if (status.equals("alive") && name.equals("blue_point") && x == 5) {
                piece.setName("blue_pointflipped");
                piece.setImagePath(new File(piecesFolder, "blue_PointPieceFlipped.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }

            else if (status.equals("alive") && name.equals("blue_pointflipped") && x == 0) {
                piece.setName("blue_point");
                piece.setImagePath(new File(piecesFolder, "blue_PointPiece.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }

            else if (status.equals("alive") && name.equals("yellow_point") && x == 0) {
                piece.setName("yellow_pointflipped");
                piece.setImagePath(new File(piecesFolder, "yellow_PointPieceFlipped.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }

            else if (status.equals("alive") && name.equals("yellow_pointflipped")
                    && x == 5) {
                piece.setName("yellow_point");
                piece.setImagePath(new File(piecesFolder, "yellow_PointPiece.png"));

                view.setIconForButton(buttonsall[x][y], piece.getImagePath(), name);

            }
        }
    }

    public void save(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Turn: " + turn + "\n");
            writer.write("Current Player: " + currentPlayer + "\n");
            for (Map.Entry<String, PointPiece> entry : piecesMap.entrySet()) {
                String key = entry.getKey();
                PointPiece value = entry.getValue();
                String fileName = value.getImagePath().getName();

                writer.write("Key: " + key + ", Piece: " + value.getName() + ", Path: " + fileName +
                        ", X: " + value.getX() + ", Y: " + value.getY() + ", Player: " + value.getPlayer()
                        + ", Status: " + value.getStatus() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 7) {
                    String key = parts[0].replace("Key: ", "");
                    String pieceName = parts[1].replace("Piece: ", "");
                    String imagePath = parts[2].replace("Path: ", "");
                    int x = Integer.parseInt(parts[3].replace("X: ", ""));
                    int y = Integer.parseInt(parts[4].replace("Y: ", ""));
                    int player = Integer.parseInt(parts[5].replace("Player: ", ""));
                    String status = parts[6].replace("Status: ", "");

                    // Create a new File object using only the file name (child)
                    File path = new File("Pieces", imagePath);

                    // Create a new PointPiece and add it to the piecesMap
                    PointPiece piece = new PointPiece(pieceName, path, x, y, player, status);
                    piecesMap.put(key, piece);
                } else if (parts.length == 1 && parts[0].startsWith("Turn: ")) {
                    // Extract and set the currentPlayer value
                    int turnbeforeswap = Integer.parseInt(parts[0].replace("Turn: ", ""));

                    turn = turnbeforeswap;
                } else if (parts.length == 1 && parts[0].startsWith("Current Player: ")) {
                    // Extract and set the currentPlayer value
                    int cp = Integer.parseInt(parts[0].replace("Current Player: ", ""));

                    currentPlayer = cp;
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
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

            if (clickedPiece != null && clickedPiece.getPlayer() == currentPlayer) {
                // If a piece is clicked, store it as the selected piece
                selectedButton = clickedButton;
                selectedButton.setBackground(Color.GREEN);
            } else if (selectedButton != null) {
                selectedPiece = getPieceAtPosition(selectedButton);

                if (ismovevalid(x, y)) {

                    capturepiece(clickedButton);

                    selectedButton.setBackground(originalButtonColors.get(selectedButton));

                    // Update the piecesMap with the new coordinates
                    selectedPiece.setX(x);
                    selectedPiece.setY(y);

                    // Update the view with the new icon for the clicked button
                    view.setIconForButton(clickedButton, selectedPiece.getImagePath(), selectedPiece.getName());

                    // Clear the icon for the selected button
                    selectedButton.setIcon(null);
                    
                    // Clear the selected button
                    selectedButton = null;


                    // for swap piece
                    if (turn == 3) {
                        swapPiece();
                        turn = 0;
                    } else {
                        turn++;
                    }

                    rotatepoint(); // rotate point piece
                    save("autosave.txt"); // will autosave after every move
                    switchPlayer(); // switch player
                    changeturntotext(); // the player is saved as number 0 and 1. This will turn it to text
                    view.setText();
                } else {
                    return;
                }
            }
        }
    }
}
