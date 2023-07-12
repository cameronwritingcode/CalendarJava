package classes;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class Window {

	JFrame frame;
	JPanel panel;
	public Window()
	{
		frame = new JFrame("Calendar");
		panel = new JPanel();
		
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		GridLayout grid = new GridLayout( 2, 6 );
		panel.setLayout( grid );
		
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JLabel label = new JLabel( "Calendar", SwingConstants.CENTER );
			
		frame.add( panel, BorderLayout.CENTER );
		
		
		
		frame.setLocationRelativeTo( null );
		
		
	}
	
	public static void createDatabase( String fileName )
	{
		String url = "jdbc:sqlite:events.db";
		
		try {
			Connection conn = DriverManager.getConnection( url );
			if( conn != null )
			{
				DatabaseMetaData meta = conn.getMetaData();
				
			}
		}catch(SQLException e ) {
			System.out.println( e.getMessage() );
		}
	}
	
	public static void createNewTable() {
		
		String sql = "CREATE TABLE IF NOT EXISTS entries ("
				+ "detail TEXT,"
				+ "time1 DATETIME,"
				+ "time2 DATETIME,"
				+ "month TEXT,"
				+ "day TEXT"
				+ ");";
		String url = "jdbc:sqlite:events.db";

		try {
			Connection conn = DriverManager.getConnection( url );
			Statement stmnt = conn.createStatement();
			stmnt.execute(sql);
				
		}catch(SQLException e ) {
			System.out.println( e.getMessage() );
		}
	}
	
	public void setVisible()
	{
		frame.setVisible(true);
	}
	
	public void addEventButton()
	{
		JButton addEvent = new JButton("Add event");
		addEvent.setSize(30,10);
		
		addEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				System.out.println("click");
				
				createNewEvent();
			}

		});
		
		panel.add(addEvent);
	}
	
	public void createNewEvent()
	{
		JPanel eventPanel = new JPanel();
		DefaultComboBoxModel dateModel = new DefaultComboBoxModel();
		
		for( int i = 1; i <= 30; i++ )
		{
			dateModel.addElement( i );
		}
		
		JComboBox dateBox = new JComboBox( dateModel );
		
		DefaultComboBoxModel monthModel = new DefaultComboBoxModel();
		
		for( int i = 1; i <= 12; i++ )
		{
			monthModel.addElement( i );
		}
		
		DefaultComboBoxModel yearModel = new DefaultComboBoxModel();
		
		for( int i = 2023; i <= 2100; i++ )
		{
			yearModel.addElement( i );
		}
		
		JComboBox yearBox = new JComboBox( yearModel );
		
		
		JComboBox monthBox = new JComboBox( monthModel );
		
		eventPanel.setLayout( new BoxLayout( eventPanel, BoxLayout.Y_AXIS ) );
		
		
		JTextField eventDetail = new JTextField( "What is the event?" );
		
		eventDetail.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				eventDetail.setText("");
			}
		});
		
		eventPanel.add( eventDetail );
		
		
		eventPanel.add( new JLabel("Enter Month: " ) );
		eventPanel.add( monthBox );
		
		eventPanel.add( new JLabel("Enter Date: " ) );
		eventPanel.add( dateBox );

		eventPanel.add( new JLabel("Enter Year: " ) );
		eventPanel.add( yearBox );
		
		
		
		
		JOptionPane.showConfirmDialog(null, eventPanel, "Create Event", JOptionPane.OK_CANCEL_OPTION );
	}
	
	public void createMonths()
	{
		List<Month> months = new ArrayList<>();
		
		months.add( new Month( "January", 31 ) );
		months.add( new Month( "February", 28 ) );
		months.add( new Month( "March", 31 ) );
		months.add( new Month( "April", 30 ) );
		months.add( new Month( "May", 31 ) );
		months.add( new Month( "June", 30 ) );
		months.add( new Month( "July", 31 ) );
		months.add( new Month( "August", 31 ) );
		months.add( new Month( "September", 30 ) );
		months.add( new Month( "October", 31 ) );
		months.add( new Month( "November", 30 ) );
		months.add( new Month( "December", 31 ) );
		
		for( Month each: months )
		{
			panel.add( each.button );
		}

	}
}
