package graphicLayer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private List<Morph> drawables = new LinkedList<Morph>();
	private List<KeyListener> keyListeners = new LinkedList<KeyListener>();

	String name = "";

	public World(String name, Dimension dim) {
		this.name = name;
		super.addKeyListener(this);
		this.setPreferredSize(dim);
	}

	public List<Morph> contents() {
		return drawables;
	}

	public void open() {
		JFrame frame = new JFrame(name);
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		frame.addWindowListener(wa);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		requestFocus();
	}

	public void addMorph(Morph d) {
		drawables.add(d);
		d.setWorld(this);
	}

	public void removeMorph(Morph d) {
		drawables.remove(d);
		d.setWorld(null);
		d.setContainer(null);
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (Iterator<Morph> iter = drawables.iterator(); iter.hasNext();) {
			iter.next().draw(g);
		}
	}

	public void addKeyListener(KeyListener k) {
		keyListeners.add(k);
	}

	public void clear() {
		for (Iterator<Morph> iter = drawables.iterator(); iter.hasNext();) {
			iter.next().setWorld(null);
		}
		drawables.clear();
	}

	public List<Morph> find(Point p) {
		List<Morph> l = new ArrayList<Morph>();
		for (Iterator<Morph> iter = drawables.iterator(); iter.hasNext();) {
			Morph element = iter.next();
			if (element.getBounds().contains(p)) {
				l.add(element);
			}
		}
		return l;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyTyped(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyReleased(e);
		}
	}

}