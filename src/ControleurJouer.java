import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurJouer implements EventHandler<ActionEvent> {
    private puissance4 appli;
    
    public ControleurJouer(puissance4 appli) {
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Jouer");
        this.appli.jouer();
    }
}
