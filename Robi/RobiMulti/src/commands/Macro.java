package commands;

import java.util.ArrayList;

import graphicLayer.Morph;

public class Macro {
	
	public Morph robi;
	public String nom;
	public ArrayList <String[]> cmds;
	
	public Macro(String n, Morph r) {
		robi = r;
		nom=n;
		cmds = new ArrayList<String[]>();
	}
	
	public void addCmd(String [] c) {
		cmds.add(c);
	}
}
