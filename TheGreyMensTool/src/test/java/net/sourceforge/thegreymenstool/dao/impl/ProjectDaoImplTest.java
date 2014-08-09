package net.sourceforge.thegreymenstool.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.sourceforge.thegreymenstool.dao.ProjectDao;
import net.sourceforge.thegreymenstool.domain.Project;
import net.sourceforge.thegreymenstool.domain.ProjectActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-config.xml")
public class ProjectDaoImplTest extends AbstractBaseDBUnitTest {
	@Autowired
	private ProjectDao dao;

	@Test
	public void testFindAllProjects() {
		List<Project> ps = dao.findAll();
		assertEquals(2, ps.size());
		assertTrue(ps.get(0).getName().endsWith("eins"));
	}

	@Test
	public void testCreateProject() {
		Project p = dao.create("Keins");
		assertNotNull(p);
		assertTables("Project");
	}

	@Test
	public void testDeleteProject() {
		Project p = dao.findByName("Meins");
		dao.delete(p);
		assertTables("Project");
	}

	@Test
	public void testFindProjectByName() {
		Project p = dao.findByName("Deins");
		assertNotNull(p);
		assertEquals("Deins", p.getName());
	}

	@Test
	public void testUpdateProject() {
		Project p = dao.findByName("Deins");
		p.setName("Seins");
		dao.update(p);
		assertTables("Project");
	}

	@Test
	public void testAddProjectActivity() {
		Project p = dao.findByName("Deins");
		dao.addProjectActivity(p, new ProjectActivity("Kaffeetrinken"));
		dao.update(p);
		assertTables("Project", "ProjectActivity");
	}
	
	@After
	public void tearDown(){
		dao.close();
	}
}
