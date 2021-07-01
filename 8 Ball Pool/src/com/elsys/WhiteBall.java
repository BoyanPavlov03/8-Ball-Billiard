package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WhiteBall extends GameObject{
    public WhiteBall() throws IOException {
        super(300, 300,"whiteBall", ImageIO.read(new File("./resources/whiteBall.png")));
        this.image = this.image.getScaledInstance(20,20, Image.SCALE_FAST);
    }

    @Override
    void tick() {

    }

    @Override
    void render(Graphics g) {
        g.drawImage(image, 720,240,null,null);
    }
}