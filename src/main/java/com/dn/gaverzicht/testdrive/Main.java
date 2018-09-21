package com.dn.gaverzicht.testdrive;

import com.dn.gaverzicht.renderingstatistics.RenderingStatisticsReport;
import com.dn.gaverzicht.slp.core.ReportFactory;

public class Main {
	
	
	public static void main(String[] args) {
		ReportFactory.createReport(RenderingStatisticsReport.class).saveToXml();
	}

}
