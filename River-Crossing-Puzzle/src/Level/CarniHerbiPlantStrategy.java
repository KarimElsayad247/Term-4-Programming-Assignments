package Level;

import Crossers.*;
import Crossers.Carnivore;
import Crossers.Herbivore;

import java.util.ArrayList;
import java.util.List;

public class CarniHerbiPlantStrategy implements ICrossingStrategy {

	@Override
	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,
			List<ICrosser> boatRiders) {

		if (boatRiders.size() > 2) {
			System.out.println("can't cross, boat too full");
			return false;
		}
		if (rightBankCrossers.size() == 2) {
			if (Math.abs(rightBankCrossers.get(0).getEatingRank() - rightBankCrossers.get(1).getEatingRank()) == 1) {
				System.out.println("Can't cross, bad stuff happens on right bank");
				return false;
			}
		}
		if (leftBankCrossers.size() == 2) {
			if (Math.abs(leftBankCrossers.get(0).getEatingRank() - leftBankCrossers.get(1).getEatingRank()) == 1) {
				System.out.println("Can't cross, bad stuff happens on left bank");
				return false;
			}
		}

		if (rightBankCrossers.size() > 2) {
			System.out.println("right bank has 3 pops");
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

		Farmer farmer = new Farmer();
		Carnivore carni = new Carnivore();
		Herbivore herbi = new Herbivore();
		Plant plant = new Plant();

		crossers.add(farmer);
		crossers.add(carni);
		crossers.add(herbi);
		crossers.add(plant);

		for (ICrosser crosser : crossers) {
			crosser.setLabelToBeShown(Double.toString(crosser.getEatingRank()));
		}

		return crossers;
	}

	@Override
	public String[] getInstructions() {
		String instruction1 = "1- The farmer is the only one who can sail the boat. "
				+ "He can only take one passenger, in addition to himself.\n\n";
		String instruction2 = "2- You can not leave any two crossers on the same bank if they can harm(eat) each other.\n\n";
		String instruction3 = "How can the farmer get across the river with all the 2 animals and the plant without any losses?";
		String[] instructions = { instruction1, instruction2, instruction3 };
		return instructions;
	}

	public boolean didWin(List<ICrosser> crossersOnLeftBank, List<ICrosser> crossersOnBoat, boolean isBoatOnLeftBank) {
		if (crossersOnLeftBank.size() == 3
				|| (crossersOnLeftBank.size() == 2 && isBoatOnLeftBank && crossersOnBoat.size() == 2))
			return true;
		else
			return false;
	}

	public String toString() {
		return "level1";
	}
}