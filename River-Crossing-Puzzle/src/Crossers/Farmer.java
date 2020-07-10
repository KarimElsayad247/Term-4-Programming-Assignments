package Crossers;


import javafx.scene.image.Image;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;

public class Farmer extends Crosser implements ICrosser  {

    public Farmer() {
        canCross=true;
        eatingRank=10;
        setLabelToBeShown(Integer.toString(eatingRank));
        crosserImages[0] = fromFXImage(new Image
                        ("resources/Farmer-port-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/Farmer-port-left.png"),
                this.crosserImages[1]);
        System.out.println("created a farmer");

    }
    public Farmer (double weight) {
        canCross=true;
        this.weight=weight;
        eatingRank=10;
        crosserImages[0] = fromFXImage(new Image
                        ("resources/Farmer-port-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/Farmer-port-left.png"),
                this.crosserImages[1]);
        System.out.println("created a farmer");
    }
    public String toString() {
        return "Farmer";
    }

}
