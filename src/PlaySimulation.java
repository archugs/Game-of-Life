import javax.swing.JButton;

/**
 * 
 */

/**
 * @author user
 *
 */
public class PlaySimulation {
	
	/**
	 * @param args
	 */
	Next n;
	boolean flag;
	int i =1;
	PlaySimulation()
	{
		n = new Next();
		flag = true;
		
	}
	
	public void play(JButton[] b, boolean flag) 
	{
		
		{
			while(flag)
			{
				n.goNext(b);	
			}	
		}
		
		
	}

}
