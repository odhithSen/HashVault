package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OnboardingScreen1Controller{

    @FXML
    private TextField nameTxt;
    @FXML
    private Button getStartedBtn;

    static String name;

    public void goToSecondPage() throws Exception {
        name = nameTxt.getText();
        if (name.equals(" ") || name.equals("")){
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Please enter your name!");
            a.show();
        }
        else{
            System.out.println("name: " + name);
            //navigate();
        }
    }

}
