import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, CloneNotSupportedException {
		
		
		
		State initial;
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;
		initial = new State();
		initial.print();
		System.out.printf("Score of intial state: %d \n", initial.getScore());
		System.out.println(Arrays.deepToString(initial.getVa1()));
		System.out.println(Arrays.deepToString(initial.getVa2()));
		System.out.println(Arrays.deepToString(initial.getVa3()));
		System.out.println(Arrays.deepToString(initial.getVb1()));
		System.out.println(Arrays.deepToString(initial.getVb2()));
		System.out.println(Arrays.deepToString(initial.getVb3()));
		System.out.println(Arrays.deepToString(initial.getVc1()));
		System.out.println(Arrays.deepToString(initial.getVc2()));
		System.out.println(Arrays.deepToString(initial.getVc3()));
		
		
		long start = System.currentTimeMillis();
		terminalState = spaceSearcher.BestFSClosedSet(initial);		
		long end = System.currentTimeMillis();
		if(terminalState == null)
		{
			System.out.println("Could not find solution");
		}
		else
		{
			terminalState.print();
			System.out.printf("Score of terminal state: %d \n", terminalState.getScore());
		}
		System.out.println("BestFS (1st heuristic) with closed set search time: " + (double)(end - start) / 1000 /60 + "min.");
		System.out.println("***************");
		System.out.println(Arrays.deepToString(terminalState.getVa1()));
		System.out.println(Arrays.deepToString(terminalState.getVa2()));
		System.out.println(Arrays.deepToString(terminalState.getVa3()));
		System.out.println(Arrays.deepToString(terminalState.getVb1()));
		System.out.println(Arrays.deepToString(terminalState.getVb2()));
		System.out.println(Arrays.deepToString(terminalState.getVb3()));
		System.out.println(Arrays.deepToString(terminalState.getVc1()));
		System.out.println(Arrays.deepToString(terminalState.getVc2()));
		System.out.println(Arrays.deepToString(terminalState.getVc3()));
		
	
	}

}
