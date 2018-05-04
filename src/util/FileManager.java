package util;

import gui.GUIManager;
import logic.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    private static FileManager instance;

    private FileManager() {}

    public static FileManager getInstance() {
        if (instance == null)
            instance = new FileManager();
        return instance;
    }

    // TODO will be implemented after iteration I
    public static ArrayList<String> getSavedGames() {
        ArrayList<String> savedGames = new ArrayList<>();
        return savedGames;
    }

    //remove save file of a game
    public void removeSavedGame(int index) throws IOException {
        Files.deleteIfExists(Paths.get("savedata" + index + ".txt"));
    }

    // TODO will be implemented after iteration I
    //load the game from a txt file
    public GameManager loadGame(int loadGameIndex) {
        GameManager gameManager = GameManager.getInstance();

        return gameManager;
    }

    // TODO will be implemented after iteration I
    //save the game into a serialized file
    private static int savecount = 0;
    public void saveGame()  {
        //TODO serializable exception

        //GUIManager.getInstance().showGamePanel();
        GUIManager saveInstance = GUIManager.getInstance();

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("savedata" + (savecount++) + ".ser");

            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            //out.writeObject(saveInstance);    // not serializable exception

            out.close();

            fileOut.close();

        } catch (NotSerializableException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


        //new PrintWriter("savedata" + (savecount++) + ".txt");
    }

    public BufferedImage getImage(String imagepath) {
        BufferedImage image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            return image;
        } catch (IOException exc) {
            System.err.println("getImage.IOException: " + imagepath);
            return null;
        } catch (IllegalArgumentException exc) {
            System.err.println("getImage.IllegalArgumentException: " + imagepath);
            return null;
        }
    }

    public static BufferedImage getResizedImage(BufferedImage image, int width, int height) {
        Image tempImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resultImage.createGraphics();
        g2d.drawImage(tempImage, 0, 0, null);
        g2d.dispose();
        return resultImage;
    }
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }
    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}
