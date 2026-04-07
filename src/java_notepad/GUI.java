package java_notepad;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI {
	
	JFrame window; //The main window for our notepad, initialized in createWindow()
	JTextArea textArea; //The area where we write the text, initialized in createTextArea()
	JScrollPane pane; //The scrolling pane
	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuFormat, menuColor;
	JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
	JMenuItem iRedo, iUndo;
	
	public static void main(String[] args) {
		new GUI(); // Class object, the main driver for our program.
	}
	
	public GUI() {
		createWindow(); //These methods are always called in the constructor of our main class.
		createTextArea();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		
		window.setVisible(true);
	}
	
	public void createWindow() {
		window = new JFrame("Notepad");
		window.setSize(600,400);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createTextArea() {
		
		textArea = new JTextArea();
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(false);
		
		pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBorder(BorderFactory.createEmptyBorder());
		
		window.add(pane);
	}
	public void createMenuBar() {
		
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		menuFormat = new JMenu("Format");
		menuColor = new JMenu("Color");
		
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuFormat);
		menuBar.add(menuColor);
	}
	
	public void createFileMenu() {
		iNew = new JMenuItem("New");
		menuFile.add(iNew);
		iSave = new JMenuItem("Save");
		menuFile.add(iSave);
		iSaveAs = new JMenuItem("Save as");
		menuFile.add(iSaveAs);
		iExit = new JMenuItem("Exit");
		menuFile.add(iExit);
	}
	
	public void createEditMenu() {
		iRedo = new JMenuItem("Redo");
		menuEdit.add(iRedo);
		iUndo = new JMenuItem("Undo");
		menuEdit.add(iUndo);
	}
	
}	