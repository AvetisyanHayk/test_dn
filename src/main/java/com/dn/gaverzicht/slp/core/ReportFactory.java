package com.dn.gaverzicht.slp.core;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.dn.gaverzicht.renderingstatistics.exceptions.ReportCreationException;
import com.dn.gaverzicht.slp.core.exceptions.ServerLogNotFoundException;

public final class ReportFactory {

	public static final String DEFAULT_LOGS_DIR = "src/main/logs";
	public static final String DEFAULT_REPORTS_DIR = "src/main/reports";
	public static final String LOGFILE_NAME = "server.log";
	
	private ReportFactory() {}
	
	public static Report createReport(Class<?> reportClass) {
		return createReport(reportClass, DEFAULT_LOGS_DIR);
	}
	
	public static Report createReport(Class<?> reportClass, String logfileDir) {
		if (reportClass == null) {
			throw new ReportCreationException();
		}
		if (!Arrays.asList(reportClass.getInterfaces()).contains(Report.class)) {
			throw new ReportCreationException();
		}
		if (logfileDir == null || logfileDir.isEmpty()) {
			logfileDir = DEFAULT_LOGS_DIR;
		}
		checkOrCreateDirectory(logfileDir);
		String logfilePath = logfileDir + "/" + LOGFILE_NAME;
		if (!(new File(logfilePath).exists())) {
			throw new ServerLogNotFoundException();
		}
		Report report = null;
		try {
			report = (Report) reportClass.newInstance();
			report.init(logfilePath);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return report;
	}

	public static void checkOrCreateDirectory(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static final class Tools {
		private Tools() {}
		
		public static String linesToString(List<String> lines) {
			return linesToString(lines.stream());
		}
		
		public static String linesToString(Stream<String> streamOfLines) {
			return (new StringBuilder())
					.append(streamOfLines.reduce("", (previous, current)
							-> previous + (!previous.isEmpty() ? "\n" + current : current)))
					.toString();
		}
	}

}
