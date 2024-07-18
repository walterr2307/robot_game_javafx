/*import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;*/

public class MovimentoInvalidoException extends Exception {

    /*
     * public void mostrarMensagem(Pane root, ImageView img_robo1, ImageView
     * img_robo2, ImageView img_comida,
     * Rectangle[][] blocos, int largura)
     * throws FileNotFoundException {
     * 
     * String estilo = "-fx-background-color: transparent; -fx-text-fill: yellow;";
     * Button mov_invalido = new Button("Movimento Inválido!");
     * Timeline timeline;
     * 
     * mov_invalido.setPrefSize(largura * 0.4, largura * 0.4);
     * mov_invalido.setLayoutX(largura * 0.3);
     * mov_invalido.setLayoutY(largura / 5);
     * mov_invalido.setStyle(estilo);
     * mov_invalido.setFont(Font.font("Times New Roman", largura * 0.04));
     * 
     * // Adicionar o botão ao root
     * root.getChildren().add(mov_invalido);
     * 
     * // Desativar o root
     * root.setDisable(true);
     * 
     * // Esconder imagens e blocos
     * img_robo1.setVisible(false);
     * img_robo2.setVisible(false);
     * img_comida.setVisible(false);
     * for (Rectangle[] linha : blocos) {
     * for (Rectangle bloco : linha) {
     * bloco.setVisible(false);
     * }
     * }
     * 
     * // Timeline para exibir o botão por 1.5 segundos e depois escondê-lo
     * timeline = new Timeline(
     * new KeyFrame(Duration.seconds(1.5), event -> {
     * mov_invalido.setVisible(false);
     * img_robo1.setVisible(true);
     * img_robo2.setVisible(true);
     * img_comida.setVisible(true);
     * for (Rectangle[] linha : blocos) {
     * for (Rectangle bloco : linha) {
     * bloco.setVisible(true);
     * }
     * }
     * root.getChildren().remove(mov_invalido);
     * // Reativar o root
     * root.setDisable(false);
     * }));
     * 
     * timeline.setCycleCount(1);
     * timeline.play();
     * }
     */

    public void invalidarMovimento(Robo robo1, Robo robo2) {
        robo1.addMovInvalido();
        robo2.addMovInvalido();
    }

}