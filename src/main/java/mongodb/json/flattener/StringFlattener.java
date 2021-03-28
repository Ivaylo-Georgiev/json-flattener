package mongodb.json.flattener;

import org.json.JSONObject;

public class StringFlattener<T> extends JsonFlattener<T> {

	public StringFlattener(StringBuilder flattenedJson, String parentKey, T value) {
		super(flattenedJson, parentKey, value);
	}

	public void flatten() {
		flattenedJson.append(JSONObject.quote(parentKey));
		flattenedJson.append(":");
		flattenedJson.append(JSONObject.quote((String) value));
	}

}
