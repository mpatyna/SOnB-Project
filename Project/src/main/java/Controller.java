
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import server.ServerCluster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Controller {
    //robię siatkę serwerów
    ServerCluster cluster = new ServerCluster();
    @FXML
    private Button buttonStart,buttonDisplay,buttonReset;

    @FXML
    private CheckBox ch1_2,ch1_3,ch2_1,ch3_1,ch1_4,ch4_1,ch5_1,ch1_5,ch1_6,ch6_1,ch1_7,ch7_1,ch1_8,ch8_1,ch1_9,ch9_1,ch1_10,ch10_1,ch1_11,ch11_1;
    @FXML
    private CheckBox ch2_3,ch3_2,ch2_4,ch4_2,ch2_5,ch5_2,ch2_6,ch6_2,ch2_7,ch7_2,ch2_8,ch8_2,ch2_9,ch9_2,ch2_10,ch10_2,ch2_11,ch11_2;
    @FXML
    private CheckBox ch3_4,ch4_3,ch3_5,ch5_3,ch3_6,ch6_3,ch3_7,ch7_3,ch3_8,ch8_3,ch3_9,ch9_3,ch3_10,ch10_3,ch3_11,ch11_3;
    @FXML
    private CheckBox ch4_5,ch5_4,ch4_6,ch6_4,ch4_7,ch7_4,ch4_8,ch8_4,ch4_9,ch9_4,ch4_10,ch10_4,ch4_11,ch11_4;
    @FXML
    private CheckBox ch5_6,ch6_5,ch5_7,ch7_5,ch5_8,ch8_5,ch5_9,ch9_5,ch5_10,ch10_5,ch5_11,ch11_5;
    @FXML
    private CheckBox ch6_7,ch7_6,ch6_8,ch8_6,ch6_9,ch9_6,ch6_10,ch10_6,ch6_11,ch11_6;
    @FXML
    private CheckBox ch7_8,ch8_7,ch7_9,ch9_7,ch7_10,ch10_7,ch7_11,ch11_7;
    @FXML
    private CheckBox ch8_9,ch9_8,ch8_10,ch10_8,ch8_11,ch11_8,ch9_10,ch10_9,ch9_11,ch11_9,ch11_10,ch10_11;
    @FXML
    private TextArea consoleTextArea;
    @FXML
    private TextField messageText;
    @FXML
    private LineChart chartCycle;
    @FXML
    private Tab chartTab;


    @FXML
    private List<CheckBox> checkBoxList;

    @FXML
    private void setServerConnection(CheckBox c1,CheckBox c2, int s1, int s2){
        if(c1.isSelected()) {
            c2.setSelected(true);
            c1.setSelected(true);
            cluster.activateConnection(s1,s2);

        } else {
            c2.setSelected(false);
            c1.setSelected(false);
            cluster.deactivateConnection(s1,s2);
        }
    }
    @FXML
    private void setCheckboxStatusConnection(List<CheckBox> chList){
        for(CheckBox ch:chList) {
            try {
                ch.setSelected(true);
            } catch ( NullPointerException e){
                System.out.println("bład");
            }
        }
    }
    @FXML
    private void setChecboxOnAction(){
        ch1_2.setOnMouseClicked((event) -> { setServerConnection(ch1_2,ch2_1,0,1); });
        ch2_1.setOnMouseClicked((event) -> { setServerConnection(ch2_1,ch1_2,0,1); });
        ch1_3.setOnMouseClicked((event) -> { setServerConnection(ch1_3,ch3_1,0,2); });
        ch3_1.setOnMouseClicked((event) -> { setServerConnection(ch3_1,ch1_3,0,2); });
        ch1_4.setOnMouseClicked((event) -> { setServerConnection(ch1_4,ch4_1,0,3); });
        ch4_1.setOnMouseClicked((event) -> { setServerConnection(ch4_1,ch1_4,0,3); });
        ch1_5.setOnMouseClicked((event) -> { setServerConnection(ch1_5,ch5_1,0,4); });
        ch5_1.setOnMouseClicked((event) -> { setServerConnection(ch5_1,ch1_5,0,4); });
        ch1_6.setOnMouseClicked((event) -> { setServerConnection(ch1_6,ch6_1,0,5); });
        ch6_1.setOnMouseClicked((event) -> { setServerConnection(ch6_1,ch1_6,0,5); });
        ch1_7.setOnMouseClicked((event) -> { setServerConnection(ch1_7,ch7_1,0,6); });
        ch7_1.setOnMouseClicked((event) -> { setServerConnection(ch7_1,ch1_7,0,6); });
        ch1_8.setOnMouseClicked((event) -> { setServerConnection(ch1_8,ch8_1,0,7); });
        ch8_1.setOnMouseClicked((event) -> { setServerConnection(ch8_1,ch1_8,0,7); });
        ch1_9.setOnMouseClicked((event) -> { setServerConnection(ch1_9,ch9_1,0,8); });
        ch9_1.setOnMouseClicked((event) -> { setServerConnection(ch9_1,ch1_9,0,8); });
        ch1_10.setOnMouseClicked((event) -> { setServerConnection(ch1_10,ch10_1,0,9); });
        ch10_1.setOnMouseClicked((event) -> { setServerConnection(ch10_1,ch1_10,0,9); });
        ch1_11.setOnMouseClicked((event) -> { setServerConnection(ch1_11,ch11_1,0,10); });
        ch11_1.setOnMouseClicked((event) -> { setServerConnection(ch11_1,ch1_11,0,10); });
        ch2_3.setOnMouseClicked((event) -> { setServerConnection(ch2_3,ch3_2,1,2); });
        ch3_2.setOnMouseClicked((event) -> { setServerConnection(ch3_2,ch2_3,1,2); });
        ch2_4.setOnMouseClicked((event) -> { setServerConnection(ch2_4,ch4_2,1,3); });
        ch4_2.setOnMouseClicked((event) -> { setServerConnection(ch4_2,ch2_4,1,3); });
        ch2_5.setOnMouseClicked((event) -> { setServerConnection(ch2_5,ch5_2,1,4); });
        ch5_2.setOnMouseClicked((event) -> { setServerConnection(ch5_2,ch2_5,1,4); });
        ch2_6.setOnMouseClicked((event) -> { setServerConnection(ch2_6,ch6_2,1,5); });
        ch6_2.setOnMouseClicked((event) -> { setServerConnection(ch6_2,ch2_6,1,5); });
        ch2_7.setOnMouseClicked((event) -> { setServerConnection(ch2_7,ch7_2,1,6); });
        ch7_2.setOnMouseClicked((event) -> { setServerConnection(ch7_2,ch2_7,1,6); });
        ch2_8.setOnMouseClicked((event) -> { setServerConnection(ch2_8,ch8_2,1,7); });
        ch8_2.setOnMouseClicked((event) -> { setServerConnection(ch8_2,ch2_8,1,7); });
        ch2_9.setOnMouseClicked((event) -> { setServerConnection(ch2_9,ch9_2,1,8); });
        ch9_2.setOnMouseClicked((event) -> { setServerConnection(ch9_2,ch2_9,1,8); });
        ch2_10.setOnMouseClicked((event) -> { setServerConnection(ch2_10,ch10_2,1,9); });
        ch10_2.setOnMouseClicked((event) -> { setServerConnection(ch10_2,ch2_10,1,9); });
        ch2_11.setOnMouseClicked((event) -> { setServerConnection(ch2_11,ch11_2,1,10); });
        ch11_2.setOnMouseClicked((event) -> { setServerConnection(ch11_2,ch2_11,1,10); });
        ch3_4.setOnMouseClicked((event) -> { setServerConnection(ch3_4,ch4_3,2,3); });
        ch4_3.setOnMouseClicked((event) -> { setServerConnection(ch4_3,ch3_4,2,3); });
        ch3_5.setOnMouseClicked((event) -> { setServerConnection(ch3_5,ch5_3,2,4); });
        ch5_3.setOnMouseClicked((event) -> { setServerConnection(ch5_3,ch3_5,2,4); });
        ch3_6.setOnMouseClicked((event) -> { setServerConnection(ch3_6,ch6_3,2,5); });
        ch6_3.setOnMouseClicked((event) -> { setServerConnection(ch6_3,ch3_6,2,5); });
        ch3_7.setOnMouseClicked((event) -> { setServerConnection(ch3_7,ch7_3,2,6); });
        ch7_3.setOnMouseClicked((event) -> { setServerConnection(ch7_3,ch3_7,2,6); });
        ch3_8.setOnMouseClicked((event) -> { setServerConnection(ch3_8,ch8_3,2,7); });
        ch8_3.setOnMouseClicked((event) -> { setServerConnection(ch8_3,ch3_8,2,7); });
        ch3_9.setOnMouseClicked((event) -> { setServerConnection(ch3_9,ch9_3,2,8); });
        ch9_3.setOnMouseClicked((event) -> { setServerConnection(ch9_3,ch3_9,2,8); });
        ch3_10.setOnMouseClicked((event) -> { setServerConnection(ch3_10,ch10_3,2,9); });
        ch10_3.setOnMouseClicked((event) -> { setServerConnection(ch10_3,ch3_10,2,9); });
        ch3_11.setOnMouseClicked((event) -> { setServerConnection(ch3_11,ch11_3,2,10); });
        ch11_3.setOnMouseClicked((event) -> { setServerConnection(ch11_3,ch3_11,2,10); });
        ch4_5.setOnMouseClicked((event) -> { setServerConnection(ch4_5,ch5_4,3,4); });
        ch5_4.setOnMouseClicked((event) -> { setServerConnection(ch5_4,ch4_5,3,4); });
        ch4_6.setOnMouseClicked((event) -> { setServerConnection(ch4_6,ch6_4,3,5); });
        ch6_4.setOnMouseClicked((event) -> { setServerConnection(ch6_4,ch4_6,3,5); });
        ch4_7.setOnMouseClicked((event) -> { setServerConnection(ch4_7,ch7_4,3,6); });
        ch7_4.setOnMouseClicked((event) -> { setServerConnection(ch7_4,ch4_7,3,6); });
        ch4_8.setOnMouseClicked((event) -> { setServerConnection(ch4_8,ch8_4,3,7); });
        ch8_4.setOnMouseClicked((event) -> { setServerConnection(ch8_4,ch4_8,3,7); });
        ch4_9.setOnMouseClicked((event) -> { setServerConnection(ch4_9,ch9_4,3,8); });
        ch9_4.setOnMouseClicked((event) -> { setServerConnection(ch9_4,ch4_9,3,8); });
        ch4_10.setOnMouseClicked((event) -> { setServerConnection(ch4_10,ch10_4,3,9); });
        ch10_4.setOnMouseClicked((event) -> { setServerConnection(ch10_4,ch4_10,3,9); });
        ch4_11.setOnMouseClicked((event) -> { setServerConnection(ch4_11,ch11_4,3,10); });
        ch11_4.setOnMouseClicked((event) -> { setServerConnection(ch11_4,ch4_11,3,10); });
        ch5_6.setOnMouseClicked((event) -> { setServerConnection(ch5_6,ch6_5,4,5); });
        ch6_5.setOnMouseClicked((event) -> { setServerConnection(ch6_5,ch5_6,4,5); });
        ch5_7.setOnMouseClicked((event) -> { setServerConnection(ch5_7,ch7_5,4,6); });
        ch7_5.setOnMouseClicked((event) -> { setServerConnection(ch7_5,ch5_7,4,6); });
        ch5_8.setOnMouseClicked((event) -> { setServerConnection(ch5_8,ch8_5,4,7); });
        ch8_5.setOnMouseClicked((event) -> { setServerConnection(ch8_5,ch5_8,4,7); });
        ch5_9.setOnMouseClicked((event) -> { setServerConnection(ch5_9,ch9_5,4,8); });
        ch9_5.setOnMouseClicked((event) -> { setServerConnection(ch9_5,ch5_9,4,8); });
        ch5_10.setOnMouseClicked((event) -> { setServerConnection(ch5_10,ch10_5,4,9); });
        ch10_5.setOnMouseClicked((event) -> { setServerConnection(ch10_5,ch5_10,4,9); });
        ch5_11.setOnMouseClicked((event) -> { setServerConnection(ch5_11,ch11_5,4,10); });
        ch11_5.setOnMouseClicked((event) -> { setServerConnection(ch11_5,ch5_11,4,10); });
        ch6_7.setOnMouseClicked((event) -> { setServerConnection(ch6_7,ch7_6,5,6); });
        ch7_6.setOnMouseClicked((event) -> { setServerConnection(ch7_6,ch6_7,5,6); });
        ch6_8.setOnMouseClicked((event) -> { setServerConnection(ch6_8,ch8_6,5,7); });
        ch8_6.setOnMouseClicked((event) -> { setServerConnection(ch8_6,ch6_8,5,7); });
        ch6_9.setOnMouseClicked((event) -> { setServerConnection(ch6_9,ch9_6,5,8); });
        ch9_6.setOnMouseClicked((event) -> { setServerConnection(ch9_6,ch6_9,5,8); });
        ch6_10.setOnMouseClicked((event) -> { setServerConnection(ch6_10,ch10_6,5,9); });
        ch10_6.setOnMouseClicked((event) -> { setServerConnection(ch10_6,ch6_10,5,9); });
        ch6_11.setOnMouseClicked((event) -> { setServerConnection(ch6_11,ch11_6,5,10); });
        ch11_6.setOnMouseClicked((event) -> { setServerConnection(ch11_6,ch6_11,5,10); });
        ch7_8.setOnMouseClicked((event) -> { setServerConnection(ch7_8,ch8_7,6,7); });
        ch8_7.setOnMouseClicked((event) -> { setServerConnection(ch8_7,ch7_8,6,7); });
        ch7_9.setOnMouseClicked((event) -> { setServerConnection(ch7_9,ch9_7,6,8); });
        ch9_7.setOnMouseClicked((event) -> { setServerConnection(ch9_7,ch7_9,6,8); });
        ch7_10.setOnMouseClicked((event) -> { setServerConnection(ch7_10,ch10_7,6,9); });
        ch10_7.setOnMouseClicked((event) -> { setServerConnection(ch10_7,ch7_10,6,9); });
        ch7_11.setOnMouseClicked((event) -> { setServerConnection(ch7_11,ch11_7,6,10); });
        ch11_7.setOnMouseClicked((event) -> { setServerConnection(ch11_7,ch7_11,6,10); });
        ch8_9.setOnMouseClicked((event) -> { setServerConnection(ch8_9,ch9_8,7,8); });
        ch9_8.setOnMouseClicked((event) -> { setServerConnection(ch9_8,ch8_9,7,8); });
        ch8_10.setOnMouseClicked((event) -> { setServerConnection(ch8_10,ch10_8,7,9); });
        ch10_8.setOnMouseClicked((event) -> { setServerConnection(ch10_8,ch8_10,7,9); });
        ch8_11.setOnMouseClicked((event) -> { setServerConnection(ch8_11,ch11_8,7,10); });
        ch11_8.setOnMouseClicked((event) -> { setServerConnection(ch11_8,ch8_11,7,10); });
        ch9_10.setOnMouseClicked((event) -> { setServerConnection(ch9_10,ch10_9,8,9); });
        ch10_9.setOnMouseClicked((event) -> { setServerConnection(ch10_9,ch9_10,8,9); });
        ch9_11.setOnMouseClicked((event) -> { setServerConnection(ch9_11,ch11_9,8,10); });
        ch11_9.setOnMouseClicked((event) -> { setServerConnection(ch11_9,ch9_11,8,10); });
        ch11_10.setOnMouseClicked((event) -> { setServerConnection(ch11_10,ch10_11,9,10); });
        ch10_11.setOnMouseClicked((event) -> { setServerConnection(ch10_11,ch11_10,9,10); });
    }
    @FXML
    private void initialize() {
        checkBoxList = new ArrayList<>(Arrays.asList(ch1_2,ch1_3,ch2_1,ch3_1,ch1_4,ch4_1,ch5_1,ch1_5,ch1_6,ch6_1,ch1_7,ch7_1,ch1_8,ch8_1,ch1_9,ch9_1,ch1_10,ch10_1,ch1_11,ch11_1,
                ch2_3,ch3_2,ch2_4,ch4_2,ch2_5,ch5_2,ch2_6,ch6_2,ch2_7,ch7_2,ch2_8,ch8_2,ch2_9,ch9_2,ch2_10,ch10_2,ch2_11,ch11_2,
                ch3_4,ch4_3,ch3_5,ch5_3,ch3_6,ch6_3,ch3_7,ch7_3,ch3_8,ch8_3,ch3_9,ch9_3,ch3_10,ch10_3,ch3_11,ch11_3,
                ch4_5,ch5_4,ch4_6,ch6_4,ch4_7,ch7_4,ch4_8,ch8_4,ch4_9,ch9_4,ch4_10,ch10_4,ch4_11,ch11_4,
                ch5_6,ch6_5,ch5_7,ch7_5,ch5_8,ch8_5,ch5_9,ch9_5,ch5_10,ch10_5,ch5_11,ch11_5,
                ch6_7,ch7_6,ch6_8,ch8_6,ch6_9,ch9_6,ch6_10,ch10_6,ch6_11,ch11_6,
                ch7_8,ch8_7,ch7_9,ch9_7,ch7_10,ch10_7,ch7_11,ch11_7,
                ch8_9,ch9_8,ch8_10,ch10_8,ch8_11,ch11_8,ch9_10,ch10_9,ch9_11,ch11_9,ch11_10,ch10_11));
        // Handle Button event.
        setChecboxOnAction();
        buttonStart.setOnAction((event) -> {
            //odpalam plotkowanie z wiadmomością z parametry
            try {
                for (String temp: cluster.gossip(messageText.getText())) {
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
        buttonReset.setOnAction((event) -> {
            setCheckboxStatusConnection(checkBoxList);
            cluster.cleanServerStatus();
        });
        chartTab.setOnSelectionChanged((event)->{
            chartCycle.getData().clear();
            XYChart.Series series =new XYChart.Series();
            series.setName("Czas rozsyłania");
            cluster.getChartData().forEach((k, v)->{

                series.getData().add(new XYChart.Data(k,v));
                });
            chartCycle.getData().add(series);
        });



    }


}
