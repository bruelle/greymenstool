package net.sourceforge.thegreymenstool.dao.impl;

import net.sourceforge.thegreymenstool.utils.DatabaseTestUtilities;
import net.sourceforge.thegreymenstool.utils.TestContextListener;

import org.dbunit.DataSourceDatabaseTester;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestExecutionListeners(listeners = { TestContextListener.class,
		DependencyInjectionTestExecutionListener.class })
public class AbstractBaseDBUnitTest {

	@Autowired
	private DataSourceDatabaseTester tester;

	@Autowired
	private DatabaseTestUtilities utils;

	@Before
	public void setUp() throws Exception {
		tester.setDataSet(utils.getInitDataset());
		tester.onSetup();
	}

	protected void assertTables(String... table) {
		utils.assertTables(table);
	}
	
	@After
	public void tearDown(){
		
	}

}
