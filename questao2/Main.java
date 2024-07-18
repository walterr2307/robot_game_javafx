import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private Timeline timeline;

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        int largura = 720, altura = (int) (largura / 1.5), x_blocos[], y_blocos[];
        String cores[] = { "cinza", "vermelho", "azul" };
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);
        // Button left, up, down, right;
        // Rectangle blocos[][];
        Tabuleiro tabuleiro = new Tabuleiro(root, largura, altura);
        Robo robo1, robo2;

        @SuppressWarnings("unused")
        Comida comida;

        // Configurando o tabuleiro
        // blocos = tabuleiro.getBlocos();
        x_blocos = tabuleiro.getXBlocos();
        y_blocos = tabuleiro.getYBlocos();

        /*
         * left = tabuleiro.getLeft();
         * up = tabuleiro.getUp();
         * down = tabuleiro.getDown();
         * right = tabuleiro.getRight();
         */

        // ESSE BLOCO DEVE SER MODIFICADO COM O MENU
        int i, j, x_comida = (int) (Math.random() * 3 + 1), y_comida = (int) (Math.random() * 3 + 1);
        String copia_cor;

        // Embaralhando as cores
        for (i = cores.length - 1; i > 0; i--) {
            j = (int) (Math.random() * (i + 1));
            copia_cor = cores[i];
            cores[i] = cores[j];
            cores[j] = copia_cor;
        }

        robo1 = new Robo(cores[0], root, largura, altura, x_blocos, y_blocos);
        robo2 = new Robo(cores[1], root, largura, altura, x_blocos, y_blocos);
        comida = new Comida(root, largura, altura, x_blocos[x_comida], y_blocos[y_comida]);
        // FIM DO BLOCO

        // Ajustando os eventos

        // Configurando a cena
        root.setStyle("-fx-background-color: #C0C0C0;");
        stage.setScene(scene);
        stage.setTitle("Jogo do Robô");
        stage.show();

        // Iniciar o evento que dura enquanto a janela estiver aberta
        eventoMover(robo1, robo2);

        // Adicionar um event handler para parar o evento quando a janela for fechada
        stage.setOnCloseRequest(event -> paraEvento());

        // AVISO!!!!!
        // CRIAR LÓGICA DO GAME OVER
    }

    private void eventoMover(Robo robo1, Robo robo2) throws FileNotFoundException {

        // Gera dois números aleatórios entre 0 e 3 para determinar o tipo de movimento
        // dos robôs

        // Cria uma nova Timeline que executa uma ação a cada 0.75 segundos
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(0.75), event -> {
            int tipo_mov1 = (int) (Math.random() * 4), tipo_mov2 = (int) (Math.random() * 4);

            try {
                // Verifica se o movimento do robo1 é inválido e lança
                // MovimentoInvalidoException se for o caso
                if (tipo_mov1 == 0 && robo1.getPosX() - 1 < 0)
                    throw new MovimentoInvalidoException();
                if (tipo_mov1 == 1 && robo1.getPosY() - 1 < 0)
                    throw new MovimentoInvalidoException();
                if (tipo_mov1 == 2 && robo1.getPosY() + 1 >= 4)
                    throw new MovimentoInvalidoException();
                if (tipo_mov1 == 3 && robo1.getPosX() + 1 >= 4)
                    throw new MovimentoInvalidoException();

                // Se o movimento for válido, registra o movimento válido e move o robo1
                robo1.addMovValido();
                robo1.mover(tipo_mov1);
            } catch (MovimentoInvalidoException e) {
                // Se o movimento for inválido, registra o movimento inválido e mostra a
                // mensagem de erro
                // Se o movimento for inválido, registra o movimento inválido
                e.invalidarMovimento(robo1, robo2);
            }

            try {
                // Verifica se o movimento do robo2 é inválido e lança
                // MovimentoInvalidoException se for o caso
                if (tipo_mov2 == 0 && robo2.getPosX() - 1 < 0)
                    throw new MovimentoInvalidoException();
                if (tipo_mov2 == 1 && robo2.getPosY() - 1 < 0)
                    throw new MovimentoInvalidoException();
                if (tipo_mov2 == 2 && robo2.getPosY() + 1 >= 4)
                    throw new MovimentoInvalidoException();
                if (tipo_mov2 == 3 && robo2.getPosX() + 1 >= 4)
                    throw new MovimentoInvalidoException();

                // Se o movimento for válido, registra o movimento válido e move o robo2
                robo2.addMovValido();
                robo2.mover(tipo_mov2);
            } catch (MovimentoInvalidoException e) {
                // Se o movimento for inválido, registra o movimento inválido
                e.invalidarMovimento(robo1, robo2);
            }
        }));

        // Define o número de ciclos da Timeline como indefinido (infinito)
        this.timeline.setCycleCount(Timeline.INDEFINITE);

        // Inicia a execução da Timeline
        this.timeline.play();
    }

    private void paraEvento() {
        if (this.timeline != null) {
            this.timeline.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}