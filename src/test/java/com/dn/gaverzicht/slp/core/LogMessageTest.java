package com.dn.gaverzicht.slp.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class LogMessageTest {
	private LogMessage logMessage;
	
	@Before
	public void before() {
		 logMessage = new LogMessage("Message Line 1");
	}

	@Test
	public void adding_empty_firstLine_does_not_throw_any_exception() {
		logMessage.addLine("");
	}
	
	@Test
	public void adding_not_empty_line_does_not_throw_any_exception() {
		logMessage.addLine("Message Line 2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void adding_null_line_throws_exception() {
		logMessage.addLine(null);
	}
	
	@Test
	public void log_message_having_empty_firstLine_is_valid() {
		new LogMessage("");
	}
	
	@Test
	public void log_message_having_not_empty_firstLine_is_valid() {
		new LogMessage(logMessage.get(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void log_message_having_null_firstLine_throws_exception() {
		new LogMessage(null);
	}
	
	@Test
	public void getting_first_line_does_not_throw_any_exception() {
		logMessage.get(0);
	}
	
	@Test
	public void first_line_containing_word_Message_contains_word_Message() {
		assertTrue(logMessage.firstLineContains("Message"));
	}
	
	@Test
	public void first_line_containing_word_Message_does_not_contain_word_message() {
		assertFalse(logMessage.firstLineContains("message"));
	}
	
	@Test
	public void empty_first_line_does_not_contain_word_message() {
		assertFalse(new LogMessage("").firstLineContains("message"));
	}
	
	@Test
	public void empty_first_line_contains_empty_text() {
		assertTrue(new LogMessage("").firstLineContains("   "));
	}
}
