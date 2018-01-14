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
		File file = new File("ten.txt");
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
    	
    	
    	ArrayList<Weather> cars = new ArrayList<Weather>();
    	Weather temp = new Weather();
    	try {
			while ( (line = br.readLine()) != null){
				 String[] atr = line.split("\\,");
				 
				 temp = new Weather(atr[0],atr[1],atr[2],atr[3],atr[4]);
				
				 cars.add(temp);
				 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ArrayList<Weather> e_set = new ArrayList<Weather>();
    	ArrayList<Weather> t_set = new ArrayList<Weather>();
    	int t_e = 0;
    	int count_e = 0;
    	int count_t = 0;
    	
    	for(Weather car: cars){
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
    	
    	file = new File("ten_atr.txt");
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
    	
    	
    	
    	for(Weather car: e_set) {
    		for(Attribute atr : attrs){
        		if(atr.getName().equals("outlook")) {
        			atr.addElement(car.getOutlook(), car.getW_class(), 1);
        		}
        		if(atr.getName().equals("temp")) {
        			atr.addElement(car.getTemp(), car.getW_class(), 1);
        		}
        		if(atr.getName().equals("humidity")) {
        			atr.addElement(car.getHumidity(), car.getW_class(), 1);
        		}
        		if(atr.getName().equals("wind")) {
        			atr.addElement(car.getWind(), car.getW_class(), 1);
        		}
        		
        	}
    	}
    	
    	for(Attribute atr : attrs){
    		System.out.println(atr.toString());    		
    		System.out.println(Arrays.deepToString(atr.getCls_map()));
    		System.out.println(atr.getMaxElements());
    		System.out.println("Entropy: " + atr.calcAtrEntropy());
    		System.out.println("Gain is " + atr.calcGain());
    	}
    	
    	
    	
    	
	}
	
	

}
