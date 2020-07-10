package Crossers;

import javafx.scene.image.Image;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;

public class FairytaleSheep extends Herbivore {
    public FairytaleSheep(int eatingRank) {
        canCross=true;
        weight=20;
        this.eatingRank=eatingRank;
        setLabelToBeShown(Integer.toString(eatingRank));

        crosserImages[0] = fromFXImage(new Image
                        ("resources/sheep-art-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/sheep-art-left.png"),
                this.crosserImages[1]);
        System.out.println("created a herbivore");

    }
}
