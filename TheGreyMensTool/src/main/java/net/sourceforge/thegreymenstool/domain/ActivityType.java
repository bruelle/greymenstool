package net.sourceforge.thegreymenstool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Type of a smaller unit of work.
 * 
 * <p><i>Examples:</i></p>
 * <ul>
 *   <li>Implementation</li>
 *   <li>Project Setup</li>
 *   <li>Document Browsing</li>
 * </ul>
 * 
 * @author Joachim
 *
 */
@Entity
public class ActivityType implements NamedObject
{
	private String name;
	@Id
	@GeneratedValue
	private long uid;

	/**
	 * Sets the name of activity type.
	 * @param name the name of activity type
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Returns the name of activity type.
	 * @return the name of activity type
	 */
	public String getName()
	{
		return name;
	}

	public void setUid(long id)
	{
		this.uid = id;
	}

	@Id
	public long getUid()
	{
		return uid;
	}
}
