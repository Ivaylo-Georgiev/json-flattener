package mongodb.json.flattener.app;

import java.io.IOException;
import java.io.InputStreamReader;

import mongodb.json.flattener.command.CommandHandler;

public class App {

	public static void main(String[] args) throws IOException {
		CommandHandler commandHandler = new CommandHandler();
		System.out.print(">");
		String command = commandHandler.readCommand(new InputStreamReader(System.in));
		if (commandHandler.validateCommand(command)) {
			commandHandler.executeCommand(command);
		} else {
			System.out.println(
					"Invalid command. Your command should match to the following pattern: cat <filename> | flatten");
		}
	}

}
