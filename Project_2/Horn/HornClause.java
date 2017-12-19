import java.util.Vector;

public class HornClause {



/*
 * A CNFClause consists of a conjunction of HornSubClauses
 * And each HornSubClause in turn consists of a disjunction of Literals
 */

    public Vector<HornSubClause> theClauses = new Vector<HornSubClause>();
    
    public Vector<HornSubClause> getSubclauses()
    {
        return theClauses;
    }
    
    public boolean contains(HornSubClause newS)
    {
        for(int i = 0; i < theClauses.size(); i ++)
        {
            if(theClauses.get(i).getLiterals().equals(newS.getLiterals()))
            {
                return true;
            }
        }
        return false;
    }

}
