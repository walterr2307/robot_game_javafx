import java.io.FileNotFoundException;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Obstaculo {
    protected int x, y;
    protected String id;
    protected ImageView img;

    public Obstaculo(int x, int y, Pane root, int largura) throws FileNotFoundException {
        this.x = x;
        this.y = y;
        this.img = this.definirImg(root, largura);
    }

    public void bater(Robo robo) {

    }

    public String getId() {
        return this.id;
    }

    public ImageView definirImg(Pane root, int largura) throws FileNotFoundException {
        ImageView img = new ImageView();
        return img;
    }

    public int getPosX() {
        return this.x;
    }

    public int getPosY() {
        return this.y;
    }
}
