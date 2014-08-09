package net.sourceforge.thegreymenstool.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sourceforge.thegreymenstool.dao.ProjectActivityExecutionDao;
import net.sourceforge.thegreymenstool.domain.ProjectActivity;
import net.sourceforge.thegreymenstool.domain.ProjectActivityExecution;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProjectActivityExecutionDaoImpl extends
		BaseDaoImpl<ProjectActivityExecution> implements
		ProjectActivityExecutionDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProjectActivity> findAllProjects() {
		return em.createQuery("select p from ProjectActivity p")
				.getResultList();
	}

	@Override
	@Transactional
	public ProjectActivityExecution create() {
		ProjectActivityExecution project = new ProjectActivityExecution();
		em.persist(project);
		return project;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectActivityExecution> findAll() {
		Query q = em.createQuery("select p from ProjectActivityExecution p");
		return q.getResultList();
	}

	@Override
	public void close() {
		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectActivityExecution> getExecutionsInTimeInterval(
			Date from, Date to) {
		Query q = em
				.createQuery("select p from ProjectActivityExecution p where p.startTime < :toDate and p.startTime >= :fromDate");
		q.setParameter("toDate", to);
		q.setParameter("fromDate", from);
		return q.getResultList();
	}
}
