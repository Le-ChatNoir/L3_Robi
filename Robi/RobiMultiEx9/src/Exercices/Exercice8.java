package Exercices;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import commands.CommandCentral;
import commands.Macro;
import elements.Robi;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

@SuppressWarnings("unused")
public class Exercice8{
	World w = new World("Robi world", new Dimension(200,200));
	Map <String,Robi> robiByName = new HashMap<String,Robi>();
	

	@SuppressWarnings("unlikely-arg-type")
	void run() {
		robiByName.put("r1",new Robi("r1", w));
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
					if(fin.length>2)
						c.run(robiByName.get(fin[0]), w,new String[] {fin[1],fin[2]});
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
				System.out.println("Création d'un nouveau Robi nommé " + fin[1]);
				robiByName.put(fin[1],new Robi(fin[1], w));
			}
			
			//Suppression d'un Robi
			if(fin[0].equals("delete")) {
				if (robiByName.containsKey(fin[1])) {
					System.out.println("Suppression d'un Robi nommé " + fin[1]);
					robiByName.get(fin[1]).delete(w);
					robiByName.remove(robiByName.get(fin[1]));
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
