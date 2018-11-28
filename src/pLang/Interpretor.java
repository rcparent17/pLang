package pLang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Interpretor {
	
	private ArrayList<String> commands;
	private ArrayList<String> For;
	private HashMap<String, Integer> vars;
	private boolean latest = false;
	private boolean inFor = false;
	private int fLow = 0;
	private int fHigh = 0;
	private int line = 1;
	
	public Interpretor(ArrayList<String> commands) {
		this.commands = commands;
		this.For = new ArrayList<String>();
		vars = new HashMap<>();
	}
	
	public void run() {
		for(int line=1; line<=commands.size(); line++) {
			if(!commands.get(line).startsWith("#"))
				interpret(commands.get(line));
		}
	}
	
	private boolean isVar(String vName) {
		return vars.containsKey(vName);
	}
	
	private void interpret(String command) {
		String[] ln = command.split(" ");
		switch(ln[0]) {
		case "PRINT":
			if(inFor) {
				For.add(command); 
				return;
			}
			if(isVar(ln[1]))
				System.out.println(vars.get(ln[1]));
			else
				System.out.println(ln[1]);
			break;
		case "VAR":
			if(inFor) {
				For.add(command); 
				return;
			}
			switch(ln[2]) {
				case "INPUT":
					Scanner input = new Scanner(System.in);
					System.out.println(ln[3]);
					int response = input.nextInt();
					vars.put(ln[1], response);
					break;
				case "ADD": 
					vars.put(ln[1], Math(ln[3],ln[4], "ADD")); 
					break;
				case "SUB":
					vars.put(ln[1], Math(ln[3],ln[4], "SUB")); 
					break;
				case "MULT":
					vars.put(ln[1], Math(ln[3],ln[4], "MULT")); 
					break;
				case "DIV":
					vars.put(ln[1], Math(ln[3],ln[4], "DIV")); 
					break;
				case "MOD":
					vars.put(ln[1], Math(ln[3],ln[4], "MOD")); 
					break;
				case "++":
					vars.put(ln[1], Math(""+vars.get(ln[1]),"1","ADD"));
					break;
				case "--":
					vars.put(ln[1], Math(""+vars.get(ln[1]),"1","SUB"));
					break;
				default: vars.put(ln[1], Integer.parseInt(ln[2]));
				}
			break;
		case "ADD":
			if(inFor) {
				For.add(command); 
				return;
			}
			System.out.println(Math(ln[1],ln[2], "ADD"));
			break;
		case "SUB":
			if(inFor) {
				For.add(command); 
				return;
			}
			System.out.println(Math(ln[1],ln[2], "SUB"));
			break;
		case "MULT":
			if(inFor) {
				For.add(command); 
				return;
			}
			System.out.println(Math(ln[1],ln[2], "MULT"));
			break;
		case "DIV":
			if(inFor) {
				For.add(command); 
				return;
			}
			System.out.println(Math(ln[1],ln[2], "DIV"));
			break;
		case "MOD":
			if(inFor) {
				For.add(command); 
				return;
			}
			System.out.println(Math(ln[1],ln[2], "MOD"));
			break;
		case "IF":
			if(inFor) {
				For.add(command); 
				return;
			}
			latest = Bool(ln[1]);
			break;
		case "INIF":
			if(inFor) {
				For.add(command); 
				return;
			}
			if(latest)
				interpret(command.replace("INIF ", ""));
			break;
		case "FOR":
			For = new ArrayList<String>();
			String s1 = ln[1];
			String s2 = ln[2];
			if(isVar(s1))
				fLow = vars.get(s1);
			else
				fLow = Integer.parseInt(s1);
			if(isVar(s2))
				fHigh = vars.get(s2);
			else
				fHigh = Integer.parseInt(s2);
			inFor = true;
			break;
		case "ENDFOR":
			inFor = false;
			for(int i=fLow; i<=fHigh; i++) {
				for(int j=0; j<For.size(); j++) {
					interpret(For.get(j));
				}
			}
			break;
		case "WHILE":
			break;
		case "INWHILE":
			break;
		case "EXIT":
			System.exit(0);
		default:
			System.err.println("Command unknown: " + command);
		}
	}
	private Integer Math(String s1, String s2, String func) {
		int num1 = 0;
		int num2 = 0;
		if(isVar(s1))
			num1 = vars.get(s1);
		else
			num1 = Integer.parseInt(s1);
		if(isVar(s2))
			num2 = vars.get(s2);
		else
			num2 = Integer.parseInt(s2);
		switch(func) {
		case "ADD":  return num1 + num2;
		case "SUB":  return num1 - num2;
		case "MULT": return num1 * num2;
		case "DIV":  return num1 / num2;
		case "MOD":  return num1 % num2;
		}
		System.err.println("Invalid math operator passed to built in Math function. Contact developers");
		System.exit(1);
		return null;
	}
	
	private boolean Bool(String bool) {
		String op = "";
		int num1 = 0;
		int num2 = 0;
		String s1 = "";
		String s2 = "";
		String[] ops = {">=", "<=", ">", "<", "="};
		for(String b : ops) {
			if(bool.contains(b)) {
				s1 = bool.split(b)[0];
				s2 = bool.split(b)[1];
				if(isVar(s1))
					num1 = vars.get(s1);
				else
					num1 = Integer.parseInt(s1);
				op = b;
				if(isVar(s2))
					num2 = vars.get(s2);
				else
					num2 = Integer.parseInt(s2);
				break;
			}
		}
		switch(op) {
		case ">=": return num1 >= num2;
		case ">":  return num1 > num2;
		case "=":  return num1 == num2;
		case "<":  return num1 < num2;
		case "<=": return num1 <= num2;
		}
		System.err.println("Invalid boolean provided in script");
		System.exit(1);
		return false;
	}
}
