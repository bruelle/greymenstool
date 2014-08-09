package net.sourceforge.thegreymenstool.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sourceforge.thegreymenstool.dao.BookingElementDao;
import net.sourceforge.thegreymenstool.domain.BookingElement;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookingElementDaoImpl extends BaseDaoImpl<BookingElement> implements BookingElementDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public BookingElement create(String id) {
		BookingElement bookingElement = new BookingElement();
		bookingElement.setName(id);
		em.persist(bookingElement);
		return bookingElement;
	}

	@Override
	public BookingElement findByName(String name) {
		Query q = em
				.createQuery("select be from BookingElement be where be.name = :name");
		q.setParameter("name", name);
		return (BookingElement) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingElement> findAll() {
		Query q = em.createQuery("select b from BookingElement b");
		return q.getResultList();
	}
}
