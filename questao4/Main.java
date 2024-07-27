import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private int ind_cor1, ind_cor2, x_comida, y_comida;
    private boolean parar_mostrar_tela;
    private String pos_comida;
    private Timeline timeline;
    private ArrayList<Integer> x_obs, y_obs;
    private ArrayList<String> tipos_obs, pos_obstaculos;
    private ArrayList<Obstaculo> obs;

    public Main() {
        this.parar_mostrar_tela = false;
        this.x_obs = new ArrayList<Integer>();
        this.y_obs = new ArrayList<Integer>();
        this.obs = new ArrayList<Obstaculo>();
        this.tipos_obs = new ArrayList<String>();
        this.pos_obstaculos = new ArrayList<String>();
    }

    public void start(Stage primary_stage) {
        // Define o título da janela principal
        primary_stage.setTitle("Configuração do Jogo");

        // Cria e estiliza o rótulo do título
        Label labelTitulo = new Label("Configuração do Jogo");
        labelTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Cria e configura a ComboBox para escolher a cor do jogador 1
        ComboBox<String> jogador1Box = new ComboBox<>();
        jogador1Box.getItems().addAll("Cinza", "Vermelho", "Azul");
        jogador1Box.setPromptText("Escolha a cor do jogador 1");
        jogador1Box.setMaxWidth(200);

        // Cria e configura a ComboBox para escolher a cor do jogador 2
        ComboBox<String> jogador2Box = new ComboBox<>();
        jogador2Box.getItems().addAll("Cinza", "Vermelho", "Azul");
        jogador2Box.setPromptText("Escolha a cor do jogador 2");
        jogador2Box.setMaxWidth(200);

        // Cria e estiliza o rótulo para a posição da comida
        Label labelPosicaoComida = new Label("Posição da Comida:");
        labelPosicaoComida.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Cria e configura o campo de texto para a posição X da comida
        TextField campoPosX = new TextField();
        campoPosX.setPromptText("Posição X");
        campoPosX.setMaxWidth(200);

        // Cria e configura o campo de texto para a posição Y da comida
        TextField campoPosY = new TextField();
        campoPosY.setPromptText("Posição Y");
        campoPosY.setMaxWidth(200);

        // Cria e estiliza o rótulo para a adição de obstáculos
        Label labelObstaculos = new Label("Adicionar Obstáculos:");
        labelObstaculos.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Cria e configura a ComboBox para escolher o tipo de obstáculo
        ComboBox<String> obstaculosBox = new ComboBox<>();
        obstaculosBox.getItems().addAll("Bomba", "Rocha");
        obstaculosBox.setPromptText("Escolha o obstáculo");
        obstaculosBox.setMaxWidth(200);

        // Cria e configura o campo de texto para a posição X do obstáculo
        TextField campoObstaculoX = new TextField();
        campoObstaculoX.setPromptText("Posição X");
        campoObstaculoX.setMaxWidth(200);

        // Cria e configura o campo de texto para a posição Y do obstáculo
        TextField campoObstaculoY = new TextField();
        campoObstaculoY.setPromptText("Posição Y");
        campoObstaculoY.setMaxWidth(200);

        // Cria e configura o botão para adicionar obstáculos
        Button botaoAdicionarObstaculo = new Button("Adicionar Obstáculo");
        botaoAdicionarObstaculo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        botaoAdicionarObstaculo.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        botaoAdicionarObstaculo.setOnAction(event -> {
            // Ação do botão de adicionar obstáculo
            String tipo = obstaculosBox.getValue();
            String posX = campoObstaculoX.getText();
            String posY = campoObstaculoY.getText();
            String posicao = posX + "," + posY;

            if (tipo != null && !posX.isEmpty() && !posY.isEmpty()) {
                if (posicao.equals(pos_comida)) {
                    // Alerta se o obstáculo estiver na mesma posição que a comida
                    Alert alerta = new Alert(Alert.AlertType.WARNING,
                            "O obstáculo não pode estar na mesma posição que a comida. Por favor, escolha outra posição.");
                    alerta.showAndWait();
                } else if (pos_obstaculos.contains(posicao)) {
                    // Alerta se já houver um obstáculo na posição escolhida
                    Alert alerta = new Alert(Alert.AlertType.WARNING,
                            "Há um objeto nesta posição. Por favor, escolha outra posição.");
                    alerta.showAndWait();
                } else {
                    // Adiciona o obstáculo na lista de posições de obstáculos
                    pos_obstaculos.add(posicao);
                    campoObstaculoX.clear();
                    campoObstaculoY.clear();
                    obstaculosBox.setValue(null);
                    tipos_obs.add(tipo);
                    x_obs.add(Integer.parseInt(posX));
                    y_obs.add(Integer.parseInt(posY));
                }
            } else {
                // Alerta se os campos do obstáculo não estiverem preenchidos
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Preencha todas as informações do obstáculo.");
                alerta.showAndWait();
            }
        });

        // Cria e configura o botão OK para finalizar a configuração do jogo
        Button botaoOK = new Button("OK");
        botaoOK.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        botaoOK.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        botaoOK.setOnAction(event -> {
            // Ação do botão OK
            String jogador1 = jogador1Box.getValue();
            String jogador2 = jogador2Box.getValue();
            String posX = campoPosX.getText();
            String posY = campoPosY.getText();
            String posicao = posX + "," + posY;

            if (jogador1 != null && jogador2 != null && !jogador1.equals(jogador2)) {
                if (!posX.isEmpty() && !posY.isEmpty()) {
                    if (pos_obstaculos.contains(posicao)) {
                        // Alerta se a comida estiver na mesma posição que um obstáculo
                        Alert alerta = new Alert(Alert.AlertType.WARNING,
                                "A comida não pode estar na mesma posição que um obstáculo. Por favor, escolha outra posição.");
                        alerta.showAndWait();
                    } else {
                        // Define a posição da comida e inicia o jogo
                        pos_comida = posicao;
                        x_comida = Integer.parseInt(posX);
                        y_comida = Integer.parseInt(posY);

                        // Lugar para adicionar a lógica do iniciar do jogo
                        primary_stage.close();
                        try {
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

                            this.comecarJogo();
                        } catch (FileNotFoundException e) {
                            Alert alerta = new Alert(Alert.AlertType.ERROR,
                                    "Arquivo não encontrado: " + e.getMessage());
                            alerta.showAndWait();
                        }
                    }
                } else {
                    // Alerta se os campos da posição da comida não estiverem preenchidos
                    Alert alerta = new Alert(Alert.AlertType.WARNING, "Por favor, insira a posição da comida.");
                    alerta.showAndWait();
                }
            } else {
                // Alerta se os jogadores não estiverem escolhidos ou forem iguais
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Escolha jogadores diferentes.");
                alerta.showAndWait();
            }
        });

        // Cria uma caixa vertical para os componentes de seleção dos jogadores
        VBox caixaJogadores = new VBox(10);
        caixaJogadores.setAlignment(Pos.TOP_CENTER);
        caixaJogadores.getChildren().addAll(jogador1Box, jogador2Box);

        // Cria uma caixa vertical para os componentes de posição da comida
        VBox caixaComida = new VBox(10);
        caixaComida.setAlignment(Pos.TOP_CENTER);
        caixaComida.getChildren().addAll(labelPosicaoComida, campoPosX, campoPosY);

        // Cria uma caixa vertical para os componentes de adição de obstáculos
        VBox caixaObstaculos = new VBox(10);
        caixaObstaculos.setAlignment(Pos.TOP_CENTER);
        caixaObstaculos.getChildren().addAll(labelObstaculos, obstaculosBox, campoObstaculoX, campoObstaculoY,
                botaoAdicionarObstaculo);

        // Cria uma caixa vertical principal para todos os componentes
        VBox caixaPrincipal = new VBox(20);
        caixaPrincipal.setAlignment(Pos.TOP_CENTER);
        caixaPrincipal.setPadding(new Insets(20));
        caixaPrincipal.getChildren().addAll(labelTitulo, caixaJogadores, caixaComida, caixaObstaculos, botaoOK);

        // Define a cena principal e a adiciona ao palco
        Scene cenaPrincipal = new Scene(caixaPrincipal, 300, 600);
        primary_stage.setScene(cenaPrincipal);
        primary_stage.show();
    }

    private void comecarJogo() throws FileNotFoundException {
        int i, largura = 720, altura = (int) (largura / 1.5), x_blocos[], y_blocos[];
        String cores[] = { "cinza", "vermelho", "azul" };
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);
        Stage stage = new Stage();
        Tabuleiro tabuleiro = new Tabuleiro(root, largura, altura);
        Obstaculo ob;
        Robo robo1;
        RoboInteligente robo2;

        Comida comida;

        x_blocos = tabuleiro.getXBlocos();
        y_blocos = tabuleiro.getYBlocos();

        for (i = 0; i < tipos_obs.size(); i++) {
            if (tipos_obs.get(i).equals("Bomba")) {
                ob = new Bomba(x_blocos[x_obs.get(i)], y_blocos[y_obs.get(i)], x_obs.get(i), y_obs.get(i), root,
                        largura);
            } else {
                ob = new Rocha(x_blocos[x_obs.get(i)], y_blocos[y_obs.get(i)], x_obs.get(i), y_obs.get(i), root,
                        largura);
            }

            this.obs.add(ob);
        }

        robo1 = new Robo(cores[ind_cor1], root, largura, altura, x_blocos, y_blocos);
        robo2 = new RoboInteligente(cores[ind_cor2], root, largura, altura, x_blocos, y_blocos);
        comida = new Comida(root, largura, altura, x_blocos[x_comida], y_blocos[y_comida], x_comida, y_comida);

        // Configurando a cena
        root.setStyle("-fx-background-color: #C0C0C0;");
        stage.setScene(scene);
        stage.setTitle("Jogo do Robô");
        stage.show();

        // Iniciar o evento que dura enquanto a janela estiver aberta
        eventoMover(stage, robo1, robo2, comida);

    }

    private void eventoMover(Stage stage, Robo robo1, RoboInteligente robo2, Comida comida)
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

                for (Obstaculo ob : this.obs)
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
            } finally {
                this.finalizarJogo(stage, robo1, robo2, comida, 1);
            }

            try {
                tipo_mov2 = robo2.verificarRegistroMov(tipo_mov2);

                // Verificando se o robô passou por um obstáculo
                if (robo2.getMovLiberado() == false)
                    robo2.setMovLiberado(true);

                for (Obstaculo ob : this.obs)
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
                finalizarJogo(stage, robo1, robo2, comida, 2);
            }
        }));

        // Define o número de ciclos da Timeline como indefinido (infinito)
        this.timeline.setCycleCount(Timeline.INDEFINITE);

        // Inicia a execução da Timeline
        this.timeline.play();
    }

    private void finalizarJogo(Stage stage, Robo robo1, Robo robo2, Comida comida, int robo_vencedor) {
        if (robo1.getPosX() == comida.getPosX() && robo1.getPosY() == comida.getPosY()
                || robo2.getPosX() == comida.getPosX() && robo2.getPosY() == comida.getPosY()) {

            GameOverScreen game_over = new GameOverScreen(robo1, robo2, robo_vencedor);
            game_over.mostrarTela();
            stage.close();
            robo1.voltarInicio();
            robo2.voltarInicio();
            robo1.bloquearMovimento();
            robo2.bloquearMovimento();
        } else if (robo1.getVivo() == false && robo2.getVivo() == false && this.parar_mostrar_tela == false) {
            GameOverScreen game_over = new GameOverScreen(robo1, robo2, 0);
            game_over.mostrarTela();
            stage.close();
            this.parar_mostrar_tela = true;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
