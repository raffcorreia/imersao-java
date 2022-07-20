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
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(YELLOW);
        graphics.drawString(msg, 100, newImage.getHeight() - 100);

        File directory = new File(OUTPUT_DIRECTORY);
        if (!directory.exists()){
            directory.mkdir();
        }
        
        try {
            ImageIO.write(newImage, "png", new File(OUTPUT_DIRECTORY + saveAs + ".png"));
        } catch (IOException ignored) {
        }
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
