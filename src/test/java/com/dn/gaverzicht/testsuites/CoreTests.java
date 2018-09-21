package com.dn.gaverzicht.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ com.dn.gaverzicht.slp.core.LogTest.class,
	com.dn.gaverzicht.slp.core.LogMessageTest.class,
	com.dn.gaverzicht.slp.core.LogTimestampTest.class,
	com.dn.gaverzicht.slp.core.LogParserTest.class,
	com.dn.gaverzicht.slp.core.LogFactoryTest.class,
	com.dn.gaverzicht.slp.core.ReportFactoryTest.class })
public class CoreTests {}
