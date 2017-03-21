package application.controllers;

import application.models.Game;
import application.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by vvinc_000 on 27/02/2017.
 */
public class HomeScreenController
{
    @FXML
    private Button startButton;
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

    private void displayInformationPopUp(String title, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(StageService.getInstance().getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText("Information");
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void handleLaunchButton() throws Exception
    {
        System.out.println("hello world !");

        if (this.isThereNoEmptyTextField())
        {
            Game game = new Game(this.dbRootTextField.getText());
            game.writeDatabaseProperties(
                    this.dbAdressTextField.getText(),
                    Integer.parseInt(this.dbPortTextField.getText()),
                    this.dbSchemaTextField.getText(),
                    this.dbUserTextField.getText(),
                    this.dbPasswordTextField.getText());
            //TODO: apparemment y'aurait un pb de lecture du port de la bdd causée par le type de db.port int/String par dataSource.setPort(Integer.valueOf(dbProperties.getProperty("db.port")));
            //quand on le lance 2 fois la 2ème fois ça fonctionne (ce n'est pas un pb de temps d'écriture du fichier)
            game.initDecryptedFileList();
            game.buildGameFromFileList();
            displayInformationPopUp("Build finished", "Game has been built");
        } else
        {
            displayInformationPopUp("Empty field", "There may be empty text fields");
        }
    }


    @FXML
    public void handleCloseButton() throws IOException
    {
        StageService.getInstance().closeStage();
    }


}
