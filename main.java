/*
 * 3110095 Άρης Κωνσταντίνου
 * 3130033 Παναγιώτης Γερασιμάτος
 * */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, CloneNotSupportedException {
		
		
		
		State initial;
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;
		initial = new State();
		initial.print("start.txt");
		PrintWriter writer = new PrintWriter("stats.txt", "UTF-8");
		writer.printf("Minimum terminal state score: %d \n", initial.getTerminal());
		writer.printf("Score of intial state: %d \n", initial.getScore());
		
		
		long start = System.currentTimeMillis();
		terminalState = spaceSearcher.BestFSClosedSet(initial);		
		long end = System.currentTimeMillis();
		if(terminalState == null)
		{
			writer.println("Could not find solution");
		}
		else
		{
			terminalState.print("final.txt");
			writer.printf("Score of terminal state: %d \n", terminalState.getScore());
		}
		writer.println("BestFS (1st heuristic) with closed set search time: " + (double)(end - start) / 1000 + "sec.");
		System.out.println("***************");
		writer.close();
		
	
	}

}
