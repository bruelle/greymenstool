package net.sourceforge.thegreymenstool.utils;

import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;

public class BidiController {
	private String manyPropertyName;
	private String onePropertyName;

	public BidiController(String manyPropertyName, String onePropertyName) {
		this.manyPropertyName = manyPropertyName;
		this.onePropertyName = onePropertyName;
	}

	@SuppressWarnings("unchecked")
	public void addManyToOne(Object manyObject, Object oneObject) {
		try {
			Collection<Object> manyCollection = (Collection<Object>) PropertyUtils
					.getProperty(oneObject, manyPropertyName);
			if (!manyCollection.contains(manyObject)) {
				manyCollection.add(manyObject);
				setOneToMany(oneObject, manyObject);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setOneToMany(Object oneObject, Object manyObject) {
		try {
			Object oneReference = PropertyUtils.getProperty(manyObject,
					onePropertyName);
			if (oneReference != oneObject) {
				if (oneReference != null) {
					PropertyUtils
							.setProperty(manyObject, onePropertyName, null);
					removeManyFromOne(manyObject, oneReference);
				}
				PropertyUtils.setProperty(manyObject, onePropertyName,
						oneObject);
				if (oneObject != null) {
					addManyToOne(manyObject, oneObject);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void removeManyFromOne(Object manyObject, Object oneObject) {
		Collection<Object> manyCollection;
		try {
			manyCollection = (Collection<Object>) PropertyUtils.getProperty(
					oneObject, manyPropertyName);
			boolean removed = manyCollection.remove(manyObject);
			if (removed) {
				setOneToMany(null, manyObject);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
