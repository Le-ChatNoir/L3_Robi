package Exercices;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import graphicLayer.Morph;
import graphicLayer.World;

public class Exercice2 implements KeyListener{
	World w = new World("Robi world", new Dimension(200,200));
	
	void run() {
		Morph robi = new Morph();
		w.addMorph(robi);
		w.open();
		w.addKeyListener(this);

	}
	
	public static void main(String args[]) {
		Exercice2 exo = new Exercice2();
		exo.run();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Integer code = e.getKeyCode();
		Morph robi = w.contents().get(0);
		Point P = robi.getPosition();
		int worldWidth = (int) w.getWidth();
		int worldHeight = (int) w.getHeight();
		int robiWidth = robi.getWidth();
		System.out.println("Key pressed");
		//Flèche gauche
		if(code == 37 && (P.x > 0))
			robi.moveLeft(1);
		//Flèche haut
		if(code == 38 && (P.y > 0))
			robi.moveUp(1);
		//Flèche droite
		if(code == 39 && (P.x < worldWidth - robiWidth))
			robi.moveRight(1);
		//Flèche bas
		if(code == 40 && (P.y < worldHeight - robiWidth))
			robi.moveDown(1);
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
