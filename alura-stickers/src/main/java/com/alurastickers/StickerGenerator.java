package com.alurastickers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.awt.Color.YELLOW;
import static java.awt.Transparency.TRANSLUCENT;

public class StickerGenerator {

    private static final int HEIGHT_OFFSET = 200;
    private static final String OUTPUT_DIRECTORY = "saida/";

    public void create(String strURL, String saveAs, String msg) {
        BufferedImage img = getImage(strURL);

        if(img == null) {
            return;
        }

        int newImgWidth = img.getWidth();
        int newImgHeight = img.getHeight() + HEIGHT_OFFSET;
        BufferedImage newImage = new BufferedImage(newImgWidth, newImgHeight, TRANSLUCENT);

        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(img, 0, 0, null);

        addMessage(msg, newImage, graphics);
        addStamp(newImgWidth, newImgHeight, graphics);

        File directory = new File(OUTPUT_DIRECTORY);
        if (!directory.exists()){
            directory.mkdir();
        }
        
        try {
            ImageIO.write(newImage, "png", new File(OUTPUT_DIRECTORY + saveAs.replaceAll("[^a-zA-Z0-9]", "") + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addMessage(String msg, BufferedImage newImage, Graphics2D graphics) {
        Font font = getFont();
        FontMetrics metrics = graphics.getFontMetrics(font);
        int textWidth = metrics.stringWidth(msg);
        int textHeight = metrics.getHeight();

        int xPos = (newImage.getWidth() - textWidth) / 2;
        int yPos = newImage.getHeight() - (HEIGHT_OFFSET - textHeight) / 2;

        graphics.setFont(font);
        graphics.setColor(YELLOW);
        graphics.drawString(msg, xPos, yPos);
    }

    private void addStamp(int newImgWidth, int newImgHeight, Graphics2D graphics) {
        File imgStampFile = new File("alura-stickers/src/main/resources/img/stamp.png");
        BufferedImage imgStamp = null;
        try {
            imgStamp = ImageIO.read(imgStampFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int stampWidth = (int)(imgStamp.getWidth() * (150f / imgStamp.getHeight()));
        int stampHeight = 150;
        graphics.drawImage(imgStamp, newImgWidth - stampWidth, newImgHeight - stampHeight, stampWidth, stampHeight, null);
    }

    private Font getFont() {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("alura-stickers/src/main/resources/fonts/impact.ttf")).deriveFont(64f);
        } catch (FontFormatException | IOException e) {
            font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        }

        return font;
    }

    private BufferedImage getImage(String strURL) {
        BufferedImage img = null;
        try {
            URL url = new URL(strURL);
            img = ImageIO.read(url);
        } catch (IOException e) {
            return img;
        }
        return img;
    }

}
