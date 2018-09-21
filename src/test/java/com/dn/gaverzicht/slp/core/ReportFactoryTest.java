package com.dn.gaverzicht.slp.core;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.dn.gaverzicht.renderingstatistics.RenderingStatisticsReport;
import com.dn.gaverzicht.renderingstatistics.exceptions.ReportCreationException;
import com.dn.gaverzicht.slp.core.exceptions.ServerLogNotFoundException;

public class ReportFactoryTest {
	
	static final String DUMMY_DIR = "src/test/testreporttoberemoved";
	
	@Test
	public void does_not_throw_report_creation_exception_when_server_log_file_found_in_particular_directory_and_the_report_class_implements_report_interface() {
		createDummyServerLogFile();
		ReportFactory.createReport(RenderingStatisticsReport.class, DUMMY_DIR);
		removeDummyServerLogFile();
	}
	
	@Test(expected = ReportCreationException.class)
	public void throws_report_creation_exception_when_creating_report_of_a_class_which_does_not_implement_the_Report_interface() {
		ReportFactory.createReport(Log.class);
	}
	
	@Test(expected = ReportCreationException.class)
	public void throws_report_creation_exception_when_creating_report_of_type_null() {
		ReportFactory.createReport(null);
	}
	
	@Test(expected = ReportCreationException.class)
	public void throws_report_creation_exception_when_server_log_file_found_in_particular_directory_but_the_report_class_does_not_implement_report_interface() {
		createDummyServerLogFile();
		ReportFactory.createReport(Log.class, DUMMY_DIR);
		removeDummyServerLogFile();
	}
	
	@Test(expected = ServerLogNotFoundException.class)
	public void throws_report_creation_exception_when_server_log_file_not_found_in_particular_directory() {
		ReportFactory.createReport(RenderingStatisticsReport.class, "wrong/directory");
	}

	static void createDummyServerLogFile() {
		File file = new File(DUMMY_DIR);
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(DUMMY_DIR + "/" + ReportFactory.LOGFILE_NAME);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void removeDummyServerLogFile() {
		File file = new File(DUMMY_DIR + "/" + ReportFactory.LOGFILE_NAME);
		if (file.exists()) {
			file.delete();
		}

		file = new File(DUMMY_DIR);
		if (file.exists()) {
			file.delete();
		}
	}
}
