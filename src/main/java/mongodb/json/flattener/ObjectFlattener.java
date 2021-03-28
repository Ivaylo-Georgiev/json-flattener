package mongodb.json.flattener;

import java.util.Iterator;

import org.json.JSONObject;

public class ObjectFlattener<T> extends JsonFlattener<T> {

	private final static String DELIMITER = ".";

	public ObjectFlattener(StringBuilder flattenedJson, String parentKey, T value) {
		super(flattenedJson, parentKey, value);
	}

	public void flatten() {
		JSONObject jsonObject = (JSONObject) value;
		Iterator<String> keyIterator = jsonObject.keys();

		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Object keyValue = jsonObject.get(key);
			if (!hasParent()) {
				key = parentKey + DELIMITER + key;
			}

			flattenNestedElements(key, keyValue);

			if (keyIterator.hasNext()) {
				flattenedJson.append(",");
			}
		}

	}

	private boolean hasParent() {
		return this.parentKey == null;
	}

	private void flattenNestedElements(String key, Object keyValue) {
		if (keyValue instanceof JSONObject) {
			flattenNestedObject(key, (JSONObject) keyValue);
		} else if (keyValue instanceof String) {
			flattenNestedString(key, (String) keyValue);
		} else if (keyValue instanceof Boolean) {
			flattenNestedBoolean(key, (Boolean) keyValue);
		} else if (keyValue instanceof Integer) {
			flattenNestedInteger(key, (Integer) keyValue);
		} else if (keyValue instanceof Double) {
			flattenNestedDouble(key, (Double) keyValue);
		} else if (JSONObject.NULL.equals(keyValue)) {
			flattenNestedNull(key);
		}

	}

	private void flattenNestedObject(String key, JSONObject nestedObject) {
		JsonFlattener<JSONObject> objectFlattener = new ObjectFlattener<>(flattenedJson, key, nestedObject);
		objectFlattener.flatten();
	}

	private void flattenNestedString(String key, String nestedString) {
		JsonFlattener<String> stringFlattener = new StringFlattener<>(flattenedJson, key, nestedString);
		stringFlattener.flatten();
	}

	private void flattenNestedBoolean(String key, Boolean nestedBoolean) {
		JsonFlattener<Boolean> booleanFlattener = new DefaultFlattener<>(flattenedJson, key, nestedBoolean);
		booleanFlattener.flatten();
	}

	private void flattenNestedInteger(String key, Integer nestedInteger) {
		JsonFlattener<Integer> integerFlattener = new DefaultFlattener<>(flattenedJson, key, nestedInteger);
		integerFlattener.flatten();
	}

	private void flattenNestedDouble(String key, Double nestedDouble) {
		JsonFlattener<Double> doubleFlattener = new DefaultFlattener<>(flattenedJson, key, nestedDouble);
		doubleFlattener.flatten();
	}

	private void flattenNestedNull(String key) {
		JsonFlattener<String> nullFlattener = new DefaultFlattener<>(flattenedJson, key, "null");
		nullFlattener.flatten();
	}

}
