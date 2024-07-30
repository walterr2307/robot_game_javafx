import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Tabuleiro {
    // Declaração de variáveis
    private int x_blocos[], y_blocos[]; // Arrays para armazenar as coordenadas dos blocos
    private Rectangle blocos[][]; // Matriz de retângulos para os blocos
    private Button left, up, down, right; // Botões de controle

    // Construtor da classe Tabuleiro
    public Tabuleiro(Pane root, int largura, int altura) throws FileNotFoundException {
        // Inicializa os arrays e matriz
        this.x_blocos = new int[4];
        this.y_blocos = new int[4];
        this.blocos = new Rectangle[4][4];
        // Chama métodos para configurar o design e implementar o plano do tabuleiro
        this.colocarDesign(root, largura, altura);
        this.implementarPlano(root, largura, altura);
        this.configurarBotoes(root, largura, altura);
    }

    // Método para configurar o design do tabuleiro
    private void colocarDesign(Pane root, int largura, int altura) throws FileNotFoundException {
        // Carrega uma imagem de fundo do espaço sideral
        ImageView img = new ImageView(new Image(new FileInputStream("imagens/espaco_sideral.jpg")));
        Rectangle bordas = new Rectangle();
        Button msg_robot_game = new Button("Robot Game");

        // Configura a imagem
        img.setFitWidth(largura * 0.4);
        img.setFitHeight(largura * 0.4);
        img.setLayoutX(largura * 0.15);
        img.setLayoutY(altura * 0.3);

        // Configura as bordas
        bordas.setWidth(largura);
        bordas.setHeight(altura);
        bordas.setFill(Color.TRANSPARENT);
        bordas.setStroke(Color.BLACK);
        bordas.setStrokeWidth(largura / 60);

        // Configura o botão de título
        msg_robot_game.setPrefSize(largura, altura * 0.2);
        msg_robot_game.setStyle("-fx-background-color: #C0C0C0; -fx-text-fill: black;");
        msg_robot_game.setFont(Font.font("Times New Roman", largura * 0.09));

        // Adiciona os componentes ao root
        root.getChildren().addAll(img, msg_robot_game, bordas);
    }

    // Método para implementar o plano do tabuleiro
    private void implementarPlano(Pane root, int largura, int altura) {
        int i, j, tam_bloco = (int) (largura / 10), x = (int) (largura * 0.15), y = (int) (altura * 0.3);

        // Calcula as coordenadas dos blocos
        for (i = 0; i < 4; i++) {
            this.x_blocos[i] = x + i * tam_bloco;
            this.y_blocos[i] = y + i * tam_bloco;
        }

        // Cria e configura os blocos
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                this.blocos[i][j] = new Rectangle();
                this.blocos[i][j].setWidth(tam_bloco);
                this.blocos[i][j].setHeight(tam_bloco);
                this.blocos[i][j].setLayoutX(this.x_blocos[i]);
                this.blocos[i][j].setLayoutY(this.y_blocos[j]);
                this.blocos[i][j].setFill(Color.TRANSPARENT);
                this.blocos[i][j].setStroke(Color.WHITE);
                this.blocos[i][j].setStrokeWidth(largura / 180);

                // Adiciona os blocos ao root
                root.getChildren().add(this.blocos[i][j]);
            }
        }
    }

    // Método para configurar os botões
    private void configurarBotoes(Pane root, int largura, int altura) {
        int tam_botao = (int) (largura * 0.08), x = (int) (largura * 0.65), y = (int) (altura * 0.6);
        int tam_fonte = (int) (largura * 0.015), ajuste = (int) (largura * 0.015);
        String estilo = "-fx-background-color: #32CD32; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 3px;";

        // Configura o botão "LEFT"
        this.left = new Button("LEFT");
        this.left.setPrefSize(tam_botao, tam_botao);
        this.left.setLayoutX(x - ajuste);
        this.left.setLayoutY(y);
        this.left.setFont(Font.font("Times New Roman", tam_fonte));
        this.left.setStyle(estilo);

        // Configura o botão "UP"
        this.up = new Button("UP");
        this.up.setPrefSize(tam_botao, tam_botao);
        this.up.setLayoutX(x + tam_botao);
        this.up.setLayoutY(y - tam_botao - ajuste);
        this.up.setFont(Font.font("Times New Roman", tam_fonte));
        this.up.setStyle(estilo);

        // Configura o botão "DOWN"
        this.down = new Button("DOWN");
        this.down.setPrefSize(tam_botao, tam_botao);
        this.down.setLayoutX(x + tam_botao);
        this.down.setLayoutY(y + tam_botao + ajuste);
        this.down.setFont(Font.font("Times New Roman", tam_fonte));
        this.down.setStyle(estilo);

        // Configura o botão "RIGHT"
        this.right = new Button("RIGHT");
        this.right.setPrefSize(tam_botao, tam_botao);
        this.right.setLayoutX(x + tam_botao * 2 + ajuste);
        this.right.setLayoutY(y);
        this.right.setFont(Font.font("Times New Roman", tam_fonte));
        this.right.setStyle(estilo);

        // Adiciona os botões ao root
        root.getChildren().addAll(this.left, this.up, this.down, this.right);
    }

    // Métodos para obter as coordenadas dos blocos
    public int[] getXBlocos() {
        return this.x_blocos;
    }

    public int[] getYBlocos() {
        return this.y_blocos;
    }

    // Métodos para obter os botões
    public Button getLeft() {
        return this.left;
    }

    public Button getUp() {
        return this.up;
    }

    public Button getDown() {
        return this.down;
    }

    public Button getRight() {
        return this.right;
    }

    // Método para obter os blocos
    public Rectangle[][] getBlocos() {
        return this.blocos;
    }

    // Método para interagir com os botões
    public void interagirBotoes() {
        // Desativa os botões
        this.left.setDisable(true);
        this.up.setDisable(true);
        this.down.setDisable(true);
        this.right.setDisable(true);

        // Cria uma pausa de 0.55 segundos
        PauseTransition pause = new PauseTransition(Duration.seconds(0.55));
        pause.setOnFinished(event -> {
            // Reativa os botões após a pausa
            this.left.setDisable(false);
            this.up.setDisable(false);
            this.down.setDisable(false);
            this.right.setDisable(false);
        });

        // Inicia a pausa
        pause.play();
    }
}