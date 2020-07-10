package Crossers;

import javafx.scene.image.Image;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;

public class FairytaleWolf extends Carnivore {

    public FairytaleWolf(int eatingRank) {
        canCross=true;
        weight=20;
        this.eatingRank=eatingRank;
        setLabelToBeShown(Integer.toString(eatingRank));
        crosserImages[0] = fromFXImage(new Image
                        ("resources/wolf-transparent-right.png"),
                this.crosserImages[0]);
        crosserImages[1] = fromFXImage(new Image
                        ("resources/wolf-transparent-left.png"),
                this.crosserImages[1]);

        System.out.println("created a canrivore");
    }

}
