package Crossers;

import Crossers.Crosser;
import Crossers.ICrosser;

import java.awt.image.BufferedImage;


public class Animal extends Crosser implements ICrosser {

    public Animal() {

    }

    public Animal(double weight) {
        this.weight = weight;
    }


}
