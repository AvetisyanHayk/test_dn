package com.dn.gaverzicht.slp.concretelogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.dn.gaverzicht.slp.concretelogs.GetRenderingReturn;
import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogParser;
import com.dn.gaverzicht.slp.core.LogTimestamp;

public class GetRenderingReturnTest {

	Log getRenderingReturnLog;
	String getRenderingReturnLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4] INFO  [ServiceProvider]:"
			+ " Service getRendering returned java.io.FileInputStream@335e335e";
	Log startRenderingReturnLog;
	String startRenderingReturnLogLine = "2010-10-06 09:03:05,873 [WorkerThread-17] INFO  [ServiceProvider]:"
			+ " Service startRendering returned 1286373785873-3536";

	@Before
	public void before() throws ParseException {
		getRenderingReturnLog = LogParser.parseLog(getRenderingReturnLogLine);
		startRenderingReturnLog = LogParser.parseLog(startRenderingReturnLogLine);
	}
	
	@Test
	public void getThreadName_equals_to_getRenderingReturnLogLine_threadName() {
		assertEquals("WorkerThread-4",
				(new GetRenderingReturn(getRenderingReturnLog))
					.getThreadName());
	}
	
	@Test
	public void getTimestamp_after_formatting_is_same_timestamp_like_in_getRenderingReturnLogLine() {
		assertEquals("2010-10-06 09:02:11,550",
				((new GetRenderingReturn(getRenderingReturnLog))
							.getTimestamp().toString()));
	}
	
	@Test
	public void getTimestamp_equals_to_getRenderingReturnLogLine_timestamp() throws ParseException {
		assertEquals(new LogTimestamp("2010-10-06 09:02:11,550"),
				((new GetRenderingReturn(getRenderingReturnLog))
							.getTimestamp()));
	}

	@Test
	public void log_is_valid_getRenderingReturn_log_if_it_contains_appropriate_discriminator() throws ParseException {
		assertTrue(GetRenderingReturn.isValidLog(getRenderingReturnLog));
	}

	@Test
	public void log_is_not_valid_getRenderingReturn_log_if_it_does_not_contain_appropriate_discriminator() {
		assertFalse(GetRenderingReturn.isValidLog(startRenderingReturnLog));
	}

	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_getRenderingReturn_log_not_allowed() throws ParseException {
		new GetRenderingReturn(startRenderingReturnLog);
	}

	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_null_log_not_allowed() {
		new GetRenderingReturn(null);
	}

	@Test
	public void new_instance_with_valid_getRenderingReturn_log_allowed() {
		new GetRenderingReturn(getRenderingReturnLog);
	}

	@Test
	public void null_is_not_valid_getRenderingReturn_log() {
		GetRenderingReturn.isValidLog(null);
	}
	
	
}