import java.io.File;

public class PointPiece {
    public String name;
    private File imagePath;
    public int xCoordinate;
    public int yCoordinate;
    public int player;

    public PointPiece(String name, File file, int x, int y, int player) {
        this.name = name;
        this.imagePath = file;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.player = player;
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
}
