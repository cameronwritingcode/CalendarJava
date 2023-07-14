package classes;

import java.awt.GridLayout;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Month {

	String name;
	JButton button;
	int days;
	Map<Integer, List<Event>> events;
	Map< String, Integer > monthTable;
	private JButton lastButton = null;
	List<Day> dayList;
	
	/*
	 * Month Constructor.
	 * Input Parameters: name of month, days of month
	 */
	public Month( String name, int days )
	{
		this.name = name;
		this.button = new JButton(name);
		this.days = days;
		this.events = new HashMap<>();
		this.monthTable = new HashMap<>()
				{
					{
						put("January", 1);
						put("February", 2);
						put("March", 3);
						put("April", 4);
						put("May", 5);
						put("June", 6);
						put("July", 7);
						put("August", 8);
						put("September", 9);
						put("October", 10);
						put("November", 11);
						put("December", 12);
						
					}
				};
	
		button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				enterMonth(name);
			}
			
		});
		
		dayList = new ArrayList<>();
		
		for( int i = 1; i <= days; i++ )
		{
			Day d = new Day( i, name );
			dayList.add(d);

		}
	}
	
	
	/*
	 * creates and opens a month panel
	 */
	public void enterMonth(String month)
	{
		JPanel monthPanel = new JPanel();
		GridLayout grid = new GridLayout( 5, 7 );
		monthPanel.setLayout( grid );

		for( Day each: dayList )
		{
			monthPanel.add(each.button);
		}
		
		JOptionPane.showConfirmDialog( null, monthPanel, name, JOptionPane.OK_CANCEL_OPTION );
	}
	
	
}
