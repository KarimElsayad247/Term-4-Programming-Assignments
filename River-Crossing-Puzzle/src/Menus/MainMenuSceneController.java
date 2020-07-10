package Menus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuSceneController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView playButton;

    @FXML
    private ImageView background;


    public void startGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(ChooseLevelSceneController.class.getResource("ChooseLevelScene.fxml"));
        AnchorPane chooseLevelPane = loader.load();

        mainPane.getChildren().setAll(chooseLevelPane);



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            playButton.setImage(new Image("resources/playButton.png"));
        } catch (Exception e) {
            System.out.println("IOEXCEPTION!");
            e.printStackTrace();
        }
    }
}
