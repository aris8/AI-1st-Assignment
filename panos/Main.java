public class Main
{

	/**
     *
     * To BFS apaitei ekthetiko xwro epomenws poly pithanon h java na xemeinei
     * apo mnhmh prin oloklhrwthei o algorithmos. Trexte thn java me thn
     * parametro -Xmx gia na xeperasete auto to provlhma.
     *
     * e.g. java -Xmx512m Main
	 */
	public static void main(String[] args)
	{
		State initialState = new State(true);
		initialState.print();
		System.out.println("***************");
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;
		
		/**
		 * BestFS (1st heuristic) with closed set
		 */
		terminalState = null;
		long start = System.currentTimeMillis();
		terminalState = spaceSearcher.BestFSClosedSet(initialState, 1);
		long end = System.currentTimeMillis();
		if(terminalState == null)
		{
			System.out.println("Could not find solution");
		}
		else
		{
			terminalState.print();
		}
		System.out.println("BestFS (1st heuristic) with closed set search time: " + (double)(end - start) / 1000 + " sec.");
		System.out.println("***************");
		
		
	} 

}
