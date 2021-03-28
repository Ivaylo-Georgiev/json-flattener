package mongodb.json.flattener.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import mongodb.json.flattener.JsonFlattener;
import mongodb.json.flattener.ObjectFlattener;

public class CommandHandler {

	private final static Pattern COMMAND_PATTERN = Pattern.compile("cat *.json | flatten");
	private final static int INDENTATION_SPACING = 4;
	
	public String readCommand(InputStreamReader inputStreamReader) throws IOException {
		try (BufferedReader commandReader = new BufferedReader(inputStreamReader)) {
			String command = commandReader.readLine();
			return command;
		}
	}

	public boolean validateCommand(String command) {
		Matcher matcher = COMMAND_PATTERN.matcher(command);
		return matcher.find();
	}

	private String extractFileName(String command) {
		return command.split(" \\| ")[0].split(" ")[1];
	}

	private String readFile(String fileName) throws FileNotFoundException, IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	private String flattenJson(String json) {
		JSONObject jsonObject = new JSONObject(json);
		StringBuilder flattenedJson = new StringBuilder("{");
		JsonFlattener<JSONObject> jsonFlattener = new ObjectFlattener<JSONObject>(flattenedJson, null, jsonObject);
		jsonFlattener.flatten();
		flattenedJson.append("}");
		JSONObject flattenedJsonObject= new JSONObject(flattenedJson.toString());
		return flattenedJsonObject.toString(INDENTATION_SPACING);
	}

	public String executeCommand(String command) throws FileNotFoundException, IOException {
		String fileName = extractFileName(command);
		String json = readFile(fileName);
		String flattenedJson = flattenJson(json);

		System.out.println(flattenedJson);

		return flattenedJson;
	}

}
