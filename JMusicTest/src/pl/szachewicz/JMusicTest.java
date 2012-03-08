package pl.szachewicz;

//==========================================================
//File:                 SimpleGUIApp4.java
//Function:             Demonstartes GUI passing of numerical args 
//                    to a jMusic program and window closing
//                    with file saving via dialog boxes, 
//                           and display of scores in ShowScore windows.
//Author:               Andrew Brown
//Environment           JDK1.1.7
//============================================================
import java.awt.*;
import java.awt.event.*;
import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.music.tools.*;


public class JMusicTest extends Frame implements ActionListener, WindowListener, JMC{	
	//--------------
	// Attributes shared within the class
	//--------------	TextField minPitch;
	TextField maxPitch;
	TextField numOfNotes;
	FileDialog fd;
	Score s = new Score("JMDemo - SimpleGUI_4");
   	Button composeBtn, showBtn, saveBtn;
	private TextField minPitch;
	//--------------
	// simple main method called when the class in run
	//--------------	
	public static void main(String[] args) {
	       new JMusicTest();
	}
	//--------------
	// constructor
	//--------------	
	public JMusicTest() {	       
		//give the window a name	       
		//super("An even more real Application 4");
		super();
	       	//register the closebox event	       
		this.addWindowListener(this);
	       	//set the layout for the Frame	       
		this.setLayout(new GridLayout(5, 2, 5, 0));
	       	//add the components	       
		Label minPL = new Label("Minimum MIDI Pitch", Label.RIGHT);
		this.add(minPL);
		
		minPitch = new TextField("50");
		this.add(minPitch);
		
		Label maxPL = new Label("Maximun MIDI Pitch", Label.RIGHT);
		this.add(maxPL);
		
		maxPitch = new TextField("70");
		this.add(maxPitch);
		
		Label numNL = new Label("Number of notes", Label.RIGHT);
		this.add(numNL);
		
		numOfNotes = new TextField("12");
		this.add(numOfNotes);
		
		Label dummy = new Label("", Label.RIGHT);
		this.add(dummy);

	       	//create a panel to put the button on
	       	// so it doesn't fill the grid space 
	       	Panel p = new Panel();

	       	//add the buttons	       
		composeBtn = new Button("Compose");
	       	composeBtn.addActionListener(this);
	       	composeBtn.setActionCommand("Create");
	       	p.add(composeBtn);
	       	
	       	showBtn = new Button("Display");
	       	showBtn.addActionListener(this);
	       	showBtn.setActionCommand("Display");
	       	p.add(showBtn);
	
	       	saveBtn = new Button("Save");
	       	saveBtn.addActionListener(this);
	       	saveBtn.setActionCommand("Save");
	       	p.add(saveBtn);
	       	
		//put the panel in the frame	       
		this.add(p);
	       	
		//display the window	       
		this.pack();                                                    
	       	this.show();
	}	
	
	//--------------
	// Class Methods
	//--------------

	// Deal with the window closebox	
	public void windowClosing(WindowEvent we) {
	       System.exit(0);
	} 
	
	//other WindowListener interface methods
	//They do nothing but are required to be present	
	public void windowActivated(WindowEvent we) {};
	public void windowClosed(WindowEvent we) {};
	public void windowDeactivated(WindowEvent we) {};
	public void windowIconified(WindowEvent we) {};
	public void windowDeiconified(WindowEvent we) {};
	public void windowOpened(WindowEvent we) {};

	// Deal with the button clicks	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == composeBtn) {
	       		makeMusic(Integer.valueOf(minPitch.getText()).intValue(), 
	                          Integer.valueOf(maxPitch.getText()).intValue(), 
	                          Integer.valueOf(numOfNotes.getText()).intValue());
	       } 
	       if (ae.getSource() == saveBtn) {
	               fd = new FileDialog(this, "Save jMusic MIDI file as...",
					   FileDialog.SAVE);
	               fd.show();
	               if (fd.getFile() != null) { //catch a cancel command
	                       	//write a MIDI file to disk	                       
	               }
				Write.midi(s, fd.getDirectory()+fd.getFile());
	       }
	       if (ae.getSource() == showBtn) {
	               View.show(s,10,170);
	       }
	}
	//--------------
	// The code that creates the jMusic score
	// and writes it as MIDI and jm files.
	//--------------	
	public void makeMusic(int minPitchVal, int maxPitchVal, int numOfNotesVal) {
	       	s.empty(); //empty the score first	       
		Part p = new Part("Piano", 1, 0);
	       	Phrase phr = new Phrase(0.0); 	       
		// the serious bit :) alter as desired	       
		for(short i=0;i<numOfNotesVal;i++){
	               	Note n = new Note((int)((Math.random()*
						 Math.abs(maxPitchVal-minPitchVal))+
							  minPitchVal), C);
	        	phr.addNote(n);
	       	}
	       	p.addPhrase(phr);
	       	s.addPart(p);	       
		//beep when done	       
		Toolkit.getDefaultToolkit().beep();
	}
}

