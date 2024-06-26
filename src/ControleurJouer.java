import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

public class ControleurJouer implements EventHandler<ActionEvent> {
    private puissance4 appli;
    
    public ControleurJouer(puissance4 appli) {
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent e) {
        Optional<ButtonType> result = appli.alerteLancerPartie().showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.YES)) {
            appli.modeJeu();
        }
        else {
            appli.modeAccueil();
        }
    }
}
