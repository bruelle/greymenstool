package net.sourceforge.thegreymenstool.utils;

import org.springframework.test.context.TestContext;

public class TestContextHolder {
	private TestContext testContext;

	public void setTestContext(TestContext testContext) {
		this.testContext = testContext;
	}

	public TestContext getTestContext() {
		return testContext;
	}
}
