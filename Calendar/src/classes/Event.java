package classes;

public class Event {

	
	Time time1;
	Time time2;
	String detail;
	
	/*
	 * Event constructor
	 */
	public Event(Time time1, Time time2, String detail )
	{
		this.time1 = time1;
		this.time2 = time2;
		this.detail = detail;
	}
	
	public String getDetail()
	{
		return detail;
	}
	
	public Time getTime1()
	{
		return time1;
	}
	
	public Time getTime2()
	{
		return time2;
	}
}
