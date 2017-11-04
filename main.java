import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		State initial;
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;
		initial = new State(true);
		
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
		}
		System.out.println("BestFS (1st heuristic) with closed set search time: " + (double)(end - start) / 1000 /60 + "min.");
		System.out.println("***************");
		
	
	}

}
