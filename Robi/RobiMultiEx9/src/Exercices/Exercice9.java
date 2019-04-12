package Exercices;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import commands.CommandCentral;
import commands.Macro;
import elements.Robi;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

@SuppressWarnings("unused")
public class Exercice9{
	World w = new World("Robi world", new Dimension(200,200));
	Map <String,Robi> robiByName = new HashMap<String,Robi>();
	

	void run() {
		robiByName.put("r1",new Robi("r1", w));
		robiByName.get("r1").graph.setWidth(100);
		robiByName.get("r1").graph.setHeight(100);
		robiByName.get("r1").graph.setColor(Color.red);
		robiByName.get("r1").graph.setX(20);
		w.open();
		CommandCentral c = new CommandCentral();
		

		while(true) {
			
			String [] fin = Tools.readCmdAndArgs();
			
			/*Si la ligne de commande commence par le nom d'un robi...*/
			if(robiByName.containsKey(fin[0])) {
				/*Correspond a une macro*/
				if(robiByName.get(fin[0]).containsMacro(fin[1])) {
					System.out.println("Execution de la macro " + fin[1] + " du robi " + fin[0]);
					Macro tempMacro = robiByName.get(fin[0]).getMacro(fin[1]);
						for(String[] commande : tempMacro.cmds) {
							c.run(robiByName.get(fin[0]), w, commande);
						}
				} else {
					/*Run basique de commandes*/
					if(fin.length>2) {
						c.run(robiByName.get(fin[0]), w,new String[] {fin[1],fin[2]});
						w.repaint();
					}
					else
						System.out.println("Cette macro n'existe pas pour ce robi...");
				}
			}
			
			/*Création de macro*/
			if(fin[0].equals("(")) {
				String s;
				s = Tools.readKeyboard();
				robiByName.get(fin[1]).addMacro(s);
			}
			
			//Création d'un nouveau Robi
			if(fin[0].equals("new")) {
				if (fin.length < 3) {
					System.out.println("Création d'un nouveau robi nommé " + fin[1]);
					robiByName.put(fin[1],new Robi(fin[1], w));
				}
				if (fin.length > 2) {
					System.out.println("Création d'un nouveau robi nommé " + fin[1] + " dans le world " + fin[2]);
					robiByName.put(fin[1],new Robi(fin[1], w,robiByName.get(fin[2]).graph));
					robiByName.get(fin[2]).graph.addSubmorph(robiByName.get(fin[1]).graph);
				}
			}
			
			//Suppression d'un Robi
			if(fin[0].equals("delete")) {
				ArrayList<String> subRemove=new ArrayList<String>();
				if (robiByName.containsKey(fin[1])) {
					System.out.println("Suppression d'un Robi nommé " + fin[1]);
					if(robiByName.get(fin[1]).graph.getContainer()!=null) {
						//suppression du robi des submorphs de son container
						robiByName.get(fin[1]).graph.getContainer().delSubmorph(robiByName.get(fin[1]).graph);
					}
					//Suppression des submorphs du robi à supprimer
					for( String s: robiByName.keySet()) {
						if (!robiByName.get(fin[1]).graph.getSubmorphs().isEmpty()) {
							if (robiByName.get(fin[1]).graph.getSubmorphs().contains(robiByName.get(s).graph)) {
								subRemove.add(s);
							}
						}
					}
					for (String s : subRemove) {
						robiByName.remove(s);
						System.out.println("Suppression du sous robi " + s);
					}
					robiByName.get(fin[1]).delete(w);
					robiByName.remove(fin[1]);
					w.repaint();
				}else {
					System.out.println("Aucun robi de ce nom n'a été trouvé.");
				}
			}
			

		}
	}
	
	public static void main(String args[]) {
		Exercice9 exo = new Exercice9();
		exo.run();
	}
	
}
