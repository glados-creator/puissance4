import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurQuitter implements EventHandler<ActionEvent> {
    private puissance4 appli;
    
    public ControleurQuitter(puissance4 appli) {
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Quitter");
        this.appli.quitter();
    }
}
