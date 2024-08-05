package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class HomePageController{

    @FXML
    private TableView <Password> mainTable;
    @FXML
    private TableColumn <Password,String> userNameCol;
    @FXML
    private TableColumn <Password,String> passwordCol;
    @FXML
    private TableColumn <Password,String> descriptionCol;

    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private TextField descriptionTxt;


    protected static ArrayList<Password> plainPasswordList= new ArrayList<>();
    protected static Stage editDetailStage;
    private PasswordGenerator pwGenerator = new PasswordGenerator();



    public void updateTable (){    // whenever we need to update the table

        ObservableList<Password> passwordItems = FXCollections.observableArrayList();

        //populating observable list
        for (int i = 0; i < plainPasswordList.size(); i++) {
            passwordItems.add(plainPasswordList.get(i));
        }

        //updating table
        mainTable.setItems(passwordItems);
        userNameCol.setCellValueFactory(new PropertyValueFactory<Password,String>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<Password,String>("password"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Password,String>("description"));

    }


    public void removePassword(){  // on remove btn press

        boolean contains = false;
        int index = 0;

        if (userNameTxt.getText().equals("") || userNameTxt.getText().equals(null))
        {
            Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
            alert4.setContentText("Enter the User name of the password\nthat should be removed"); // change
            alert4.show();
        }
        else
        {
            //finding the object
            for (int i = 0; i < plainPasswordList.size() ; i++) {
                if(plainPasswordList.get(i).getUserName().equals(userNameTxt.getText()))
                {
                    contains = true;
                    index = i;
                    break;
                }
                else
                {
                    contains = false;
                }
            }
            if (contains)
            {
                Alert alertC1 = new Alert(Alert.AlertType.CONFIRMATION);
                alertC1.setContentText("Do you want to remove selected Password"); // change
                alertC1.show();

                // capture alert result
                Optional<ButtonType> result = alertC1.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    plainPasswordList.remove(index);
                    Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
                    alert5.setContentText("Password details removed successfully"); // change
                    alert5.show();
                    System.out.println("password found and removed"); // remove

                    descriptionTxt.setText("");
                    userNameTxt.setText("");
                    passwordTxt.setText("");

                    updateTable();
                }
                else
                {
                    descriptionTxt.setText("");
                    userNameTxt.setText("");
                    passwordTxt.setText("");
                }

            }
            else
            {
                Alert alert6 = new Alert(Alert.AlertType.INFORMATION);
                alert6.setContentText("no such password found"); // change
                alert6.show();

                System.out.println("no such password found"); // remove
            }
        }
    }



    public void signOut (){

        Alert alertC2 = new Alert(Alert.AlertType.CONFIRMATION);
        alertC2.setContentText("Do you want to close the application?"); // change
        alertC2.show();

        // capture alert result
        Optional<ButtonType> result = alertC2.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            // close the main stage
            // save data
        }
    }


    @FXML
    public void displayEditPage() throws Exception {       // when edit password button clicked
        editDetailStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("editDetails.fxml"));
        editDetailStage.setTitle("HashVault");
        editDetailStage.setScene(new Scene(root, 375, 400));
        editDetailStage.show();
    }



}


