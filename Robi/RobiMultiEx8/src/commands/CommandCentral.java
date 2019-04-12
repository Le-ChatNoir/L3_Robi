package commands;

import elements.Robi;
import graphicLayer.World;
import tools.Tools;

public class CommandCentral {

		public void up(Robi r, String [] args) {
			int jump = Integer.valueOf(args[2]);

			if(r.graph.getPosition().y - jump > 0)
				r.graph.moveUp(jump);	
		}
		
		
		public void left(Robi r, String [] args) {
			int jump = Integer.valueOf(args[2]);
			
			if(r.graph.getPosition().x - jump > 0)
				r.graph.moveLeft(jump);
		}
		
		
		public void down(Robi r, String [] args) {
			int wHeight=Integer.valueOf(args[1]);
			int jump=Integer.valueOf(args[2]);
			
			if((r.graph.getPosition().y + jump) < (wHeight - r.graph.getWidth()))
				r.graph.moveDown(Integer.valueOf(args[2]));
		}
		
		
		public void right(Robi r, String [] args) {
			int wWidth=Integer.valueOf(args[0]);
			int jump =Integer.valueOf(args[2]);

			if((r.graph.getPosition().x + jump) < (wWidth - r.graph.getWidth()))
				r.graph.moveRight(jump);
		}
		
		
		public void color(Robi r, String [] args) {
			r.graph.setColor(Tools.getColorByName(args[2]));
		}
		
		public void run(Robi r, World w, String[] fin) {
			int worldWidth = (int) w.getWidth();
			int worldHeight = (int) w.getHeight();
			
			switch(fin[0]) {
				
				case "l":
						this.left(r, new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[1]});
						Tools.sleep(100);
						break;
				case "r":
						this.right(r, new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[1]});
						Tools.sleep(100);
						break;
				case "u":
						this.up(r, new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[1]});
						Tools.sleep(100);
						break;
				case "d":
						this.down(r, new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[1]});
						Tools.sleep(100);
						break;
				case "c":
						this.color(r, new String[]{Integer.toString(worldWidth), Integer.toString(worldHeight), fin[1]});
						break;
				default:
						System.out.println("Cette commande n'existe pas...");
			}
				
		}
		
		
		
}
