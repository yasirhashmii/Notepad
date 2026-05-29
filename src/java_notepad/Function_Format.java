package java_notepad;

import java.awt.Font;

public class Function_Format {
	
	GUI gui;
	Function_Format(GUI gui){
		this.gui = gui;
	}
	public void fontSize(int size) {
		Font currentFont = gui.textArea.getFont();
		Font newFont = currentFont.deriveFont((float) size);
		gui.textArea.setFont(newFont);
	}
}
