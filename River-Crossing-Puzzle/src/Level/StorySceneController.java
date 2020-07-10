package Level;

import Crossers.ICrosser;
import Game.*;
import Menus.ChooseLevelSceneController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import static javafx.embed.swing.SwingFXUtils.toFXImage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class StorySceneController implements Initializable {


    @FXML
    private HBox rightBankHbox;

    @FXML
    private HBox leftBankHbox;

    @FXML
    private HBox river;

    @FXML
    private ImageView boatSpotOne;

    @FXML
    private ImageView boatSpotTwo;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button undoButton;

    @FXML
    private Button redoButton;

    @FXML
    private Button informationButton;

    @FXML
    Button backToChooseButton;
    private Label label;

    private RiverCrossingController currentStrategy = RiverCrossingController.MakeRiverCrossingController();

    private List<ICrosser> crossersOnRightBank = currentStrategy.getCrossersOnRightBank();
    private List<ICrosser> crossersOnLeftBank = currentStrategy.getCrossersOnLeftBank();
    private ArrayList<ICrosser> crossersOnBoat = new ArrayList<>();
    private ArrayList<ICrosser> myCrossers = new ArrayList<>();

    private HashMap<Image, ICrosser> myRightImages = new HashMap<>();
    private HashMap<Image, Image> myLeftImages = new HashMap<>();
    private HashMap<Image, ICrosser> myLeftCrossersAndImages = new HashMap<>();

    private double prefImageSize = 60;
    private boolean isBoatOnLeftBank = false;
    private boolean fromLeftToRight = false;
    
    public void returnToChooseLevel() {
        
        try {
            FXMLLoader loader = new FXMLLoader(ChooseLevelSceneController.class.getResource("ChooseLevelScene.fxml"));
            AnchorPane chooseLevelPane = loader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().addAll(chooseLevelPane);
            currentStrategy.clear();
         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryCrossing() {

        if (currentStrategy.canMove(crossersOnBoat, fromLeftToRight)) {
            currentStrategy.doMove(crossersOnBoat, fromLeftToRight);

            if (fromLeftToRight == false) {
                fromLeftToRight = true;
                river.setAlignment(Pos.BOTTOM_LEFT);

            } else if (fromLeftToRight == true) {

                fromLeftToRight = false;
                river.setAlignment(Pos.BOTTOM_RIGHT);

            }
            currentStrategy.saveState(currentStrategy);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Can't Pass!");
            alert.setHeaderText(null);
            alert.setContentText("You can't do that");
            alert.showAndWait();
        }

        isBoatOnLeftBank = currentStrategy.isBoatOnTheLeftBank();
        if (currentStrategy.didWin()) {
            Alert winBox = new Alert(AlertType.INFORMATION);
            winBox.setTitle("You Win");
            winBox.setHeaderText(null);
            winBox.setContentText("You can get back to menu now");
            Optional<ButtonType> result = winBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                returnToChooseLevel();
            }
        }

    }




    private ICrosser findRightOwner(Image image) {
        if (myRightImages.containsKey(image)) {
            return myRightImages.get(image);
        } else {
            System.out.println("can't find");
            return null;
        }
    }

    public ICrosser findLeftOwner(Image image) {
        if (myLeftCrossersAndImages.containsKey(image)) {
            return myLeftCrossersAndImages.get(image);
        } else {
            System.out.println("can't find");
            return null;
        }
    }


    private void addImageToBoat(Image image) {
        if (boatSpotOne.getImage() == null) {
            boatSpotOne.setImage(image);
        } else if (boatSpotTwo.getImage() == null) {
            boatSpotTwo.setImage(image);
        } 


    }


    private void addFromLeftBank(HBox currentBank, ICrosser currentCrosser) {
        if (isBoatOnLeftBank) {
        	currentStrategy.saveState(currentStrategy);
            crossersOnBoat.add(currentCrosser);
            crossersOnLeftBank.remove(currentCrosser);
          currentStrategy.saveState(currentStrategy);
        } 

    }

    private void addFromRightBank(HBox currentBank, ICrosser currentCrosser) {
        if (!isBoatOnLeftBank) {
        	currentStrategy.saveState(currentStrategy);
            crossersOnBoat.add(currentCrosser);
            crossersOnRightBank.remove(currentCrosser);
            currentStrategy.saveState(currentStrategy);
        }
    }

    @FXML
    public void addCrosserToBoat(MouseEvent clicked) {
        StackPane crosserStack = (StackPane) clicked.getTarget();
        ImageView crosserImage = (ImageView) crosserStack.getChildren().get(0);
        Image currentImage = crosserImage.getImage();
        ICrosser currentCrosser = findRightOwner(currentImage);
        if (crossersOnBoat.size() != 2) {
            if (crossersOnRightBank.contains(currentCrosser) && isBoatOnLeftBank == false) {

                //we add them form right bank
                addFromRightBank(rightBankHbox, currentCrosser);
                rightBankHbox.getChildren().remove(crosserStack);
                addImageToBoat(currentImage);
               
            } else if (crossersOnLeftBank.contains(currentCrosser) && isBoatOnLeftBank == true) {
            	
                //we add them from left bank
                addFromLeftBank(leftBankHbox, currentCrosser);
                leftBankHbox.getChildren().remove(crosserStack);
                addImageToBoat(currentImage);
           
            } 
          

        } 
    }
    
    private void unloadToRightBank(Image image, ICrosser crosserToRemove) {
    	currentStrategy.saveState(currentStrategy);
        crossersOnBoat.remove(crosserToRemove);
        crossersOnRightBank.add(crosserToRemove);
        addImageToBank(image, rightBankHbox, crosserToRemove);
        currentStrategy.saveState(currentStrategy);
    }

    private void unloadToLeftBank(Image image, ICrosser crosserToRemove) {
    	currentStrategy.saveState(currentStrategy);
        crossersOnBoat.remove(crosserToRemove);
        crossersOnLeftBank.add(crosserToRemove);
        addImageToBank(image, leftBankHbox, crosserToRemove);
        currentStrategy.saveState(currentStrategy);
    }

    public void removeCrossersFromBoat(MouseEvent clicked) {
        ImageView crosserImage = (ImageView) clicked.getTarget();
        Image currentImage = crosserImage.getImage();
        if (currentImage == null) {
        
            return;
        }
  
        ICrosser crosser = findRightOwner(currentImage);
        if (!fromLeftToRight) {
            unloadToRightBank(currentImage, crosser);
            crosserImage.setImage(null);
        } else {
            unloadToLeftBank(currentImage, crosser);
            crosserImage.setImage(null);
        }
    
    }

    private void addImageToBank(Image image, HBox bankHbox, ICrosser crosser) {
        ImageView imageView = new ImageView();
        StackPane stackPane = new StackPane();
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addCrosserToBoat(event);
            }
        });


        this.label = new Label(crosser.getLabelToBeShown());
      
        label.setTextFill(Color.web("#B97A57"));

        stackPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                imageView.setFitWidth(prefImageSize * 1.5);
                imageView.setFitHeight(prefImageSize * 1.5);
                label.setScaleX(3);
                label.setScaleY(3);
            }
        });

        stackPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                imageView.setFitWidth(prefImageSize);
                imageView.setFitHeight(prefImageSize);
                label.setScaleX(1);
                label.setScaleY(1);
            }
        });

        imageView.setMouseTransparent(true);
        label.setMouseTransparent(true);

        imageView.setPreserveRatio(false);
        imageView.setFitWidth(prefImageSize);
        imageView.setFitHeight(prefImageSize);
        imageView.setImage(image);
        stackPane.getChildren().addAll(imageView, label);
        bankHbox.getChildren().add(stackPane);
    }


    public void saveGame() {
        try {
            currentStrategy.saveGame();
            Alert conformationBox = new Alert(AlertType.INFORMATION);
            conformationBox.setTitle("Game saved");
            conformationBox.setHeaderText(null);
            conformationBox.setContentText("Game saved successfully");
            conformationBox.showAndWait();
        } catch (Exception e) {
            currentStrategy.saveGame();
            Alert conformationBox = new Alert(AlertType.ERROR);
            conformationBox.setTitle("Error");
            conformationBox.setHeaderText("Saving Error!");
            conformationBox.setContentText("Error while saving game");
            conformationBox.showAndWait();
        }
    }

    public void loadGame() {

        currentStrategy.loadGame(); //Gets what is stored in the XML file from the controller.
        
        if(currentStrategy.isCanLoad()) {
        myRightImages.clear();
        clears();  //Clears the Hboxes, and removes any images on boat.
        crossersOnRightBank = currentStrategy.getCrossersOnRightBank();
        crossersOnLeftBank = currentStrategy.getCrossersOnLeftBank();
        crossersOnBoat = currentStrategy.getCrossersOnBoat();


        for (int i = 0; i < crossersOnRightBank.size(); i++) {
            loadImagesR(crossersOnRightBank.get(i)); //loads images from R bank
            this.label = new Label(crossersOnRightBank.get(i).getLabelToBeShown());
            label.setTextFill(Color.web("#B97A57"));
        }
        for (int i = 0; i < crossersOnLeftBank.size(); i++) {
        	
            loadImagesL(crossersOnLeftBank.get(i)); //loads images from L bank
            this.label = new Label(crossersOnLeftBank.get(i).getLabelToBeShown());
            label.setTextFill(Color.web("#B97A57"));
            
        }


        for (int i = 0; i < crossersOnBoat.size(); i++) {
            Image image = toFXImage(crossersOnBoat.get(i).getImages()[0], null);
            myRightImages.put(image, crossersOnBoat.get(i));
            addImageToBoat(image);
            this.label = new Label(crossersOnBoat.get(i).getLabelToBeShown());
            label.setTextFill(Color.web("#B97A57"));
        }
        isBoatOnLeftBank = currentStrategy.isBoatOnTheLeftBank();
        fromLeftToRight = isBoatOnLeftBank; //To know which position the boat is left at.

        if (!isBoatOnLeftBank) river.setAlignment(Pos.BOTTOM_RIGHT); //Changes the boat position
        else if (isBoatOnLeftBank) river.setAlignment(Pos.BOTTOM_LEFT); //Changes the boat position

        }
        else
        {
        	
             Alert conformationBox = new Alert(AlertType.ERROR);
             conformationBox.setTitle("Error");
             conformationBox.setHeaderText("Loading Error!");
             conformationBox.setContentText("Not possible to load");
             conformationBox.showAndWait();
        }
    }

    //Clears everything on the HBoxes that contain that crossers images.
    private void clears() {
        rightBankHbox.getChildren().clear();
        leftBankHbox.getChildren().clear();
        boatSpotOne.setImage(null);
        boatSpotTwo.setImage(null);
    }

    //Loads images from left bank, and right bank.
    private void loadImagesL(ICrosser loaded) {

        myCrossers.add(loaded);
        Image rightImage = toFXImage(loaded.getImages()[0], null);
        Image leftImage = toFXImage(loaded.getImages()[1], null);
        myRightImages.put(rightImage, loaded);
        myLeftImages.put(rightImage, leftImage);
        myLeftCrossersAndImages.put(leftImage, loaded);
        addImageToBank(rightImage, leftBankHbox, loaded);
    }

    private void loadImagesR(ICrosser loaded) {
        myCrossers.add(loaded);
        Image rightImage = toFXImage(loaded.getImages()[0], null);
        Image leftImage = toFXImage(loaded.getImages()[1], null);
        myRightImages.put(rightImage, loaded);
        myLeftImages.put(rightImage, leftImage);
        myLeftCrossersAndImages.put(leftImage, loaded);
        addImageToBank(rightImage, rightBankHbox, loaded);
    }

