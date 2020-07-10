package Menus;

import Game.RiverCrossingController;
import Level.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChooseLevelSceneController implements Initializable {


    private ICrossingStrategy chosenStrategy;
    private RiverCrossingController gameController;

    @FXML
    private AnchorPane chooseLevelPane;

    @FXML
    private ImageView startButton;
    @FXML
    private ImageView backButton;
    @FXML
    private RadioButton storyOneRadio;
    @FXML
    private RadioButton storyTwoRadio;

    @FXML
    private RadioButton stroyThreeRadio;

    @FXML
    private ImageView storyOneImage;
    @FXML
    private ImageView storyTwoImage;



    private Stage primaryStage = new Stage();

    private ToggleGroup chooseStoryToggle = new ToggleGroup();

    @FXML
    private ImageView background;
    private boolean selected=false;

    private void initiateNewGame(ICrossingStrategy strategy) {
        gameController = RiverCrossingController.MakeRiverCrossingController();
        gameController.newGame(strategy);
        System.out.println(gameController.getCrossersOnRightBank().size());
    }

    @FXML
    public void startGame( ) {
        Scene scene = chooseLevelPane.getScene();
        primaryStage = (Stage) scene.getWindow();

        /*
         * WARNING: IT'S IMPORTANT TO INITIATE A NEW GAME BEFORE INITIALIZING
         */
        if (storyOneRadio.isSelected()) {
            chosenStrategy = new CarniHerbiPlantStrategy();
            initiateNewGame(chosenStrategy);
            selected=true;
        }
        else if (storyTwoRadio.isSelected()) {
            chosenStrategy = new WeightProblemCrossingStrategy();
            initiateNewGame(chosenStrategy);
            selected=true;
        } else if (stroyThreeRadio.isSelected()) {
            chosenStrategy = new WolvesAndSheep();
            initiateNewGame(chosenStrategy);
            selected=true;
        } else if(!storyOneRadio.isSelected()&&!storyTwoRadio.isSelected()&&!stroyThreeRadio.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("You must choose a story!");
            alert.showAndWait();
            selected = false;
        }
        AnchorPane pane = null;
        if(selected)
        {
        try {
            FXMLLoader loader = new FXMLLoader(StorySceneController.class.getResource("StoryScene.fxml"));
            pane = loader.load();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Error Loading Scene! IOException");
            alert.showAndWait();
        }
        primaryStage.setWidth(960);
        primaryStage.setHeight(540);
        chooseLevelPane.getChildren().setAll(pane);
        }


    }

    public void back() throws IOException {

        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));

        chooseLevelPane.getChildren().setAll(mainPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chooseStoryToggle.getToggles().addAll(storyOneRadio, storyTwoRadio, stroyThreeRadio);
    }
}
