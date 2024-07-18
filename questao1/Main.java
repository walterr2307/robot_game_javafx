import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        int largura = 720, altura = (int) (largura / 1.5), x_blocos[], y_blocos[];
        String cores[] = { "cinza", "vermelho", "azul" };
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);
        Button left, up, down, right;
        Rectangle blocos[][];
        Tabuleiro tabuleiro = new Tabuleiro(root, largura, altura);
        Robo robo;
        Comida comida;

        // Configurando o tabuleiro
        blocos = tabuleiro.getBlocos();
        x_blocos = tabuleiro.getXBlocos();
        y_blocos = tabuleiro.getYBlocos();
        left = tabuleiro.getLeft();
        up = tabuleiro.getUp();
        down = tabuleiro.getDown();
        right = tabuleiro.getRight();

        // ESSE BLOCO DEVE SER MODIFICADO COM O MENU
        int teste = (int) (Math.random() * 3);
        int x_comida = (int) (Math.random() * 3 + 1), y_comida = (int) (Math.random() * 3 + 1);

        robo = new Robo(cores[teste], root, largura, altura, x_blocos, y_blocos);
        comida = new Comida(root, largura, altura, x_blocos[x_comida], y_blocos[y_comida]);
        // FIM DO BLOCO

        // Ajustando os botões
        left.setOnAction(event -> {
            try {
                moverLeft(root, robo, comida, blocos, largura);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        up.setOnAction(event -> {
            try {
                moverUp(root, robo, comida, blocos, largura);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        down.setOnAction(event -> {
            try {
                moverDown(root, robo, comida, blocos, largura);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        right.setOnAction(event -> {
            try {
                moverRight(root, robo, comida, blocos, largura);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Configurando a cena
        root.setStyle("-fx-background-color: #C0C0C0;");
        stage.setScene(scene);
        stage.setTitle("Jogo do Robô");
        stage.show();

        // AVISO!!!!!
        // CRIAR LÓGICA DO GAME OVER
    }

    private void moverLeft(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura)
            throws FileNotFoundException {
        try {
            root.setDisable(true); // Desabilita o root
            if (robo.getPosX() - 1 < 0)
                throw new MovimentoInvalidoException();

            robo.mover(0);
        } catch (MovimentoInvalidoException e) {
            e.mostrarMensagem(root, robo.getImg(), comida.getImg(), blocos, largura);
        } finally {
            root.setDisable(false); // Reabilita o root
        }
    }

    private void moverUp(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura)
            throws FileNotFoundException {
        try {
            root.setDisable(true); // Desabilita o root
            if (robo.getPosY() - 1 < 0)
                throw new MovimentoInvalidoException();

            robo.mover(1);
        } catch (MovimentoInvalidoException e) {
            e.mostrarMensagem(root, robo.getImg(), comida.getImg(), blocos, largura);
        } finally {
            root.setDisable(false); // Reabilita o root
        }
    }

    private void moverDown(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura)
            throws FileNotFoundException {
        try {
            root.setDisable(true); // Desabilita o root
            if (robo.getPosY() + 1 >= 4)
                throw new MovimentoInvalidoException();

            robo.mover(2);
        } catch (MovimentoInvalidoException e) {
            e.mostrarMensagem(root, robo.getImg(), comida.getImg(), blocos, largura);
        } finally {
            root.setDisable(false); // Reabilita o root
        }
    }

    private void moverRight(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura)
            throws FileNotFoundException {
        try {
            root.setDisable(true); // Desabilita o root
            if (robo.getPosX() + 1 >= 4)
                throw new MovimentoInvalidoException();

            robo.mover(3);
        } catch (MovimentoInvalidoException e) {
            e.mostrarMensagem(root, robo.getImg(), comida.getImg(), blocos, largura);
        } finally {
            root.setDisable(false); // Reabilita o root
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}