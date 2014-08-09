package net.sourceforge.thegreymenstool.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	@Override
	protected String resolveSystemProperty(String key) {
		String sysProp = super.resolveSystemProperty(key);
		if ("user.home".equals(key)) {
			sysProp = sysProp.replace('\\', '/');
		}
		return sysProp;
	}
}
