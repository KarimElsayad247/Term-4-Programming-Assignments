package Crossers;

import Crossers.Crosser;
import Crossers.ICrosser;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;

public class Carnivore extends Crosser implements ICrosser {

    public Carnivore() {
        canCross=false;
        weight=20;
        eatingRank=2;
        setLabelToBeShown(Integer.toString(eatingRank));
        crosserImages[0] = fromFXImage(new Image
                        ("resources/wolf-transparent-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/wolf-transparent-left.png"),
                this.crosserImages[1]);

        System.out.println("created a canrivore");

    }
    public Carnivore(double weight) {
        canCross=false;
        this.weight=weight;
        eatingRank=2;
        crosserImages[0] = fromFXImage(new Image
                        ("resources/wolf-transparent-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/wolf-transparent-left.png"),
                this.crosserImages[1]);

        System.out.println("created a canrivore");
    }
    public String toString() {
        return "Carni";
    }
}
