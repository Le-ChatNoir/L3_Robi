package Exercices;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

public class Exercice1 {
	World w = new World("Robi world", new Dimension(200,200));
	
	void run() {
		Morph robi = new Morph();
		w.addMorph(robi);
		w.open();
		int robiWidth = robi.getWidth();

		while (true) {
			Tools.sleep(400);
			int worldWidth = (int) w.getWidth();
			int worldHeight = (int) w.getHeight();
			// a completer
			Point P;
			
			//Mouvement vers la droite du World
			P = robi.getPosition();
			while(P.x <= (worldWidth - robiWidth)) {
				robi.moveRight(robiWidth);
				Tools.sleep(400);
				P = robi.getPosition();
			}
			
			//Mouvement vers le bas du world
			P = robi.getPosition();
			while(P.y <= (worldHeight - robiWidth)) {
				robi.moveDown(robiWidth);
				Tools.sleep(400);
				P = robi.getPosition();
			}
			
			//Mouvement vers la gauche du world
			P = robi.getPosition();
			while(P.x >= robiWidth) {
				robi.moveLeft(robiWidth);
				Tools.sleep(400);
				P = robi.getPosition();
			}
			
			//Mouvement vers le haut du world
			P = robi.getPosition();
			while(P.y >= robiWidth) {
				robi.moveUp(robiWidth);
				Tools.sleep(400);
				P = robi.getPosition();
			}
			
			//Changement de couleur
			robi.setColor(new Color ((int) (Math.random() * 0x1000000)));
		}
	}
	
	public static void main(String args[]) {
		Exercice1 exo = new Exercice1();
		exo.run();
	}

}
