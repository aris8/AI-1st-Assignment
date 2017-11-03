import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		State initial;
		int avg = 0;
		
		initial = new State();
		avg += initial.getScore();
			
		System.out.println(avg);
		
		
		
		
	
	}

}
