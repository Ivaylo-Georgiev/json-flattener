package mongodb.json.flattener.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class CommandHandlerTest {
	
	

	@Test
	public void testShoudFlattenJsonExampleFromTask() throws FileNotFoundException, IOException {
		String command = "cat ./src/test/resources/jsonFromTask.json | flatten";
		CommandHandler commandHandler = new CommandHandler();
		assertTrue(commandHandler.validateCommand(command));

		String flattenedJson = commandHandler.executeCommand(command);
		assertEquals("{\n" + 
				"    \"a\": 1,\n" + 
				"    \"b\": true,\n" + 
				"    \"c.d\": 3,\n" + 
				"    \"c.e\": \"test\"\n" + 
				"}", flattenedJson);
	}
	
	@Test
	public void testShoudFlattenJsonWithTwoNestedObjectsAndANullValue() throws FileNotFoundException, IOException {
		String command = "cat ./src/test/resources/jsonWithTwoNestedObjectsAndNullValue.json | flatten";
		CommandHandler commandHandler = new CommandHandler();
		assertTrue(commandHandler.validateCommand(command));

		String flattenedJson = commandHandler.executeCommand(command);
		assertEquals("{\n" + 
				"    \"a\": 1,\n" + 
				"    \"b\": true,\n" + 
				"    \"c.d\": 3,\n" + 
				"    \"c.e\": \"test\",\n" + 
				"    \"c.f.g\": null\n" + 
				"}", flattenedJson);
	}
	
	@Test
	public void testShoudFlattenJsonWithMultipleNestedObjects() throws FileNotFoundException, IOException {
		String command = "cat ./src/test/resources/jsonWithMultipleNestedObjects.json | flatten";
		CommandHandler commandHandler = new CommandHandler();
		assertTrue(commandHandler.validateCommand(command));

		String flattenedJson = commandHandler.executeCommand(command);
		assertEquals("{\"a.b.c.d.e\": 1}", flattenedJson);
	}


}
