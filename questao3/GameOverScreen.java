import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverScreen extends StackPane {

    public GameOverScreen(Robo robo1, Robo robo2, int robo_vencedor) {
        // Carregar a imagem de fundo
        Image backgroundImage = new Image("imagens/espaco_sideral.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(400); // Ajuste de largura da imagem de fundo
        backgroundView.setFitHeight(300); // Ajuste de altura da imagem de fundo

        // Texto "Game Over"
        Text gameOverText;
        if (robo_vencedor == 1) {
            gameOverText = new Text("ROBÔ COMUM VENCEU!");
        } else {
            gameOverText = new Text("ROBÔ INTELIGENTE VENCEU!");
        }
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gameOverText.setFill(Color.RED);

        // Informações dos robôs
        Text infoText = new Text(
                String.format("Robô Comum: (%dv, %di)\nRobô Inteligente: (%dv, %di)",
                        robo1.getMovValidos(), robo1.getMovInvalidos(),
                        robo2.getMovValidos(), robo2.getMovInvalidos()));
        infoText.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        infoText.setFill(Color.WHITE);

        // Organizar o layout com VBox
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER); // Centraliza o conteúdo vertical e horizontalmente
        vbox.getChildren().addAll(gameOverText, infoText); // Adiciona os textos ao VBox
        vbox.setPadding(new Insets(20)); // Adiciona um pouco de preenchimento ao redor

        // Adicionar a imagem de fundo e o VBox ao StackPane principal
        getChildren().addAll(backgroundView, vbox);
        StackPane.setAlignment(backgroundView, Pos.CENTER);
        StackPane.setAlignment(vbox, Pos.CENTER);
    }

    public void mostrarTela() {
        Scene scene = new Scene(this, 400, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Game Over!");
        stage.show();
    }
}
