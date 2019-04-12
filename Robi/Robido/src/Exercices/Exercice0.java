package Exercices;


import java.awt.Color;
import java.awt.Dimension;

import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

public class Exercice0 {
	// Creation du RobiWorld
	World w = new World("Robi world", new Dimension (200,200));

	void run() {
		Morph robi = new Morph();
		w.addMorph(robi);
		// Ouverture du RobiWorld
		w.open();
		// Un peu de tempo pour qu'on ait le temps de voir quelque chose
		Tools.sleep(800);
		// Deplacement à droite de 40 points
		robi.moveRight(40);
		// un peu de tempo, etc...
		Tools.sleep(800);
		robi.moveDown(60);
		Tools.sleep(800);
		robi.moveLeft(10);
		Tools.sleep(800);
		robi.moveUp(30);
		Tools.sleep(800);
		// Change de couleur avec une couleur aléatoire
		robi.setColor(new Color ((int) (Math.random() * 0x1000000)));
		Tools.sleep(400);
		robi.setColor(new Color ((int) (Math.random() * 0x1000000)));
		Tools.sleep(400);
		robi.setColor(new Color ((int) (Math.random() * 0x1000000)));
	}
	
	public static void main(String args[]) {
		Exercice0 exo = new Exercice0();
		exo.run();
	}

}
