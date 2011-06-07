


/**
 * Grid is "the world" in the Game of Life
 * 
 * @author jkk
 * 
 */
public class Grid
{
    /**
     * dimX - Number of rows in the grid
     */
    private int dimX;

    /**
     * dimY - Number of columns in the grid
     */
    private int dimY;

    /**
     * cells - 2d array of cells in the grid
     */
    public Cell[][] cells;

    /**
     * Constructor to instantiate the World Grid
     * 
     * @param dimX
     *            - Dimension along x-axis
     * @param dimY
     *            - Dimension along y-axis
     */
    public Grid(int dimX, int dimY) {
	this.dimX = dimX;
	this.dimY = dimY;
	cells = new Cell[dimX][dimY];
	populateDeadCells();
    }

    /**
     * Populate the whole grid with dead cells
     */
    public void populateDeadCells()
    {
	for (int i = 0; i < dimX; i++) {
	    for (int j = 0; j < dimY; j++) {
		cells[i][j] = new Cell('D');
	    }
	}
    }
    
    public void populate(char[][] c)
    {
    	for(int i = 0; i < c.length; i ++)
    	{
    		for(int j = 0; j < c.length; j ++)
    		{
    			if(c[i][j] == 'A')
    			{
    				cells[i][j].setAlive(true);
    			}
    		}
    	}
    }
    
    /**
     * Cell is the unit of Life in the grid. It is either alive or dead.
     * 
     * @author jkk
     * 
     */
    class Cell
    {
	/**
	 * alive - stores the alive/dead state of the cell
	 */
	private boolean alive;

	/**
	 * Default constructor - Initializes the cell as alive
	 */
	public Cell() {
	    this.alive = true;
	}

	/**
	 * Non-default constructor - Initializes cell status from parameter
	 * alive
	 * 
	 * @param alive
	 *            - Boolean alive/dead status of the cell
	 */
	public Cell(boolean alive) {
	    this.alive = alive;
	}

	/**
	 * More readable constructor syntax for creating alive/dead cells
	 * 
	 * @param c
	 *            - if 'a' or 'A', the cell is alive; dead otherwise
	 */
	public Cell(char c) {
	    if (c == 'a' || c == 'A')
		this.alive = true;
	    else
		this.alive = false;
	}

	/**
	 * @return boolean value of whether the cell is alive
	 */
	public boolean isAlive()
	{
	    return alive;
	}

	/**
	 * Set alive/dead state of the cell
	 * 
	 * @param alive
	 *            - boolean value whether cell should be alive/dead
	 */
	public void setAlive(boolean alive)
	{
	    this.alive = alive;
	}

	/**
	 * Return a string representation of alive/dead status of cell.
	 */
	@Override
	public String toString()
	{
	    if (alive)
		return "+";
	    else
		return "-";
	}

	/**
	 * @return Cell object denoting next generation of the current cell
	 */
	public Cell getChild()
	{

	    return new Cell(true);
	}
    }

    /**
     * Returns a String representation of the Grid
     */
    @Override
    public String toString()
    {
	StringBuilder gridstr = new StringBuilder();

	for (int i = 0; i < dimX; i++) {
	    for (int j = 0; j < dimY; j++) {
		gridstr.append(cells[i][j].toString());
		// gridstr.append("\t");
	    }
	    gridstr.append("\n");
	}
	return gridstr.toString();
    }

    /**
     * @param ix
     *            - x-index of the cell
     * @param iy
     *            - y-index of the cell
     * @return The number of alive neighbours a cell has
     */
    public int getAliveNeighbours(int ix, int iy)
    {
	int aliveNeighbours = 0;
	if (ix != 0) {
	    if (iy != 0) {
		if (cells[ix - 1][iy - 1].isAlive())
		    aliveNeighbours++;
	    }
	    if (iy != dimY - 1) {
		if (cells[ix - 1][iy + 1].isAlive())
		    aliveNeighbours++;
	    }
	    if (cells[ix - 1][iy].isAlive())
		aliveNeighbours++;
	}
	if (ix != dimX - 1) {
	    if (iy != 0) {
		if (cells[ix + 1][iy - 1].isAlive())
		    aliveNeighbours++;
	    }
	    if (iy != dimY - 1) {
		if (cells[ix + 1][iy + 1].isAlive())
		    aliveNeighbours++;
	    }
	    if (cells[ix + 1][iy].isAlive())
		aliveNeighbours++;
	}
	if (iy != 0) {
	    if (cells[ix][iy - 1].isAlive())
		aliveNeighbours++;
	}
	if (iy != dimY - 1) {
	    if (cells[ix][iy + 1].isAlive())
		aliveNeighbours++;
	}
	return aliveNeighbours;
    }

    /**
     * Non-idempotent method that updates the cells to the next generation. The
     * alive/dead status of next generation cells is determined by the number of
     * neighbours every cell has. The rules are:
     * <ol>
     * <li>Any live cell with fewer than two live neighbours dies, as if caused
     * by under-population.</li>
     * <li>Any live cell with two or three live neighbours lives on to the next
     * generation.</li>
     * <li>Any live cell with more than three live neighbours dies, as if by
     * overcrowding.</li>
     * <li>Any dead cell with exactly three live neighbours becomes a live cell,
     * as if by reproduction.</li>
     * </ol>
     */
    public void advance1Generation()
    {
	Cell[][] nextGenCells = new Cell[dimX][dimY];
	for (int i = 0; i < dimX; i++) {
	    for (int j = 0; j < dimY; j++) {
		switch (getAliveNeighbours(i, j)) {
		case 0:
		case 1:
		    nextGenCells[i][j] = new Cell('D');
		    break;
		case 2:
		    nextGenCells[i][j] = cells[i][j];
		    break;
		case 3:
		    nextGenCells[i][j] = new Cell('A');
		    break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		    nextGenCells[i][j] = new Cell('D');
		}
	    }
	}
	cells = nextGenCells;
    }

    /**
     * Driver used to test the Grid with sample input
     * @param args - Not used
     */
    /*public static void main(String args[])
    {
	Grid g = new Grid(10, 10);

	// Creating a Glider
	g.cells[2][0].setAlive(true);
	g.cells[2][1].setAlive(true);
	g.cells[2][2].setAlive(true);
	g.cells[1][2].setAlive(true);
	g.cells[0][1].setAlive(true);

	for (int generation = 0; generation < 10; generation++) {
	    System.out.println(g.toString());
	    g.advance1Generation();
	}*/
   // }
}
