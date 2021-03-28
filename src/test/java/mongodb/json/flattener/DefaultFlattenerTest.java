package mongodb.json.flattener;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultFlattenerTest extends JsonFlattenerTest {

	private final static double EXPONENTIAL_VALUE = 2.99792458e8;

	@Test
	public void testBooleanFlattenerForTrueValue() {
		JsonFlattener<Boolean> booleanFlattener = new DefaultFlattener<>(flattenedJson, BOOLEAN_KEY, true);
		booleanFlattener.flatten();
		assertEquals("\"" + BOOLEAN_KEY + "\":true", flattenedJson.toString());
	}

	@Test
	public void testBooleanFlattenerForFalseValue() {
		JsonFlattener<Boolean> booleanFlattener = new DefaultFlattener<>(flattenedJson, BOOLEAN_KEY, false);
		booleanFlattener.flatten();
		assertEquals("\"" + BOOLEAN_KEY + "\":false", flattenedJson.toString());
	}

	@Test
	public void testIntegerFlattenerForPositiveIntegerValue() {
		JsonFlattener<Integer> integerFlattener = new DefaultFlattener<>(flattenedJson, INTEGER_KEY, Integer.MAX_VALUE);
		integerFlattener.flatten();
		assertEquals("\"" + INTEGER_KEY + "\":" + Integer.MAX_VALUE, flattenedJson.toString());
	}

	@Test
	public void testIntegerFlattenerForNegativeIntegerValue() {
		JsonFlattener<Integer> integerFlattener = new DefaultFlattener<>(flattenedJson, INTEGER_KEY, Integer.MIN_VALUE);
		integerFlattener.flatten();
		assertEquals("\"" + INTEGER_KEY + "\":" + Integer.MIN_VALUE, flattenedJson.toString());
	}

	@Test
	public void testDoubleFlattenerForPositiveDoubleValue() {
		JsonFlattener<Double> doubleFlattener = new DefaultFlattener<>(flattenedJson, DOUBLE_KEY, Math.PI);
		doubleFlattener.flatten();
		assertEquals("\"" + DOUBLE_KEY + "\":" + Math.PI, flattenedJson.toString());
	}

	@Test
	public void testDoubleFlattenerForNegativeDoubleValue() {
		JsonFlattener<Double> doubleFlattener = new DefaultFlattener<>(flattenedJson, DOUBLE_KEY, -Math.PI);
		doubleFlattener.flatten();
		assertEquals("\"" + DOUBLE_KEY + "\":" + (-Math.PI), flattenedJson.toString());
	}

	@Test
	public void testDoubleFlattenerForPositiveExponentialDoubleValue() {
		JsonFlattener<Double> doubleFlattener = new DefaultFlattener<>(flattenedJson, DOUBLE_KEY, EXPONENTIAL_VALUE);
		doubleFlattener.flatten();
		assertEquals("\"" + DOUBLE_KEY + "\":" + EXPONENTIAL_VALUE, flattenedJson.toString());
	}

	@Test
	public void testDoubleFlattenerForNegativeExponentialDoubleValue() {
		JsonFlattener<Double> doubleFlattener = new DefaultFlattener<>(flattenedJson, DOUBLE_KEY, -EXPONENTIAL_VALUE);
		doubleFlattener.flatten();
		assertEquals("\"" + DOUBLE_KEY + "\":" + (-EXPONENTIAL_VALUE), flattenedJson.toString());
	}

}
