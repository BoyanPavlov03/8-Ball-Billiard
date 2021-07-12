package com.elsys;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;

public class Cue {
    Vector2D position;
    Image image;
    int radius;

    public Cue(Vector2D position, Image image){
        this.position = position;
        this.radius = 150;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
    }

    public Image getImage() {
        return image;
    }

    /*public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }*/

    public BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }


    void tick(WhiteBall whiteBall, Vector2D mousePos){
        Vector2D whiteBallPos = new Vector2D(whiteBall.position.x + 14, whiteBall.position.y + 14);
        double dist = whiteBallPos.distance(mousePos);
        double distx = whiteBallPos.x - mousePos.x;
        double disty = whiteBallPos.y - mousePos.y;
        double whatper = (15 / dist) * 100;
        double addx = (distx * whatper) / 100;
        double addy = (disty * whatper) / 100;

        position.x = whiteBallPos.x + addx;
        position.y = whiteBallPos.y + addy;
    }

    void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        BufferedImage bufferedImage = toBufferedImage(image);

        double rotationRequired = Math.toRadians (110);
        double locationX = bufferedImage.getWidth() / 2.0;
        double locationY = bufferedImage.getHeight() / 2.0;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g2d.drawImage(op.filter(bufferedImage, null), (int)position.x, (int)position.y, null);
    }
}
