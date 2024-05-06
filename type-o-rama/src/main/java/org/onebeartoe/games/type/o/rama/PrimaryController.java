package org.onebeartoe.games.type.o.rama;

import java.io.IOException;
import javafx.fxml.FXML;

//TODO: delete if it  not used
public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
