
import java.util.HashSet;
import java.util.Iterator;


/*
 * A HornSubClause in turn consists of a disjunction of literals
 */
public class HornSubClause implements Comparable<HornSubClause>
{
    //The literals contained in the clause
    private HashSet<Literal> literals;
    private Literal head;
    private int count;
    
    public Literal getHead(){
    	return head;
    }
    
    public void setHead(Literal h){
    	head = h;
    }
    
    public int getCount(){
    	return count;
    }
    
    public void decCount(){
    	count--;
    }
            
    public HornSubClause()
    {
        literals = new HashSet<Literal>();  
        count = 0;
    }
         
    public  HashSet<Literal> getLiterals()            
    {
        return literals;
    }
    
    public void addLiteral(Literal a){
    	literals.add(a);
    	count++;
    }
    
    public Iterator<Literal> getLiteralsList()
    {
        return literals.iterator();
    }
         
    public boolean isEmpty()
    {
        return literals.isEmpty();
    }
    
    public void print()
    {
        System.out.println("**************************");
        Iterator<Literal> iter = this.getLiteralsList();
        
        while(iter.hasNext())
        {
            Literal l = iter.next();
            
            l.print();
        }
        System.out.println("Head: ");
        head.print();
        System.out.println("Count: " + count);
        System.out.println("**************************\n");
    }

    
    
    
    //Override
    public boolean equals(Object obj)
    {
        HornSubClause l = (HornSubClause)obj;

        Iterator<Literal> iter = l.getLiteralsList();
        
        while(iter.hasNext())
        {
            Literal lit = iter.next();
            if(!this.getLiterals().contains(lit))
                return false;
        }
        
        if(l.getLiterals().size() != this.getLiterals().size())
            return false;
        
        return true;
    }
	
    //@Override
    public int hashCode()
    {
        Iterator<Literal> iter = this.getLiteralsList();
        int code = 0;
        
        while(iter.hasNext())
        {
            Literal lit = iter.next();
               code = code + lit.hashCode();
        }
        
        return code;
    }
	
    //@Override
    public int compareTo(HornSubClause x)
    {
        int cmp = 0;
        
        Iterator<Literal> iter = x.getLiteralsList();
        
        while(iter.hasNext())
        {
            Literal lit = iter.next();
            
            Iterator<Literal> iter2 = this.getLiterals().iterator();
                    
            while(iter2.hasNext())
            {                
                Literal lit2 = iter2.next();
                cmp = cmp + lit.compareTo(lit2);
            }
        }
        
        return cmp;
    }

	public boolean contains(Literal p) {
		if(literals.contains(p)){
			return true;
		}
		
		return false;
	}
}
