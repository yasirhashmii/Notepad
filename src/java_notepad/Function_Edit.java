package java_notepad;

import javax.swing.undo.UndoManager;

public class Function_Edit {
	
	GUI gui;
	UndoManager undoManager = new UndoManager();
	
	public Function_Edit(GUI gui) {
		this.gui = gui;
	}
	

	
	public void redo() {
		if(undoManager.canRedo()) {
			undoManager.redo();
		}
	}
	public void undo() {
		if(undoManager.canUndo()) {
			undoManager.undo();
		}
	}
	
}
