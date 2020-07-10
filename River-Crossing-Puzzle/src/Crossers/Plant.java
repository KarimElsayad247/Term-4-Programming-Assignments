package Crossers;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;

public class Plant extends Crosser implements ICrosser{

    public Plant() {
        canCross=false;
        eatingRank=0;
        setLabelToBeShown(Integer.toString(eatingRank));

        crosserImages[0] = fromFXImage(new Image
                        ("resources/plant2.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/plant2.png"),
                this.crosserImages[1]);
        System.out.println("created a plant");

    }
    public Plant (double weight) {
        canCross=false;
        this.weight=weight;
        eatingRank=0;

        crosserImages[0] = fromFXImage(new Image
                        ("resources/plant2.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/plant2.png"),
                this.crosserImages[1]);
        System.out.println("created a plant");

    }

    public String toString() {
        return "Plant";
    }

}
