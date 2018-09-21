package com.dn.gaverzicht.slp.core;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LogFactoryTest {
	@Test
	public void wrong_logfilePath_returns_null() {
		assertNull(LogFactory.createLogs("wrongpathfile", "discriminataor"));
	}
	
	@Test
	public void correct_logfilePath_does_not_return_null() {
		ReportFactoryTest.createDummyServerLogFile();
		assertNotNull(LogFactory.createLogs(ReportFactoryTest.DUMMY_DIR + "/" + ReportFactory.LOGFILE_NAME, "discriminataor"));
		ReportFactoryTest.removeDummyServerLogFile();
	}
}
