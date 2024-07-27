import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private int ind_cor1, ind_cor2, x_comida, y_comida;
    private Timeline timeline;

    public void start(Stage primaryStage) throws FileNotFoundException {
        // Define o título da janela principal
        primaryStage.setTitle("Configuração do Jogo");

        // Cria um rótulo (label) para o título da configuração do jogo
        Label label = new Label("Configuração do Jogo");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Define a fonte do rótulo

        // Cria uma ComboBox para escolher a cor do jogador 1
        ComboBox<String> jogador1Box = new ComboBox<>();
        jogador1Box.getItems().addAll("Cinza", "Vermelho", "Azul"); // Adiciona opções à ComboBox
        jogador1Box.setPromptText("Escolha a cor do jogador 1"); // Define o texto de prompt
        jogador1Box.setMaxWidth(200); // Define a largura máxima da ComboBox

        // Cria uma ComboBox para escolher a cor do jogador 2
        ComboBox<String> jogador2Box = new ComboBox<>();
        jogador2Box.getItems().addAll("Cinza", "Vermelho", "Azul"); // Adiciona opções à ComboBox
        jogador2Box.setPromptText("Escolha a cor do jogador 2"); // Define o texto de prompt
        jogador2Box.setMaxWidth(200); // Define a largura máxima da ComboBox

        // Cria um rótulo para a posição da comida
        Label posicaoLabel = new Label("Posição da Comida:");
        posicaoLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); // Define a fonte do rótulo

        // Cria um campo de texto para a posição X da comida
        TextField xField = new TextField();
        xField.setPromptText("Posição X"); // Define o texto de prompt
        xField.setMaxWidth(200); // Define a largura máxima do campo de texto

        // Cria um campo de texto para a posição Y da comida
        TextField yField = new TextField();
        yField.setPromptText("Posição Y"); // Define o texto de prompt
        yField.setMaxWidth(200); // Define a largura máxima do campo de texto

        // Cria um botão "OK" para confirmar as configurações
        Button okBtn = new Button("OK");
        okBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Define a fonte do botão
        okBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); // Define o estilo do botão
        // Define o que acontece quando o botão é clicado
        okBtn.setOnAction(event -> {
            String jogador1 = jogador1Box.getValue(); // Obtém a cor escolhida para o jogador 1
            String jogador2 = jogador2Box.getValue(); // Obtém a cor escolhida para o jogador 2
            String posX = xField.getText(); // Obtém a posição X da comida
            String posY = yField.getText(); // Obtém a posição Y da comida

            // Converte as posições X e Y para inteiros
            x_comida = Integer.parseInt(posX);
            y_comida = Integer.parseInt(posY);

            // Verifica se os jogadores foram escolhidos e são diferentes
            if (jogador1 != null && jogador2 != null && !jogador1.equals(jogador2)) {
                // Verifica se a posição da comida está dentro dos limites válidos
                if (x_comida > 0 && x_comida < 4 && y_comida > 0 && y_comida < 4) {
                    // Define os índices das cores dos jogadores com base nas escolhas
                    if (jogador1.equals("Cinza"))
                        ind_cor1 = 0;
                    else if (jogador1.equals("Vermelho"))
                        ind_cor1 = 1;
                    else
                        ind_cor1 = 2;

                    if (jogador2.equals("Cinza"))
                        ind_cor2 = 0;
                    else if (jogador2.equals("Vermelho"))
                        ind_cor2 = 1;
                    else
                        ind_cor2 = 2;

                    // Fecha a janela de configuração e inicia o jogo
                    primaryStage.close();
                    this.comecarJogo();
                } else {
                    // Exibe um alerta se a posição da comida estiver fora dos limites
                    Alert alert = new Alert(Alert.AlertType.WARNING,
                            "Por favor, insira uma posição válida para a comida.");
                    alert.showAndWait();
                }
            } else {
                // Exibe um alerta se os jogadores não forem diferentes
                Alert alert = new Alert(Alert.AlertType.WARNING, "Escolha jogadores diferentes.");
                alert.showAndWait();
            }
        });

        // Cria um VBox para agrupar as ComboBoxes dos jogadores
        VBox jogadoresBox = new VBox(10);
        jogadoresBox.setAlignment(Pos.TOP_CENTER); // Define o alinhamento dos itens dentro do VBox
        jogadoresBox.getChildren().addAll(jogador1Box, jogador2Box); // Adiciona as ComboBoxes ao VBox

        // Cria um VBox para agrupar os campos de texto da posição da comida
        VBox comidaBox = new VBox(10);
        comidaBox.setAlignment(Pos.TOP_CENTER); // Define o alinhamento dos itens dentro do VBox
        comidaBox.getChildren().addAll(posicaoLabel, xField, yField); // Adiciona o rótulo e campos de texto ao VBox

        // Cria o VBox principal que irá conter o título e as seções dos jogadores e
        // comida
        VBox mainBox = new VBox(20);
        mainBox.setAlignment(Pos.TOP_CENTER); // Define o alinhamento dos itens dentro do VBox
        mainBox.setPadding(new Insets(20)); // Define o preenchimento ao redor do VBox
        mainBox.getChildren().addAll(label, jogadoresBox, comidaBox); // Adiciona o título, VBox dos jogadores e VBox da
                                                                      // comida ao VBox principal

        // Cria um BorderPane para organizar os elementos da interface
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mainBox); // Define o VBox principal como o centro do BorderPane
        borderPane.setBottom(okBtn); // Define o botão "OK" como a parte inferior do BorderPane
        BorderPane.setAlignment(okBtn, Pos.CENTER); // Alinha o botão no centro da parte inferior
        BorderPane.setMargin(okBtn, new Insets(20)); // Define a margem ao redor do botão

        // Cria a cena e define suas dimensões
        Scene scene = new Scene(borderPane, 300, 310);
        primaryStage.setScene(scene); // Define a cena na janela principal
        primaryStage.show(); // Exibe a janela principal
    }

    private void comecarJogo() {
        int largura = 720, altura = (int) (largura / 1.5), x_blocos[], y_blocos[];
        String cores[] = { "cinza", "vermelho", "azul" };
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);
        Stage stage = new Stage();

        try {
            Tabuleiro tabuleiro = new Tabuleiro(root, largura, altura);
            x_blocos = tabuleiro.getXBlocos();
            y_blocos = tabuleiro.getYBlocos();

            Robo robo1 = new Robo(cores[ind_cor1], root, largura, altura, x_blocos, y_blocos);
            Robo robo2 = new Robo(cores[ind_cor2], root, largura, altura, x_blocos, y_blocos);
            Comida comida = new Comida(root, largura, altura, x_blocos[x_comida], y_blocos[y_comida], x_comida,
                    y_comida);

            // Configurando a cena
            root.setStyle("-fx-background-color: #C0C0C0;");
            stage.setScene(scene);
            stage.setTitle("Jogo do Robô");
            stage.show();

            // Iniciar o evento que dura enquanto a janela estiver aberta
            eventoMover(robo1, robo2, comida, stage);

            // Adicionar um event handler para parar o evento quando a janela for fechada
            stage.setOnCloseRequest(event -> pararEvento());

        } catch (FileNotFoundException e) {
            // Se ocorrer uma FileNotFoundException, exiba uma mensagem de erro para o
            // usuário
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Arquivo");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Um arquivo necessário não foi encontrado. Por favor, verifique os arquivos e tente novamente.");
            alert.showAndWait();
        }
    }

    private void eventoMover(Robo robo1, Robo robo2, Comida comida, Stage stage) throws FileNotFoundException {

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
                e.invalidarMovimento(robo1);
            } finally {
                this.verificarGameOver(robo1, robo2, comida, 1, stage);
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
                e.invalidarMovimento(robo2);
            } finally {
                this.verificarGameOver(robo1, robo2, comida, 2, stage);
            }
        }));

        // Define o número de ciclos da Timeline como indefinido (infinito)
        this.timeline.setCycleCount(Timeline.INDEFINITE);

        // Inicia a execução da Timeline
        this.timeline.play();
    }

    private void pararEvento() {
        if (this.timeline != null) {
            this.timeline.stop();
        }
    }

    private void verificarGameOver(Robo robo1, Robo robo2, Comida comida, int robo_vencedor, Stage stage) {
        if (robo1.getPosX() == comida.getPosX() && robo1.getPosY() == comida.getPosY()
                || robo2.getPosX() == comida.getPosX() && robo2.getPosY() == comida.getPosY()) {

            GameOverScreen game_over = new GameOverScreen(robo1, robo2, robo_vencedor);
            game_over.mostrarTela();
            stage.close();
            robo1.voltarInicio();
            robo2.voltarInicio();
            robo1.bloquearMovimento();
            robo2.bloquearMovimento();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
