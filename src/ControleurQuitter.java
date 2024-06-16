import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

public class ControleurQuitter implements EventHandler<ActionEvent> {
    private puissance4 appli;
    
    public ControleurQuitter(puissance4 appli) {
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent e) {
        Optional<ButtonType> result = appli.alerteQuitter().showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.YES)) {
            appli.quitter();
        }
        else {
            appli.modeAccueil();
        }
    }
}
