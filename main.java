import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, CloneNotSupportedException {
		
	/*	State initial;
		State terminalState = null;
		initial = new State(true);
		initial.print();
		
		State ending = initial.swap(1,1,1);
		ending.print();
		System.out.println(ending.equals(initial));
		*/
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(10);
		ids.add(12);
		ids.add(13);
		Teacher a = new Teacher("A",101,ids,10);
		Teacher b = (Teacher) a.clone();
		System.out.println(a.equals(b));
		b.setName("B");
		System.out.println(a.equals(b));
		System.out.println(a.getName());
		System.out.println(b.getName());
		System.out.println("-----------------");
		
		Lesson c = new Lesson(10,"AAA","A",10);
		Lesson d = (Lesson) c.clone();
		System.out.println(c.equals(d));
		d.setLes_name("BBB");
		System.out.println(c.equals(d));
		System.out.println(c.getLes_name());
		System.out.println(d.getLes_name());
		System.out.println("-----------------");
		
		Hour e = new Hour(c,a);
		Hour f = (Hour) e.clone();
		System.out.println(e.equals(f));
		f.setLesson(d);
		System.out.println(e.equals(f));
		System.out.println(f.getLesson());
		System.out.println(e.getLesson());
		System.out.println("-----------------");
		
		Program p = new Program(0,"A");
		Program k = (Program) p.clone();
		System.out.println(p.equals(k));
		k.setLesson(c, 0, 0);
		System.out.println(k.equals(p));
		p.print();
		k.print();
		
		
		
		
		
		/*State initial;
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;
		initial = new State(true);
		System.out.println(initial);
		
		
		
		
		long start = System.currentTimeMillis();
		terminalState = spaceSearcher.BestFSClosedSet(initial);		
		long end = System.currentTimeMillis();
		if(terminalState == null)
		{
			System.out.println("Could not find solution");
		}
		else
		{
			terminalState.print();
		}
		System.out.println("BestFS (1st heuristic) with closed set search time: " + (double)(end - start) / 1000 /60 + "min.");
		System.out.println("***************");*/
		
	
	}

}
