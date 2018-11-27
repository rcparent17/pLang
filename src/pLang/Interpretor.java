package pLang;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpretor {
	
	private ArrayList<String> commands;
	private HashMap<String, Integer> vars;
	
	public Interpretor(ArrayList<String> commands) {
		this.commands = commands;
		vars = new HashMap<>();
	}
	
	public void run() {
		for(String command : commands) {
			interpret(command);
		}
	}
	
	private boolean isVar(String vName) {
		return vars.containsKey(vName);
	}
	
	private void interpret(String command) {
		String[] ln = command.split(" ");
		switch(ln[0]) {
		case "PRINT":
			if(isVar(ln[1]))
				System.out.println(vars.get(ln[1]));
			else
				System.out.println(ln[1]);
			break;
		case "VAR":
			if(isVar(ln[1]))
				System.out.println("Var \"" + ln[1] + "\" already exists.");
			else
				switch(ln[2]) {
				case "ADD": 
					int varV = 0;
					vars.put(ln[1], Integer.parseInt(ln[3]) + Integer.parseInt(ln[4])); 
					break;
				case "SUB":
					vars.put(ln[1], Integer.parseInt(ln[3]) - Integer.parseInt(ln[4])); 
					break;
				case "MULT":
					vars.put(ln[1], Integer.parseInt(ln[3]) * Integer.parseInt(ln[4])); 
					break;
				case "DIV":
					vars.put(ln[1], Integer.parseInt(ln[3]) / Integer.parseInt(ln[4])); 
					break;
				case "MOD":
					vars.put(ln[1], Integer.parseInt(ln[3]) % Integer.parseInt(ln[4])); 
					break;
				default: vars.put(ln[1], Integer.parseInt(ln[2]));
				}
			break;
		case "ADD":
			break;
		case "SUB":
			break;
		case "MULT":
			break;
		case "DIV":
			break;
		case "MOD":
			break;
		case "IF":
			break;
		case "INIF":
			break;
		case "FOR":
			break;
		case "INFOR":
			break;
		case "WHILE":
			break;
		case "INWHILE":
			break;
		}
	}
}
