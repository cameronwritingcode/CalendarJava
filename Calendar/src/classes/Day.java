package classes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class Day {

	Map<Integer, List<Event>> events;
	Map<String, Integer> monthTable;
	JButton lastButton;
	JButton button;
	
	/*
	 * Day Constructor
	 */
	public Day(int d, String month)
	{
		this.button = new JButton(String.valueOf(d));
		
		button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				enterDay(d, month);
			}
		});
		
		this.events = new HashMap<>();
		this.lastButton = null;
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

	}
	/*
	 * creates and enters a day panel
	 * Gets the events for the day from the SQLite database,
	 * and creates buttons for the events. Each button is 
	 * then added to the day panel and displayed for the user
	 * to see and interact with.
	 */
	public void enterDay( int day, String month )
	{
		JPanel dayPanel = new JPanel();
		BoxLayout box = new BoxLayout(dayPanel, BoxLayout.Y_AXIS);
		
		GridLayout grid = new GridLayout(0, 1);
		
		dayPanel.setLayout(grid);
		
		ActionListener listener = e -> {
			lastButton =  (JButton) e.getSource();
		};
		
		
		String sql = "SELECT id AS id, strftime('%H:%M', time1) AS  t1, strftime('%H:%M', time2) as t2, detail FROM entries WHERE month = ? AND day = ?;";
		
		String url = "jdbc:sqlite:events.db";

		try {
			Connection conn = DriverManager.getConnection( url );
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setString(1, month);
			stmnt.setString(2, String.valueOf(day));


			ResultSet rs = stmnt.executeQuery();
			while( rs.next() )
			{
				String text = rs.getString("detail") + " From: " + rs.getString("t1") + " To: " + rs.getString("t2" );
				int id = rs.getInt("id");
				JButton newButton = new JButton(text);
				newButton.setName(String.valueOf(id));
				newButton.addActionListener(listener);
				
				dayPanel.add(newButton);
				
				
			}
		}catch(SQLException e ) {
			System.out.println( e.getMessage() );
		}
		
		JButton addButton = new JButton("Add Event");
		
		JButton removeButton = new JButton("Remove Event");

		
		addButton.addActionListener( new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e ) {
				
				int result = addEvent(month, day);
				
				if( result == JOptionPane.OK_OPTION )
				{
					JOptionPane.getRootFrame().dispose();
					
					enterDay( day, month );
				}
				
			}
		});
		
		removeButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				

				try {
					String sql = "DELETE FROM entries WHERE id = ?;";
					
					String idString = "";
					if( lastButton != null )
					{
						idString = lastButton.getName();
					}
					
					String url = "jdbc:sqlite:events.db";
					Connection conn = DriverManager.getConnection( url );
					PreparedStatement stmnt = conn.prepareStatement(sql);
					stmnt.setString(1, idString);
					stmnt.executeUpdate();
					
					JOptionPane.getRootFrame().dispose();
					
					enterDay( day, month );
					
					
				}
				catch(SQLException e1 ) {
					System.out.println( e1.getMessage() );
				}
					
			}
		});
		
		dayPanel.add(addButton);
		dayPanel.add(removeButton);	
		
		int res = JOptionPane.showConfirmDialog( null, dayPanel, month + " " + String.valueOf(day), JOptionPane.OK_CANCEL_OPTION );
	}

	/*
	 * Adds an event to the events.db database
	 */
	public int addEvent(String month, int day)
	{
		
		JPanel addPanel = new JPanel();
		DefaultComboBoxModel hourModel = new DefaultComboBoxModel();
		
		for( int i = 1; i <= 24; i++ )
		{
			String s = String.valueOf(i);
			if( s.length() == 1 )
			{
				s = "0" + s;
			}
			hourModel.addElement( s );		}
		
		JComboBox hourBox = new JComboBox( hourModel );
		
		DefaultComboBoxModel minuteModel = new DefaultComboBoxModel();
		
		for( int i = 0; i <= 60; i++ )
		{
			String s = String.valueOf(i);
			if( s.length() == 1 )
			{
				s = "0" + s;
			}
			minuteModel.addElement( s );
		}
		
		JComboBox minuteBox = new JComboBox( minuteModel );
		
		JTextField eventDetail = new JTextField( "What is the event?" );
		
		eventDetail.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				eventDetail.setText("");
			}
		});
		
		addPanel.add( eventDetail );
		
		DefaultComboBoxModel hourModel2 = new DefaultComboBoxModel();
		
		for( int i = 1; i <= 24; i++ )
		{
			String s = String.valueOf(i);
			if( s.length() == 1 )
			{
				s = "0" + s;
			}
			hourModel2.addElement( s );
		}
			
		DefaultComboBoxModel minuteModel2 = new DefaultComboBoxModel();
		
		for( int i = 0; i <= 60; i++ )
		{
			String s = String.valueOf(i);
			if( s.length() == 1 )
			{
				s = "0" + s;
			}
			minuteModel2.addElement( s );
		}
		
		JComboBox hourBox2 = new JComboBox( hourModel2 );
	
		
		JComboBox minuteBox2 = new JComboBox( minuteModel2 );
		
						
		
		addPanel.add( new JLabel("From: " ) );
		addPanel.add( hourBox );
		
		addPanel.add( new JLabel(":" ) );
		addPanel.add( minuteBox );
		
		addPanel.add( new JLabel("To: " ));
		addPanel.add( hourBox2 );
		
		addPanel.add( new JLabel(":" ) );
		addPanel.add( minuteBox2 );

		int option = JOptionPane.showConfirmDialog( null, addPanel, "Add an Event", JOptionPane.OK_CANCEL_OPTION );
		
		if( option == JOptionPane.OK_OPTION )
		{
			Time t1 = new Time( hourBox.getSelectedItem().toString(), minuteBox.getSelectedItem().toString() );
			Time t2 = new Time( hourBox2.getSelectedItem().toString(), minuteBox2.getSelectedItem().toString() );
			
			String eventString = eventDetail.getText();
			
			Event event = new Event( t1, t2, eventString );
			
			if( !events.containsKey(day))
			{
				
				events.put( day, new ArrayList<>() );
			}
			
			events.get( day ).add( event );
			
			String sql = "INSERT INTO entries values(NULL,?,?,?,?,?)";
			
			String dayString = String.valueOf( day );
			String monthString = String.valueOf(monthTable.get(month));
			if( dayString.length() == 1 ) dayString = "0" + dayString;
			if( monthString.length() == 1 ) monthString = "0" + monthString;

			String time1 = "2023-" + monthString + "-" + dayString + " " + t1.getHour() + ":" + t1.getMinute() + ":00";
			String time2 = "2023-" + monthString + "-" + dayString + " " + t2.getHour() + ":" + t2.getMinute() + ":00";
			
			String mon = month;
			String d = String.valueOf(day);
			String url = "jdbc:sqlite:events.db";

			try {
				Connection conn = DriverManager.getConnection( url );
				PreparedStatement stmnt = conn.prepareStatement(sql);
				stmnt.setString(1, eventString);
				stmnt.setString(2,  time1);
				stmnt.setString(3, time2);
				stmnt.setString(4, mon);
				stmnt.setString(5, d);
				stmnt.executeUpdate();
					
			}catch(SQLException e ) {
				System.out.println( e.getMessage() );
			}
			

		}
		
		return option;
	}
}
