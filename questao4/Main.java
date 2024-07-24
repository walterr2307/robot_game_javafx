import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        Robo robo1;
        RoboInteligente robo2;

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
        int tipo_obs, x[] = { 2, 3 }, y[] = { 1, 3 };
        String copia_cor;
        Obstaculo ob;
        ArrayList<Obstaculo> obs = new ArrayList<Obstaculo>();

        // Embaralhando as cores
        for (i = cores.length - 1; i > 0; i--) {
            j = (int) (Math.random() * (i + 1));
            copia_cor = cores[i];
            cores[i] = cores[j];
            cores[j] = copia_cor;
        }

        // Gerando os obstáculos
        for (i = 0; i < 2; i++) {
            tipo_obs = (int) (Math.random() * 2);

            if (tipo_obs == 0)
                ob = new Rocha(x_blocos[x[i]], y_blocos[y[i]], x[i], y[i], root, largura);
            else
                ob = new Bomba(x_blocos[x[i]], y_blocos[y[i]], x[i], y[i], root, largura);

            obs.add(ob);
        }

        robo1 = new Robo(cores[0], root, largura, altura, x_blocos, y_blocos);
        robo2 = new RoboInteligente(cores[1], root, largura, altura, x_blocos, y_blocos);
        comida = new Comida(root, largura, altura, x_blocos[x_comida], y_blocos[y_comida]);
        // FIM DO BLOCO

        // Ajustando os eventos

        // Configurando a cena
        root.setStyle("-fx-background-color: #C0C0C0;");
        stage.setScene(scene);
        stage.setTitle("Jogo do Robô");
        stage.show();

        // Iniciar o evento que dura enquanto a janela estiver aberta
        eventoMover(stage, robo1, robo2, obs);

        // AVISO!!!!!
        // CRIAR LÓGICA DO GAME OVER
    }

    private void eventoMover(Stage stage, Robo robo1, RoboInteligente robo2, ArrayList<Obstaculo> obs)
            throws FileNotFoundException {

        // Gera dois números aleatórios entre 0 e 3 para determinar o tipo de movimento
        // dos robôs

        // Cria uma nova Timeline que executa uma ação a cada 1 segundo
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int tipo_mov1 = (int) (Math.random() * 4), tipo_mov2 = (int) (Math.random() * 4);

            try {
                // Verificando se o robô passou por um obstáculo

                if (robo1.getMovLiberado() == false)
                    robo1.setMovLiberado(true);

                for (Obstaculo ob : obs)
                    ob.bater(robo1);

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

                if (robo1.getMovLiberado() == true && robo1.getVivo() == true)
                    robo1.mover(tipo_mov1);

            } catch (MovimentoInvalidoException e) {
                // Se o movimento for inválido, registra o movimento inválido e mostra a
                // mensagem de erro
                // Se o movimento for inválido, registra o movimento inválido
                e.invalidarMovimento(robo1);
            }

            try {
                tipo_mov2 = robo2.verificarRegistroMov(tipo_mov2);

                // Verificando se o robô passou por um obstáculo
                if (robo2.getMovLiberado() == false)
                    robo2.setMovLiberado(true);

                for (Obstaculo ob : obs)
                    ob.bater(robo2);

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

                if (robo2.getMovLiberado() == true && robo2.getVivo() == true)
                    robo2.mover(tipo_mov2);

            } catch (MovimentoInvalidoException e) {
                // Se o movimento for inválido, registra o movimento inválido
                e.invalidarMovimento(robo2);
                robo2.registrarMovimento(tipo_mov2);
            } finally {
                finalizarJogo(stage, robo1, robo2);
            }
        }));

        // Define o número de ciclos da Timeline como indefinido (infinito)
        this.timeline.setCycleCount(Timeline.INDEFINITE);

        // Inicia a execução da Timeline
        this.timeline.play();
    }

    private void finalizarJogo(Stage stage, Robo robo1, Robo robo2) {
        if (robo1.getVivo() == false && robo2.getVivo() == false) {
            System.out.println("Os robôs morreram!");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}