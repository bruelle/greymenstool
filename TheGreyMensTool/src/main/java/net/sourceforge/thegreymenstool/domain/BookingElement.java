package net.sourceforge.thegreymenstool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Element for booking project activities. Reference to the booking system.
 * 
 * @author Joachim
 * 
 */
@Entity
public class BookingElement implements NamedObject {
	@OneToMany(mappedBy = "bookingElement")
	private List<ProjectActivity> projectActivities = new ArrayList<ProjectActivity>();
	
	private String name;
	@Id
	@GeneratedValue
	private long uid;

	/**
	 * Sets the project activities to be associated with this booking element.
	 * 
	 * @param projectActivities
	 *            the project activities to be associated with this booking
	 *            element
	 */
	public void setProjectActivities(List<ProjectActivity> projectActivities) {
		this.projectActivities = projectActivities;
	}

	/**
	 * Returns the project activities associated with this booking element.
	 * 
	 * @return the project activities associated with this booking element
	 */
	public List<ProjectActivity> getProjectActivities() {
		return projectActivities;
	}

	/**
	 * Sets the identifier of this project.
	 * 
	 * @param id
	 *            the identifier of this project
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the identifier of this project.
	 * 
	 * @return the identifier of this project
	 */
	public String getName() {
		return name;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	@Id
	public long getUid() {
		return uid;
	}

}
