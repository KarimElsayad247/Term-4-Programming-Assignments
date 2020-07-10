package Level;

import java.util.ArrayList;
import java.util.List;

import Crossers.Herbivore;
import Crossers.Farmer;
import Crossers.ICrosser;

public class WeightProblemCrossingStrategy implements ICrossingStrategy {

    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,
                           List<ICrosser> boatRiders) {
        int totalweight = 0;
        for (ICrosser i : boatRiders) {
            totalweight += i.getWeight();
        }
        if (totalweight > 100) {
            return false;
        }
        for (int i = 0; i < boatRiders.size(); i++) {
            if (boatRiders.get(i).canSail() == true) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ICrosser> getInitialCrossers() {

        List<ICrosser> crossers = new ArrayList<ICrosser>();

        crossers.add(new Farmer(90));
        crossers.add(new Farmer(80));
        crossers.add(new Farmer(60));
        crossers.add(new Farmer(40));
        crossers.add(new Herbivore(20));

        for (ICrosser crosser : crossers) {
            crosser.setLabelToBeShown(Double.toString(crosser.getWeight()));
        }

        return crossers;
    }

    @Override
    public String[] getInstructions() {
        String instruction1 ="1- The boat cannot bear a load heavier than 100 kg\n\n";

        String instruction2 ="2- All farmers can raft, while the animal cannot\n\n";
        String instruction3 ="How can they all get to the other side with their animal?";
        String[] instructions = {instruction1, instruction2, instruction3};
        return instructions;
    }


    public boolean didWin(List<ICrosser> crossersOnLeftBank, List<ICrosser> crossersOnBoat, boolean isBoatOnLeftBank) {
        if (crossersOnLeftBank.size() == 5 || (crossersOnLeftBank.size() == 3
                && isBoatOnLeftBank && crossersOnBoat.size() == 2))
            return true;
        else
            return false;
    }
    
    public String toString() {
		return "level2";
	}
}
