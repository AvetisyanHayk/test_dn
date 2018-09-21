package com.dn.gaverzicht.slp.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTimestamp {
	
	public static final String FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";
	
	private final Date date;
	
	public LogTimestamp(Date date) {
		this.date = date;
	}
	
	public LogTimestamp(String timestamp) throws ParseException {
		this.date = parseTimestamp(timestamp);
	}
	
	public Date getDate() {
		return date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return new SimpleDateFormat(LogTimestamp.FORMAT).format(date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogTimestamp other = (LogTimestamp) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	public static Date parseTimestamp(String timestamp) throws ParseException {
		try {
			if (timestamp.length() != LogTimestamp.FORMAT.length()) {
				throw new ParseException("String is shorter than the expected date format length", timestamp.length());
			}
			return (new SimpleDateFormat(LogTimestamp.FORMAT))
					.parse(timestamp);
		} catch (NullPointerException ex) {
			throw new ParseException("Null String cannot be parsed", 0);
		}
	}
}

