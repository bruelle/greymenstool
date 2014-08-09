package net.sourceforge.thegreymenstool.utils;

import java.util.Calendar;
import java.util.Date;

public class Time {
	private int time;

	public Time(int time) {
		this.time = time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setTimeByInterval(Date from, Date to) {
		this.time = (int) (to.getTime() / 1000 - from.getTime() / 1000);
	}

	public int getHours() {
		return time / 3600;
	}

	public int getMinutes() {
		return (time % 3600) / 60;
	}

	public int getSeconds() {
		return time % 60;
	}
	
	public int getTime()
	{
		return time;
	}

	public static Date getBeginningOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getBeginningOfFutureDay(Date date, int offset) {
		Date futureDate = new Date(date.getTime() + offset * 1000 * 3600 * 24);
		return getBeginningOfDay(futureDate);
	}
}
