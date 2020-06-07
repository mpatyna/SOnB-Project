
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import server.ServerCluster;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/sonb.fxml"));

        primaryStage.setTitle("Systemy odporne na błędy");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();




    /*
                Button button = new Button("Start symulacji");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(button.getText().equals("Start symulacji"))
                    button.setText("Stop symulacji");
                else
                    button.setText("Start symulacji");
            }});
        Button button1 = new Button("Start symulacji");
    */
    }


    public static void main(String[] args){
        launch(args);
    }
}
