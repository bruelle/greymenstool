package net.sourceforge.thegreymenstool.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.sourceforge.thegreymenstool.dao.BookingElementDao;
import net.sourceforge.thegreymenstool.domain.BookingElement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-config.xml")
public class BookingElementDaoImplTest extends AbstractBaseDBUnitTest {
	@Autowired
	private BookingElementDao dao;

	@Test
	public void testFindAllBookingElements() {
		List<BookingElement> ps = dao.findAll();
		assertEquals(2, ps.size());
		assertTrue(ps.get(0).getName().endsWith("eilig"));
	}

	@Test
	public void testCreateBookingElement() {
		BookingElement at = dao.create("mehrzeilig");
		assertNotNull(at);
		assertTables("BookingElement");
	}

	@Test
	public void testDeleteBookingElement() {
		BookingElement at = dao.findByName("Book langweilig");
		dao.delete(at);
		assertTables("BookingElement");
	}

	@Test
	public void testFindBookingElementByName() {
		BookingElement at = dao.findByName("Book langweilig");
		assertNotNull(at);
		assertEquals("Book langweilig", at.getName());
	}

	@Test
	public void testUpdateBookingElement() {
		BookingElement at = dao.findByName("Book langweilig");
		at.setName("mehrteilig");
		dao.update(at);
		assertTables("BookingElement");
	}

}
