package Game;

import java.util.Stack;
public class UndoCareteker implements Cloneable {
	private Stack<UndoMemento> undo = new Stack<UndoMemento>();
	private Stack<UndoMemento> redo = new Stack<UndoMemento>();

	public UndoCareteker() {
	}

	@SuppressWarnings("unchecked")
	public UndoCareteker(UndoCareteker uc) {
		undo = (Stack<UndoMemento>) uc.undo.clone();
		redo = (Stack<UndoMemento>) uc.redo.clone();
	}

	synchronized public void SaveState(RiverCrossingController re) {
		UndoMemento memento = new UndoMemento(re);
		undo.push(memento);
		
	}

	synchronized public void getPreviousState(RiverCrossingController re) {
		UndoMemento u = undo.pop();
		re.setBoatOnTheLeftBank(u.isBoatOnTheLeftBank());
		re.getCrossersOnBoat().clear();
		re.getCrossersOnRightBank().clear();
		re.getCrossersOnLeftBank().clear();
		re.getCrossersOnBoat().addAll(u.getCrossersOnBoat());
		re.getCrossersOnLeftBank().addAll(u.getCrossersOnLeftBank());
		re.getCrossersOnRightBank().addAll(u.getCrossersOnRightBank());
		redo.push(u);
	}

	synchronized public void getNextState(RiverCrossingController re) {
		UndoMemento u = redo.pop();
		re.setBoatOnTheLeftBank(u.isBoatOnTheLeftBank());
		re.getCrossersOnBoat().clear();
		re.getCrossersOnRightBank().clear();
		re.getCrossersOnLeftBank().clear();
		re.getCrossersOnBoat().addAll(u.getCrossersOnBoat());
		re.getCrossersOnLeftBank().addAll(u.getCrossersOnLeftBank());
		re.getCrossersOnRightBank().addAll(u.getCrossersOnRightBank());
		undo.push(u);
	}

	public boolean isUndoEmpty() {
		return !(undo.size() == 0);
	}

	public boolean isRedoEmpty() {
		return !(redo.size() == 0);

	}
	public void flush()
	{
		redo.clear();
		undo.clear();
	}
}