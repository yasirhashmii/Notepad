package java_notepad;

import javax.swing.undo.UndoManager;

public class Function_Edit {
	
	GUI gui;
	UndoManager undoManager = new UndoManager();
	
	public Function_Edit(GUI gui) {
		this.gui = gui;
	}
	

	
	public void redo() {
		gui.textArea.getDocument().addUndoableEditListener(e -> {
			undoManager.addEdit(e.getEdit());
		});
		if(undoManager.canRedo()) {
			undoManager.redo();
		}
	}
	public void undo() {
		gui.textArea.getDocument().addUndoableEditListener(e -> {
			undoManager.addEdit(e.getEdit());
		});
		if(undoManager.canUndo()) {
			undoManager.undo();
		}
	}
	
}
