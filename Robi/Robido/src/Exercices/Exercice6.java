package Exercices;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import commands.*;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

public class Exercice6{
	World w = new World("Robi world", new Dimension(200,200));
	Map <String,Command> commandByKey= new HashMap<String,Command>();
	ArrayList <Macro> macroList= new ArrayList<Macro>();
	
	void run() {
		Morph robi = new Morph();
		w.addMorph(robi);
		w.open();
		
		commandByKey.put("l", new CommandLeft(robi));
		commandByKey.put("u", new CommandUp(robi));
		commandByKey.put("r", new CommandRight(robi));
		commandByKey.put("d", new CommandDown(robi));
		commandByKey.put("c", new CommandColor(robi));
		
		int worldWidth = (int) w.getWidth();
		int worldHeight = (int) w.getHeight();

		while(true) {
			
			String [] fin = Tools.readCmdAndArgs();
			
			//Commande dans la console
			if(commandByKey.containsKey(fin[0])) {
				commandByKey.get(fin[0]).run(new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[1]});
			}
			
			//Création de macro
			if(fin[0].equals("(")) {
				System.out.println("Création d'une macro...");
				String s;
				s = Tools.readKeyboard();
				String[] commandeAjout;
				Macro tmpMacro = new Macro(s);
				macroList.add(tmpMacro);
				System.out.println("Macro " + s + " créée. Veuillez indiquer le set de commandes...");
				
				commandeAjout = Tools.readCmdAndArgs();
				while(!commandeAjout[0].equals(")")) {
					tmpMacro.addCmd(commandeAjout);
					commandeAjout = Tools.readCmdAndArgs();
				}
				System.out.println("Macro " + s + " créée avec succès !");
			}
			
			
			//Appel d'une macro
			for(Macro m : macroList) {
				if (m.nom.equals(fin[0])) {
					System.out.println("Execution de la macro " + m.nom);
					for(String[] commande : m.cmds) {
						commandByKey.get(commande[0]).run(new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), commande[1]});
					}
				}
			}
		
		}
		
	}
	
	public static void main(String args[]) {
		Exercice6 exo = new Exercice6();
		exo.run();
	}
	
}
