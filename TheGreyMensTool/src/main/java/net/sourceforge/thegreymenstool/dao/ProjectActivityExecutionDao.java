package net.sourceforge.thegreymenstool.dao;

import java.util.Date;
import java.util.List;

import net.sourceforge.thegreymenstool.domain.Project;
import net.sourceforge.thegreymenstool.domain.ProjectActivityExecution;

/**
 * Data access object for {@link Project} related access.
 * 
 * @author bruelle
 * 
 */
public interface ProjectActivityExecutionDao extends UnnamedObjectDao<ProjectActivityExecution> {
	List<ProjectActivityExecution> getExecutionsInTimeInterval(Date from, Date to);
}
