package net.sourceforge.thegreymenstool.presentation.model;

import net.sourceforge.thegreymenstool.domain.ActivityType;
import net.sourceforge.thegreymenstool.domain.Project;
import net.sourceforge.thegreymenstool.domain.ProjectActivity;
import net.sourceforge.thegreymenstool.domain.ProjectActivityExecution;

import com.jgoodies.binding.list.ArrayListModel;

public class MainWindowPresentationModel {
	private ProjectActivity currentProjectActivity;
	private ArrayListModel<ProjectActivity> activityList;
	private ArrayListModel<ActivityType> typeList;
	private ArrayListModel<Project> projectList;
	private TimerStatus timerStatus;
	private ProjectActivityExecution currentProjectActivityExecution;
	private boolean activityLocked;
	private boolean projectLocked;

	public void setActivityList(ArrayListModel<ProjectActivity> activityList) {
		this.activityList = activityList;
	}

	public ArrayListModel<ProjectActivity> getActivityList() {
		return activityList;
	}

	public void setCurrentProjectActivity(ProjectActivity currentProjectActivity) {
		this.currentProjectActivity = currentProjectActivity;
	}

	public ProjectActivity getCurrentProjectActivity() {
		return currentProjectActivity;
	}

	public void setTypeList(ArrayListModel<ActivityType> typeList) {
		this.typeList = typeList;
	}

	public ArrayListModel<ActivityType> getTypeList() {
		return typeList;
	}

	public void setProjectList(ArrayListModel<Project> projectList) {
		this.projectList = projectList;
	}

	public ArrayListModel<Project> getProjectList() {
		return projectList;
	}

	public void setTimerStatus(TimerStatus timerStatus) {
		this.timerStatus = timerStatus;
	}

	public TimerStatus getTimerStatus() {
		return timerStatus;
	}

	public void setCurrentProjectActivityExecution(
			ProjectActivityExecution currentProjectActivityExecution) {
		this.currentProjectActivityExecution = currentProjectActivityExecution;
	}

	public ProjectActivityExecution getCurrentProjectActivityExecution() {
		return currentProjectActivityExecution;
	}

	public void setActivityLocked(boolean activityLocked) {
		this.activityLocked = activityLocked;
	}

	public boolean isActivityLocked() {
		return activityLocked;
	}

	public void setProjectLocked(boolean projectLocked) {
		this.projectLocked = projectLocked;
	}

	public boolean isProjectLocked() {
		return projectLocked;
	}
}
