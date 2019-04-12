package commands;

import graphicLayer.Morph;

public class CommandDown extends Command{

	public CommandDown(Morph target) {
		super(target);
	}

	@Override
	public void run() {
		target.moveDown(1);
		
	}

	@Override
	public void run(String[] args) {
		int wHeight=Integer.valueOf(args[1]);
		int jump=Integer.valueOf(args[2]);
		//Exercice 3/4
		/*
		if(target.getPosition().y < (wHeight - target.getWidth()))
			target.moveDown(1);
		*/
		//exercice 5
		
		if((target.getPosition().y + jump) < (wHeight - target.getWidth()))
			target.moveDown(Integer.valueOf(args[2]));
		
	}

}
