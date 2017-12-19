import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class main {
	public static void main(String[] args) throws IOException 
    {
		
		
		
        
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter literal to prove or 0 to quit: ");
		String input = sc.nextLine();
		
    	while (!input.equals("0")) {
			Literal a;
			a = new Literal(input, false);
			System.out.println("----------------------------------------------------");
			System.out.println();
			HornClause KB = new HornClause();
	    	
	    	File file = new File("KB.txt");
	    	FileReader fr = new FileReader(file);
	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	String line;
	    	
	    	while ((line = br.readLine()) != null) {
	       	 
		        HornSubClause C = new HornSubClause();
		        
		        String[] horn = line.split("\\>");
		        if(horn.length > 1){
		        	C.setHead(new Literal(horn [ horn.length -1], false));
		        	for (String literal : horn[0].split("\\^")) {	        	
			        	C.addLiteral(new Literal(literal, false));	        	
			        }
		        }else{
		        	C.setHead(new Literal(horn[0], false));
		        }
		      
		        
		        
		       
		
		        System.out.println("This is a sub clause...");
		        C.print(); 
		        

		        KB.getSubclauses().add(C);
	    	}
	    	br.close();
			//Running resolution
			boolean b = PL_FC_Entails(KB, a);
			a.print();
			System.out.println("is " + b);
			System.out.println("Enter literal to prove or 0 to quit: ");
			input = sc.nextLine();
		}
		sc.close();
    }

	private static boolean PL_FC_Entails(HornClause KB, Literal a) {
		Stack<Literal> agenda = new Stack<Literal>();
		for(int i=0; i < KB.getSubclauses().size();i++){
			if( KB.getSubclauses().get(i).isEmpty()){
				agenda.push(KB.getSubclauses().get(i).getHead());
			}			
		}
		
		
		Hashtable<Literal,Boolean> inferred = new Hashtable<Literal,Boolean>();
		for(int i=0; i < KB.getSubclauses().size();i++){
			for(int j =0; j < KB.getSubclauses().get(i).getLiterals().size();j++ ){
				Iterator<Literal> iter = KB.getSubclauses().get(i).getLiteralsList();
				while(iter.hasNext())
		        {
		           Literal l = iter.next();		            
		           inferred.put(l,false);
		        }
			}
		}	
		
		while(agenda.size() != 0 ){
			Literal p = agenda.pop();
			
			if( !inferred.contains(p) ){
				inferred.put(p,true);
				for(int i=0; i < KB.getSubclauses().size();i++){
					if(KB.getSubclauses().get(i).contains(p)){
						
						KB.getSubclauses().get(i).decCount();
						
						if(KB.getSubclauses().get(i).getCount() == 0){
							
							if(KB.getSubclauses().get(i).getHead().equals(a)){
								return true;
							}else{
								agenda.push(KB.getSubclauses().get(i).getHead());
							}
						}
					}
				}
				
			}
			
		}
		
		return false;
	}
}


