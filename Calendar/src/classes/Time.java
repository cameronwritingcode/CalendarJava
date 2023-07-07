package classes;

public class Time {

	int hour;
	int minute;
	
	public Time(String t1, String t2)
	{
		this.hour = Integer.parseInt( t1 );
		this.minute = Integer.parseInt( t2 );
	}
	
	public String getHour()
	{
		return String.valueOf( hour );
	}
	
	public String getMinute()
	{
		return String.valueOf( minute );
	}
}
