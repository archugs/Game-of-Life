import java.awt.Color;

import javax.swing.JButton;


public class ButtonHandler {

	private static final int NO_OF_ROWS= 60;
	private static final int NO_OF_COLS = 100;
	
	Grid g;
	
	public ButtonHandler()
	{
		g = new Grid(NO_OF_ROWS, NO_OF_COLS);
	}
	
	public int[][] goNext()
	{
		g.advance1Generation();
		int[][] index = new int[NO_OF_ROWS][NO_OF_COLS];
		int ix = 0, iy ;
		for(int i = 0; i < NO_OF_ROWS; i ++)
		{
			for(int j = 0; j < NO_OF_COLS; j ++)
			{
				if(g.cells[i][j].isAlive())
				{
					iy = 0;
					index[ix][iy] = i;
					iy++;
					index[ix][iy] = j;
					ix++;
				}
			}
		}
		
		return index;
		
	}
	
	public void populateCell(int ix, int iy)
	{
		g.cells[ix][iy].setAlive(true);
		
	}
}
