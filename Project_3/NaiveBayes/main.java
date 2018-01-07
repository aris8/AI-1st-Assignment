import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		File file = new File("car.txt");
    	FileReader fr = null;
    	Random prob = new Random(); 
    	prob.setSeed(System.currentTimeMillis());
    	
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
    	BufferedReader br = new BufferedReader(fr);
    	String line = null;
    	
    	dataset data = new dataset();
    	ArrayList<Car> cars = new ArrayList<Car>();
    	Car temp = new Car();
    	try {
			while ( (line = br.readLine()) != null){
				 String[] atr = line.split("\\,");
				 
				 temp = new Car(atr[0],atr[1],atr[2],atr[3],atr[4],atr[5],atr[6]);
				
				 cars.add(temp);
				 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ArrayList<Car> e_set = new ArrayList<Car>();
    	ArrayList<Car> t_set = new ArrayList<Car>();
    	int t_e = 0;
    	int count_e = 0;
    	int count_t = 0;
    	
    	for(Car car: cars){
    		t_e = prob.nextInt(10);
    		if(t_e < 7){
    			e_set.add(car);
    			count_e++;
    		}else{
    			t_set.add(car);
    			count_t++;
    		}
    	}
    	System.out.printf("Training set : %d \n Test set: %d \n", count_e,count_t);
    	System.out.println("Training set_per: " + (count_e/1728f)*100);
    	System.out.println("Test set_per: " + (count_t/1728f)*100);
    	
    	for(Car car: e_set){
    		data.addTrainingSet(car);
    	}
    	data.print();
    	for(Car car: t_set){
    		data.addTest(car);
    	}
    	System.out.println("Test set contains: " + data.getTotal_samples());
    	System.out.println("Correct guesses: " + data.getCorrect());
    	System.out.println("Accuracy: " + (data.getCorrect() / (float) data.getTotal_samples()) * 100);
	}

}