package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class LoginScreenController {

    @FXML
    private Label promptLbl;
    @FXML
    private TextField passTxt;
    @FXML
    private Button logInBtn;

    private String realPasswordEncoded;
    protected static String userName;
    private ArrayList<String> fileContent;
    private int incorrectPasswordCount = 0;


    public void initialize(){
        getData();
        displayGreeting();
    }

    public void displayGreeting(){
        promptLbl.setText("Welcome back, "+ userName);
    }

    public void validatePassword() throws Exception {     //on login button press

        if (incorrectPasswordCount > 1)
        {
            //capture
        }

        if (passTxt.getText().equals("") || passTxt.getText().equals(null))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Enter the password");
            alert.show();
        }
        else
        {
            String enteredPassword = passTxt.getText();
            String encodedEnteredPassword = hashPassword(enteredPassword);

            if (encodedEnteredPassword.equals(realPasswordEncoded))
            {
                System.out.println("correct password");
                navigateToHomePage();
            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Incorrect password");
                alert.show();
                incorrectPasswordCount ++;

                if(incorrectPasswordCount > 3)
                {
                    // method to prevent access
                    //see code
                }
            }
        }
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        // adding salt
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(password);
        //salt
        sBuilder.append("Cryptography is the study of secure communications techniques that allow only" +
                " the sender and intended recipient of a message to view its contents");

        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final String SaltedPassword = sBuilder.toString();
        final byte[] hashBytes = digest.digest(SaltedPassword.getBytes(StandardCharsets.UTF_8));

        String encodedString = Base64.getEncoder().encodeToString(hashBytes);
        return encodedString;
    }

    public void getData(){

        fileContent = new ArrayList<>();
        try
        {
            FileReader fReader  = new FileReader(Main.PATH);
            BufferedReader bReader = new BufferedReader(fReader);

            String dataLine;
            int i = 0;
            while ((dataLine = bReader.readLine()) != null)
            {
                fileContent.add(dataLine);
                i +=1;
            }
            bReader.close();
        }catch (IOException e){
            System.out.println("File not found");
        }

        userName = fileContent.get(0);
        realPasswordEncoded = fileContent.get(1);
    }

    @FXML
    public void navigateToHomePage() throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        newStage.setTitle("HashVault");
        newStage.setScene(new Scene(root, 390, 490));
        newStage.show();


    }


}
