package com.dn.gaverzicht.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	com.dn.gaverzicht.slp.concretelogs.GetRenderingRequestTest.class,
	com.dn.gaverzicht.slp.concretelogs.GetRenderingReturnTest.class,
	com.dn.gaverzicht.slp.concretelogs.StartRenderingRequestTest.class,
	com.dn.gaverzicht.slp.concretelogs.StartRenderingReturnTest.class })
public class ConcreteLogsTests {

}
