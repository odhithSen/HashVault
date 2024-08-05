package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditMasterKeyController {

    @FXML
    private Label colourLbl;
    @FXML
    private TextField newPass1Txt;
    @FXML
    private Button updateKeyBtn;
    @FXML
    private TextField newPass2Txt;
    @FXML
    private Label colourLbl2;

    private String newPassword;
    private String newPassword2;

    private boolean passwordIsValid = false;

    public void initialize(){
        newPass2Txt.setVisible(false);
        updateKeyBtn.setVisible(false);
        // should hide the main window here
    }

    public void checkStrength (){

        newPassword = newPass1Txt.getText();
        System.out.println(newPassword);

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(newPassword);

        if (m.matches()){
            colourLbl.setStyle("-fx-border-color:black; -fx-background-color: green;");
            System.out.println("valid");
            updateKeyBtn.setVisible(false);
            reCheckPassword();
        }
        else {
            colourLbl.setStyle("-fx-border-color:black; -fx-background-color: red;");
            System.out.println("invalid");
            updateKeyBtn.setVisible(false);
        }
    }

    public void reCheckPassword(){
        newPass2Txt.setVisible(true);
        newPassword2 = newPass2Txt.getText();

        if (newPassword.equals(newPassword2)){
            passwordIsValid = true;
            colourLbl2.setStyle("-fx-border-color:black; -fx-background-color: green;");
            System.out.println("match");
            updateKeyBtn.setVisible(true);
        }
        else {
            colourLbl2.setStyle("-fx-border-color:black; -fx-background-color: red;");
            System.out.println("does not match");
            updateKeyBtn.setVisible(false);
        }
    }

    public void updateKey() throws Exception {

        if (passwordIsValid){
            System.out.println("password is valid");
            System.out.println("Password is:" +newPassword);

            // creating file
            try
            {
                File dataFile = new File(Main.PATH);
                if (dataFile.createNewFile())
                {
                    System.out.println(dataFile.getName() + " File created");
                }

            } catch (IOException e)
            {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            //storing data
            try
            {
                FileWriter fWriter = new FileWriter(Main.PATH);
                PrintWriter pWriter = new PrintWriter(fWriter);

                pWriter.println(LoginScreenController.userName);
                pWriter.println(hashPassword()); // password

                fWriter .close();
                System.out.println("Program Data stored Successfully");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            // show the hidden home page
        }
        else {
            System.out.println("password should adhere to above criteria");
        }
    }

    public String hashPassword() throws NoSuchAlgorithmException {

        // adding salt
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(newPassword2);
        //salt
        sBuilder.append("Cryptography is the study of secure communications techniques that allow " +
                "only the sender and intended recipient of a message to view its contents");

        final String SaltedPassword = sBuilder.toString();

        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashBytes = digest.digest(SaltedPassword.getBytes(StandardCharsets.UTF_8)); // add salt here

        String encodedString = Base64.getEncoder().encodeToString(hashBytes);
        return encodedString;
    }



}
