/**
 *   File Name: Calendar.java<br>
 *
 *   Zhestkov, Michael<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: May 2, 2016
 *
 */

package com.sqa.mz.util.helpers;

import java.text.*;
import java.util.*;

public class Dates {

	public void enterCheckIn() {
		SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		String dayToday = checkIn.format(c.getTime());
		System.out.println(dayToday);

	}

	public String enterCheckIn(int daysFromToday) {
		SimpleDateFormat checkIn = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, daysFromToday);
		String dayToday = checkIn.format(c.getTime());
		System.out.println(dayToday);
		return dayToday;

	}

	public void enterCheckOut() {
		SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		String dayToday = checkOut.format(c.getTime());
		System.out.println(dayToday);

	}

	public String enterCheckOut(int daysFromToday) {
		SimpleDateFormat checkOut = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, daysFromToday);
		String dayToday = checkOut.format(c.getTime());
		System.out.println(dayToday);
		return dayToday;

	}

}
