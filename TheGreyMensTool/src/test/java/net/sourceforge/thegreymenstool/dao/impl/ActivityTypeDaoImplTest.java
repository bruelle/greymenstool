package net.sourceforge.thegreymenstool.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.sourceforge.thegreymenstool.dao.ActivityTypeDao;
import net.sourceforge.thegreymenstool.domain.ActivityType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-config.xml")
public class ActivityTypeDaoImplTest extends AbstractBaseDBUnitTest {
	@Autowired
	private ActivityTypeDao dao;

	@Test
	public void testFindAllActivityTypes() {
		List<ActivityType> ps = dao.findAll();
		assertEquals(2, ps.size());
		assertTrue(ps.get(0).getName().endsWith("eilig"));
	}

	@Test
	public void testCreateActivityType() {
		ActivityType at = dao.create("mehrzeilig");
		assertNotNull(at);
		assertTables("ActivityType");
	}

	@Test
	public void testDeleteActivityType() {
		ActivityType at = dao.findByName("langweilig");
		dao.delete(at);
		assertTables("ActivityType");
	}

	@Test
	public void testFindActivityTypeByName() {
		ActivityType at = dao.findByName("langweilig");
		assertNotNull(at);
		assertEquals("langweilig", at.getName());
	}

	@Test
	public void testUpdateActivityType() {
		ActivityType at = dao.findByName("langweilig");
		at.setName("mehrteilig");
		dao.update(at);
		assertTables("ActivityType");
	}

}
