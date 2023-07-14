package classes;

public class Time {

	String hour;
	String minute;
	
	/*
	 * time constructor
	 */
	public Time(String t1, String t2)
	{
		this.hour = t1;
		this.minute = t2;
	}
	
	/*
	 * hour getter
	 */
	public String getHour()
	{
		return hour;
	}
	
	/*
	 * minute getter
	 */
	public String getMinute()
	{
		return minute;
	}
}
