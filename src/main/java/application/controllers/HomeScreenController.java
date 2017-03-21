package application.controllers;

import application.services.StageService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by vvinc_000 on 27/02/2017.
 */
public class HomeScreenController {

    @FXML
    void handleLaunchButton() throws IOException {
        // Load the QuizOverview.fxml file and get its root AnchorPane as a java object;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(application.HiddenGameApp.class.getResource("view/QuizOverview.fxml"));
        AnchorPane rootPane = loader.load();

        // Create a Scene object from the AnchorPane object;
        Scene overViewScene = new Scene(rootPane);

        // Get the PrimaryStage from the StageService  class and set the scene you created inside;
        StageService.getInstance().getPrimaryStage().setScene(overViewScene);

    }
    @FXML
    void handleCloseButton() throws IOException {

    }

}
