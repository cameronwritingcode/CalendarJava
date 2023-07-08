package classes;

public class Main {

	public static void main( String[] args )
	{
		Window window = new Window();
		
		//window.addEventButton();
		
		window.createMonths();
		
		window.setVisible();
		
		window.createDatabase("SSSIT.db");
		window.createNewTable();
	}
}
