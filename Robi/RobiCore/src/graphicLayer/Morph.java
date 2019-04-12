
package graphicLayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tools.Observable;
import tools.Observer;

public class Morph implements Observable {
	protected Morph container;
	protected World world;
	protected Rectangle bounds;
	protected Color color;
	protected List<Morph> submorphs = new ArrayList<Morph>();
	protected List<Observer> observers = new ArrayList<Observer>();

	public Morph(Color color, Point pos, Dimension dim) {
		this(pos, dim);
		this.color = color;
	}
	
	public Morph(Point pos, Dimension dim) {
		this(dim);
		setPosition(pos);
	}
	
	public List<Morph> getSubmorphs(){
		return this.submorphs;
	}
	
	public Morph(Dimension dim) {
		this.bounds = new Rectangle(dim);
	}
	
	public Morph() {
		this.bounds = new Rectangle(0,0, 20,20);
		this.color = Color.blue;
	}

	public void setWorld(World w) {
		world = w;
	}

	public Morph getContainer() {
		return container;
	}
	
	public void setContainer(Morph aContainer) {
		container = aContainer;
	}
	
	public void setColor (Color c) {
		color = c;
		if (world != null)
			world.repaint();		
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
		g.setColor(c);
		if (submorphs.isEmpty()) {
			return;
		}
		Shape currentClip = g.getClipBounds();
		g.setClip(getX(), getY(), getWidth(), getHeight());
		g.translate(getX(), getY());
		Iterator<Morph> itor = submorphs.iterator();
		while (itor.hasNext()) {
			Morph m = itor.next();
			m.draw(g);
		}
		g.translate(-getX(), -getY());
		g.setClip(currentClip);
	}

	public Rectangle getBounds() {
		return (Rectangle) bounds.clone();
	}
	
	public void clearSubmorphs() {
		this.submorphs.clear();
	}
	
	public Rectangle getGlobalBounds() {
		return (Rectangle) bounds.clone();
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void setWidth(Integer width) {
		this.setBounds(new Rectangle(getX(), getY(), width, this.getHeight()));
	}
	
	public void setHeight(Integer height) {
		this.setBounds(new Rectangle(getX(), getY(), this.getWidth(), height));
	}

	public void addSubmorph(Morph m) {
		if (submorphs.contains(m))
			return;
		submorphs.add(m);
		m.setContainer(this);
	}
	
	public void delSubmorph(Morph m) {
		if (!submorphs.contains(m))
			return;
		submorphs.remove(m);
		m.setContainer(null);
		m.setWorld(world);
	}

	public Point getPosition() {
		Point p = bounds.getLocation();
		return p;
	}
	
	public Point getGlobalPosition() {
		Point p = new Point(bounds.getLocation());
		if (container == null) {
			return p;
		}
		p.translate(container.getGlobalPosition().x, container.getGlobalPosition().y);
		return p;
	}

	public void setPosition(Point p) {
		bounds.x = p.x;
		bounds.y = p.y;
		if (world != null)
			world.repaint();
		notifyObservers();	
	}

	public int getWidth() {
		return (int) bounds.getWidth();
	}
	public int getHeight() {
		return (int) bounds.getHeight();
	}
	
	public void setX(int x) {
		Point p = getPosition();
		setPosition(new Point(x, p.y));
	}

	public int getX() {
		return (getPosition().x);
	}

	public void setY(int y) {
		Point p = getPosition();
		setPosition(new Point(p.x, y));
	}

	public int getY() {
		return (getPosition().y);
	}

	public void moveRight(int gap) {
		setX(getX() + gap);
	}

	public void moveLeft(int gap) {
		setX(getX() - gap);
	}

	public void moveUp(int gap) {
		setY(getY() - gap);
	}

	public void moveDown(int gap) {
		setY(getY() + gap);
	}
	

	@Override
	public void notifyObservers() {
		if (observers == null) return;
		Iterator<Observer> itor = observers.iterator();
		while (itor.hasNext()) {
			itor.next().updateFrom(this);
		}
	}

	@Override
	public void register(Observer obs) {
		if (! observers.contains(obs)) observers.add(obs);
	}

	@Override
	public void unregister(Observer obs) {
		if (observers.contains(obs)) observers.remove(obs);	
	}

	
}
