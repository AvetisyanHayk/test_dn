package com.dn.gaverzicht.renderingstatistics;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.dn.gaverzicht.slp.concretelogs.StartRenderingRequest;
import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogParser;

public class RenderingTest {
	
	StartRenderingRequest startRenderingRequest;
	
	Log startRenderingRequestLog;
	
	String startRenderingRequestLogLine = "2010-10-06 09:02:11,550 [WorkerThread-4]" + " INFO  [ServiceProvider]:"
			+ " Executing request startRendering with arguments [123456, 0]";
	

	@Before
	public void before() throws ParseException {
		startRenderingRequestLog = LogParser.parseLog(startRenderingRequestLogLine);
		startRenderingRequest = new StartRenderingRequest(startRenderingRequestLog);
	}
	
	@Test
	public void new_instance_with_valid_startRenderingRequest_is_allowed() {
		new Rendering(startRenderingRequest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_null_startRenderingRequest_not_allowed() {
		new Rendering(null);
	}
	
	
}
