package commands;

import graphicLayer.Morph;

public class CommandRight extends Command{

	public CommandRight(Morph target) {
		super(target);
	}

	@Override
	public void run() {
		target.moveRight(1);
	}

	@Override
	public void run(String[] args) {
		int wWidth=Integer.valueOf(args[0]);
		int jump =Integer.valueOf(args[2]);
		
		//Exercice 3/4
		/*
		if(target.getPosition().x < (wWidth - target.getWidth()))
			target.moveRight(1);
		*/
		if((target.getPosition().x + jump) < (wWidth - target.getWidth()))
			target.moveRight(jump);
	}


}
