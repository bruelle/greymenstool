package net.sourceforge.thegreymenstool.dao;

import net.sourceforge.thegreymenstool.domain.NamedObject;

public interface NamedObjectDao<O extends NamedObject> extends DefaultDao<O> {

	O findByName(String name);

	O create(String name);

}
