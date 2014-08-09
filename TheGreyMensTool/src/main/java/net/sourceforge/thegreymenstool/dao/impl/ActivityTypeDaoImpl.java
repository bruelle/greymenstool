package net.sourceforge.thegreymenstool.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sourceforge.thegreymenstool.dao.ActivityTypeDao;
import net.sourceforge.thegreymenstool.domain.ActivityType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ActivityTypeDaoImpl extends BaseDaoImpl<ActivityType> implements ActivityTypeDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ActivityType> findAllActivityTypes() {
		return em.createQuery("select at from ActivityType at").getResultList();
	}

	@Override
	@Transactional
	public ActivityType create(String name) {
		ActivityType activityType = new ActivityType();
		activityType.setName(name);
		em.persist(activityType);
		return activityType;
	}

	@Override
	@Transactional
	public void delete(ActivityType activityType) {
		em.remove(em.merge(activityType));
	}
	@Override
	public ActivityType findByName(String name) {
		Query q = em
				.createQuery("select at from ActivityType at where at.name = :name");
		q.setParameter("name", name);
		return (ActivityType) q.getSingleResult();
	}

	@Override
	@Transactional
	public void update(ActivityType activityType) {
		em.merge(activityType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityType> findAll() {
		Query q = em.createQuery("select at from ActivityType at");
		return q.getResultList();
	}

	@Override
	public void close() {
		em.close();
	}
}
