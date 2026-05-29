package java_notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI implements ActionListener {
	
	JFrame window; //The main window for our notepad, initialized in createWindow()
	JTextArea textArea; //The area where we write the text, initialized in createTextArea()
	JScrollPane pane; //The scrolling pane
	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuFormat, menuColor, menuMode;
	JMenuItem iNew, iOpen, iSave, iSaveAs, iExit; //For menuFie
	JMenuItem iRedo, iUndo; //For menuEdit
	JMenuItem iTypeWriter; //For menuMode
	
	
	Function_File file = new Function_File(this);
	Function_Edit edit = new Function_Edit(this);
	Function_Mode mode = new Function_Mode(this);
	Function_Format format = new Function_Format(this);
	
	public static void main(String[] args) {
		new GUI(); // Class object, the main driver for our program.
	}
	
	public GUI() {
		createWindow(); //These methods are always called in the constructor of our main class.
		createTextArea();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createFormatMenu();
		createModeMenu();
		
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
		textArea.setWrapStyleWord(true);
		
		textArea.getDocument().addUndoableEditListener(e -> {
			edit.undoManager.addEdit(e.getEdit());
		});
		
		textArea.addCaretListener(new javax.swing.event.CaretListener() {
			@Override
			public void caretUpdate(javax.swing.event.CaretEvent e) {
				if(mode.isTypeWriterMode) {
					mode.centerLineInscrollPane();
				}
			}
		});
		
		pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBorder(BorderFactory.createEmptyBorder()); //Removes the solid border between menu bar and text area
		
		window.add(pane);
	}
	
	public void createMenuBar() {
		
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		menuFormat = new JMenu("Format");
		menuColor = new JMenu("Color");
		menuMode = new JMenu("Mode");
		
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuFormat);
		menuBar.add(menuColor);		//JMenuBar -> JMenu -> JMenuItem
		menuBar.add(menuMode);
		
	}
	
	public void createFileMenu() {
		iNew = new JMenuItem("New");
		iNew.addActionListener(this);
		iNew.setActionCommand("New");
		menuFile.add(iNew);
		
		iOpen = new JMenuItem("Open");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);
		
		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);
		
		iSaveAs = new JMenuItem("Save as");
		iSaveAs.addActionListener(this);
		iSaveAs.setActionCommand("SaveAs");;
		menuFile.add(iSaveAs);
		
		iExit = new JMenuItem("Exit");
		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		menuFile.add(iExit);
	}
	
	public void createEditMenu() {
		iRedo = new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		menuEdit.add(iRedo);
		
		iUndo = new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		menuEdit.add(iUndo);
	}
	
	public void createFormatMenu() {
		JMenu menuFontSize = new JMenu("Font Size");
		
		java.awt.event.ActionListener sizeListener = new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int size = Integer.parseInt(e.getActionCommand());
				format.fontSize(size);
			}
		};
		for(int i=8;i<=36;i+=4) {
			JMenuItem sizeItem = new JMenuItem(String.valueOf(i));
			sizeItem.addActionListener(sizeListener);
			sizeItem.setActionCommand(String.valueOf(i));
			menuFontSize.add(sizeItem);
		}
		menuFormat.addSeparator();
		menuFormat.add(menuFontSize);
	}
	
	public void createModeMenu() {
		iTypeWriter = new JMenuItem("TypeWriter: Off");
		iTypeWriter.addActionListener(this);
		iTypeWriter.setActionCommand("TypeWriter");
		menuMode.add(iTypeWriter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
		case "New": file.newFile(); break;
		case "Open": file.openFile(); break;
		case "Save": file.save(); break;
		case "SaveAs": file.saveAs(); break;
		case "Exit": file.exit(); break;
		
		case "Redo": edit.redo(); break;
		case "Undo": edit.undo(); break;
		
		case "TypeWriter": mode.typeWriter(); break;
		
		}
		
	}
	
}	