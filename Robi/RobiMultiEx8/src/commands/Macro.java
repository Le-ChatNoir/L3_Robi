package commands;

import java.util.ArrayList;

public class Macro {
	
	public String nom;
	public ArrayList <String[]> cmds;
	
	public Macro(String n) {
		nom=n;
		cmds = new ArrayList<String[]>();
	}
	
	public void addCmd(String [] c) {
		cmds.add(c);
	}
}
