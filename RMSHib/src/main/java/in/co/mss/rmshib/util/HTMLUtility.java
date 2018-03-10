package in.co.mss.rmshib.util;

import in.co.mss.rmshib.dto.DropdownList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */

public class HTMLUtility {

	public static String getList(String name, String selectedVal,

	HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style= 'width:283px' class='form-control' name='"
						+ name + "'>");
		sb.append("<option value= >" + " " + "Select" + "</option>"); // *****SELECT
																		// ****//FOR
																		// HASH
																		// MAP
		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val
						+ "</option>");

			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	/**
	 * Create HTML SELECT List from List parameter
	 * 
	 * @param name
	 * @param selectedVal
	 * @param list
	 * @return
	 */
	public static String getList(String name, String selectedVal, List list) {

		Collections.sort(list);

		List<DropdownList> dd = (List<DropdownList>) list;

		StringBuffer sb = new StringBuffer(
				"<select style='width:283px' class='form-control' name='"
						+ name + "'>");
		sb.append("<option value= >" + " " + "Select" + "</option>");// ****SELECT
		String key = null;
		String val = null;

		for (DropdownList obj : dd) {// FOR EACH LOOP
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val
						+ "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

}
