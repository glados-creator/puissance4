import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.Group; 
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider ;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.geometry.Orientation;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class puissance4 extends Application {

    private BorderPane panelCentral;

    private Button Accueil;
    private Button Settings;
    private Button BQuitter;
    private Button BJouer;

    @Override
    public void init() {
    }    

    private Scene laScene() {
        BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.titre());
        panelCentral = new BorderPane();
        fenetre.setCenter(this.panelCentral);
        return new Scene(fenetre, 800, 600);
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
        // Accueil.setOnAction(new RetourAccueil(this.modelePendu, this));

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

        Accueil.setDisable(true);
        Settings.setDisable(false);
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
    public void jouer() {
        System.out.println("Start Game");
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
