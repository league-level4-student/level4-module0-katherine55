package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	int liveNeighbors;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] myshells;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = h / cpr;
		// 3. Initialize the cell array to the appropriate size.
		myshells = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < myshells.length; i++) {
			for (int j = 0; j < myshells[i].length; j++) {
				myshells[i][j] = new Cell(i * cellSize, j * cellSize, cellSize);
			}
		}

	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive member to true of false
		for (int i = 0; i < myshells.length; i++) {
			for (int j = 0; j < myshells[i].length; j++) {
				int rand = new Random().nextInt(2);
				if (rand == 0) {
					myshells[i][j].isAlive = true;
				} else {
					myshells[i][j].isAlive = false;
				}
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterated through the cells and set them all to dead.
		for (int i = 0; i < myshells.length; i++) {
			for (int j = 0; j < myshells[i].length; j++) {
				myshells[i][j].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int i = 0; i < myshells.length; i++) {
			for (int j = 0; j < myshells[i].length; j++) {
				myshells[i][j].draw(g);
			}
		}
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and get their neighbors
		for (int i = 0; i < myshells.length; i++) {
			for (int j = 0; j < myshells[i].length; j++) {
				myshells[i][j].liveOrDie(getLivingNeighbors(i, j));
			}
		}
		// 8. check if each cell should live or die

		repaint();
	}

	// 9. Complete the method.
	// It returns an array list of the 8 or less neighbors of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {
		liveNeighbors = 0;
		for (int k = x - 1; k <= x + 1; k++) {
			for (int f = y - 1; f <= y + 1; f++) {
				if (k >= 0 && k < myshells[0].length && f >= 0 && f < myshells.length && !(k == x && f == y)) {
					if (myshells[k][f].isAlive) {
						liveNeighbors++;
					} // setting bounds for neighbors
				}
			}
		}
		return liveNeighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int i = e.getX() / cellSize;
		int j = e.getY() / cellSize;
		// 10. Use e.getX() and e.getY() to determine
		if (myshells[i][j].isAlive) {
			myshells[i][j].isAlive = false;
		} else {
			myshells[i][j].isAlive = true;
		}
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
