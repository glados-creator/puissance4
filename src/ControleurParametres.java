import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

public class ControleurParametres implements EventHandler<ActionEvent> {
    private puissance4 appli;
    
    public ControleurParametres(puissance4 appli) {
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent e) {
        Optional<ButtonType> result = appli.alerteEntrerParametre().showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.YES)) {
            appli.modeParametres();
        }
        else {
            appli.modeAccueil();
        }
    }
}
