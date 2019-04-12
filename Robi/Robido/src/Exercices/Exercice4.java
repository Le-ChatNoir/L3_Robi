package Exercices;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import commands.*;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

public class Exercice4{
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
		
		int worldWidth = (int) w.getWidth();
		int worldHeight = (int) w.getHeight();

		String s = "";
		s = Tools.readKeyboard();
		while(!s.equals("stop")) {
			if(commandByKey.containsKey(s)) {
				commandByKey.get(s).run(new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight)});
				
			}
			s = Tools.readKeyboard();
		}

	}
	
	public static void main(String args[]) {
		Exercice4 exo = new Exercice4();
		exo.run();
	}


}
