import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Group; 
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider ;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.geometry.Orientation;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.event.EventHandler;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class puissance4 extends Application {
    
    @Override
    public void init(){
    }    
    
    @Override
    public void start(Stage stage){
        GridPane root = new GridPane();
        Scene scene = new Scene(root);
        stage.setTitle("puissance 4");
        stage.setScene(scene);
        stage.show();
    }

   
        /**
     * Cette méthode permet de quitter l'application
     */
    public void quitter(){
        Platform.exit();
    }
    /**
     * main entry point
     * @param args String[] terms args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
