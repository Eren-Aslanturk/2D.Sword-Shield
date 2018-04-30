package gui;
/*
* Author: Mehmet Enes Keles
* Date: 12.03.2018
*
* UnitCardPanel is simple card that represents a single unit such as a factory or a turret.
*/
import util.FileManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class UnitCardPanel extends JPanel {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 120;

    private BufferedImage background;
    private String unitName;
    private String subUnitName;
    private int unitCost;

    private boolean selected;
    private BufferedImage unitImage;
    private BufferedImage subUnitImage;

    public UnitCardPanel(String unitName, String subUnitName, int unitCost, String unitImagepath, String subUnitImagepath) {
        this.unitName = unitName;
        this.subUnitName = subUnitName;
        this.unitCost = unitCost;
        selected = false;

        background = FileManager.getInstance().getImage("/images/gamepanel/unitcardbg.png");
        background = FileManager.getInstance().getResizedImage(background, WIDTH, HEIGHT);

        unitImage = FileManager.getInstance().getImage(unitImagepath);
        unitImage = FileManager.getInstance().getResizedImage(unitImage, 50, 50);
        // reactor doesn't have a sub unit
        if (subUnitImagepath != null) {
            subUnitImage = FileManager.getInstance().getImage(subUnitImagepath);
            subUnitImage = FileManager.getInstance().getResizedImage(subUnitImage, 30, 30);
        }

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(unitImage, 10, 10, null);
        if (subUnitImage != null)
            g.drawImage(subUnitImage, 10, 70, null);

        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.setColor(Color.RED);
        g.drawString(unitName,60,10);

        if (subUnitName != null)
            g.drawString(subUnitName, 60, 70);

        if (selected)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.YELLOW);
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
