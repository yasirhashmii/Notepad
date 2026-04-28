package java_notepad;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;

public class Function_File {
	GUI gui;
	String fileName, fileAddress;
	
	public Function_File(GUI gui) {
		this.gui = gui;
	}
	public void newFile() {
		gui.textArea.setText("");
		gui.window.setTitle("New");
	}
	public void openFile() {
		FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
		fd.setVisible(true);
		
		if(fd.getFile()!=null) {
			gui.textArea.setText("");
			fileName = fd.getFile();
			fileAddress = fd.getDirectory();
			gui.window.setTitle(fileName);
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileAddress+fileName));
			String line = null;
			
			while((line = reader.readLine())!=null) {
				gui.textArea.append(line + "\n");
			}
			reader.close();
			
		} catch(Exception e) {
			System.err.println("FIle could not be opened");
		}
		
		
	}
}