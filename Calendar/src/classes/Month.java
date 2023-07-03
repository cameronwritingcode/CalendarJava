package classes;

import java.awt.GridLayout;
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
	
	
	
	public Month( String name, int days )
	{
		this.name = name;
		this.button = new JButton(name);
		this.days = days;
		this.events = new HashMap<>();
		
		
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
		GridLayout grid = new GridLayout( 5, 7 );
		monthPanel.setLayout( grid );
		for( int i = 1; i <= days; i++ )
		{
			JButton day = new JButton(String.valueOf(i));
			
			int d = i;
			day.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					enterDay(d);
				}
			});
			
			monthPanel.add(day);
		}
		
		JOptionPane.showConfirmDialog( null, monthPanel, name, JOptionPane.OK_CANCEL_OPTION );
	}
	
	public void enterDay( int day )
	{
		JPanel dayPanel = new JPanel();
		BoxLayout box = new BoxLayout(dayPanel, BoxLayout.Y_AXIS);
		
		dayPanel.setLayout(box);
		
		if( events.containsKey(day))
		{
			for( Event each: events.get( day ) )
			{
				JLabel eventLabel = new JLabel( each.eventDetails );
				dayPanel.add( eventLabel );
			}
		}
		
		JButton addButton = new JButton("Add Event");
		
		addButton.addActionListener( new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e ) {
				addEvent();
			}
		});
		
		dayPanel.add(addButton);
		
		
		JOptionPane.showConfirmDialog( null, dayPanel, String.valueOf(day), JOptionPane.OK_CANCEL_OPTION );
	}
	
	public void addEvent()
	{
		
		JPanel addPanel = new JPanel();
		DefaultComboBoxModel hourModel = new DefaultComboBoxModel();
		
		for( int i = 1; i <= 24; i++ )
		{
			hourModel.addElement( i );
		}
		
		JComboBox hourBox = new JComboBox( hourModel );
		
		DefaultComboBoxModel minuteModel = new DefaultComboBoxModel();
		
		for( int i = 0; i <= 60; i++ )
		{
			minuteModel.addElement( i );
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
		
		
		addPanel.add( new JLabel("Enter Hour: " ) );
		addPanel.add( hourBox );
		
		addPanel.add( new JLabel("Enter Minute: " ) );
		addPanel.add( minuteBox );

		JOptionPane.showConfirmDialog( null, addPanel, "Add an Event", JOptionPane.OK_CANCEL_OPTION );
	}
}
