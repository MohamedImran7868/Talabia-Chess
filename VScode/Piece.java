import java.io.File;
//All
public class Piece {
    private String name;
    private File imagePath;
    private int xCoordinate;
    private int yCoordinate;
    private int player;
    private String status;

    public Piece(String name, File file, int x, int y, int player, String status) {
        this.name = name;
        this.imagePath = file;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.player = player;
        this.status = status;
    }

    //Getter Method
    public String getName() {
        return name;
    }

    public File getImagePath() {
        return imagePath;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public int getPlayer() {
        return player;
    }

    public String getStatus() {
        return status;
    }

    //Setter Method
    public void setName(String name) {
        this.name = name;
    }

    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    public void setX(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setY(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
