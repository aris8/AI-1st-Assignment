import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub

		Program a1 = new Program(0, "A");
		Program a2 = new Program("A");
		Program a3 = new Program("A");
		Program b1 = new Program("B");
		Program b2 = new Program("B");
		Program b3 = new Program("B");
		Program c1 = new Program("C");
		Program c2 = new Program("C");
		Program c3 = new Program("C");
		
		a1.printArray("A1.txt");
		a2.printArray("A2.txt");
		a3.printArray("A3.txt");
		b1.printArray("B1.txt");
		b2.printArray("B2.txt");
		b3.printArray("B3.txt");
		c1.printArray("C1.txt");
		c2.printArray("C2.txt");
		c3.printArray("C3.txt");
	
	}

}
