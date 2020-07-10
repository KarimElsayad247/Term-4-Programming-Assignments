package Crossers;
import Crossers.Crosser;
import Crossers.ICrosser;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;

public class Herbivore extends Crosser implements ICrosser {

    public Herbivore() {
        canCross=false;
        eatingRank=1;
        setLabelToBeShown(Integer.toString(eatingRank));

        crosserImages[0] = fromFXImage(new Image
                        ("resources/sheep-art-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/sheep-art-left.png"),
                this.crosserImages[1]);
        System.out.println("created a herbivore");
    }
    public Herbivore (double weight) {
        canCross=false;
        this.weight=weight;
        eatingRank=1;
        crosserImages[0] = fromFXImage(new Image
                        ("resources/sheep-art-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/sheep-art-left.png"),
                this.crosserImages[1]);
        System.out.println("created a herbivore");
    }
    public String toString() {
        return "Herbi";
    }
}
