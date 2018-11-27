package pLang;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	private static ArrayList<String> commands = new ArrayList<String>();
	private static Interpretor interpretor;
	
	public static void main(String[] args) throws FileNotFoundException {
		File commandFile = new File(args[0]);
		Scanner commandReader = new Scanner(commandFile);
		while(commandReader.hasNextLine()) {
			commands.add(commandReader.nextLine());
		}
		commandReader.close();
		interpretor = new Interpretor(commands);
		interpretor.run();
	}

}
