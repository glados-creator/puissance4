import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Platform;

public class puissance4 extends Application {

    private BorderPane panelCentral;

    private Button Accueil;
    private Button Settings;
    private Button BQuitter;
    private Button BJouer;

    private Chronometre chrono1;
    private Chronometre chrono2;

    private GridPane grille;
    private boolean joueur1 = true; // true si c'est le tour du joueur 1 (rouge), false pour le joueur 2 (jaune)
    private model jeu;
    private Label joueurActuelLabel;


    @Override
    public void init() {
        chrono1 = new Chronometre();
        chrono2 = new Chronometre();
        jeu = new model(7, 6); // Initialiser le modèle de jeu avec une grille de 7x6
        joueurActuelLabel = new Label();
        joueurActuelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        joueurActuelLabel.setPadding(new Insets(10));
        updateJoueurActuelLabel();
    }
    
    private Scene laScene() {
        BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.titre());
        panelCentral = new BorderPane();
        fenetre.setCenter(this.panelCentral);
        Scene scene = new Scene(fenetre, 800, 600);
        return scene;
    }

    private Pane titre() {
        BorderPane banniere = new BorderPane();
        banniere.setBackground(new Background(new BackgroundFill(Color.valueOf("#dededf"), CornerRadii.EMPTY, Insets.EMPTY)));
        banniere.setPadding(new Insets(20));
        HBox buttonBox = new HBox(10); 
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        Label Titre = new Label("Puissance 4");
        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 32);
        Titre.setFont(font);

        Accueil = new Button();
        Settings = new Button();

        ImageView accueilImageView = new ImageView(new Image("file:img/home.png"));
        accueilImageView.setFitWidth(32); 
        accueilImageView.setFitHeight(32); 
        Accueil.setGraphic(accueilImageView);

        ImageView settingsImageView = new ImageView(new Image("file:img/parametres.png"));
        settingsImageView.setFitWidth(32); 
        settingsImageView.setFitHeight(32); 
        Settings.setGraphic(settingsImageView);

        banniere.setCenter(Titre);
        BorderPane.setAlignment(Titre, Pos.CENTER);

        BorderPane.setMargin(Titre, new Insets(0, 200, 0, 50));

        buttonBox.getChildren().addAll(Accueil, Settings);
        banniere.setLeft(buttonBox);
        BorderPane.setAlignment(buttonBox, Pos.CENTER_LEFT);

        return banniere;
    }

    public void modeAccueil() {
        panelCentral.getChildren().clear();
        
        Accueil.setDisable(true);
        Settings.setDisable(false);

        Settings.setOnAction(new ControleurParametres(this));

        BQuitter = new Button("Quitter");
        BJouer = new Button("Jouer");

        BQuitter.setMinWidth(150);
        BQuitter.setMinHeight(50);
        BJouer.setMinWidth(150);
        BJouer.setMinHeight(50);

        Font buttonFont = Font.font("Verdana", FontWeight.BOLD, 18);
        BQuitter.setFont(buttonFont);
        BJouer.setFont(buttonFont);

        BQuitter.setStyle("-fx-background-color: #FF8484; -fx-text-fill: white;");
        BJouer.setStyle("-fx-background-color: #318B31; -fx-text-fill: white;");

        BQuitter.setOnAction(new ControleurQuitter(this));
        BJouer.setOnAction(new ControleurJouer(this));

        HBox hbox = new HBox(10, BQuitter, BJouer);
        hbox.setAlignment(Pos.CENTER);

        panelCentral.setCenter(hbox);

    }

    /**
     * Cette méthode permet de quitter l'application
     */
    public void quitter() {
        Platform.exit();
    }

    /**
     * Cette méthode permet de lancer la partie
     */

     public void modeJeu() {
        jeu.reset();
        panelCentral.getChildren().clear();
    
        grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
    
        for (int row = 0; row < jeu.getheight(); row++) {
            for (int col = 0; col < jeu.getwidth(); col++) {
                Pane plateau = new Pane();
                plateau.setPrefSize(80, 80);
                plateau.setStyle("-fx-border-color: blue; -fx-background-color: blue;");
    
                Circle circle = new Circle(37);
                circle.setFill(Color.WHITE);
                circle.setStroke(Color.BLUE);
                circle.setStrokeWidth(2);
    
                circle.setLayoutX(40);
                circle.setLayoutY(40);
    
                plateau.getChildren().add(circle);
                int currentCol = col;
                plateau.setOnMouseClicked(new ControleurPlacerPiece(this, currentCol, grille, jeu));
    
                grille.add(plateau, col, row);
            }
        }
    
        VBox leftBox = new VBox(10, chrono1);
        leftBox.setPadding(new Insets(10));
    
        VBox rightBox = new VBox(10, chrono2);
        rightBox.setPadding(new Insets(10));
    
        TitledPane chronoPaneL = new TitledPane("Joueur 1", chrono1);
        chronoPaneL.setCollapsible(false);
        leftBox.getChildren().add(chronoPaneL);
    
        TitledPane chronoPaneR = new TitledPane("Joueur 2", chrono2);
        chronoPaneR.setCollapsible(false);
        rightBox.getChildren().add(chronoPaneR);
    
        panelCentral.setLeft(leftBox);
        panelCentral.setRight(rightBox);
    
        VBox centerBox = new VBox(10, joueurActuelLabel, grille);
        centerBox.setAlignment(Pos.CENTER);
        panelCentral.setCenter(centerBox);
    
        Accueil.setDisable(false);
        Settings.setDisable(true);
    
        chrono1.start();
        chrono2.start();
    }

    private void updateJoueurActuelLabel() {
        if (joueur1) {
            joueurActuelLabel.setText("Tour de Joueur 1 (Rouge)");
        } else {
            joueurActuelLabel.setText("Tour de Joueur 2 (Jaune)");
        }
    }

    public void switchJoueur() {
        joueur1 = !joueur1;
        updateJoueurActuelLabel();
    }
    
    public boolean isJoueur1() {
        return joueur1;
    }

    /**
     * Passe le jeu en mode paramètres
     */
    public void modeParametres() {
        panelCentral.getChildren().clear();
        Accueil.setDisable(false);
        Settings.setDisable(true);

        Accueil.setOnAction(new ControleurAccueil(this));

        Label titrePara = new Label("Aucun paramètre disponible pour le moment");
        titrePara.setStyle("-fx-font-size: 25px;");

        panelCentral.setCenter(titrePara);
    }

    public Chronometre getChrono1() {
        return chrono1;
    }

    public Chronometre getChrono2() {
        return chrono2;
    }

    /**
     * Crée une alerte pour demander si le joueur veut entrer dans les paramètres
     * @return l'alerte de confirmation
     */
    public Alert alerteLancerPartie() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous lancer une nouvelle partie ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Attention");
        alert.setTitle("Puissance 4");
        return alert;
    }

    /**
     * Crée une alerte pour demander si le joueur veut entrer dans les paramètres
     * @return l'alerte de confirmation
     */
    public Alert alerteEntrerParametre() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous modifier les paramètres du jeu ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Attention");
        alert.setTitle("Puissance 4");
        return alert;
    }

    /**
     * Crée une alerte pour demander si le joueur veut entrer dans les paramètres
     * @return l'alerte de confirmation
     */
    public Alert alerteEntrerAccueil() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous retourner à l'accueil ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Attention");
        alert.setTitle("Puissance 4");
        return alert;
    }

    /**
     * Crée une alerte pour demander si le joueur veut entrer dans les paramètres
     * @return l'alerte de confirmation
     */
    public Alert alerteQuitter() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous quitter le jeu ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Attention");
        alert.setTitle("Puissance 4");
        return alert;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Puissance 4");
        stage.setScene(this.laScene());
        this.modeAccueil();
        stage.show();
    }

    /**
     * main entry point
     * @param args String[] terms args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}