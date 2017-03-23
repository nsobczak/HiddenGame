package application;

import application.services.StageService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HiddenGameApp extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Set a title on the primaryStage parameter;
        primaryStage.setTitle("Hidden Game");
        // Store the primaryStage parameter inside the PrimaryStage class;
        StageService.getInstance().setPrimaryStage(primaryStage);
        // Call the showHomeScreen() method you just created.
        try
        {
            showHomeScreen();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void showHomeScreen() throws IOException
    {
        //Load the HomeScreen.fxml file and get its root AnchorPane as a java object;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HiddenGameApp.class.getResource("controllers/HomeScreen.fxml"));
        AnchorPane rootPane = loader.load();
        //Create a Scene object from the AnchorPane object;
        Scene homeScene = new Scene(rootPane);
        //Get the PrimaryStage from the StageService  class and set the scene you created inside;
        StageService.getInstance().getPrimaryStage().setScene(homeScene);
        //Get the PrimaryStage from the StageService  class and show it!
        StageService.getInstance().getPrimaryStage().show();
    }


}
