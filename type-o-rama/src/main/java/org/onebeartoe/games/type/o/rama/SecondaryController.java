package org.onebeartoe.games.type.o.rama;

import java.io.IOException;
import javafx.fxml.FXML;

//TODO: delete if it  not used
public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}