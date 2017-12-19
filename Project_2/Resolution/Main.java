import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class Main 
{ 
    public static void main(String[] args) throws IOException 
    {
        
        example2();
    }


   

    //An example of applying resolution to see whether we can infer a literal from our knowledge base
    public static void example2() throws IOException
    {
        //The clause that are in our knowledge base
    	CNFClause KB = new CNFClause();
    	
    	File file = new File("KB.txt");
    	FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	
    	String line;
    	
    	while ((line = br.readLine()) != null) {
    	 
	        CNFSubClause C = new CNFSubClause();
	        
	        for (String literal : line.split("\\^")) {
	        	if (literal.contains("-")) {
	        		C.getLiterals().add(new Literal(literal.substring(1), true));
	        	} else {
	        		C.getLiterals().add(new Literal(literal, false));
	        	}
	        }
	       
	
	        System.out.println("This is a sub clause...");
	        C.print();   

	        KB.getSubclauses().add(C);
    	}
    	br.close();
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter literal to prove or 0 to quit: ");
		String input = sc.nextLine();
    	while (!input.equals("0")) {
			Literal a;
			if (input.contains("-")) {
				a = new Literal(input.substring(1), true);
			} else {
				a = new Literal(input, false);
			}
			System.out.println("----------------------------------------------------");
			System.out.println();
			//Running resolution
			boolean b = PL_Resolution(KB, a);
			a.print();
			System.out.println("is " + b);
			System.out.println("Enter literal to prove or 0 to quit: ");
			input = sc.nextLine();
		}
		sc.close();
    }

    //The resolution algorithm
    public static boolean PL_Resolution(CNFClause KB, Literal a)
    {
        //We create a CNFClause that contains all the clauses of our Knowledge Base
        CNFClause clauses = new CNFClause();
        clauses.getSubclauses().addAll(KB.getSubclauses());
        
        //...plus a clause containing the negation of the literal we want to prove
        Literal aCopy = new Literal(a.getName(), !a.getNeg());
        CNFSubClause aClause = new CNFSubClause();
        aClause.getLiterals().add(aCopy);
        clauses.getSubclauses().add(aClause);

        System.out.println("We want to prove...");
        a.print();

        boolean stop = false;
        int step = 1;
        //We will try resolution till either we reach a contradiction or cannot produce any new clauses
        while(!stop)
        {
            Vector<CNFSubClause> newsubclauses = new Vector<CNFSubClause>();
            Vector<CNFSubClause> subclauses = clauses.getSubclauses();

            System.out.println("Step:" + step);
            step++;
            //For every pair of clauses Ci, Cj...
            for(int i = 0; i < subclauses.size(); i++)
            {
                CNFSubClause Ci = subclauses.get(i);

                for(int j = i+1; j < subclauses.size(); j++)
                {
                    CNFSubClause Cj = subclauses.get(j);

                    //...we try to apply resolution and we collect any new clauses
                    Vector<CNFSubClause> new_subclauses_for_ci_cj = CNFSubClause.resolution(Ci, Cj);

                    //We check the new subclauses...
                    for(int k = 0; k < new_subclauses_for_ci_cj.size(); k++)
                    {
                        CNFSubClause newsubclause = new_subclauses_for_ci_cj.get(k);

                        //...and if an empty subclause has been generated we have reached contradiction; and the literal has been proved
                        if(newsubclause.isEmpty()) 
                        {   
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                            Ci.print();
                            System.out.println("and");
                            Cj.print();
                            System.out.println("produced:");
                            System.out.println("Empty subclause!!!");
                            System.out.println("----------------------------------");
                            return true;
                        }
                        
                        //All clauses produced that don't exist already are added
                        if(!newsubclauses.contains(newsubclause) && !clauses.contains(newsubclause))
                        {
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                            Ci.print();
                            System.out.println("and");
                            Cj.print();
                            System.out.println("produced:");
                            newsubclause.print();
                            newsubclauses.add(newsubclause);
                            System.out.println("----------------------------------");
                        }
                    }                           
                }
            }
            
            boolean newClauseFound = false;

            //Check if any new clauses were produced in this loop
            for(int i = 0; i < newsubclauses.size(); i++)
            {
                if(!clauses.contains(newsubclauses.get(i)))
                {
                    clauses.getSubclauses().addAll(newsubclauses);                    
                    newClauseFound = true;
                }                        
            }

            //If not then Knowledge Base does not logically infer the literal we wanted to prove
            if(!newClauseFound)
            {
                System.out.println("Not found new clauses");
                stop = true;
            }   
        }
        return false;
    }
    
    

}
