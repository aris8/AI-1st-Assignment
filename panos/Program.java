import java.util.Random;


public class Program {
	
	// 2 dimension table to hold the program
	private int program[][];
	//The dimensions of the table
	private final int dimension_x = 2;
	private final int dimension_y = 4;

	
	
	/*
	 * Constructor that initializes the lists.
	 * We use 2 different constructors to avoid doing initializing the lists more
	 * times that we need to. The constructor also initializes a random program.
	 */
	public Program() {
		randProgram();
	}
	
	
	public int[][] getProgram() {
		return program;
	}

	public void setProgram(int[][] program) {
		this.program = program;
	}	

	// The method creates a random program.
	private void randProgram() {
		
		program = new int[dimension_x][dimension_y];
		Random rand = new Random();
		for (int i = 1; i < 5; i++) {
			for (int j=0; j< dimension_x; j++) {
				boolean flag = true;
				int x_pos = 0;
				int y_pos = 0;
				while(flag) {
					x_pos = rand.nextInt(dimension_x);
					y_pos = rand.nextInt(dimension_y);
					if (program[x_pos][y_pos] == 0) flag = false;
				}
				program[x_pos][y_pos] = i;
						
			}
	    }
			
	}
	
	public void printProgram(){
		for(int[] row : this.program) {
			 for (int i : row) {
		            System.out.print(i);
		            System.out.print("\t");
		        }
		        System.out.println();
        }
	}
	
	public void swap(int i, int j)
	{
		
		int temp = this.program[0][0];
		this.program[0][0] = this.program[i][j];
		this.program[i][j] = temp;
	}


	public int getDimension_x() {
		return dimension_x;
	}


	public int getDimension_y() {
		return dimension_y;
	}
	
	public int getElement(int i, int j){
		return this.program[i][j];
	}
	
	public void setElement(int i, int j, int x){
		this.program[i][j] = x;
	}

}