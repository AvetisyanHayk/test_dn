package com.dn.gaverzicht.slp.concretelogs;

import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogTimestamp;

public class StartRenderingReturn {

	public static final String DISCRIMINATOR = "Service startRendering returned";
	
	private Log log;
	
	public StartRenderingReturn(Log log) {
		if (!isValidLog(log)) {
			throw new IllegalArgumentException();
		}
		this.log = log;
	}
	
	public String getThreadName() {
		return log.getThreadName();
	}
	
	public String getUid() {
		return getUid(log);
	}
	
	public static boolean isValidLog(Log log) {
		if (log == null) {
			return false;
		}
		if (!log.getMessage().firstLineContains(DISCRIMINATOR)) {
			return false;
		}
		return getUid(log) != null;
	}
	
	public LogTimestamp getTimestamp() {
		return log.getTimestamp();
	}
	
	private static String getUid(Log log) {
		String messageFirstLine = log.getMessageLine(0);
		if (messageFirstLine != null) {
			try {
				String uid = messageFirstLine.substring(DISCRIMINATOR.length()).trim();
				if (ParsetimeRegularExpressions.UID_REGEX.matcher(uid).matches()) {
					return uid;
				}
			} catch (StringIndexOutOfBoundsException | NullPointerException ex) {

			}
		}
		return null;
	}
}
