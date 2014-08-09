package net.sourceforge.thegreymenstool.dao;

public interface BaseDao<O> {

	/**
	 * Returns project with a specific name.
	 * 
	 * @param id
	 *            project name
	 * @return project with a specific name
	 */
	<T extends O> T findById(long id, Class<T> clazz);

	void update(O object);

	void delete(O object);

	void close();

}
