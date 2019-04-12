package commands;

import graphicLayer.Morph;
import tools.Tools;

public class CommandColor extends Command{

	public CommandColor(Morph target) {
		super(target);
	}

	@Override
	public void run() {

	}

	@Override
	public void run(String[] args) {
		target.setColor(Tools.getColorByName(args[2]));
	}

}
