import java.util.ArrayList;

public class State implements Comparable<State>
{
	private Program A;
	private Program B;
    //The following variables hold the position of the empty tile
    //We use seperate variables for these to avoid searching the board every time
	private int score;
	
	public State()
	{
		this.A = null;
		this.B = null;
		this.score = -1;
		this.evaluate();
	}

	public State(Program A, Program B)
	{
		this.A = A;
		this.B = B;
		this.score = -1;
		this.evaluate();
	}
	
	public State(boolean randomized)
	{
		if(randomized)
		{
			this.A = new Program();
			this.B = new Program();
			this.score = -1;
			this.evaluate();
		}
	}
	
	public Program getA() {
		return A;
	}

	public void setA(Program a) {
		A = a;
	}

	public Program getB() {
		return B;
	}

	public void setB(Program b) {
		B = b;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public void swap(Program X, int i, int j)
	{
		X.swap(i, j);
	}
	
	public ArrayList<State> getChildren(int heuristic)
	{
		ArrayList<State> children = new ArrayList<State>();
		State child = new State(this.A, this.B);
		for (int i = 0; i < 2; i++)
		{
			for (int x = 0; x < A.getDimension_x(); x++){
				for (int y = 0; y < A.getDimension_y(); y++){
					if (i == 0){
						int temp = this.A.getElement(0, 0);
						this.A.setElement(0, 0, this.A.getElement(x, y));
						this.A.setElement(x, y, temp);
						child = new State(this.A, this.B);
						if(heuristic > 0)
						{
							child.evaluate();
						}
						children.add(child);
						temp = this.A.getElement(0, 0);
						this.A.setElement(0, 0, this.A.getElement(x, y));
						this.A.setElement(x, y, temp);
					}
					else{
						int temp = this.B.getElement(0, 0);
						this.B.setElement(0, 0, this.B.getElement(x, y));
						this.B.setElement(x, y, temp);
						child = new State(this.A, this.B);
						if(heuristic > 0)
						{
							child.evaluate();
						}
						children.add(child);
						temp = this.B.getElement(0, 0);
						this.B.setElement(0, 0, this.B.getElement(x, y));
						this.B.setElement(x, y, temp);
					}
				}
			}
            //If a heuristic is chosen to be used, calculate it
		}
		return children;
	}

	/***
     * Checks whether a state is terminal
     * If all the tiles in the board are in the correct position, there is no
     * need for any further moves
     */
	public boolean isTerminal()
	{
		return this.score == 0;
	}
	
	/**
	 * Evaluates the state based on heuristic functions
	 * @param heuristic: the heuristic to use
	 */
	private void evaluate()
	{

				this.heuristic1();
	}
	
	/**
	 * Evaluates the state by counting the number of tiles not in the correct position
	 */
	private void heuristic1()
	{
		for (int i = 0; i < A.getDimension_x(); i++){
			for (int j = 0; j < A.getDimension_y(); j++){
				if (A.getElement(i,j) == B.getElement(i,j)) this.score++;
			}
		}
		this.score++;
	}
	
	@Override
	public boolean equals(Object obj)
	{

		for(int i=0; i<this.A.getDimension_x(); i++)
		{
			for(int j=0; j<this.A.getDimension_y(); j++)
			{
				if((this.A.getElement(i, j) != ((State)obj).A.getElement(i, j)) || (this.B.getElement(i, j) != ((State)obj).B.getElement(i, j)))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void print(){
		A.printProgram();
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		B.printProgram();
	}
	
	@Override
	public int hashCode()
	{
		return this.A.toString().hashCode() + this.B.toString().hashCode();
	}
	
	
    //We override the compareTo function of this class so only the heuristic scores are compared
	public int compareTo(State s)
	{
		return Double.compare(this.score, s.score);
	}
}
