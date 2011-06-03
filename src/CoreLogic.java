import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CoreLogic  
{ 
	int[] Xpoint;
	int[] Ypoint;
	
	public CoreLogic()
	{
		 Xpoint = new int[5000];
		 Ypoint = new int[5000];
	}
	
	char[][] acceptInput(int[] Xp, int[] Yp)
   // initial configuration for glider
	
	{
		int X, Y;
		char[][] grid = new char [50][100];
		for(int i = 0; i < Xp.length; i++)
		{
			
				X = Xp[i];
				Y = Yp[i];
			
			if((X == 6000) && (Y == 6000))
				grid[0][0] = 'A';
			
			else if((X == 0) && (Y == 0))
				continue;
			
			else
				grid[X][Y] = 'A';
		}
		/*grid[0][2] = 'A';
		grid[1][0] = 'A';
		grid[1][2] = 'A';
		grid[2][1] = 'A';
		grid[2][2] = 'A';*/
		
	   //finds out live and dead cells
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				if(grid[i][j]!='A')
				grid[i][j] = 'D';
				//System.out.print(grid[i][j]);
			}
			//System.out.println();
		}
		return grid;
	}
	
	char[][] calculateOutput(char[][] currentgrid)
	{
	   //calculates next generation of cells
		
		char[][] nextgrid = new char[50][100];	
		
	   //calculate neighbors for each cell in the grid
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 100; j++)
			{ 
		
				int c = 0; 
				
				List<Character> neighbors = new ArrayList<Character>();
				
				if((i != 0) && (j != 0))
					neighbors.add(currentgrid[i - 1][j - 1]);
				if(i != 0)
					neighbors.add(currentgrid[i - 1][j]);
				if((i != 0) && (j != 99))
					neighbors.add(currentgrid[i - 1][j + 1]);
				if(j != 0)
					neighbors.add(currentgrid[i][j - 1]);
				if((i != 49) && (j != 0))
					neighbors.add(currentgrid[i + 1][j - 1]);
				if(j != 99)
				 neighbors.add(currentgrid[i][j+1]);
				if(i != 49)
					neighbors.add(currentgrid[i + 1][j]);
				if((i != 49) && (j != 99))
					neighbors.add(currentgrid[i + 1][j + 1]);
				
				Iterator<Character> elements = neighbors.iterator();
				
			   //calculate number of live neigboring cells
				while(elements.hasNext())
				{
					if(elements.next().equals('A'))
						c = c + 1;	
				}
				
			   //cell dies due to under population	
				if((currentgrid[i][j] == 'A') && (c < 2))
					nextgrid[i][j] = 'D';
				
			   //cell lives to next generation
				else if((currentgrid[i][j] == 'A') && ((c==2)||(c==3)))
					nextgrid[i][j] = 'A';
				
			   //cell dies due to over population
				else if((currentgrid[i][j] == 'A') && (c>3))
					nextgrid[i][j] = 'D';
				
			   //dead cell becomes alive due to reproduction
				else if((currentgrid[i][j] == 'D') && (c==3))
					nextgrid[i][j] = 'A';
				
			   //the cell remains unaffected
				else 
					nextgrid[i][j] = currentgrid[i][j];
				
			}
		}
		/*for(int i=0;i<50;i++){
			for(int j=0;j<100;j++)
			{
				System.out.print(nextgrid[i][j]);
			}
			System.out.println();
		}*/
		return nextgrid;
	}
	
	void displayOutput(char[][] output)
	{
	   //displays the cells in the grid
		
		int k = 0;
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				if(output[i][j] == 'A')
				{
				Xpoint[k] = i;
				Ypoint[k] = j;
				//System.out.println(Xpoint[k]+","+Ypoint[k]);
				k++;
				}
				else
				{
					Xpoint[k] = 6000;
					Ypoint[k] = 6000;
					k++;
				}
			}
		 }
	}
	
	int[] getXCoordinate()
	{
		return Xpoint;
	}
	
	int[] getYCoordinate()
	{
		return Ypoint;
	}}

	/**
	 * @param args
	 */
	/*public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
	CoreLogic obj = new CoreLogic();
	char[][] outputarr = new char[5][5];
	outputarr = obj.acceptInput();
	obj.displayOutput(outputarr);
	
   //calculates and prints 10 generations of cells
	for( int i = 0; i <= 10; i++)
	{
		outputarr = obj.calculateOutput(outputarr);
		obj.displayOutput(outputarr);
	}
  }	
}*/