/**
 * 
 */

/**
 * @author user
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InteractiveGUI  {
	JFrame jf;
	JPanel jp1, jp2; 
	JButton[][] b;
	JButton next;
	JButton pause;
	JButton play;
	JLabel jl;
	JComboBox jc;
	String[] comboItems;
	Integer i;
	Integer j;
	static final int NO_OF_ROWS = 60;
	static final int NO_OF_COLS = 100;
	int flag;
	ButtonHandler obj;

	public InteractiveGUI()
	{
	
		obj = new ButtonHandler();
		jf = new JFrame("Conway's Game of Life");
		jp1 = new JPanel();
		jp2 = new JPanel();
		b = new JButton[NO_OF_ROWS][NO_OF_COLS];
		for( i = 0; i < NO_OF_ROWS; i++)
		{
			for(int j = 0; j < NO_OF_COLS; j ++)
			{
				b[i][j] = new JButton();
			}
		}
		next =  new JButton("next");
		pause = new JButton("pause");
		play = new JButton("play");
		jl = new JLabel("Speed");
		comboItems = new String[10];
		for(i = 1; i < 10; i++)
			comboItems[i] = i.toString();
		jc = new JComboBox(comboItems);
		
	}

	public void launchFrame()
	{
		jf.add(jp1,BorderLayout.NORTH);
		jf.add(jp2,BorderLayout.CENTER);
		jp1.setLayout(new GridLayout(NO_OF_ROWS,NO_OF_COLS));
		jp2.setLayout(new FlowLayout());
		jp2.add(play);
		jp2.add(next);
		jp2.add(pause);
		jp2.add(jl);
		jp2.add(jc);
		
		for( i = 0; i < NO_OF_ROWS; i++)
		{
			for( j = 0; j < NO_OF_COLS; j ++)
			{
				
				jp1.add(b[i][j]);
				b[i][j].addActionListener(new ActionListener()
				{				
					public void actionPerformed(ActionEvent e)
					{
						JButton button = (JButton)e.getSource();
						button.setBackground(Color.yellow);
						
						obj.populateCell(i,j);
					}
				});
			}	
		}
		next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int[][] buttons = obj.goNext();
				for(int  a = 0; a < buttons.length; a ++)
				{
					int ix = buttons[a][0];
					int iy = buttons[a][1];
					b[ix][iy].setBackground(Color.yellow);
				}
			}
		});
		
		/*play.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				PlaySimulation p = new PlaySimulation();
				p.play(b, true);
					
			}
		});*/
		
		/*pause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PauseSimulation ps = new PauseSimulation();
				ps.pause(b);
			}
		});*/
		jf.pack();
		jf.setVisible(true);
		
		
	}
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			InteractiveGUI obj = new InteractiveGUI();
			obj.launchFrame();
		}

}
		
