                   package com.dn.gaverzicht.slp.concretelogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogParser;

public class GetRenderingRequestTest {

	Log getRenderingRequestLog;
	Log getRenderingRequestWithNoUidLog;
	Log getRenderingRequestWithCorruptedUidLog;
	Log startRenderingRequestLog;
	
	String getRenderingRequestLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request getRendering with arguments [1286373729338-5317]"
			+ " on service object { ReflectionServiceObject"
			+ " -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }";
	
	String getRenderingRequestWithNoUidLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request getRendering with arguments";

	String getRenderingRequestWithCorruptedLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request getRendering with arguments [1286373729338-abcd]"
			+ " on service object { ReflectionServiceObject"
			+ " -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }";
	
	String startRenderingRequestLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request startRendering with arguments [123456, 0]";
	

	@Before
	public void before() throws ParseException {
		getRenderingRequestLog = LogParser.parseLog(getRenderingRequestLogLine);
		getRenderingRequestWithNoUidLog = LogParser.parseLog(getRenderingRequestWithNoUidLogLine);
		getRenderingRequestWithCorruptedUidLog = LogParser.parseLog(getRenderingRequestWithCorruptedLogLine);
		startRenderingRequestLog = LogParser.parseLog(startRenderingRequestLogLine);
	}
	
	@Test
	public void getThreadName_equals_to_getRenderingRequestLogLine_threadName() {
		assertEquals("WorkerThread-4",
				(new GetRenderingRequest(getRenderingRequestLog))
					.getThreadName());
	}
	
	@Test
	public void getUid_equals_to_getRenderingRequestLogLine_uid() {
		assertEquals("1286373729338-5317",
				(new GetRenderingRequest(getRenderingRequestLog))
					.getUid());
	}

	@Test
	public void log_is_valid_getRenderingRequest_log_if_it_contains_appropriate_discriminator() throws ParseException {
		assertTrue(GetRenderingRequest.isValidLog(getRenderingRequestLog));
	}
	
	@Test
	public void log_is_not_valid_getRenderingRequest_log_if_it_contains_corrupted_uid() {
		assertFalse(GetRenderingRequest.isValidLog(getRenderingRequestWithCorruptedUidLog));
	}

	@Test
	public void log_is_not_valid_getRenderingRequest_log_if_it_does_not_contain_appropriate_discriminator() {
		assertFalse(GetRenderingRequest.isValidLog(startRenderingRequestLog));
	}
	
	@Test
	public void log_is_not_valid_getRenderingRequest_log_if_it_does_not_contain_uid() {
		assertFalse(GetRenderingRequest.isValidLog(getRenderingRequestWithNoUidLog));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_getRenderingRequest_or_corrupted_uid_not_allowed() throws ParseException {
		new GetRenderingRequest(getRenderingRequestWithCorruptedUidLog);
	}

	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_getRenderingRequest_or_incorrect_type_log_not_allowed() throws ParseException {
		new GetRenderingRequest(startRenderingRequestLog);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_getRenderingRequest_or_no_uid_log_not_allowed() throws ParseException {
		new GetRenderingRequest(getRenderingRequestWithNoUidLog);
	}

	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_null_log_not_allowed() {
		new GetRenderingRequest(null);
	}

	@Test
	public void new_instance_with_valid_getRenderingRequest_log_allowed() {
		new GetRenderingRequest(getRenderingRequestLog);
	}

	@Test
	public void null_is_not_valid_getRenderingRequest_log() {
		GetRenderingRequest.isValidLog(null);
	}
}