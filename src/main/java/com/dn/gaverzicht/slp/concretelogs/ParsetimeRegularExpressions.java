package com.dn.gaverzicht.slp.concretelogs;

import java.util.regex.Pattern;

public final class ParsetimeRegularExpressions {
	private ParsetimeRegularExpressions() {}
	
	public final static Pattern UID_REGEX = Pattern.compile("^\\d{13}-\\d{3,4}$");
	public final static Pattern DOCUMENT_REGEX = Pattern.compile("^\\d+,\\s*\\d+$");
}
