package com.dn.gaverzicht.slp.core;

import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class LogFactory {
	private LogFactory() {}
	
	public static List<Log> createLogs(String logFilePath, String... discriminators) {
		List<Log> logs = new ArrayList<>();
		try (Scanner scanner = new Scanner(Paths.get(logFilePath))) {
			while (scanner.hasNext()) {
				String logLine = scanner.nextLine();
				if (LogParser.isLogMessageFragment(logLine) && logs.size() > 0) {
					logs.get(logs.size() - 1).appendMessage(logLine);
				} else if (logContainsDiscriminator(logLine, discriminators)) {
					try {
						logs.add(LogParser.parseLog(logLine));
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (NoSuchFileException ex) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return logs;
	}
	
	private static boolean logContainsDiscriminator(String logLine, String... discriminators) {
		if (discriminators == null || discriminators.length == 0) {
			return true;
		}
		for (String discriminator : discriminators) {
			if (logLine.contains(discriminator)) {
				return true;
			}
		}
		return false;
	}
}
