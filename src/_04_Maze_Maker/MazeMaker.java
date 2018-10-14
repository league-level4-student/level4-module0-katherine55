package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);
		// 4. select a random cell to start
		selectNextPath(maze.getCell(randGen.nextInt(w), randGen.nextInt(h)));
		// 5. call selectNextPath method with the randomly selected cell
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		// C. if has unvisited neighbors,
		if (getUnvisitedNeighbors(currentCell).size() > 0) {
			int rant = randGen.nextInt(getUnvisitedNeighbors(currentCell).size());
			Cell rando = getUnvisitedNeighbors(currentCell).get(rant);
			uncheckedCells.push(rando);
			removeWalls(currentCell, rando);
			rando.setBeenVisited(true);
			selectNextPath(rando);
		}
		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited
		// D. if all neighbors are visited
		if (getUnvisitedNeighbors(currentCell).size() == 0) {
			if (uncheckedCells.size() != 0) {
				Cell now = uncheckedCells.pop();
				now.setBeenVisited(true);
				selectNextPath(now);
			}
		}
		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c2.getX() > c1.getX()) {
			c2.setWestWall(false);
			c1.setEastWall(false);
		}
		if (c2.getX() < c1.getX()) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
		if (c2.getY() > c1.getY()) {
			c2.setNorthWall(false);
			c1.setSouthWall(false);
		}
		if (c2.getY() < c1.getY()) {
			c2.setSouthWall(false);
			c1.setNorthWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> poss = new ArrayList<Cell>();
		if (c.getX() != 0) {
			if (!maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited()) {
				poss.add(maze.getCell(c.getX() - 1, c.getY()));
			}
		}

		if (c.getY() != height - 1) {
			if (!maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited()) {
				maze.getCell(c.getX(), c.getY() + 1);
			}
		}

		if (c.getX() != width - 1) {
			if (!maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited()) {
				maze.getCell(c.getX() + 1, c.getY());
			}
		}

		if (c.getY() != 0) {
			if (!maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited()) {
				maze.getCell(c.getX(), c.getY() - 1);
			}
		}

		return poss;
	}
}
