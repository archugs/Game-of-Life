import javax.swing.JButton;

/**
 * 
 */

/**
 * @author user
 *
 */
public class PauseSimulation {
	
	boolean flag;
	
	PauseSimulation()
	{
		flag = false;
	}
	
public void pause(JButton[] b) 
{
	PlaySimulation ps = new PlaySimulation();
	ps.play(b, flag);
}
}
