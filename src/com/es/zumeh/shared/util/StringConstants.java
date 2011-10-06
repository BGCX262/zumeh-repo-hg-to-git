package com.es.zumeh.shared.util;

public enum StringConstants {
	
	GOOGLE_AUTH_URL("https://accounts.google.com/o/oauth2/auth"),
	GOOGLE_CLIENT_ID("857888815693.apps.googleusercontent.com"),
	BUZZ_READONLY_SCOPE("https://www.googleapis.com/auth/buzz.readonly "),
	PROFILE_READONLY_SCOPE("https://www.googleapis.com/auth/userinfo.profile "),
	GOOGLE_PLUS_SCOPE("https://www.googleapis.com/plus/v1/people/me "),
	DOCS_SCOPE("https://docs.google.com/feeds/ "),
	FEEDS("http://www.google.com/m8/feeds/ "),
	
//	GOOGLE_IMAGE("images"+ StringUtils.getFileSeparator() +"google_logo.jpg"),
//	FACEBOOK_IMAGE("images"+ StringUtils.getFileSeparator() +"facebook.jpg"),
//	ZUMEH_LOGO_IMAGE("images"+ StringUtils.getFileSeparator() +"bode2.png");

	GOOGLE_IMAGE("images/google_logo.jpg"),
	FACEBOOK_IMAGE("images/facebook.jpg"),
	ZUMEH_LOGO_IMAGE("images/bode2.png");
	
	
	private String value;
	
	StringConstants(String cte) {
		this.setValue(cte);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String name) {
		this.value = name;
	}
	
	

}
