package java_notepad;

import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

public class Function_Mode {
	
	GUI gui;
	boolean isTypeWriterMode = false;
	
	public Function_Mode(GUI gui) {
		this.gui = gui;
	}
	public void typeWriter() {
		isTypeWriterMode = !isTypeWriterMode;
		
		if(isTypeWriterMode) {
			gui.iTypeWriter.setText("Typewiter: On");
			
			int halfHeight = gui.window.getHeight() / 2;
			gui.textArea.setMargin(new Insets(halfHeight,10,halfHeight,10));
			
			centerLineInscrollPane();
		}
		else {
			gui.iTypeWriter.setText("Typewriter: Off");
			gui.textArea.setMargin(new Insets(0, 0, 0, 0));
			gui.pane.getVerticalScrollBar().setValue(0);
		}
	}
	public void centerLineInscrollPane() {
		
		SwingUtilities.invokeLater(() ->{
			try {
				
				//To get the y coordinate of the cursor
				int caretPos = gui.textArea.getCaretPosition();
				Rectangle caretRect = gui.textArea.modelToView2D(caretPos).getBounds();
				
				//Find out where the scrollbar needs to be
				int viewPortHeight = gui.pane.getViewport().getHeight();
				int targetY = caretRect.y - (viewPortHeight/2) + (caretRect.height/2);
				
				//Move the scrollbar
				gui.pane.getVerticalScrollBar().setValue(Math.max(0, targetY));
				
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	
}
