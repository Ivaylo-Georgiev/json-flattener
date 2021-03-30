package mongodb.json.flattener.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mongodb.json.flattener.command.CommandHandler;

public class App {

	public static void main(String[] args) throws IOException {
		System.out.println("    _                        __ _       _   _                        \r\n" + 
				"   (_)___  ___  _ __        / _| | __ _| |_| |_ ___ _ __   ___ _ __  \r\n" + 
				"   | / __|/ _ \\| '_ \\ _____| |_| |/ _` | __| __/ _ \\ '_ \\ / _ \\ '__| \r\n" + 
				"   | \\__ \\ (_) | | | |_____|  _| | (_| | |_| ||  __/ | | |  __/ |    \r\n" + 
				"  _/ |___/\\___/|_| |_|     |_| |_|\\__,_|\\__|\\__\\___|_| |_|\\___|_|    \r\n" + 
				" |__/                                                                \n");
		
		CommandHandler commandHandler = new CommandHandler();

		try (BufferedReader commandReader = new BufferedReader(new InputStreamReader(System.in))) {
			String command = null;
			while (true) {
				System.out.print(">");
				command = commandHandler.readCommand(commandReader);

				if (commandHandler.isQuitCommand(command)) {
					System.out.println("Program terminated successfully");
					break;
				}

				if (commandHandler.validateCommand(command)) {
					commandHandler.executeCommand(command);
				} else {
					System.out.println(
							"Invalid command. Your command should match the following pattern: cat <filename> | flatten. To quit, type 'quit'");
				}
			}
		}
	}

}
