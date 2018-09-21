package com.dn.gaverzicht.slp.concretelogs;

import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogTimestamp;

public class GetRenderingReturn {

	public static final String DISCRIMINATOR = "Service getRendering returned";
	
	private final Log log;
	
	public GetRenderingReturn(Log log) {
		if (!isValidLog(log)) {
			throw new IllegalArgumentException();
		}
		this.log = log;
	}

	public String getThreadName() {
		return log.getThreadName();
	}
	
	public static boolean isValidLog(Log log) {
		if (log == null) {
			return false;
		}
		return log.getMessage().firstLineContains(DISCRIMINATOR);
	}
	
	public LogTimestamp getTimestamp() {
		return log.getTimestamp();
	}
}
