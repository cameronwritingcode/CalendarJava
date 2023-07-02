package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Month {

	String name;
	JButton button;
	
	public Month( String name )
	{
		this.name = name;
		this.button = new JButton(name);
		
		button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				enterMonth();
			}
			
		});
	}
	
	public void enterMonth()
	{
		JPanel monthPanel = new JPanel();
		JOptionPane.showConfirmDialog( null, monthPanel, name, JOptionPane.OK_CANCEL_OPTION );
	}
}
