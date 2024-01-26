import java.io.File;

public class PointPiece {
    public String name;
    public File imagePath;
    public int xCoordinate;
    public int yCoordinate;
    public int player;
    public String status;

    public PointPiece(String name, File file, int x, int y, int player, String status) {
        this.name = name;
        this.imagePath = file;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.player = player;
        this.status = status;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }
}
