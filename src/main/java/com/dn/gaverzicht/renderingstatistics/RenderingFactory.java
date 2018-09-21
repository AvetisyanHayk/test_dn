package com.dn.gaverzicht.renderingstatistics;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.dn.gaverzicht.slp.concretelogs.GetRenderingRequest;
import com.dn.gaverzicht.slp.concretelogs.GetRenderingReturn;
import com.dn.gaverzicht.slp.concretelogs.StartRenderingRequest;
import com.dn.gaverzicht.slp.concretelogs.StartRenderingReturn;
import com.dn.gaverzicht.slp.core.Log;
import com.dn.gaverzicht.slp.core.LogFactory;

final class RenderingFactory {
	private RenderingFactory() {}

	private final static String[] DISCRIMINATORS = new String[] { StartRenderingRequest.DISCRIMINATOR,
			StartRenderingReturn.DISCRIMINATOR, GetRenderingRequest.DISCRIMINATOR, GetRenderingReturn.DISCRIMINATOR };

	private Map<String, StartRenderingRequest> startRenderingRequests = new LinkedHashMap<>();
	private Map<String, GetRenderingRequest> getRenderingRequests = new LinkedHashMap<>();
	private Map<String, Rendering> renderingsMap = new LinkedHashMap<>();
	
	static Set<Rendering> createRenderings(String logFilePath) {
		Set<Rendering> renderings = new LinkedHashSet<>();
		RenderingFactory factory = new RenderingFactory();
		LogFactory.createLogs(logFilePath, DISCRIMINATORS).stream().forEach(log -> {
			factory.processLog(log);
		});
		renderings.addAll(factory.renderingsMap.values());
		return renderings;
	}

	private void processLog(Log log) {
		if (StartRenderingRequest.isValidLog(log)) {
			processStartRenderingRequest(new StartRenderingRequest(log));
		} else if (StartRenderingReturn.isValidLog(log)) {
			processStartRenderingReturn(new StartRenderingReturn(log));
		} else if (GetRenderingRequest.isValidLog(log)) {
			processGetRenderingRequest(new GetRenderingRequest(log));
		} else if (GetRenderingReturn.isValidLog(log)) {
			processGetRenderingReturn(new GetRenderingReturn(log));
		}

	}
	
	void processStartRenderingRequest(StartRenderingRequest srRequest) {
		startRenderingRequests.put(srRequest.getThreadName(), srRequest);
	}
	
	void processStartRenderingReturn(StartRenderingReturn srReturn) {
		StartRenderingRequest srRequest = startRenderingRequests.get(srReturn.getThreadName());
		if (srRequest != null) {
			Rendering rendering = renderingsMap.get(srReturn.getUid());
			if (rendering != null) {
				rendering.processStartRenderingReturn(srReturn);
			} else {
				rendering = new Rendering(srRequest);
				rendering.processStartRenderingReturn(srReturn);
				renderingsMap.put(srReturn.getUid(), rendering);
			}
			startRenderingRequests.remove(srReturn.getThreadName());
		}
	}
	
	void processGetRenderingRequest(GetRenderingRequest grRequest) {
		getRenderingRequests.put(grRequest.getThreadName(), grRequest);
	}
	
	void processGetRenderingReturn(GetRenderingReturn grReturn) {
		GetRenderingRequest grRequest = getRenderingRequests.get(grReturn.getThreadName());
		if (grRequest != null) {
			Rendering rendering = renderingsMap.get(grRequest.getUid());
			if (rendering != null) {
				rendering.processGetRenderingReturn(grReturn);
				getRenderingRequests.remove(grReturn.getThreadName());
			}
		}
	}
}
