package net.sourceforge.thegreymenstool.utils;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.apache.commons.lang.ArrayUtils;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitRuntimeException;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTestUtilities {

	private static final String INIT_SUFFIX = "ini";
	private static final String REF_SUFFIX = "ref";
	private static final String XML_SUFFIX = "xml";

	@Autowired
	private TestContextHolder context;

	@Autowired
	private IDatabaseTester dbt;
	private IDataSet masterInitDataset;
	private String resultFileDir;

	public IDataSet getMasterInitDataset() {
		return masterInitDataset;
	}

	public void setMasterInitDataset(IDataSet masterInitDataset) {
		this.masterInitDataset = masterInitDataset;
	}

	public void assertTables(String... tables) {
		IDataSet result;
		try {
			result = dbt.getConnection().createDataSet(tables);
			String referenceLocation = buildResourceLocationString(
					getTestMethod().getName(), REF_SUFFIX);
			XmlDataSet reference;
			File file = new File(resultFileDir + referenceLocation);
			file.getParentFile().mkdirs();
			XmlDataSet.write(result, new FileWriter(file), "UTF-8");
			try {
				reference = loadXmlDataset(referenceLocation);
				Assertion.assertEquals(reference, result);
			} catch (DataSetException e) {
				Assert.fail("wrote result dataset to " + file.getPath());
			}
		} catch (SQLException e) {
			throw new DatabaseUnitRuntimeException(e);
		} catch (Exception e) {
			throw new DatabaseUnitRuntimeException(e);
		}
	}

	private String buildResourceLocationString(String... typeSuffices) {
		StringBuilder b = new StringBuilder();
		b.append('/').append(getTestClass().getName().replace('.', '/'));
		Object[] sufs = ArrayUtils.add(typeSuffices, XML_SUFFIX);
		for (Object s : sufs) {
			b.append('.').append(s);
		}
		return b.toString();
	}

	public IDataSet getInitDataset() throws DataSetException {
		String resourceLoaction = buildResourceLocationString(INIT_SUFFIX);
		IDataSet ds = loadXmlDataset(resourceLoaction);
		if (masterInitDataset != null) {
			ds = new CompositeDataSet(new IDataSet[] { masterInitDataset, ds });
		}
		return ds;
	}

	private Class<?> getTestClass() {
		return context.getTestContext().getTestClass();
	}

	private Method getTestMethod() {
		return context.getTestContext().getTestMethod();
	}

	private XmlDataSet loadXmlDataset(String resourceLoaction)
			throws DataSetException {
		return new XmlDataSet(getTestClass().getResourceAsStream(
				resourceLoaction));
	}

	public String getResultFileDir() {
		return resultFileDir;
	}

	public void setResultFileDir(String resultFileDir) {
		this.resultFileDir = resultFileDir;
	}
}
