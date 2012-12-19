package se.kth.ssvl.tslab.wsn.general.systemlib.util;

import java.util.Calendar;
import java.util.Date;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.DTNTime;

/**
 * Helper class to support time calculation
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class TimeHelper {

	/**
	 * Calculate Elaspse time compared with current time in Milliseconds
	 * 
	 * @param time
	 *            input time to calculate
	 * @return Milliseconds passed from the moment
	 */
	public static long elapsed_ms(Date time) {
		return Calendar.getInstance().getTimeInMillis() - time.getTime();
	}

	/**
	 * Get current seconds from the reference time in DTN ( Jan , 2000 )
	 */
	public static long current_seconds_from_ref() {
		return (Calendar.getInstance().getTimeInMillis() / 1000)
				- DTNTime.TIMEVAL_CONVERSION;
	}

	/**
	 * Get seconds from the reference time in DTN( Jan , 2000 ) from the time
	 * specified
	 */
	public static long seconds_from_ref(Date time) {
		Calendar time_calendar = Calendar.getInstance();
		time_calendar.setTime(time);
		return (time_calendar.getTimeInMillis() / 1000)
				- DTNTime.TIMEVAL_CONVERSION;
	}

	/**
	 * Get seconds from the reference time in DTN( Jan , 2000 ) from the time
	 * specified
	 */
	public static long seconds_from_ref(Calendar time_calendar) {

		return (time_calendar.getTimeInMillis() / 1000)
				- DTNTime.TIMEVAL_CONVERSION;
	}
}
