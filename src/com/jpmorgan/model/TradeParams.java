/**
 * 
 */
package com.jpmorgan.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kmac
 *
 */
public class TradeParams {

	public static final Map<String, Integer> daysMap = createDaysMap();

	/**
	 * method to create map of days in the week
	 * @return days map
	 */
	private static final Map<String, Integer> createDaysMap() {
		Map<String, Integer> daysMap = new HashMap<>();
		daysMap.put("SUNDAY", 0);
		daysMap.put("MONDAY", 1);
		daysMap.put("TUESDAY", 2);
		daysMap.put("WEDNESDAY", 3);
		daysMap.put("THURSDAY", 4);
		daysMap.put("FRIDAY", 5);
		daysMap.put("SATURDAY", 6);
		return daysMap;
	}

	/**
	 * method to return the actual working day 
	 * @param currency
	 * @param current day
	 * @return index for the working day e.g 1 for Monday
	 */
	public static int getWorkingDay(String currency, String day) {

		int numOfDay = daysMap.get(day);
		if (currency.equalsIgnoreCase("AED") || currency.equalsIgnoreCase("SAR")) {
			if (numOfDay >= 0 && numOfDay <= 4) {
				return 0;
			} else if (numOfDay == 5) {
				return 2;
			} else
				return 1;
		} else {
			if (numOfDay >= 1 && numOfDay <= 5) {
				return 0;
			} else if (numOfDay == 6) {
				return 2;
			} else
				return 1;
		}

	}

}
