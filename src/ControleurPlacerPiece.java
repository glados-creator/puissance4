import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ControleurPlacerPiece implements EventHandler<MouseEvent> {

    private puissance4 app;
    private int colonne;
    private GridPane grille;
    private model jeu;

    public ControleurPlacerPiece(puissance4 app, int colonne, GridPane grille, model jeu) {
        this.app = app;
        this.colonne = colonne;
        this.grille = grille;
        this.jeu = jeu;
    }

    @Override
    public void handle(MouseEvent event) {
        boolean joueur1 = app.isJoueur1(); // Récupérer l'état du joueur courant depuis l'application principale
        int ligne = jeu.placerPiece(colonne, joueur1 ? 1 : 2); // 1 pour joueur 1 (rouge), 2 pour joueur 2 (jaune)
        if (ligne == -1) {
            // Colonne pleine, ignorer le clic
            return;
        }

        // Mise à jour de l'interface graphique
        Pane cellule = (Pane) getNodeFromGridPane(grille, colonne, ligne);
        if (cellule != null) {
            Circle cercle = new Circle(37);
            cercle.setFill(joueur1 ? Color.RED : Color.YELLOW); // Rouge pour joueur 1, Jaune pour joueur 2
            cercle.setStroke(Color.BLUE);
            cercle.setStrokeWidth(2);
            cercle.setLayoutX(40);
            cercle.setLayoutY(40);
            cellule.getChildren().add(cercle);
        }

        // Vérifier la condition de victoire
        if (jeu.checkVictory(colonne, ligne)) {
            // Afficher un message de victoire et désactiver les clics
            String winner = joueur1 ? "Joueur 1 (Rouge)" : "Joueur 2 (Jaune)";
            app.getChrono1().stop();
            app.getChrono2().stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, winner + " a gagné !");
            alert.setHeaderText("Félicitations !");
            alert.setTitle("Puissance 4");
            alert.showAndWait().ifPresent(response -> app.modeAccueil());
            grille.setDisable(true);
        } else {
            // Alternance des joueurs
            app.switchJoueur();
        }
    }

    /**
     * Helper method to get a node from a GridPane by its column and row indices
     */
    private Pane getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Pane) node;
            }
        }
        return null;
    }
}
