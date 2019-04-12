package elements;

import java.util.ArrayList;

import commands.Macro;
import graphicLayer.Morph;
import graphicLayer.World;
import tools.Tools;

public class Robi {
	public String nom;
	public Morph graph;
	public ArrayList<Macro> macro;
	public String lastCommand;
	public String curColor = "blue";

	public Robi(String n,World w) {
		this.nom=n;
		this.graph=new Morph();
		w.addMorph(this.graph);
		this.macro = new ArrayList<Macro>();
		this.graph.setContainer(null);
		this.lastCommand = this.nom + " est en (0,0)";
		w.repaint();
		System.out.println("Robi "  + this.nom + " créé !");
	}

	public Robi(String n,World w,Morph container) {
		this.nom=n;
		this.graph=new Morph();
		this.macro = new ArrayList<Macro>();
		this.graph.setContainer(container);
		this.lastCommand = this.nom + " est en (0,0)";
		w.repaint();
		System.out.println("Robi "  + this.nom + " créé !");
	}

	public void delete(World w) {
		w.removeMorph(this.graph);
		w.repaint();
		System.out.println("Le Robi " + this.nom + " a bien été supprimé.");
	}

	public void addMacro(String n) {
		Macro tmpMacro = new Macro(n);
		this.macro.add(tmpMacro);
		System.out.println("Création d'une macro...");
		String[] commandeAjout;

		commandeAjout = Tools.readCmdAndArgs();
		while(!commandeAjout[0].equals(")")) {
			tmpMacro.addCmd(commandeAjout);
			commandeAjout = Tools.readCmdAndArgs();
		}
		System.out.println("Macro " + n + " créée pour le robi " + this.nom + ".");
	}


	public boolean containsMacro(String name) {
		for(Macro m : this.macro) {
			if (m.nom.equals(name))
				return true;
		}
		return false;
	}

	public Macro getMacro(String name) {
		for(Macro m : this.macro) {
			if (m.nom.equals(name))
				return m;
		}
		return null;
	}

	public String getLastCommand() {
		return this.lastCommand;
	}
}
