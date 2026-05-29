package java_notepad;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {
	GUI gui;
	String fileName, fileAddress;
	
	public Function_File(GUI gui) {
		this.gui = gui;
	}
	
	public void newFile() {
		gui.textArea.setText("");
		gui.window.setTitle("New");
		fileName = null;
		fileAddress = null;
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
			System.err.println("File could not be opened");
		}
		
		
	}
	public void save() {
		if(fileName == null) {
			saveAs();
		}else {
			try {
				FileWriter fw = new FileWriter(fileAddress+fileName);
				fw.write(gui.textArea.getText());
				gui.window.setTitle(fileName);
				fw.close();
			}catch(Exception e) {
				System.err.print("Something went wrong");
			}
		}
	}
	
	public void saveAs() {
		FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
		fd.setVisible(true);
		
		if(fd.getFile()!=null) {
			fileName = fd.getFile();
			fileAddress = fd.getDirectory();
			gui.window.setTitle(fileName);
		}
		try {
			FileWriter fw = new FileWriter(fileAddress+fileName);
			fw.write(gui.textArea.getText());
			fw.close();
			
		} catch(Exception e) {
			System.err.print("Something went wrong");
		}
	}
	public void exit() {
		System.exit(0);
		
	}
	
}








