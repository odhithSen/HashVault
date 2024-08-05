package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditDetailsController {

    @FXML
    private TextField searchTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private TextField descriptionTxt;

    @FXML
    private Button searchBtn;
    @FXML
    private Button updateBtn;

    private boolean contains = false;
    private int index = 0;


    public void initialize(){

        nameTxt.setVisible(false);
        passwordTxt.setVisible(false);
        descriptionTxt.setVisible(false);
        updateBtn.setVisible(false);

    }

    public void search(){   // when search button click
        if (searchTxt.getText().equals("") || searchTxt.getText().equals(null))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Enter the username");
            alert.show();
        }
        else
        {
            for (int i = 0; i < HomePageController.plainPasswordList.size() ; i++) {
                if(HomePageController.plainPasswordList.get(i).getUserName().equals(searchTxt.getText()))
                {
                    contains = true;
                    index = i;
                    displayData();
                    break;
                }
                else
                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setContentText("Entered username does not exist");
                    alert2.show();
                    contains = false;
                }
            }
        }
    }

    public void displayData (){

        nameTxt.setVisible(true);
        passwordTxt.setVisible(true);
        descriptionTxt.setVisible(true);
        updateBtn.setVisible(true);

        nameTxt.setText(HomePageController.plainPasswordList.get(index).getUserName());
        passwordTxt.setText(HomePageController.plainPasswordList.get(index).getPassword());
        descriptionTxt.setText(HomePageController.plainPasswordList.get(index).getDescription());
    }

    public void updateFiled(){    // when update button is clicked

        if (nameTxt.getText().equals("") || nameTxt.getText().equals(null) || passwordTxt.getText().equals("") || passwordTxt.getText().equals(null) || descriptionTxt.getText().equals("") || descriptionTxt.getText().equals(null) )
        {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Enter all details");
            alert1.show();
        }
        else
        {
            HomePageController.plainPasswordList.get(index).setUserName(nameTxt.getText());
            HomePageController.plainPasswordList.get(index).setPassword(passwordTxt.getText());
            HomePageController.plainPasswordList.get(index).setDescription(descriptionTxt.getText());
            System.out.println("edited details");

            // call update table
//            HomePageController h1 = new HomePageController();
//            h1.updateTable();


        }
    }
}
