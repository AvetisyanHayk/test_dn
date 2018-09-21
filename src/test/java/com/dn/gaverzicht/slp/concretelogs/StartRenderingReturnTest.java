package com.dn.gaverzicht.slp.concretelogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.dn.gaverzicht.slp.concretelogs.StartRenderingReturn;
import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogParser;
import com.dn.gaverzicht.slp.core.LogTimestamp;

public class StartRenderingReturnTest {

	Log getRenderingReturnLog;
	Log startRenderingReturnLog;
	Log startRenderingReturntWithNoUidLog;
	Log startRenderingReturnWithCorruptedUidLog;
	
	String getRenderingReturnLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4] INFO  [ServiceProvider]:"
			+ " Service getRendering returned java.io.FileInputStream@335e335e";
	
	String startRenderingReturnLogLine = "2010-10-06 09:03:05,873 [WorkerThread-17] INFO  [ServiceProvider]:"
			+ " Service startRendering returned 1286373785873-3536";
	
	String startRenderingReturnWithNoUidLogLine = "2010-10-06 09:03:05,873 [WorkerThread-17] INFO  [ServiceProvider]:"
			+ " Service startRendering returned";
	
	String startRenderingReturnWithCorruptedUidLogLine = "2010-10-06 09:03:05,873 [WorkerThread-17] INFO  [ServiceProvider]:"
			+ " Service startRendering returned 1286373785873-abcd";

	@Before
	public void before() throws ParseException {
		getRenderingReturnLog = LogParser.parseLog(getRenderingReturnLogLine);
		startRenderingReturnLog = LogParser.parseLog(startRenderingReturnLogLine);
		startRenderingReturntWithNoUidLog = LogParser.parseLog(startRenderingReturnWithNoUidLogLine);
		startRenderingReturnWithCorruptedUidLog = LogParser.parseLog(startRenderingReturnWithCorruptedUidLogLine);
	}
	
	@Test
	public void getThreadName_equals_to_startRenderingReturnLogLine_threadName() {
		assertEquals("WorkerThread-17",
				(new StartRenderingReturn(startRenderingReturnLog))
					.getThreadName());
	}
	
	@Test
	public void getTimestamp_after_formatting_is_same_timestamp_like_in_startRenderingReturnLogLine() {
		assertEquals("2010-10-06 09:03:05,873",
				((new StartRenderingReturn(startRenderingReturnLog))
							.getTimestamp().toString()));
	}
	
	@Test
	public void getTimestamp_equals_to_startRenderingReturnLogLine_timestamp() throws ParseException {
		assertEquals(new LogTimestamp("2010-10-06 09:03:05,873"),
				((new StartRenderingReturn(startRenderingReturnLog))
							.getTimestamp()));
	}

	@Test
	public void log_is_valid_startRenderingReturn_log_if_it_contains_appropriate_discriminator() throws ParseException {
		assertTrue(StartRenderingReturn.isValidLog(startRenderingReturnLog));
	}

	@Test
	public void log_is_not_valid_startRenderingReturn_log_if_it_contains_corrupted_uid() {
		assertFalse(StartRenderingReturn.isValidLog(startRenderingReturnWithCorruptedUidLog));
	}
	
	@Test
	public void log_is_not_valid_startRenderingReturn_log_if_it_does_not_contain_appropriate_discriminator() {
		assertFalse(StartRenderingReturn.isValidLog(getRenderingReturnLog));
	}
	
	@Test
	public void log_is_not_valid_startRenderingReturn_log_if_it_does_not_contain_uid() {
		assertFalse(StartRenderingReturn.isValidLog(startRenderingReturntWithNoUidLog));
	}

	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_startRenderingReturn_log_not_allowed() throws ParseException {
		new StartRenderingReturn(getRenderingReturnLog);
	}

	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_null_log_not_allowed() {
		new StartRenderingReturn(null);
	}

	@Test
	public void new_instance_with_valid_startRenderingReturn_log_allowed() {
		new StartRenderingReturn(startRenderingReturnLog);
	}

	@Test
	public void null_is_not_valid_startRenderingReturn_log() {
		StartRenderingReturn.isValidLog(null);
	}
	
	
}