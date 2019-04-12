package commands;

import graphicLayer.Morph;

public class CommandUp extends Command{

	public CommandUp(Morph target) {
		super(target);
	}

	@Override
	public void run() {
		
	}

	@Override
	public void run(String[] args) {
		int jump = Integer.valueOf(args[2]);
		// Exercice 3/4
		/*
		if(target.getPosition().y > 0)
			target.moveUp(1);
		*/
		//exercice 5
		if(target.getPosition().y - jump > 0)
			target.moveUp(jump);		
	}

}
