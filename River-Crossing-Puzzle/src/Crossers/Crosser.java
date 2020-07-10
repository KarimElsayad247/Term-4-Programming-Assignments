package Crossers;

import java.awt.image.BufferedImage;

public class Crosser implements ICrosser {
    protected double weight=0.0;
    protected int eatingRank;
    protected boolean canCross;
    protected String labelToBeShown;
    protected BufferedImage[] crosserImages = new BufferedImage[2];

    public Crosser() {

    }

    public Crosser(double weight, int eatingRank, boolean canCross) {
        this.weight = weight;
        this.eatingRank = eatingRank;
        this.canCross = canCross;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int getEatingRank() {
        return eatingRank;
    }

    @Override
    public BufferedImage[] getImages() {
        return crosserImages;
    }

    @Override
    public ICrosser makeCopy() {
        return new Crosser(weight, eatingRank, canCross);
    }

    @Override
    public void setLabelToBeShown(String Label) {
        this.labelToBeShown = Label;
    }

    public void setEatingRank(short eatingRank) {

        this.eatingRank = eatingRank;
    }

    public boolean canSail() {

        return canCross;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }


    public void setCanCross(boolean canCross) {

        this.canCross = canCross;
    }

    public String getLabelToBeShown() {
        return labelToBeShown;
    }
}
