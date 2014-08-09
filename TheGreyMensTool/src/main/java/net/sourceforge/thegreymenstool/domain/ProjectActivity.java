package net.sourceforge.thegreymenstool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProjectActivity implements NamedObject{
	@Id
	@GeneratedValue
	private long id;

	private String name;

	@ManyToOne
	private Project project;

	@ManyToOne
	private ActivityType activity;

	@ManyToOne
	private BookingElement bookingElement;

	@OneToMany(mappedBy = "projectActivity")
	private List<ProjectActivityExecution> executions = new ArrayList<ProjectActivityExecution>();

	@SuppressWarnings("unused")
	private ProjectActivity() {
	}

	public ProjectActivity(String name) {
		this.name = name;
	}

	public List<ProjectActivityExecution> getExecutions() {
		return executions;
	}

	public void setExecutions(List<ProjectActivityExecution> executions) {
		this.executions = executions;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ActivityType getActivity() {
		return activity;
	}

	public void setActivity(ActivityType activity) {
		this.activity = activity;
	}

	public void setBookingElement(BookingElement bookingElement) {
		this.bookingElement = bookingElement;
	}

	public BookingElement getBookingElement() {
		return bookingElement;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
