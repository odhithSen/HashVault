package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    //file path
    public final static String PATH = "Login Data.bin";

    @Override
    public void start(Stage primaryStage) throws Exception{
        boolean containsFile = false;

        //check for login data file
        File logFile = new File(PATH);
        if(logFile.exists())
        {
            containsFile = true;
        }

        //Login page
        if (containsFile)
        {
            System.out.println("file exist"); // remove
            Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
            primaryStage.setTitle("HashVault");
            primaryStage.setScene(new Scene(root, 310, 400)); // !change
            primaryStage.show();
        }

        //Sign in page 1
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("onboardingScreen1.fxml"));
            primaryStage.setTitle("HashVault");
            primaryStage.setScene(new Scene(root, 310, 350)); // !change
            primaryStage.show();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
