/**
 * 
 */
package net.sourceforge.thegreymenstool.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sourceforge.thegreymenstool.dao.BaseDao;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Joachim
 *
 */
public class BaseDaoImpl<O> implements BaseDao<O> {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void update(O object) {
		em.merge(object);
	}

	@Transactional
	@Override
	public void delete(O object) {
		em.remove(em.merge(object));
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends O> T findById(long id, Class<T> clazz) {
		return em.find(clazz, id);
	}
}
