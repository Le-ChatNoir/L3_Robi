package commands;

import graphicLayer.Morph;

public abstract class Command {
	Morph target;
	
	public Command (Morph target) {
		this.target = target;
	}
	Morph getTarget() { return target; }
	
	abstract public void run();
	
	//args[wWidth;wHeight;jump/color]
	abstract public void run(String [] args);
}
