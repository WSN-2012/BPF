
package se.kth.ssvl.tslab.wsn.general.servlib.routing.routerentry;

import java.util.Comparator;

/**
 * Comparator class for sorting the RouteEntry. This is used for sorting the
 * Route Entry before processing This comparator compares based on the Route
 * Priority.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RouteEntryComparator implements Comparator<RouteEntry> {

	/**
	 * compare function override from Java Comparator
	 */

	public int compare(RouteEntry r1, RouteEntry r2) {

		if (r1.priority() < r2.priority())
			return -1;
		if (r1.priority() > r2.priority())
			return +1;
		return r1.link().bytes_queued() < r2.link().bytes_queued() ? -1 : +1;
	}

}
