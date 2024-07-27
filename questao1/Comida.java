import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Comida {
    private int x_comida, y_comida, pos_x, pos_y;
    private ImageView img_comida;

    public Comida(Pane root, int largura, int altura, int x, int y, int pos_x, int pos_y) throws FileNotFoundException {
        this.x_comida = x;
        this.y_comida = y;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.img_comida = this.definirImg(root, largura, altura);
    }

    private ImageView definirImg(Pane root, int largura, int altura) throws FileNotFoundException {
        ImageView img = new ImageView(new Image(new FileInputStream("imagens\\frango.png")));

        img.setFitWidth(largura / 10);
        img.setFitHeight(altura * 0.15);
        img.setLayoutX(this.x_comida);
        img.setLayoutY(this.y_comida);
        root.getChildren().add(img);

        return img;
    }

    public ImageView getImg() {
        return this.img_comida;
    }

    public int getPosX() {
        return this.pos_x;
    }

    public int getPosY() {
        return this.pos_y;
    }

}
