import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Obstaculo {
    // Bloco --> pos, Atual --> indice
    protected int x_bloco, y_bloco, x_atual, y_atual;
    protected String id;
    protected ImageView img;

    public Obstaculo(int x_bloco, int y_bloco, int x_atual, int y_atual, Pane root, int largura)
            throws FileNotFoundException {
        this.x_bloco = x_bloco;
        this.y_bloco = y_bloco;
        this.x_atual = x_atual;
        this.y_atual = y_atual;
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
        return this.x_atual;
    }

    public int getPosY() {
        return this.y_atual;
    }
}
