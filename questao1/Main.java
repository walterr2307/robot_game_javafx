import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private int largura, altura, indice_cor, x_comida, y_comida;
    private Button botao_fechar;

    public Main() {
        this.largura = 720;
        this.altura = (int) (this.largura / 1.5);
        this.botao_fechar = new Button("OK");
    }

    private void configurarMenu() {
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);
        Stage stage = new Stage();

        // Cria três botões de menu para seleção de cor, posição X e posição Y
        MenuButton menu_cor = new MenuButton("Escolha a cor do jogador: ");
        MenuButton menu_x_comida = new MenuButton("Escolha a posição X: ");
        MenuButton menu_y_comida = new MenuButton("Escolha a posição Y: ");

        // Cria itens de menu personalizados para as opções de cor
        CustomMenuItem cinza = createCustomMenuItem("Cinza", this.largura / 2);
        CustomMenuItem vermelho = createCustomMenuItem("Vermelho", this.largura / 2);
        CustomMenuItem azul = createCustomMenuItem("Azul", this.largura / 2);

        // Cria itens de menu personalizados para as posições X
        CustomMenuItem pos1_x = createCustomMenuItem("1", this.largura / 2);
        CustomMenuItem pos2_x = createCustomMenuItem("2", this.largura / 2);
        CustomMenuItem pos3_x = createCustomMenuItem("3", this.largura / 2);

        // Cria itens de menu personalizados para as posições Y
        CustomMenuItem pos1_y = createCustomMenuItem("1", this.largura / 2);
        CustomMenuItem pos2_y = createCustomMenuItem("2", this.largura / 2);
        CustomMenuItem pos3_y = createCustomMenuItem("3", this.largura / 2);

        // Adiciona ação de seleção aos itens de cor
        cinza.setOnAction(e -> {
            indice_cor = 0;
            menu_cor.setText("Cor selecionada: Cinza");
        });
        vermelho.setOnAction(e -> {
            indice_cor = 1;
            menu_cor.setText("Cor selecionada: Vermelho");
        });
        azul.setOnAction(e -> {
            indice_cor = 2;
            menu_cor.setText("Cor selecionada: Azul");
        });

        // Adiciona ação de seleção aos itens de posição X
        pos1_x.setOnAction(e -> {
            x_comida = 1;
            menu_x_comida.setText("Posição X selecionada: 1");
        });
        pos2_x.setOnAction(e -> {
            x_comida = 2;
            menu_x_comida.setText("Posição X selecionada: 2");
        });
        pos3_x.setOnAction(e -> {
            x_comida = 3;
            menu_x_comida.setText("Posição X selecionada: 3");
        });

        // Adiciona ação de seleção aos itens de posição Y
        pos1_y.setOnAction(e -> {
            y_comida = 1;
            menu_y_comida.setText("Posição Y selecionada: 1");
        });
        pos2_y.setOnAction(e -> {
            y_comida = 2;
            menu_y_comida.setText("Posição Y selecionada: 2");
        });
        pos3_y.setOnAction(e -> {
            y_comida = 3;
            menu_y_comida.setText("Posição Y selecionada: 3");
        });

        // Configura o botão de menu para seleção de cor
        menu_cor.setPrefSize(this.largura / 2, this.altura / 10);
        menu_cor.setLayoutX(this.largura / 4);
        menu_cor.setLayoutY(this.altura * 0.2);
        menu_cor.getItems().addAll(cinza, vermelho, azul);
        root.getChildren().add(menu_cor);

        // Configura o botão de menu para seleção da posição X
        menu_x_comida.setPrefSize(this.largura / 2, this.altura / 10);
        menu_x_comida.setLayoutX(this.largura / 4);
        menu_x_comida.setLayoutY(this.altura * 0.4);
        menu_x_comida.getItems().addAll(pos1_x, pos2_x, pos3_x);
        root.getChildren().add(menu_x_comida);

        // Configura o botão de menu para seleção da posição Y
        menu_y_comida.setPrefSize(this.largura / 2, this.altura / 10);
        menu_y_comida.setLayoutX(this.largura / 4);
        menu_y_comida.setLayoutY(this.altura * 0.6);
        menu_y_comida.getItems().addAll(pos1_y, pos2_y, pos3_y);
        root.getChildren().add(menu_y_comida);

        // Configura o botão "OK" para fechar a janela e processar as seleções
        this.botao_fechar.setPrefSize(this.largura / 4, this.altura / 10);
        this.botao_fechar.setLayoutX(this.largura / 2 - this.botao_fechar.getPrefWidth() / 2);
        this.botao_fechar.setLayoutY(this.altura * 0.8);
        this.botao_fechar.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        root.getChildren().add(this.botao_fechar);

        // Configurando a cena
        root.setStyle("-fx-background-color: #C0C0C0;");
        stage.setTitle("Escolha a cor e posição do jogador: ");
        stage.setScene(scene);
        stage.show();

        // Adiciona ação ao botão para iniciar o jogo
        this.botao_fechar.setOnAction(e -> {
            stage.close();
            try {
                colocarTelaJogo(new Stage());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    private CustomMenuItem createCustomMenuItem(String text, double width) {
        Label label = new Label(text);
        label.setPrefWidth(width - 20); // Ajustar a largura menos um valor para espaçamento
        label.setStyle("-fx-padding: 5px;"); // Adicionar padding para melhor visualização
        return new CustomMenuItem(label, false);
    }

    private void colocarTelaJogo(Stage primary_stage) throws FileNotFoundException {
        int x_blocos[], y_blocos[];
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

        // Inicializando o jogo
        robo = new Robo(cores[indice_cor], root, largura, altura, x_blocos, y_blocos);
        comida = new Comida(root, largura, altura, x_blocos[x_comida], y_blocos[y_comida], x_comida, y_comida);

        // Ajustando os botões
        left.setOnAction(event -> {
            try {
                moverLeft(root, robo, comida, blocos, largura, primary_stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        up.setOnAction(event -> {
            try {
                moverUp(root, robo, comida, blocos, largura, primary_stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        down.setOnAction(event -> {
            try {
                moverDown(root, robo, comida, blocos, largura, primary_stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        right.setOnAction(event -> {
            try {
                moverRight(root, robo, comida, blocos, largura, primary_stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Configurando a cena
        root.setStyle("-fx-background-color: #C0C0C0;");
        primary_stage.setScene(scene);
        primary_stage.setTitle("Jogo do Robô");
        primary_stage.show();

    }

    @Override
    public void start(Stage primary_stage) {
        configurarMenu();
    }

    private void moverLeft(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura, Stage stage)
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
            this.verificarGameOver(stage, robo, comida);
        }
    }

    private void moverUp(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura, Stage stage)
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
            this.verificarGameOver(stage, robo, comida);
        }
    }

    private void moverDown(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura, Stage stage)
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
            this.verificarGameOver(stage, robo, comida);
        }
    }

    private void moverRight(Pane root, Robo robo, Comida comida, Rectangle[][] blocos, int largura, Stage stage)
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
            this.verificarGameOver(stage, robo, comida);
        }
    }

    private void verificarGameOver(Stage stage, Robo robo, Comida comida) {
        GameOverScreen game_over = new GameOverScreen();

        if (robo.getPosX() == comida.getPosX() && robo.getPosY() == comida.getPosY()) {
            stage.close();
            game_over.mostrarTela();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}