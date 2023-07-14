package classes;

public class Main {

	/*
	 * Create an instance of Home Window
	 * and call its methods.
	 */
	public static void main( String[] args )
	{
		HomeWindow home = new HomeWindow();
		
		home.createMonths();
		home.setVisible();		
		home.createNewTable();
	}
}
