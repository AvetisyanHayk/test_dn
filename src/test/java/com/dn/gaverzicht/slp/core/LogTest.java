package com.dn.gaverzicht.slp.core;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class LogTest {
	private Log log;
	
	@Before
	public void before() {
		log = new Log(new LogTimestamp(new Date()), "Thread", LogLevel.INFO, "LogGeneratorClass", "MessageLine 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void empty_logGeneratorClass_throws_exception() {
		new Log(log.getTimestamp(), log.getThreadName(), log.getLogLevel(), "", log.getMessageLine(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void empty_threadName_throws_exception() {
		new Log(log.getTimestamp(), "", log.getLogLevel(), log.getLogGeneratorClass(), log.getMessageLine(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void null_logGeneratorClass_throws_exception() {
		new Log(log.getTimestamp(), log.getThreadName(), log.getLogLevel(), null, log.getMessageLine(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void null_logLevel_throws_exception() {
		new Log(log.getTimestamp(), log.getThreadName(), null, log.getLogGeneratorClass(), log.getMessageLine(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void null_threadName_throws_exception() {
		new Log(log.getTimestamp(), null, log.getLogLevel(), log.getLogGeneratorClass(), log.getMessageLine(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void null_timestamp_throws_exception() {
		new Log(null, log.getThreadName(), log.getLogLevel(), log.getLogGeneratorClass(), log.getMessageLine(0));
	}
	
	@Test
	public void valid_log_does_not_throw_any_exception() {
		new Log(log.getTimestamp(), log.getThreadName(), log.getLogLevel(), log.getLogGeneratorClass(), log.getMessageLine(0));
	}
}