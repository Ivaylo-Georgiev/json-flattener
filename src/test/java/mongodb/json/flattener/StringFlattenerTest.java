package mongodb.json.flattener;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringFlattenerTest extends JsonFlattenerTest {

	@Test
	public void testStringFlattener() {
		JsonFlattener<String> stringFlattener = new StringFlattener<>(flattenedJson, STRING_KEY, STRING_VALUE);
		stringFlattener.flatten();
		assertEquals("\"" + STRING_KEY + "\":\"" + STRING_VALUE + "\"", flattenedJson.toString());
	}

}
