package com.dn.gaverzicht.slp.core;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;

import org.junit.Test;

public class LogTimestampTest {

	@Test
	public void timestamp_equals_to_new_instance_toString() throws ParseException {
		assertEquals("2010-10-06 09:02:10,533", new LogTimestamp("2010-10-06 09:02:10,533").toString());
	}
	
	@Test (expected = ParseException.class)
	public void parsing_timestamp_from_empty_string_throws_parse_exception() throws ParseException {
		LogTimestamp.parseTimestamp("");
	}
	
	@Test (expected = ParseException.class)
	public void parsing_timestamp_from_longer_string_than_date_format_throws_parse_exception() throws ParseException {
		LogTimestamp.parseTimestamp("2010-10-06 09:02:10,1000");
	}
	
	@Test (expected = ParseException.class)
	public void parsing_timestamp_from_null_throws_parse_exception() throws ParseException {
		LogTimestamp.parseTimestamp(null);
	}
	
	@Test (expected = ParseException.class)
	public void parsing_timestamp_from_shorter_string_than_date_format_throws_parse_exception() throws ParseException {
		LogTimestamp.parseTimestamp("2010-10-06 09:02:10,10");
	}
	
	@Test (expected = ParseException.class)
	public void parsing_timestamp_from_string_with_incorrect_timestamp_format_throws_parse_exception() throws ParseException {
		LogTimestamp.parseTimestamp("2010/10/06");
	}
}
