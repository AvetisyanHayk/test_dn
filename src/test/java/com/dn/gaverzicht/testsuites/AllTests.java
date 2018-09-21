package com.dn.gaverzicht.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	CoreTests.class, 
	ConcreteLogsTests.class,
	RenderingStatisticsTests.class })
public class AllTests {

}
