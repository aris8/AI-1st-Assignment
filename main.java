import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		int[] max = new int[100];
		State initial;
		int avg = 0;
		for(int i=0;i < 100;i++){
			initial = new State();
			avg += initial.getScore();
			//System.out.println(initial.getScore());
		}
		System.out.println(avg /100);
		
		
		
		
	
	}

}
