package Exercices;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import commands.*;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

public class Exercice8{
	World w = new World("Robi world", new Dimension(200,200));
	Map <String,Command> commandByKey= new HashMap<String,Command>();
	ArrayList <Macro> macroList= new ArrayList<Macro>();
	Map <String,Morph> morphByName = new HashMap<String,Morph>();
	
	void run() {
		morphByName.put("robi", new Morph());
		w.addMorph(morphByName.get("robi"));
		w.open();
		
		commandByKey.put("robi l", new CommandLeft(morphByName.get("robi")));
		commandByKey.put("robi u", new CommandUp(morphByName.get("robi")));
		commandByKey.put("robi r", new CommandRight(morphByName.get("robi")));
		commandByKey.put("robi d", new CommandDown(morphByName.get("robi")));
		commandByKey.put("robi c", new CommandColor(morphByName.get("robi")));
		
		int worldWidth = (int) w.getWidth();
		int worldHeight = (int) w.getHeight();

		while(true) {
			
			String [] fin = Tools.readCmdAndArgs();
			
			//Commande dans la console
			if(commandByKey.containsKey(fin[0] + " " + fin[1])) {
				commandByKey.get(fin[0] + " " + fin[1]).run(new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[2]});
			}
			
			//Création de macro
			if(fin[0].equals("(")) {
				System.out.println("Création d'une macro...");
				String s;
				s = Tools.readKeyboard();
				String[] commandeAjout;
				Macro tmpMacro = new Macro(s, morphByName.get(fin[1]));
				macroList.add(tmpMacro);
				System.out.println("Macro " + s + " créée pour le robi " + fin[1] + ". Veuillez indiquer le set de commandes...");
				
				commandeAjout = Tools.readCmdAndArgs();
				while(!commandeAjout[0].equals(")")) {
					tmpMacro.addCmd(commandeAjout);
					commandeAjout = Tools.readCmdAndArgs();
				}
				System.out.println("Macro " + s + " créée avec succès !");
			}
			
			
			//Appel d'une macro
			for(Macro m : macroList) {
				if (m.nom.equals(fin[1]) && morphByName.get(fin[0]).equals(m.robi)) {
					System.out.println("Execution de la macro " + m.nom + " du robi " + fin[0]);
					for(String[] commande : m.cmds) {
						commandByKey.get(fin[0] + " " + commande[0]).run(new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), commande[1]});
					}
				}
			}
			
			//Création d'un nouveau Robi
			if(fin[0].equals("new")) {
				System.out.println("Création d'un nouveau Robi nommé " + fin[1]);
				morphByName.put(fin[1], new Morph());
				w.addMorph(morphByName.get(fin[1]));
				commandByKey.put(fin[1] + " l", new CommandLeft(morphByName.get(fin[1])));
				commandByKey.put(fin[1] + " u", new CommandUp(morphByName.get(fin[1])));
				commandByKey.put(fin[1] + " r", new CommandRight(morphByName.get(fin[1])));
				commandByKey.put(fin[1] + " d", new CommandDown(morphByName.get(fin[1])));
				commandByKey.put(fin[1] + " c", new CommandColor(morphByName.get(fin[1])));
				System.out.println("Robi " + fin[1] + " créé avec succès !");
				w.repaint();
			}
			
			//Suppression d'un Robi
			if(fin[0].equals("delete")) {
				if (morphByName.containsKey(fin[1])) {
					System.out.println("Suppression d'un Robi nommé " + fin[1]);
					w.removeMorph(morphByName.get(fin[1]));
					morphByName.remove(fin[1]);
					commandByKey.remove(fin[1] + " l");
					commandByKey.remove(fin[1] + " u");
					commandByKey.remove(fin[1] + " r");
					commandByKey.remove(fin[1] + " d");
					commandByKey.remove(fin[1] + " c");
					System.out.println("Robi " + fin[1] + " supprimé avec succès !");
					w.repaint();
				}else {
					System.out.println("Aucun robi de ce nom n'a été trouvé.");
				}
			}
		
		}
		
	}
	
	public static void main(String args[]) {
		Exercice8 exo = new Exercice8();
		exo.run();
	}
	
}
