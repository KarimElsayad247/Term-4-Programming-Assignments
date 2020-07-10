package Game;
import java.util.ArrayList;
import java.util.List;

import Crossers.ICrosser;

public class UndoMemento {
	private boolean BoatOnTheLeftBank;
	private List<ICrosser> CrossersOnRightBank = new ArrayList<ICrosser>();
	private List<ICrosser> CrossersOnLeftBank = new ArrayList<ICrosser>();
	private List<ICrosser> CrossersOnBoat = new ArrayList<ICrosser>();
	public UndoMemento(boolean boatOnTheLeftBank, List<ICrosser> crossersOnRightBank, List<ICrosser> crossersOnLeftBank,List<ICrosser> crossersOnBoat) {
		BoatOnTheLeftBank = boatOnTheLeftBank;
		CrossersOnRightBank = crossersOnRightBank;
		CrossersOnLeftBank = crossersOnLeftBank;
		CrossersOnBoat = crossersOnBoat;
	}
	public UndoMemento(RiverCrossingController re) {
		BoatOnTheLeftBank = re.isBoatOnTheLeftBank();
		CrossersOnRightBank.addAll(re.getCrossersOnRightBank());
		CrossersOnLeftBank.addAll(re.getCrossersOnLeftBank());
		CrossersOnBoat.addAll(re.getCrossersOnBoat());
	}
	public boolean isBoatOnTheLeftBank() {
		return BoatOnTheLeftBank;
	}
	public void setBoatOnTheLeftBank(boolean boatOnTheLeftBank) {
		BoatOnTheLeftBank = boatOnTheLeftBank;
	}
	public List<ICrosser> getCrossersOnRightBank() {
		return CrossersOnRightBank;
	}
	public void setCrossersOnRightBank(List<ICrosser> crossersOnRightBank) {
		CrossersOnRightBank = crossersOnRightBank;
	}
	public List<ICrosser> getCrossersOnLeftBank() {
		return CrossersOnLeftBank;
	}
	public void setCrossersOnLeftBank(List<ICrosser> crossersOnLeftBank) {
		CrossersOnLeftBank = crossersOnLeftBank;
	}
	public List<ICrosser> getCrossersOnBoat() {
		return CrossersOnBoat;
	}
	public void setCrossersOnBoat(List<ICrosser> crossersOnBoat) {
		CrossersOnBoat = crossersOnBoat;
	}



}
