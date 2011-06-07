import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class InteractiveCUI {

	public void acceptInput()
	{
		System.out.println("Enter the no. of rows and columns");
		Scanner input = new Scanner(System.in);
		int rows = input.nextInt();
		int cols = input.nextInt();
		System.out.println("Enter the no. of generations");
		int generations = input.nextInt();
		InputStreamReader ir = new InputStreamReader(System.in);
		Grid g = new Grid(rows,cols);
		char[][] user_input = new char[rows][cols];
		try
		{
			for(int i = 0; i < rows; i ++)
			{
				for(int j = 0; j < cols; j ++)
				{
					user_input[i][j] = (char)(ir.read());
					
				}
			}
		}
		
		 catch (IOException e)
		 {
			
			e.printStackTrace();
		}
		 
		 g.populate(user_input);
		 
		 for(int i = 0; i < generations; i ++)
		 {
		 System.out.println(g.toString());
		 g.advance1Generation();
		 }
	}
	
	public static void main(String[] args)
	{
		InteractiveCUI ic = new InteractiveCUI();
		ic.acceptInput();
	}
}
