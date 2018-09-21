package com.dn.gaverzicht.slp.core;

public final class Log {

	private final LogTimestamp timestamp;
	private final String threadName;
	private final LogLevel logLevel;
	private final String logGeneratorClass;
	private LogMessage message;
	
	public Log(LogTimestamp timestamp, String threadName, LogLevel logLevel,
			String logGeneratorClass, String messageLine) {
		if (timestamp == null || logLevel == null
				|| threadName == null || threadName.isEmpty()
				|| logGeneratorClass == null || logGeneratorClass.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.message = new LogMessage(messageLine);
		this.timestamp = timestamp;
		this.threadName = threadName;
		this.logLevel = logLevel;
		this.logGeneratorClass = logGeneratorClass;
	}
	
	public LogTimestamp getTimestamp() {
		return timestamp;
	}

	public String getThreadName() {
		return threadName;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public String getLogGeneratorClass() {
		return logGeneratorClass;
	}

	public LogMessage getMessage() {
		return message;
	}
	
	public String getMessageLine(int lineIndex) {
		return message.get(lineIndex);
	}
	
	public void appendMessage(String messageLine) {
		message.addLine(messageLine);
	}
	
	public String getFormattedTimeStamp() {
		return timestamp.toString();
	}

}
