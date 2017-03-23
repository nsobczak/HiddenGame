package application.controllers;

import application.models.Game;
import application.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Vincent Reynaert & Nicolas Sobczak on 27/02/2017.
 */
public class HomeScreenController
{
    @FXML
    private Button startButton;
    @FXML
    private RadioButton launchGameAfterBuildFinishedRadioButton;
    @FXML
    private Button closeButton;

    @FXML
    private TextField dbAdressTextField;
    @FXML
    private TextField dbSchemaTextField;
    @FXML
    private TextField dbPasswordTextField;
    @FXML
    private TextField dbPortTextField;
    @FXML
    private TextField dbUserTextField;
    @FXML
    private TextField dbRootTextField;

    //______________________________________________________________________________
    private Boolean isThereNoEmptyTextField()
    {
        Boolean result = true;
        if (this.dbRootTextField.getText().trim().isEmpty()) result = false;
        else if (this.dbAdressTextField.getText().trim().isEmpty()) result = false;
        else if (this.dbSchemaTextField.getText().trim().isEmpty()) result = false;
        else if (this.dbPortTextField.getText().trim().isEmpty()) result = false;
        else if (this.dbUserTextField.getText().trim().isEmpty()) result = false;
        return result;
    }

    private void displayPopUpAlert(int errorWeight, String title, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR.INFORMATION);
        switch (errorWeight)
        {
            case 1:
                alert.setAlertType(Alert.AlertType.ERROR.WARNING);
                alert.setHeaderText("WARNING");
                break;
            case 2:
                alert.setAlertType(Alert.AlertType.ERROR.ERROR);
                alert.setHeaderText("ERROR");
                break;
            default:
                alert.setAlertType(Alert.AlertType.ERROR.INFORMATION);
                alert.setHeaderText("Information");
                break;
        }
        alert.initOwner(StageService.getInstance().getPrimaryStage());
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void handleLaunchGameAfterBuildFinishedRadioButtonOption() throws IOException
    {
        if (this.launchGameAfterBuildFinishedRadioButton.isSelected())
        {
            if (Desktop.isDesktopSupported())
            {
                if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                    Desktop.getDesktop().browse(new File(Paths.get(this.dbRootTextField.getText(), "index.html").toString()).toURI());
                else
                    displayPopUpAlert(2, "OS Error", "Your os does not support \"Browse\" action.\n" +
                            "Game can't be launched from this software.");
            } else
                displayPopUpAlert(2, "OS Error", "Your os is not supported.\n" +
                        "Game can't be launched from this software.");
        }
    }


    @FXML
    public void handleLaunchButton()
    {
        System.out.println("hello from hidden game !");

        if (this.isThereNoEmptyTextField())
        {
            try
            {
                Game game = new Game(this.dbRootTextField.getText());
                Game.initGameDatabaseProperties(this.dbAdressTextField.getText(),
                        Integer.valueOf(this.dbPortTextField.getText()),
                        this.dbSchemaTextField.getText(),
                        this.dbUserTextField.getText(),
                        this.dbPasswordTextField.getText());
                if (game.initDecryptedFileList())
                {
                    game.buildGameFromFileList();
                    displayPopUpAlert(0, "Build finished", "Game has been built.");
                    this.handleLaunchGameAfterBuildFinishedRadioButtonOption();
                } else
                {
                    displayPopUpAlert(2, "Can't build game 1", "Can't connect to database.\nThere may be wrong fields.");
                }
            } catch (Exception e)
            {
                displayPopUpAlert(2, "Can't build game", "Can't build game.\nThere may be wrong fields.\nTip: port has to be a number.");
            }
        } else
        {
            displayPopUpAlert(1, "Empty field", "There may be empty text fields.");
        }
    }


    @FXML
    public void handleCloseButton() throws IOException
    {
        StageService.getInstance().closeStage();
    }


}
