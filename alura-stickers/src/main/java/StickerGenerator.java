package main.java;

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

        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight() + HEIGHT_OFFSET, TRANSLUCENT);

        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        Font font = getFont();

        FontMetrics metrics = graphics.getFontMetrics(font);
        int textWidth = metrics.stringWidth(msg);
        int textHeight = metrics.getHeight();

        int xPos = (newImage.getWidth() - textWidth) / 2;
        int yPos = newImage.getHeight() - (HEIGHT_OFFSET - textHeight) / 2;

        graphics.setFont(font);
        graphics.setColor(YELLOW);
        graphics.drawString(msg, xPos, yPos);

        File directory = new File(OUTPUT_DIRECTORY);
        if (!directory.exists()){
            directory.mkdir();
        }
        
        try {
            ImageIO.write(newImage, "png", new File(OUTPUT_DIRECTORY + saveAs.replaceAll("[^a-zA-Z0-9]", "") + ".png"));
        } catch (IOException ignored) {
        }
    }

    private Font getFont() {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("alura-stickers/src/resources/fonts/impact.ttf")).deriveFont(64f);
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
