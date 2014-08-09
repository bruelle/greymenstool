package net.sourceforge.thegreymenstool.service;

import java.util.Date;
import java.util.List;

import net.sourceforge.thegreymenstool.dao.ProjectActivityDao;
import net.sourceforge.thegreymenstool.dao.ProjectActivityExecutionDao;
import net.sourceforge.thegreymenstool.dao.ProjectDao;
import net.sourceforge.thegreymenstool.domain.DomainObject;
import net.sourceforge.thegreymenstool.domain.Project;
import net.sourceforge.thegreymenstool.domain.ProjectActivity;
import net.sourceforge.thegreymenstool.domain.ProjectActivityExecution;
import net.sourceforge.thegreymenstool.utils.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Facade for persistence management and relational change of domain objects.
 * 
 * @author bruelle
 * 
 */
@Service
public class DomainService {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ProjectActivityExecutionDao projectActivityExecutionDao;
	@Autowired
	private ProjectActivityDao projectActivityDao;

	public Project createProject(String name) {
		return projectDao.create(name);
	}

	public List<Project> findAllProjects() {
		return projectDao.findAll();
	}

	public boolean addProjectActivityToProject(ProjectActivity activity,
			Project project) {
		boolean r = project.getProjectActivities().add(activity);

		if (r) {
			Project oldProject = activity.getProject();
			if (oldProject != null) {
				removeActivityFromProject(activity, oldProject);
			}
			activity.setProject(project);
		}
		return r;
	}

	public boolean removeActivityFromProject(ProjectActivity activity,
			Project project) {
		boolean r = project.getProjectActivities().remove(activity);

		if (r) {
			activity.setProject(null);
		}
		return r;
	}

	public Time getDayTime(Date date)
	{
		Date beginningOfDay = Time.getBeginningOfDay(date);
		Date beginningOfNextDay = Time.getBeginningOfFutureDay(date, 1);
		List<ProjectActivityExecution> l = projectActivityExecutionDao.getExecutionsInTimeInterval(beginningOfDay, beginningOfNextDay);
		int sum = 0;
		for (ProjectActivityExecution projectActivityExecution : l) {
			sum += projectActivityExecution.getDuration();
		}
		return new Time(sum);
	}

	public void update(DomainObject domainObject) {
		
	}

	public ProjectActivity createProjectActivity(String name) {
		return projectActivityDao.create(name);
	}

	public ProjectActivityExecution createProjectActivityExecution() {
		return projectActivityExecutionDao.create();
	}
}
