package gui;

import util.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MenuButton extends JButton {
    private final int IMAGE_MARGIN = 35;
    private final int HOVER_IMAGE_MARGIN = 55;

    private BufferedImage image;
    private BufferedImage hoverImage;
    private int buttonX, buttonY;

    public MenuButton(int buttonX, int buttonY, BufferedImage image, BufferedImage hoverImage) {
        super("");
        this.buttonX = buttonX;
        this.buttonY = buttonY;
        this.image = image;
        this.hoverImage = hoverImage;
        setIcon(new ImageIcon(image));
        setOpaque(false);
        setContentAreaFilled(false);
        setBounds(new Rectangle(buttonX-IMAGE_MARGIN, buttonY, image.getWidth(), image.getHeight()));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon(hoverImage));
                setBounds(new Rectangle(buttonX-HOVER_IMAGE_MARGIN, buttonY, hoverImage.getWidth(), hoverImage.getHeight()));
                SoundManager.getInstance().playSound("/sounds/menubuttonsound.wav");
            }

            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon(image));
                setBounds(new Rectangle(buttonX-IMAGE_MARGIN, buttonY, image.getWidth(), image.getHeight()));
            }
        });
    }
}
