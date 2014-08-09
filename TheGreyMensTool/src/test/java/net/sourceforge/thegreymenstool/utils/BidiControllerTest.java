package net.sourceforge.thegreymenstool.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class BidiControllerTest {
	public static class OneClass {
		private Collection<ManyClass> manyCollection = new ArrayList<ManyClass>();
		private String name;

		public OneClass(String name) {
			this.name = name;
		}

		public Collection<ManyClass> getManyCollection() {
			return manyCollection;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static class ManyClass {
		private OneClass oneReference;
		private String name;

		public ManyClass(String name) {
			this.name = name;
		}

		public void setOneReference(OneClass oneReference) {
			this.oneReference = oneReference;
		}

		public OneClass getOneReference() {
			return oneReference;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private OneClass oneObject;
	private ManyClass manyObject1;
	private BidiController controller;
	private ManyClass manyObject2;
	private OneClass otherOneObject;

	@Before
	public void setUp() {
		oneObject = new OneClass("oneObject");
		otherOneObject = new OneClass("otherOneObject");
		manyObject1 = new ManyClass("manyObject1");
		manyObject2 = new ManyClass("manyObject2");
		controller = new BidiController("manyCollection", "oneReference");
		oneObject.getManyCollection().add(manyObject2);
		manyObject2.setOneReference(oneObject);
	}

	@Test
	public void testAddManyToOne() throws Exception {
		controller.addManyToOne(manyObject1, oneObject);
		assertTrue(oneObject.getManyCollection().contains(manyObject1));
		assertEquals(oneObject, manyObject1.getOneReference());
	}

	@Test
	public void testSetOneToMany() throws Exception {
		controller.setOneToMany(oneObject, manyObject1);
		assertTrue(oneObject.getManyCollection().contains(manyObject1));
		assertEquals(oneObject, manyObject1.getOneReference());
	}

	@Test
	public void testRemoveManyFromOne() throws Exception {
		controller.removeManyFromOne(manyObject2, oneObject);
		assertFalse(oneObject.getManyCollection().contains(manyObject2));
		assertNull(manyObject2.getOneReference());
	}

	@Test
	public void testResetOneToMany() throws Exception {
		controller.setOneToMany(null, manyObject2);
		assertFalse(oneObject.getManyCollection().contains(manyObject2));
		assertNull(manyObject2.getOneReference());
	}

	@Test
	public void testSwapByAdding() throws Exception {
		controller.addManyToOne(manyObject2, otherOneObject);
		assertFalse(oneObject.getManyCollection().contains(manyObject2));
		assertTrue(otherOneObject.getManyCollection().contains(manyObject2));
		assertEquals(otherOneObject, manyObject2.getOneReference());
	}
}
