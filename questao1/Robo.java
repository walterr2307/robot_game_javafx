import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Robo {
    private int x_atual, y_atual, x_blocos[], y_blocos[];
    private String cor;
    private ImageView img_robo;
    private TranslateTransition movimento;

    public Robo(String cor, Pane root, int largura, int altura, int[] x_blocos, int[] y_blocos)
            throws FileNotFoundException {
        this.cor = cor;
        this.x_atual = 0;
        this.y_atual = 0;
        this.ajustarBlocos(largura, altura, x_blocos, y_blocos);
        this.img_robo = this.definirImg(root, largura, altura);
        this.movimento = new TranslateTransition(Duration.seconds(0.5), this.img_robo);
    }

    private void ajustarBlocos(int largura, int altura, int[] x_blocos, int[] y_blocos) {
        int i, ajuste_x = (int) (largura * 0.02), ajuste_y = (int) (altura * 0.04);
        int x_copia[] = Arrays.copyOf(x_blocos, 4), y_copia[] = Arrays.copyOf(y_blocos, 4);

        for (i = 0; i < 4; i++) {
            x_copia[i] += ajuste_x;
            y_copia[i] += ajuste_y;
        }

        this.x_blocos = x_copia;
        this.y_blocos = y_copia;
    }

    private ImageView definirImg(Pane root, int largura, int altura) throws FileNotFoundException {
        String caminho;
        ImageView img;

        if (this.cor.equals("cinza"))
            caminho = "imagens/robo_cinza.png";
        else if (this.cor.equals("vermelho"))
            caminho = "imagens/robo_vermelho.png";
        else
            caminho = "imagens/robo_azul.png";

        img = new ImageView(new Image(new FileInputStream(caminho)));
        img.setFitWidth(largura / 16);
        img.setFitHeight(altura / 16);
        img.setLayoutX(this.x_blocos[0]);
        img.setLayoutY(this.y_blocos[0]);
        root.getChildren().add(img);

        return img;
    }

    public void mover(int tipo_mov) {
        int soma_x = 0, soma_y = 0, dx, dy;

        if (tipo_mov == 0)
            soma_x = -1;
        else if (tipo_mov == 1)
            soma_y = -1;
        else if (tipo_mov == 2)
            soma_y = 1;
        else
            soma_x = 1;

        // Calculando as distâncias
        dx = this.x_blocos[soma_x + this.x_atual] - this.x_blocos[this.x_atual];
        dy = this.y_blocos[soma_y + this.y_atual] - this.y_blocos[this.y_atual];

        // Iniciando o movimento
        movimento.setByX(dx);
        movimento.setByY(dy);
        movimento.play();

        // Atualizando o posicionamento atual
        this.x_atual += soma_x;
        this.y_atual += soma_y;
    }

    public int getPosX() {
        return this.x_atual;
    }

    public int getPosY() {
        return this.y_atual;
    }

    public ImageView getImg() {
        return this.img_robo;
    }

}
