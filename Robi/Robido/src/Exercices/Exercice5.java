package Exercices;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import commands.*;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

public class Exercice5 {
	World w = new World("Robi world", new Dimension(200,200));
	Map <String,Command> commandByKey= new HashMap<String,Command>();
	
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
			
			if(commandByKey.containsKey(fin[0])) {
				commandByKey.get(fin[0]).run(new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[1]});
			}
		}
	}
	
	public static void main(String args[]) {
		Exercice5 exo = new Exercice5();
		exo.run();
	}


}
