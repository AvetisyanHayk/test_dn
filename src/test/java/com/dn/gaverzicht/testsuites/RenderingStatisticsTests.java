package com.dn.gaverzicht.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	com.dn.gaverzicht.renderingstatistics.DocumentTest.class,
	com.dn.gaverzicht.renderingstatistics.SummaryTest.class,
	com.dn.gaverzicht.renderingstatistics.RenderingTest.class })
public class RenderingStatisticsTests {

}
