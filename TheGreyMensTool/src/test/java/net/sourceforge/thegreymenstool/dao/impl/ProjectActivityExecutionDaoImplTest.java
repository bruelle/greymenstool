package net.sourceforge.thegreymenstool.dao.impl;

import java.util.Calendar;
import java.util.List;

import net.sourceforge.thegreymenstool.dao.ProjectActivityExecutionDao;
import net.sourceforge.thegreymenstool.domain.ProjectActivityExecution;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-config.xml")
public class ProjectActivityExecutionDaoImplTest extends AbstractBaseDBUnitTest{
	@Autowired
	private ProjectActivityExecutionDao dao;
	@Test
	public void testGetExecutionsInTimeInterval() {
		Calendar from = Calendar.getInstance();
		from.clear();
		from.set(2000, 0, 1);
		Calendar to = Calendar.getInstance();
		to.clear();
		to.set(2000, 0, 3);
		List<ProjectActivityExecution> l = dao.getExecutionsInTimeInterval(from.getTime(), to.getTime());
		assertEquals(2, l.size());
		assertEquals(24*3600, l.get(0).getDuration());
	}

}
