
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import server.ServerCluster;

import java.util.List;

public class Controller {
    //robię siatkę serwerów
    ServerCluster cluster = new ServerCluster();
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonDisplay;
    @FXML
    private CheckBox myCheckBox;
    @FXML
    private TextArea consoleTextArea;

    //uszkadzam połączenie
    //    cluster.deactivateConnection(0, 4);

    //wyświetlam stan wiadmomości na serwerach
    //    cluster.displayServerMessages();
    //naprawiam serwery i czyszczę wagi przy adresach
    //    cluster.cleanServerStatus();

    @FXML
    private void initialize() {
        // Handle Button event.
        buttonStart.setOnAction((event) -> {
            //odpalam plotkowanie z wiadmomością z parametry
            try {
                for (String temp: cluster.gossip("test")) {
                    consoleTextArea.appendText("\n"+ temp);
                }
            } catch (InterruptedException e) {
                consoleTextArea.appendText(e.getMessage());
            }

        });
        buttonDisplay.setOnAction((event) -> {
                for (String temp: cluster.displayServerMessages())
                    consoleTextArea.appendText("\n"+ temp);
        }) ;
        // Handle CheckBox event.
        /*
        myCheckBox.setOnAction((event) -> {
            boolean selected = myCheckBox.isSelected();

        });
        */
    }


}
