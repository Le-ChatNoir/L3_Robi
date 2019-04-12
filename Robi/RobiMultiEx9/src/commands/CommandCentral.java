package commands;

import elements.Robi;
import graphicLayer.World;
import tools.Tools;

public class CommandCentral {

		public void up(Robi r, String [] args) {
			int jump = Integer.valueOf(args[2]);
			
			if (r.graph.getContainer() == null && r.graph.getPosition().y - jump > 0) {
				r.graph.moveUp(jump);
			}
			else {
				if(r.graph.getPosition().y - jump > 0) {
					r.graph.moveUp(jump);	
				}
			r.lastCommand = "nil";
			}
		}
		
		
		public void left(Robi r, String [] args) {
			int jump = Integer.valueOf(args[2]);
			
			if(r.graph.getContainer() == null && r.graph.getPosition().x - jump > 0) {
				r.graph.moveLeft(jump);
			}
			else {
				if(r.graph.getPosition().x - jump > 0) {
					r.graph.moveLeft(jump);
				}
			r.lastCommand = "nil";
			}
		}
		
		
		public void down(Robi r, String [] args) {
			int wHeight=Integer.valueOf(args[1]);
			int jump=Integer.valueOf(args[2]);
			
			if (r.graph.getContainer() == null && (r.graph.getPosition().y + jump) < (wHeight - r.graph.getHeight())){
				r.graph.moveDown(jump);
			}
			else {
				if(r.graph.getPosition().y - jump < (r.graph.getContainer().getHeight()) ) {
					r.graph.moveDown(jump);
				}
			r.lastCommand = "nil";
			}
		}
		
		
		public void right(Robi r, String [] args) {
			int wWidth=Integer.valueOf(args[0]);
			int jump =Integer.valueOf(args[2]);

			if (r.graph.getContainer() == null && (r.graph.getPosition().x + jump) < (wWidth - r.graph.getWidth())) {
				r.graph.moveRight(jump);
			}
			else {
				if( r.graph.getPosition().x + jump + r.graph.getWidth() < (r.graph.getContainer().getWidth())) {
					r.graph.moveRight(jump);
				}
			r.lastCommand = "nil";
			}
		}
		
		
		public void color(Robi r, String [] args) {
			r.graph.setColor(Tools.getColorByName(args[2]));
			r.lastCommand = "nil";
			r.curColor = args[2];
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
				case "w":
						r.graph.setWidth(Integer.valueOf(fin[1]));
						w.repaint();
						break;
				case "h":
						r.graph.setHeight(Integer.valueOf(fin[1]));
						w.repaint();
						break;
				case "x":
						r.lastCommand = String.valueOf(r.graph.getX());
						System.out.println(r.getLastCommand());
						break;
				case "y":
						r.lastCommand = String.valueOf(r.graph.getY());
						System.out.println(r.getLastCommand());
						break;
				case "color":
						r.lastCommand = r.curColor;
						System.out.println(r.getLastCommand());
						break;
				case "last":
						System.out.println(r.getLastCommand());
						break;
				default:
						System.out.println("Cette commande n'existe pas...");
			}
				
		}
		
		
		
}
