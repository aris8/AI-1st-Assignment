import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    		if(t_e < 10){
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
    	
    	try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	file = new File("car_atr.txt");
    	fr = null;
    	
    	try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
    	br = new BufferedReader(fr);
    	line = null;
    	
    	
    	ArrayList<Attribute> attrs = new ArrayList<Attribute>();
    	ArrayList<String> name_atr = new ArrayList<String>();
    	try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String[] classes = line.split("\\,");
    	Attribute test = null;
    	try {
			while((line = br.readLine()) != null ){
		    	String[] atr = line.split("\\,");
		    	name_atr.add(atr[0]);
		    	test = new Attribute(atr.length -1,classes.length,atr[0]);
		    	test.setCls_names(classes);
		    	test.setAtr_names(Arrays.copyOfRange(atr, 1, atr.length));
		    	attrs.add(test);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
    	for(Car car: e_set) {
    		for(Attribute atr : attrs){
        		if(atr.getName().equals("buying")) {
        			atr.addElement(car.getPrice(), car.getCar_class(), 1);
        		}
        		if(atr.getName().equals("maint")) {
        			atr.addElement(car.getMaint(), car.getCar_class(), 1);
        		}
        		if(atr.getName().equals("doors")) {
        			atr.addElement(car.getDoors(), car.getCar_class(), 1);
        		}
        		if(atr.getName().equals("persons")) {
        			atr.addElement(car.getPersons(), car.getCar_class(), 1);
        		}
        		if(atr.getName().equals("lug_boot")) {
        			atr.addElement(car.getLug_boot(), car.getCar_class(), 1);
        		}
        		if(atr.getName().equals("safety")) {
        			atr.addElement(car.getSafety(), car.getCar_class(), 1);
        		}
        	}
    	}
    	
    	for(Attribute atr : attrs){
    		
    		System.out.println(atr.toString());
    		System.out.println(Arrays.deepToString(atr.getCls_map()));
    		System.out.println(atr.getMaxElements());
    	}
    	
    	
	}
	
	

}
