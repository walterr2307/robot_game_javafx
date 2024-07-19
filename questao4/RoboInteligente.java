import java.io.FileNotFoundException;
import javafx.scene.layout.Pane;

public class RoboInteligente extends Robo {
    private boolean movs_registrados[][][];

    public RoboInteligente(String cor, Pane root, int largura, int altura, int[] x_blocos, int[] y_blocos)
            throws FileNotFoundException {
        super(cor, root, largura, altura, x_blocos, y_blocos);
        this.movs_registrados = this.definirMovimentosRegistrados();
    }

    private boolean[][][] definirMovimentosRegistrados() {
        int i, j, k;
        boolean movs_registrados[][][] = new boolean[4][4][4];

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                for (k = 0; k < 4; k++) {
                    movs_registrados[i][j][k] = false;
                }
            }
        }

        return movs_registrados;
    }

    public int verificarRegistroMov(int tipo_mov) {
        while (this.movs_registrados[this.x_atual][this.y_atual][tipo_mov] == true)
            tipo_mov = (int) (Math.random() * 4);

        return tipo_mov;
    }

    public void registrarMovimento(int tipo_mov) {
        this.movs_registrados[this.x_atual][this.y_atual][tipo_mov] = true;
    }

}
