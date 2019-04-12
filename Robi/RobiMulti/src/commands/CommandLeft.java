package commands;

import graphicLayer.Morph;

public class CommandLeft extends Command{

	public CommandLeft(Morph target) {
		super(target);
	}

	@Override
	public void run() {
		target.moveLeft(1);
		
	}

	@Override
	public void run(String[] args) {
		int jump = Integer.valueOf(args[2]);
		//Exercice 3/4
		/*
		if(target.getPosition().x > 0)
			target.moveLeft(1);
		*/
		//exercice 5
		
		if(target.getPosition().x - jump > 0)
			target.moveLeft(jump);
	}

}
