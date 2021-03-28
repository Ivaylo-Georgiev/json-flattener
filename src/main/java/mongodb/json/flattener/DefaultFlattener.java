package mongodb.json.flattener;

import org.json.JSONObject;

public class DefaultFlattener<T> extends JsonFlattener<T> {

	public DefaultFlattener(StringBuilder flattenedJson, String parentKey, T value) {
		super(flattenedJson, parentKey, value);
	}

	@Override
	public void flatten() {
		flattenedJson.append(JSONObject.quote(parentKey));
		flattenedJson.append(":");
		flattenedJson.append(value);
	}

}
