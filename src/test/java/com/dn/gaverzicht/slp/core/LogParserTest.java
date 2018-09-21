package com.dn.gaverzicht.slp.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

public class LogParserTest {
	@Test
	public void line_not_beginning_with_timestamp_is_a_log_message_fragment() {
		assertTrue(LogParser.isLogMessageFragment("This line does not contain timestamp"));
	}
	
	@Test
	public void line_beginning_with_wrong_timestamp_format_is_a_log_message_fragment() {
		assertTrue(LogParser.isLogMessageFragment("2010/10/06 This line contains wrong timestamp"));
	}
	
	@Test
	public void line_beginning_with_correct_timestamp_format_is_not_a_message_fragment() {
		assertFalse(LogParser.isLogMessageFragment("2010-10-06 09:02:10,357 This line contains correct timestamp"));
	}
	
	@Test (expected = ParseException.class)
	public void parsing_log_from_empty_string_throws_parse_exception() throws ParseException {
		LogParser.parseLog(null);
	}
	
	@Test (expected = ParseException.class)
	public void parsing_log_from_null_throws_parse_exception() throws ParseException {
		LogParser.parseLog(null);
	}

	@Test (expected = ParseException.class)
	public void parsing_log_from_a_string_with_wrong_logGeneratorClass_format_throws_parse_exception() throws ParseException {
		LogParser.parseLog("2010-10-06 09:02:10,100 [ThreadName] INFO [logGeneratorClass}");
	}
	
	@Test (expected = ParseException.class)
	public void parsing_log_from_a_string_with_wrong_logLevel_value_throws_parse_exception() throws ParseException {
		LogParser.parseLog("2010-10-06 09:02:10,100 [ThreadName] WRONG [logGeneratorClass]: Message Line 1");
	}
	
	@Test (expected = ParseException.class)
	public void parsing_log_from_a_string_with_wrong_thread_format_throws_parse_exception() throws ParseException {
		LogParser.parseLog("2010-10-06 09:02:10,100 {ThreadName] INFO [logGeneratorClass]: Message Line 1");
	}
	
	@Test
	public void parsing_log_from_correct_log_format_string_does_not_throw_any_exception() {
		try {
			LogParser.parseLog("2010-10-06 09:02:10,100 [ThreadName] INFO [logGeneratorClass]: Message Line 1");
			assertTrue(true);
		} catch (ParseException e) {}
	}
}
