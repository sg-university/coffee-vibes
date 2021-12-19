package utils;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class Time {
	int s, m, h;
	int dd, mm, yyyy;
	int timeFormat = 24;

	Calendar c = Calendar.getInstance();

	public Time() {
		// TODO Auto-generated constructor stub
	}

	public Time(int s, int m, int h) {
		super();
		this.s = s;
		this.m = m;
		this.h = h;
	}

	public void print() {
		@SuppressWarnings("deprecation")
		Date d = new Date(this.yyyy, this.mm, this.dd, this.h, this.m, this.s);
		System.out.println(d);
	}

	public void sync24() {
		this.timeFormat = 24;

		this.s = c.get(Calendar.SECOND);
		this.m = c.get(Calendar.MINUTE);
		this.h = c.get(Calendar.HOUR_OF_DAY);

		this.dd = c.get(Calendar.DAY_OF_WEEK);
		this.mm = c.get(Calendar.MONTH);
		this.yyyy = c.get(Calendar.YEAR) - 1900;
	}

	public void sync12() {
		this.timeFormat = 12;
		this.sync24();
		this.h = c.get(Calendar.HOUR);
	}

	public void addSecond(int s) {
		this.addMinute((this.s + s) / 60);
		this.s = (this.s + s) % 60;
	}

	public void addMinute(int m) {
		this.addHour((this.m + m) / 60);
		this.m = (this.m + m) % 60;
	}

	public void addHour(int h) {
		this.addDay((this.h + h) / this.timeFormat);
		this.h = (this.h + h) % this.timeFormat;
	}

	public void addDay(int dd) {
		int dayToAdd = dd;
		while (true) {
			YearMonth yearMonth = YearMonth.of(this.yyyy, this.mm);
			if (dayToAdd - yearMonth.lengthOfMonth() < 0)
				break;

			dayToAdd -= yearMonth.lengthOfMonth();
			this.addMonth(1);

		}
		this.dd += dayToAdd;
	}

	public void addMonth(int mm) {
		this.addYear((this.mm + mm) / 12);
		this.mm = (this.mm + mm) % 12;
	}

	public void addYear(int yyyy) {
		this.yyyy += yyyy;
	}
}
