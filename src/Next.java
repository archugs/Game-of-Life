import java.awt.Color;

import javax.swing.JButton;

/**
 * 
 */

/**
 * @author user
 *
 */
public class Next {
	
	int[] rowno = new int[5000];
	int[] colno = new int[5000];
	
	public void goNext(JButton[] b)
	{
	int j = 0;
	for(int i = 0; i < 5000; i++)
	{
		if(b[i].getBackground() == Color.YELLOW)
		{	
			if((b[i].getX() == 0) && (b[i].getY() == 0))
			{
				rowno[j] = 6000;
				colno[j] = 6000;
			}
			else
			{
				rowno[j] = (b[i].getY())/10;
				colno[j] = (b[i].getX())/10;
				j++;
			}
			
		}
	}
	CoreLogic cl = new CoreLogic();
	char[][] inputarr = cl.acceptInput(rowno, colno);
	char[][] outputarr = cl.calculateOutput(inputarr);
	cl.displayOutput(outputarr);
	int[] Xpoint = cl.getXCoordinate();
	int[] Ypoint = cl.getYCoordinate();
	for(int i = 0; i < 5000; i++)
	{
		b[i].setBackground(null);
	}
	for(int i = 0; i < Xpoint.length; i++)
	{
	
		int X = Xpoint[i];
		int Y = Ypoint[i];
		if((X != 6000) && (Y != 6000))
			b[(X*100)+Y].setBackground(Color.YELLOW);
	}
   }
 }

