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
    protected boolean vivo, mov_liberado, ultimo_mov; // Indica se achou a comida
    protected String cor;
    protected ImageView img_robo;
    protected TranslateTransition movimento;

    // Construtor da classe Robo
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
        this.ultimo_mov = false;
        this.x_blocos = x_blocos;
        this.y_blocos = y_blocos;
        this.img_robo = this.definirImg(root, largura, altura);
        this.movimento = new TranslateTransition(Duration.seconds(0.5), this.img_robo);
    }

    // Método para definir a imagem do robô
    protected ImageView definirImg(Pane root, int largura, int altura) throws FileNotFoundException {
        String caminho;
        ImageView img;

        // Define o caminho da imagem com base na cor
        if (this.cor.equals("cinza"))
            caminho = "imagens/robo_cinza.png";
        else if (this.cor.equals("vermelho"))
            caminho = "imagens/robo_vermelho.png";
        else
            caminho = "imagens/robo_azul.png";

        // Carrega a imagem e ajusta suas dimensões
        img = new ImageView(new Image(new FileInputStream(caminho)));
        img.setFitWidth(largura / 16);
        img.setFitHeight(altura / 16);
        img.setLayoutX(this.x_blocos[0]);
        img.setLayoutY(this.y_blocos[0]);
        root.getChildren().add(img);

        return img;
    }

    // Método para mover o robô baseado em um inteiro que representa a direção
    public void mover(int tipo_mov) {
        int soma_x = 0, soma_y = 0, dx, dy;

        if (this.vivo && !this.ultimo_mov) {
            this.x_anterior = this.x_atual;
            this.y_anterior = this.y_atual;

            // Define a direção do movimento
            if (tipo_mov == 0)
                soma_x = -1;
            else if (tipo_mov == 1)
                soma_y = -1;
            else if (tipo_mov == 2)
                soma_y = 1;
            else
                soma_x = 1;

            // Calcula as distâncias para o movimento
            dx = this.x_blocos[soma_x + this.x_atual] - this.x_blocos[this.x_atual];
            dy = this.y_blocos[soma_y + this.y_atual] - this.y_blocos[this.y_atual];

            // Inicia a animação de translação
            movimento.setByX(dx);
            movimento.setByY(dy);
            movimento.play();

            // Atualiza a posição atual
            this.x_atual += soma_x;
            this.y_atual += soma_y;
        }
    }

    // Método sobrecarregado para mover o robô baseado em uma string que representa
    // a direção
    public void mover(String tipo_mov) {
        int soma_x = 0, soma_y = 0, dx, dy;

        if (this.vivo && !this.ultimo_mov) {
            this.x_anterior = this.x_atual;
            this.y_anterior = this.y_atual;

            // Define a direção do movimento
            if (tipo_mov.equals("left"))
                soma_x = -1;
            else if (tipo_mov.equals("up"))
                soma_y = -1;
            else if (tipo_mov.equals("down"))
                soma_y = 1;
            else
                soma_x = 1;

            // Calcula as distâncias para o movimento
            dx = this.x_blocos[soma_x + this.x_atual] - this.x_blocos[this.x_atual];
            dy = this.y_blocos[soma_y + this.y_atual] - this.y_blocos[this.y_atual];

            // Inicia a animação de translação
            movimento.setByX(dx);
            movimento.setByY(dy);
            movimento.play();

            // Atualiza a posição atual
            this.x_atual += soma_x;
            this.y_atual += soma_y;
        }
    }

    // Métodos getters para a posição atual e a imagem do robô
    public int getPosX() {
        return this.x_atual;
    }

    public int getPosY() {
        return this.y_atual;
    }

    public ImageView getImg() {
        return this.img_robo;
    }

    // Métodos para atualizar o número de movimentos válidos e inválidos
    public void addMovValido() {
        ++this.num_mov_validos;
    }

    public void addMovInvalido() {
        ++this.num_mov_invalidos;
    }

    public int getMovValidos() {
        return this.num_mov_validos;
    }

    public int getMovInvalidos() {
        return this.num_mov_invalidos;
    }

    // Método para retroceder o movimento do robô
    public void retroceder() {
        int dx = this.x_blocos[this.x_anterior] - this.x_blocos[this.x_atual];
        int dy = this.y_blocos[this.y_anterior] - this.y_blocos[this.y_atual];

        // Inicia a animação de translação
        movimento.setByX(dx);
        movimento.setByY(dy);
        movimento.play();

        // Atualiza a posição atual para a posição anterior
        this.x_atual = this.x_anterior;
        this.y_atual = this.y_anterior;
    }

    // Métodos para liberar e bloquear o movimento
    public void setMovLiberado(boolean mov_liberado) {
        this.mov_liberado = mov_liberado;
    }

    public boolean getMovLiberado() {
        return this.mov_liberado;
    }

    // Métodos para verificar se o robô está vivo e para "matar" o robô
    public boolean getVivo() {
        return this.vivo;
    }

    public void morrer() {
        this.vivo = false;
        this.x_atual = 0;
        this.y_atual = 0;
        this.img_robo.setVisible(false);
    }

    // Método para resetar a posição do robô para o início
    public void voltarInicio() {
        this.x_atual = 0;
        this.y_atual = 0;
    }

    // Método para bloquear o movimento do robô
    public void bloquearMovimento() {
        this.ultimo_mov = true;
    }
}