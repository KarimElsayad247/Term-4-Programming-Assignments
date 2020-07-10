package Level;

import Crossers.FairytaleSheep;
import Crossers.Farmer;
import Crossers.ICrosser;
import Crossers.FairytaleWolf;

import java.util.ArrayList;
import java.util.List;

public class WolvesAndSheep implements ICrossingStrategy{
    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
        int rightBankSum = 0;
        int leftBankSum = 0;
        int boatRidersSum = 0;


        if (boatRiders.size() == 0) {
            return false;
        }




        if (boatRiders.size() > 2) {
            System.out.println("can't cross, boat too full");
            return false;
        }

        for (ICrosser crosser: rightBankCrossers
             ) {
                rightBankSum = rightBankSum + crosser.getEatingRank();

        }for (ICrosser crosser: leftBankCrossers
             ) {
            leftBankSum = leftBankSum + crosser.getEatingRank();
        }for (ICrosser crosser: boatRiders
             ) {
            boatRidersSum = boatRidersSum + crosser.getEatingRank();
        }

        System.out.println("left bank sum: " + leftBankSum);
        System.out.println("right bamk sum: " + rightBankSum);
        System.out.println("boat sum: " + boatRidersSum);

        //TODO: try better nested conditions

        if (rightBankSum > 0) {
            if (boatRidersSum + leftBankSum != -30 ||  leftBankSum != -30) {
                return false;
            }
        }

        if (leftBankSum > 0) {
            if (boatRidersSum + rightBankSum != -30 || rightBankSum != -30) {
                return false;
            }
        }





//        if ( rightBankSum > 0 && leftBankSum + boatRidersSum < 0) {
//            return false;
//        }
//
//        if (( leftBankSum > 0 && rightBankSum+ boatRidersSum < 0 )){
//            return false;
//        }
//
//        if (( leftBankSum < 0 && rightBankSum + boatRidersSum > 0 && rightBankSum != 0)){
//            return false;
//        }
//
//        if ((rightBankSum < 0 && leftBankSum + boatRidersSum > 0 && leftBankSum != 0))
//        {
//            return false;
//        }
        return true;
    }


    @Override
    public List<ICrosser> getInitialCrossers() {

        List<ICrosser> crossers = new ArrayList<>();

        crossers.add(new FairytaleSheep(-10));
        crossers.add(new FairytaleSheep(-10));
        crossers.add(new FairytaleSheep(-10));

        crossers.add(new FairytaleWolf(10));
        crossers.add(new FairytaleWolf(10));
        crossers.add(new FairytaleWolf(10));

        for (ICrosser crosser: crossers)
        {
            crosser.setLabelToBeShown(Double.toString(crosser.getEatingRank()));
        }


        return crossers;
    }

    @Override
    public String[] getInstructions() {
        String instruction1 ="";
        String instruction2 ="";
        String instruction3 ="";
        String[] instructions = {instruction1, instruction2, instruction3};
        return instructions;
    }
    
    public String toString() {
		return "level3";
	}
}
