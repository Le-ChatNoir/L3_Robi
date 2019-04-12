package Exercices;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import commands.*;
import graphicLayer.Morph;
import graphicLayer.World;

public class Exercice3 implements KeyListener{
	World w = new World("Robi world", new Dimension(200,200));
	
	void run() {
		Morph robi = new Morph();
		w.addMorph(robi);
		w.open();
		w.addKeyListener(this);

	}
	
	public static void main(String args[]) {
		Exercice3 exo = new Exercice3();
		exo.run();
	}
	
	public Command getCommandFromKeyCode(int code) {
		System.out.println("Key pressed");
		Morph robi = w.contents().get(0);
		
		//Flèche gauche
		if(code == 37)
			return new CommandLeft(robi);
		//Flèche haut
		if(code == 38)
			return new CommandUp(robi);
		//Flèche droite
		if(code == 39)
			return new CommandRight(robi);
		//Flèche bas
		if(code == 40)
			return new CommandDown(robi);
		
		return null;
	}
 
	@Override
	public void keyPressed(KeyEvent e) {
		Command command = getCommandFromKeyCode(e.getKeyCode());
		if (command != null) {
			int worldWidth = (int) w.getWidth();
			int worldHeight = (int) w.getHeight();
			command.run(new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight)});
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
