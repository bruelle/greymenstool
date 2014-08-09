package net.sourceforge.thegreymenstool.utils;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class TestContextListener extends AbstractTestExecutionListener {

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		setContextInHolder(testContext);
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		setContextInHolder(testContext);
	}

	private void setContextInHolder(TestContext testContext) {
		TestContextHolder holder = testContext.getApplicationContext().getBean(
				TestContextHolder.class);
		holder.setTestContext(testContext);
	}
}
