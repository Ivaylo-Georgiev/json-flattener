package mongodb.json.flattener;

import org.junit.Before;

public class JsonFlattenerTest {

	protected final static String BOOLEAN_KEY = "booleanKey";
	protected final static String INTEGER_KEY = "integerKey";
	protected final static String DOUBLE_KEY = "doubleKey";
	protected final static String STRING_KEY = "stringKey";
	protected final static String OBJECT_KEY = "objectKey";
	protected final static String NULL_KEY = "nullKey";

	protected final static String STRING_VALUE = "stringValue";

	protected StringBuilder flattenedJson;

	@Before
	public void init() {
		flattenedJson = new StringBuilder();
	}

}
