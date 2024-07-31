import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverScreen extends StackPane {

    public GameOverScreen() {
        // Carregar a imagem de fundo
        Image backgroundImage = new Image("imagens/espaco_sideral.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(400); // Ajuste de largura da imagem de fundo
        backgroundView.setFitHeight(300); // Ajuste de altura da imagem de fundo

        // Texto "Game Over"
        Text gameOverText = new Text("YOU WIN!");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gameOverText.setFill(Color.RED);

        // Adicionar a imagem de fundo e o texto ao StackPane
        getChildren().addAll(backgroundView, gameOverText);
        setAlignment(gameOverText, Pos.CENTER);
    }

    public void mostrarTela() {
        Scene scene = new Scene(this, 400, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Game Over!");
        stage.show();
    }
}