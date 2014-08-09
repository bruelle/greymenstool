package net.sourceforge.thegreymenstool.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Represents a top-level organizational unit of work.
 * 
 * @author Joachim
 * 
 */
@Entity
public class Project implements NamedObject{
	@Id
	@GeneratedValue
	private long id;
	private String name;

	@OneToMany(mappedBy = "project")
	private Set<ProjectActivity> projectActivities = new HashSet<ProjectActivity>();

	/**
	 * Returns the name of the project.
	 * 
	 * @return the name of the project
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the project.
	 * 
	 * @param name
	 *            new name of the project
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the activities associated with this project.
	 * 
	 * @return the activities associated with this project
	 */
	public Set<ProjectActivity> getProjectActivities() {
		return projectActivities;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
