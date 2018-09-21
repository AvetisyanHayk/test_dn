package com.dn.gaverzicht.slp.core;

import java.text.ParseException;

public final class LogParser {

	private LogParser() {}

	
	
	public static Log parseLog(String logLine) throws ParseException {
		try {
			LogTimestamp timestamp = new LogTimestamp(logLine.substring(0, LogTimestamp.FORMAT.length()));
			logLine = logLine.substring(LogTimestamp.FORMAT.length()).trim();
			String threadName = logLine.substring(logLine.indexOf("[") + 1, logLine.indexOf("]"));
			logLine = logLine.substring(logLine.indexOf("]") + 1).trim();
			LogLevel logLevel = LogLevel.valueOf(logLine.substring(0, logLine.indexOf("[")).trim());
			logLine = logLine.substring(logLine.indexOf("[")).trim();
			String logGeneratorClass = logLine.substring(logLine.indexOf("[") + 1, logLine.indexOf("]")).trim();
			logLine = logLine.substring(logLine.indexOf("]") + 1).trim();
			String message = logLine.substring(logLine.indexOf(":") + 1).trim();
			return new Log(timestamp, threadName, logLevel, logGeneratorClass, message);
		} catch (StringIndexOutOfBoundsException | NullPointerException | IllegalArgumentException ex) {
			throw new ParseException("Incorrect Log Format", 0);
		}
		
	}

	static boolean isLogMessageFragment(String logLine) {
		if (logLine.length() < LogTimestamp.FORMAT.length()) {
			return true;
		}
		try {
			new LogTimestamp(logLine.substring(0, LogTimestamp.FORMAT.length()));
			return false;
		} catch (ParseException ex) {}
		return true;
	}
}
