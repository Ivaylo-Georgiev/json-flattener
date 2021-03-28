package mongodb.json.flattener;

public abstract class JsonFlattener<T> {

	protected StringBuilder flattenedJson;
	protected String parentKey;
	protected T value;

	public JsonFlattener(StringBuilder flattenedJson, String parentKey, T value) {
		this.flattenedJson = flattenedJson;
		this.parentKey = parentKey;
		this.value = value;
	}

	public abstract void flatten();

}
