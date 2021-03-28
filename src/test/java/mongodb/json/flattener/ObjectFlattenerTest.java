package mongodb.json.flattener;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

public class ObjectFlattenerTest extends JsonFlattenerTest {

	@Test
	public void testFlatObjectFlattener() {
		JSONObject flatObject = createFlatObject();

		JsonFlattener<JSONObject> objectFlattener = new ObjectFlattener<>(flattenedJson, null, flatObject);
		objectFlattener.flatten();
		assertEquals(
				"\"" + NULL_KEY + "\":null,\"" + BOOLEAN_KEY + "\":true,\"" + STRING_KEY + "\":\"" + STRING_VALUE
						+ "\",\"" + DOUBLE_KEY + "\":" + Math.PI + ",\"" + INTEGER_KEY + "\":" + Integer.MAX_VALUE + "",
				flattenedJson.toString());
	}

	@Test
	public void testCompositeObjectFlattener() {
		JSONObject compositeObject = createCompositeObject();

		JsonFlattener<JSONObject> objectFlattener = new ObjectFlattener<>(flattenedJson, null, compositeObject);
		objectFlattener.flatten();
		assertEquals("\"" + OBJECT_KEY + "." + BOOLEAN_KEY + "\":true", flattenedJson.toString());
	}

	private JSONObject createFlatObject() {
		JSONObject flatObject = new JSONObject();
		flatObject.put(BOOLEAN_KEY, true);
		flatObject.put(INTEGER_KEY, Integer.MAX_VALUE);
		flatObject.put(DOUBLE_KEY, Math.PI);
		flatObject.put(STRING_KEY, STRING_VALUE);
		flatObject.put(NULL_KEY, JSONObject.NULL);
		return flatObject;
	}

	private JSONObject createCompositeObject() {
		JSONObject nestedObject = new JSONObject();
		nestedObject.put(BOOLEAN_KEY, true);

		JSONObject compositeObject = new JSONObject();
		compositeObject.put(OBJECT_KEY, nestedObject);

		return compositeObject;
	}

}
