package net.sourceforge.thegreymenstool.dao;

import java.util.List;

public interface DefaultDao<O> extends BaseDao<O> {

	/**
	 * Returns all projects.
	 * 
	 * @return all projects
	 */
	List<O> findAll();
}
