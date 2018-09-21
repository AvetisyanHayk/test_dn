package com.dn.gaverzicht.slp.concretelogs;

import com.dn.gaverzicht.renderingstatistics.Document;
import com.dn.gaverzicht.slp.core.Log;

public class StartRenderingRequest {

	public static final String DISCRIMINATOR = "Executing request startRendering with arguments";
	
	
	private final Log log;

	public StartRenderingRequest(Log log) {
		if (!isValidLog(log)) {
			throw new IllegalArgumentException();
		}
		this.log = log;
	}

	public Document getDocument() {
		String messageFirstLine = log.getMessageLine(0);
		Document document = null;
		if (messageFirstLine != null) {
			String[] documentProperties = messageFirstLine
					.substring(messageFirstLine.indexOf("[") + 1, messageFirstLine.indexOf("]")).split(",");
			document = new Document(Long.parseLong(documentProperties[0].trim()),
					Integer.parseInt(documentProperties[1].trim()));
		}
		return document;
	}

	public String getThreadName() {
		return log.getThreadName();
	}

	public static boolean isValidLog(Log log) {
		if (log == null) {
			return false;
		}
		if (!log.getMessage().firstLineContains(DISCRIMINATOR)) {
			return false;
		}
		return getDocument(log) != null;
	}

	private static Document getDocument(Log log) {
		String messageFirstLine = log.getMessageLine(0);
		if (messageFirstLine != null) {
			try {
				String documentString = messageFirstLine
						.substring(messageFirstLine.indexOf("[") + 1, messageFirstLine.indexOf("]"));
				if (ParsetimeRegularExpressions.DOCUMENT_REGEX.matcher(documentString).matches()) {
					String[] documentProperties = documentString.split(",");
					return new Document(Long.parseLong(documentProperties[0].trim()),
							Integer.parseInt(documentProperties[1].trim()));
				}
			} catch (StringIndexOutOfBoundsException ex) {
				//
			}
		}
		return null;
	}

}
