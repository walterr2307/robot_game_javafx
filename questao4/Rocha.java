import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Rocha extends Obstaculo {

    public Rocha(int x_bloco, int y_bloco, int x_atual, int y_atual, Pane root, int largura) throws FileNotFoundException {
        super(x_bloco, y_bloco, x_atual, y_atual, root, largura);
        this.id = "rocha";
    }

    public ImageView definirImg(Pane root, int largura) throws FileNotFoundException {
        ImageView img = new ImageView(new Image(new FileInputStream("imagens\\rocha.png")));

        img.setFitWidth(largura / 10);
        img.setFitHeight(largura / 10);
        img.setLayoutX(this.x_bloco);
        img.setLayoutY(this.y_bloco);
        root.getChildren().add(img);

        return img;
    }

    public void bater(Robo robo) {
        if (this.x_atual == robo.getPosX() && this.y_atual == robo.getPosY()) {
            robo.retroceder();
            robo.setMovLiberado(false);
        }
    }

}
