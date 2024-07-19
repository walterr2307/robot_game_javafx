import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Rocha extends Obstaculo {

    public Rocha(int x, int y, Pane root, int largura) throws FileNotFoundException {
        super(x, y, root, largura);
        this.id = "rocha";
    }

    public ImageView definirImg(Pane root, int largura) throws FileNotFoundException {
        ImageView img = new ImageView(new Image(new FileInputStream("imagens\\rocha.png")));

        img.setFitWidth(largura / 10);
        img.setFitHeight(largura / 10);
        img.setLayoutX(this.x);
        img.setLayoutY(this.y);
        root.getChildren().add(img);

        return img;
    }

    public void bater(Robo robo) {
        if (this.x == robo.getPosX() && this.y == robo.getPosY()) {
            System.out.println("Rocha colidiu com o robô!");
            robo.retroceder();
            robo.setMovLiberado(false);
        }
    }

}
