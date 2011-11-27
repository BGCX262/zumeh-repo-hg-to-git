package com.es.zumeh.shared.util;



public class Validate {
	
	public static boolean verifyDate(String date) {
        return (date != null && date.matches("\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d"));
	}

}
