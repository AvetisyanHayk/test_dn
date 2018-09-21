package com.dn.gaverzicht.slp.concretelogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.dn.gaverzicht.slp.concretelogs.GetRenderingRequest;
import com.dn.gaverzicht.slp.concretelogs.StartRenderingRequest;
import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogParser;

public class StartRenderingRequestTest {

	Log getRenderingRequestLog;
	Log startRenderingRequestLog;
	Log startRenderingRequestWithNoDocumentInfoLog;
	Log startRenderingRequestWithEmptyDocumentInfoLog;
	Log startRenderingRequestWithCorruptedDocumentInfoLog;
	
	String getRenderingRequestLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request getRendering with arguments [1286373729338-5317]"
			+ " on service object { ReflectionServiceObject"
			+ " -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }";
	
	String startRenderingRequestLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request startRendering with arguments [123456, 0]";
	
	String startRenderingRequestWithNoDocumentInfoLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request startRendering with arguments";
	
	String startRenderingRequestWithEmptyDocumentInfoLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request startRendering with arguments []";
	
	String startRenderingRequestWithCorruptedDocumentInfoLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request startRendering with arguments [abc, def]";

	@Before
	public void before() throws ParseException {
		getRenderingRequestLog = LogParser.parseLog(getRenderingRequestLogLine);
		startRenderingRequestLog = LogParser.parseLog(startRenderingRequestLogLine);
		startRenderingRequestWithNoDocumentInfoLog = LogParser.parseLog(startRenderingRequestWithNoDocumentInfoLogLine);
		startRenderingRequestWithEmptyDocumentInfoLog = LogParser.parseLog(startRenderingRequestWithEmptyDocumentInfoLogLine);
		startRenderingRequestWithCorruptedDocumentInfoLog = LogParser.parseLog(startRenderingRequestWithCorruptedDocumentInfoLogLine);
	}
	
	@Test
	public void getThreadName_equals_to_startRenderingRequestLogLine_threadName() {
		assertEquals("WorkerThread-4",
				(new StartRenderingRequest(startRenderingRequestLog))
					.getThreadName());
	}
	
	@Test
	public void log_is_not_valid_startRenderingRequest_log_if_it_contains_corrupted_document_info() {
		assertFalse(GetRenderingRequest.isValidLog(startRenderingRequestWithCorruptedDocumentInfoLog));
	}

	@Test
	public void log_is_not_valid_startRenderingRequest_log_if_it_contains_empty_document_info() {
		assertFalse(GetRenderingRequest.isValidLog(startRenderingRequestWithNoDocumentInfoLog));
	}
	
	@Test
	public void log_is_not_valid_startRenderingRequest_log_if_it_does_not_contain_document_info() {
		assertFalse(GetRenderingRequest.isValidLog(startRenderingRequestWithNoDocumentInfoLog));
	}

	@Test
	public void log_is_valid_startRenderingRequest_log_if_it_contains_appropriate_discriminator() throws ParseException {
		assertTrue(StartRenderingRequest.isValidLog(startRenderingRequestLog));
	}

	@Test
	public void log_is_not_valid_startRenderingRequest_log_if_it_does_not_contain_appropriate_discriminator() {
		assertFalse(StartRenderingRequest.isValidLog(getRenderingRequestLog));
	}

	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_startRenderingRequest_log_not_allowed() throws ParseException {
		new StartRenderingRequest(getRenderingRequestLog);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_startRenderingRequest_or_with_corrupted_document_log_not_allowed() throws ParseException {
		new StartRenderingRequest(startRenderingRequestWithCorruptedDocumentInfoLog);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_startRenderingRequest_or_with_empty_document_log_not_allowed() throws ParseException {
		new StartRenderingRequest(startRenderingRequestWithEmptyDocumentInfoLog);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_not_valid_startRenderingRequest_or_with_no_document_log_not_allowed() throws ParseException {
		new StartRenderingRequest(startRenderingRequestWithNoDocumentInfoLog);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_null_log_not_allowed() {
		new StartRenderingRequest(null);
	}

	@Test
	public void new_instance_with_valid_startRenderingRequest_log_allowed() {
		new StartRenderingRequest(startRenderingRequestLog);
	}

	@Test
	public void null_is_not_valid_startRenderingRequest_log() {
		StartRenderingRequest.isValidLog(null);
	}
}