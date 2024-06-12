import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class ControleurPlacerPiece implements EventHandler<MouseEvent> {
    private puissance4 appli;
    private int col;
    private GridPane grille;
    private model jeu;
    private boolean joueur1;

    public ControleurPlacerPiece(puissance4 appli, int col, GridPane grille, model jeu, boolean joueur1) {
        this.appli = appli;
        this.col = col;
        this.grille = grille;
        this.jeu = jeu;
        this.joueur1 = joueur1;
    }

    @Override
    public void handle(MouseEvent event) {
        short equipe = joueur1 ? (short)1 : (short)2; // Déplacer la déclaration ici

        for (int row = jeu.getheight() - 1; row >= 0; row--) {
            if (jeu.get((short)col, (short)row) == 0) {
                // Vérifier si la cellule précédente dans la même colonne contient déjà un jeton de la même équipe
                if (row < jeu.getheight() - 1 && jeu.get((short)col, (short)(row + 1)) == equipe) {
                    // Si oui, ne rien faire et sortir de la méthode
                    return;
                }
                
                jeu.set((short)col, (short)row, equipe); // Utiliser la variable equipe ici

                Pane plateau = (Pane) getPosition(grille, col, row);
                Circle cercle = new Circle(35);

                if (joueur1) {
                    cercle.setFill(Color.RED);
                } else {
                    cercle.setFill(Color.YELLOW);
                }

                cercle.setStroke(Color.BLUE);
                cercle.setStrokeWidth(2);
                cercle.setLayoutX(40);
                cercle.setLayoutY(40);

                plateau.getChildren().add(cercle);

                if (jeu.win()) {
                    System.out.println("Le joueur " + equipe + " a gagné !");
                    // Gérer la fin de la partie
                }

                joueur1 = !joueur1; // Changer de joueur
                break;
            }
        }
    }


    private Pane getPosition(GridPane gridPane, int col, int row) {
        for (javafx.scene.Node pos : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(pos) == col && GridPane.getRowIndex(pos) == row) {
                return (Pane) pos;
            }
        }
        return null;
    }
}
