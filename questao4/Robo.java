import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Robo {
    protected int x_atual, y_atual, x_anterior, y_anterior, x_blocos[], y_blocos[];
    protected int num_mov_validos, num_mov_invalidos;
    protected boolean vivo, mov_liberado;
    protected String cor;
    protected ImageView img_robo;
    protected TranslateTransition movimento;

    public Robo(String cor, Pane root, int largura, int altura, int[] x_blocos, int[] y_blocos)
            throws FileNotFoundException {
        this.cor = cor;
        this.x_atual = 0;
        this.y_atual = 0;
        this.x_anterior = 0;
        this.y_anterior = 0;
        this.num_mov_validos = 0;
        this.num_mov_invalidos = 0;
        this.vivo = true;
        this.mov_liberado = true;
        this.x_blocos = x_blocos;
        this.y_blocos = y_blocos;
        this.img_robo = this.definirImg(root, largura, altura);
        this.movimento = new TranslateTransition(Duration.seconds(0.5), this.img_robo);
    }

    protected ImageView definirImg(Pane root, int largura, int altura) throws FileNotFoundException {
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

        this.x_anterior = this.x_atual;
        this.y_anterior = this.y_atual;

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

    public void addMovValido() {
        ++this.num_mov_validos;
    }

    public void addMovInvalido() {
        ++this.num_mov_invalidos;
    }

    // ESSE METODO É PROVISÓRIO!!!!!
    public void mostrarMovs() {
        System.out.printf("\nMovimentos válidos: %d\nMovimentos inválidos: %d\n", this.num_mov_validos,
                this.num_mov_invalidos);
    }

    public void retroceder() {
        int dx = this.x_blocos[this.x_anterior] - this.x_blocos[this.x_atual];
        int dy = this.y_blocos[this.y_anterior] - this.y_blocos[this.y_atual];

        // Iniciando o movimento
        movimento.setByX(dx);
        movimento.setByY(dy);
        movimento.play();

        this.x_atual = this.x_anterior;
        this.y_atual = this.y_anterior;
    }

    public void setMovLiberado(boolean mov_liberado) {
        this.mov_liberado = mov_liberado;
    }

    public boolean getMovLiberado() {
        return this.mov_liberado;
    }

    public boolean getVivo() {
        return this.vivo;
    }

    public void morrer() {
        this.vivo = false;
        this.x_atual = -1;
        this.y_atual = -1;
        this.img_robo.setVisible(false);
    }

}
