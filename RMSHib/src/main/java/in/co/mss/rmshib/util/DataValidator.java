package in.co.mss.rmshib.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class validates input data
 * 
 * @author Session Facade
 * @version 1.0
 *
 */

public class DataValidator {
	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	/******************** IS ALPHABETS ******************************************/

	public static boolean isAlpha(String name) {
		if (name.matches("!=^[a-zA-Z0-9]*$") || name.matches("!=[a-zA-Z]+$")) {
			return true;
		} else {
			if (name.matches("[0-9]+")) {
				return false;
			}
		}
		return true;
	}

	/************************* IS NOT ALPHABETS *********************************************/

	/************** WILL RETURN FALSE IN CASE STRING IS ALPHABETS *******************/

	/************** WILL RETURN TRUE IN CASE STRING IS NOT ALPHABETS ****************/

	public static boolean isNotAlpha(String name) {
		if (name.matches("!=[a-zA-Z0-9]") || name.matches("!=[0-9]")) {
			return true;
		} else {
			if (name.matches("(?i)[a-z]+(\\s+[a-z]+)*")) {

				return false;
			}
		}
		return true;
	}

	/****************************** INVALID STATE **************************************/

	public static boolean isInvalidState(String state) {
		if (!state.matches("[a-zA-Z][.][a-zA-Z]$")) {
			return true;

		} else {
			return false;
		}

	}

	/********************* NOT STRONG PASSWORD ****************************************/

	public static boolean isNotStrongPasscode(String Password) {
		if (Password
				.matches("!=/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,10}$/")) {
			return true;
		} else {
			if (Password
					.matches("/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,10}$/")) {
				return false;

			}
		}
		return true;

	}

	/**************************** IS VALID DATE ***********************************************/

	public static boolean isValidDate(Date date) {
		Date todaysDate = new Date();
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date today = new java.util.Date(sdf2.format(todaysDate));
		System.out.println(today);
		System.out.println(date);
		if (date.compareTo(today) <= 0) {
			return true;
		} else

			return false;

	}

	/**************************** IS PAST DATE ***********************************************/

	public static boolean isPastDate(String val) {
		Date d = null;

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		d = DataUtility.getDate(val);
		Date now = new Date();
		// Calendar now = Calendar.getInstance();
		dateFormat.format(d);
		dateFormat.format(now);
		/*
		 * Calendar cal = Calendar.getInstance(); cal.setTime(d); cal =
		 * dateFormat.getCalendar();
		 */
		// int year = Calendar.getInstance().get(Calendar.YEAR);

		// int d1 = cal.get(Calendar.YEAR);
		// System.out.println("Month is:"+cal.get(Calendar.MONTH));
		// int age = year - d1;

		System.out.println("@#@#@#@#@" + d);
		System.out.println(now);

		if (d.after(now)) {
			System.out.println("INSIDE PAST DATE");
			return true;
		}

		return false;

	}

	/************************************* IS 18 YEAR OLD *****************************************************/

	public static boolean isUnderAge(String val) {

		Date d = null;

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		d = DataUtility.getDate(val);

		Calendar now = Calendar.getInstance();
		dateFormat.format(d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal = dateFormat.getCalendar();
		int year = Calendar.getInstance().get(Calendar.YEAR);

		int d1 = cal.get(Calendar.YEAR);
		// System.out.println("Month is:"+cal.get(Calendar.MONTH));
		int age = year - d1;

		if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH) || cal
				.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				&& cal.get(Calendar.DAY_OF_MONTH) > now
						.get(Calendar.DAY_OF_MONTH))) {

			age--;
		}
		// System.out.println(age);

		// System.out.println("The age is" + age);
		if (age < 18) {
			return true;
		}
		return false;

	}

	/************************************** OVER AGE *************************************************************/

	public static boolean isOverAge(String val) {

		Date d = null;

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		d = DataUtility.getDate(val);

		Calendar now = Calendar.getInstance();
		dateFormat.format(d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal = dateFormat.getCalendar();
		int year = Calendar.getInstance().get(Calendar.YEAR);

		int d1 = cal.get(Calendar.YEAR);
		// System.out.println("Month is:"+cal.get(Calendar.MONTH));
		int age = year - d1;

		if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH) || cal
				.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				&& cal.get(Calendar.DAY_OF_MONTH) > now
						.get(Calendar.DAY_OF_MONTH))) {

			age--;
		}
		// System.out.println(age);

		// System.out.println("The age is" + age);
		if (age > 35) {
			return true;
		}
		return false;

	}

	/**************************** IS NOT VALID MOBILE NUMBER ****************************/
	public static boolean isNotMobileNo(String MobileNo) {
		if (MobileNo.matches("[0-6][0-9]{9}$")) {
			return true;
		} else {
			return false;
		}

	}

	/**************************** IS INVALID DURATION ****************************/
	public static boolean isInvalidDuration(String Duration) {
		if (!Duration.matches("[1-5]$")) {
			return true;

		} else {
			return false;
		}

	}

	public static boolean isZeroDuration(String Duration) {
		if (!Duration.matches("[0]$")) {
			return true;

		} else {
			return false;
		}

	}

	/********************************* IS SPECIAL ***********************************************/

	public static boolean isSpecial(String name) {
		// if
		// (name.matches("[a-zA-Z]")||name.matches("[0-9]")||name.matches("!=[&%$##@!~]")){
		if (name.matches("[a-zA-Z0-9]*$")) {
			return false;
		}

		return true;
	}

	public static boolean ContainSpaces(String rollNo) {
		if (rollNo.matches("[^\\s+$]")) {
			return true;
		} else {
			return false;
		}

	}

	/**************************** IS ALPHABETS ONLY ****************************/
	public static boolean isAlphabetsOnly(String lastName) {
		if (lastName.matches("[a-zA-Z]*$")) {
			return true;
		} else {
			return false;
		}

	}

	/**************************** IS ALPHABETS ONLY ****************************/
	public static boolean isAlphaNumeric(String rollNo) {
		if (rollNo.matches("[0-9]+[a-zA-z]+[0-9]*$")) {
			return true;
		} else {
			return false;
		}

	}

	/************************************ TEST METHOD *************************************/

	/* 
 */
	/**
	 * Test above methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("MOBILE " + isNotMobileNo("9865202020"));
		System.out.println("IS NOT ALPHABETS" + isNotAlpha("TARUN"));
		System.out.println("IS ALPHABETS" + isAlpha("tarun "));
		System.out.println("Not Null 2" + isNotNull("ABC"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));
		System.out.println("Is Email" + isEmail("tarunjadhav119@gmail.com"));
		System.out.println("Is Date " + isDate("20/12/12"));
		System.out.println("Is long " + isLong("0"));

	}
}