//// Unimplemented methods
//When undo button is clicked
    public void undoLastAction() {
   
            currentStrategy.undo();

            clears();
            myRightImages.clear();
            
            for (int i = 0; i < currentStrategy.getCrossersOnRightBank().size(); i++) loadImagesR(currentStrategy.getCrossersOnRightBank().get(i)); //loads images from R bank
            for (int i = 0; i < currentStrategy.getCrossersOnBoat().size(); i++) {
                Image image = toFXImage(currentStrategy.getCrossersOnBoat().get(i).getImages()[0], null);
                addImageToBoat(image);
                myRightImages.put(image, currentStrategy.getCrossersOnBoat().get(i));
                
            }

            
            
           

            for (int i = 0; i < currentStrategy.getCrossersOnLeftBank().size(); i++) loadImagesL(currentStrategy.getCrossersOnLeftBank().get(i)); //loads images from L bank


           
            isBoatOnLeftBank = currentStrategy.isBoatOnTheLeftBank(); fromLeftToRight=isBoatOnLeftBank; //To know which position the boat is left at.
            if (isBoatOnLeftBank == false) river.setAlignment(Pos.BOTTOM_RIGHT); //Changes the boat position
            else if (isBoatOnLeftBank == true) river.setAlignment(Pos.BOTTOM_LEFT); //Changes the boat position
            
      
        
    }


    public void redoLastAction() {
        
       
            currentStrategy.redo();
            
            clears();
            myRightImages.clear();
            
            myRightImages.clear();
            myLeftImages.clear();
            myLeftCrossersAndImages.clear();
            for (int i = 0; i < currentStrategy.getCrossersOnRightBank().size(); i++) loadImagesR(currentStrategy.getCrossersOnRightBank().get(i)); //loads images from R bank

            for (int i = 0; i < currentStrategy.getCrossersOnLeftBank().size(); i++) loadImagesL(currentStrategy.getCrossersOnLeftBank().get(i)); //loads images from L bank

           
            for (int i = 0; i < currentStrategy.getCrossersOnBoat().size(); i++) {
                Image image = toFXImage(currentStrategy.getCrossersOnBoat().get(i).getImages()[0], null);
                myRightImages.put(image, currentStrategy.getCrossersOnBoat().get(i));
                addImageToBoat(image);
                
            }
            isBoatOnLeftBank = currentStrategy.isBoatOnTheLeftBank(); fromLeftToRight=isBoatOnLeftBank; //To know which position the boat is left at.
            if (isBoatOnLeftBank == false) river.setAlignment(Pos.BOTTOM_RIGHT); //Changes the boat position
            else if (isBoatOnLeftBank == true) river.setAlignment(Pos.BOTTOM_LEFT); //Changes the boat position

       
        
    }

    //When solveGame button is clicked
    public void solveGame() {
            currentStrategy.saveState(currentStrategy);
            currentStrategy.solveGame();

    }

    /////////////////////////////////////////
    public void resetGame() {
        currentStrategy.resetGame();

        crossersOnRightBank = currentStrategy.getCrossersOnRightBank();
        crossersOnLeftBank = currentStrategy.getCrossersOnLeftBank();
        crossersOnBoat = currentStrategy.getCrossersOnBoat();

        river.setAlignment(Pos.BOTTOM_RIGHT);
        isBoatOnLeftBank = currentStrategy.isBoatOnTheLeftBank();
        fromLeftToRight = isBoatOnLeftBank;

        rightBankHbox.getChildren().clear();
        leftBankHbox.getChildren().clear();

        boatSpotOne.setImage(null);
        boatSpotTwo.setImage(null);

        for (int i = 0; i < crossersOnRightBank.size(); i++) {
            ICrosser currentCrosser = crossersOnRightBank.get(i);
            myCrossers.add(currentCrosser);
            Image rightimage = toFXImage(currentCrosser.getImages()[0], null);
            Image leftimage = toFXImage(currentCrosser.getImages()[1], null);
            myRightImages.put(rightimage, currentCrosser);
            myLeftImages.put(rightimage, leftimage);
            myLeftCrossersAndImages.put(leftimage, currentCrosser);
            addImageToBank(rightimage, rightBankHbox, currentCrosser);
        }


    }

    public void refreshAll() {
        rightBankHbox.getChildren().clear();
        leftBankHbox.getChildren().clear();

        boatSpotOne.setImage(null);
        boatSpotTwo.setImage(null);

        crossersOnRightBank = currentStrategy.getCrossersOnRightBank();
        crossersOnLeftBank = currentStrategy.getCrossersOnLeftBank();


        for (int i = 0; i < crossersOnRightBank.size(); i++) {
            myCrossers.add(crossersOnRightBank.get(i));
            Image image = toFXImage(crossersOnRightBank.get(i).getImages()[0], null);
            addImageToBank(image, rightBankHbox, crossersOnRightBank.get(i));
        }

        for (int i = 0; i < crossersOnLeftBank.size(); i++) {
            myCrossers.add(crossersOnLeftBank.get(i));
            Image image = toFXImage(crossersOnLeftBank.get(i).getImages()[0], null);
            addImageToBank(image, leftBankHbox, crossersOnLeftBank.get(i));

        }

    }


    @FXML
    public void getInstructions() {
        String[] strings = currentStrategy.getInstructions();

        Alert instructionsInfo = new Alert(AlertType.INFORMATION);
        instructionsInfo.setTitle("Game Rules");
        instructionsInfo.setHeaderText("How this Story works");

        StringBuilder context = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            context.append(strings[i]);
        }
        instructionsInfo.setContentText(context.toString());
        instructionsInfo.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	currentStrategy.saveState(currentStrategy);
        scoreLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                scoreLabel.setScaleX(1.5);
                scoreLabel.setScaleY(1.5);
            }
        });

        scoreLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                scoreLabel.setScaleX(1);
                scoreLabel.setScaleY(1);
            }
        });

        boatSpotOne.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                boatSpotOne.setFitWidth(75);
                boatSpotOne.setFitHeight(80);
            }
        });

        boatSpotOne.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                boatSpotOne.setFitWidth(50);
                boatSpotOne.setFitHeight(60);
            }
        });
        boatSpotTwo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                boatSpotTwo.setFitWidth(75);
                boatSpotTwo.setFitHeight(80);
            }
        });

        boatSpotTwo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                boatSpotTwo.setFitWidth(50);
                boatSpotTwo.setFitHeight(60);
            }
        });

        undoButton.setTooltip(new Tooltip("Undo"));
        redoButton.setTooltip(new Tooltip("Redo"));
        saveButton.setTooltip(new Tooltip("Save"));
        loadButton.setTooltip(new Tooltip("Load"));

        crossersOnRightBank = currentStrategy.getCrossersOnRightBank();
        crossersOnLeftBank = currentStrategy.getCrossersOnLeftBank();
        crossersOnBoat = currentStrategy.getCrossersOnBoat();

        currentStrategy.addObserver(scoreLabel);


        for (int i = 0; i < crossersOnRightBank.size(); i++) {

            ICrosser currentCrosser = crossersOnRightBank.get(i);
            myCrossers.add(currentCrosser);
            Image rightimage = toFXImage(currentCrosser.getImages()[0], null);
            Image leftimage = toFXImage(currentCrosser.getImages()[1], null);
            myRightImages.put(rightimage, currentCrosser);
            myLeftImages.put(rightimage, leftimage);
            myLeftCrossersAndImages.put(leftimage, currentCrosser);
            addImageToBank(rightimage, rightBankHbox, currentCrosser);


        }
    }
}
