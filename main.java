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
		
		/*ArrayList<Integer> ids = new ArrayList<Integer>();
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
		System.out.println( e == f );
		f.setLesson(d);
		System.out.println(e.equals(f));
		System.out.println(f.getLesson());
		System.out.println(e.getLesson());
		System.out.println("-----------------");
		
		
		Hour[][] a1 = new Hour[7][5];
		
		for(int i = 0;i < 7;i++) {
			for(int j = 0;j<5;j++) {
				a1[i][j] = new Hour((Lesson) c.clone(),(Teacher) a.clone());
			}
		}
		Hour[][] a2 =  new Hour[7][5];
		a1[0][0] = (Hour) f.clone();
		for(int o = 0;o < 7;o++) {
			for(int m = 0;m<5;m++) {
				a2[o][m] = (Hour) a1[o][m].clone();
			}
		}
		
		
		
		Hour temp = a2[0][0] ;
		a2[0][0] = a2[0][1];
		a2[0][1] = temp;
		boolean flag = true;
		for(int k = 0;k < 7;k++) {
			for(int p = 0;p<5;p++) {
				if( !(a1[k][p].equals(a2[k][p]))) {
					flag = false;
				}
			}
		}
		System.out.println(flag);
		*/
		
		
		
		State initial;
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;
		initial = new State();
		System.out.println(initial);
		System.out.printf("Score of intial state: %d \n", initial.getScore());
		
		
		
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
			System.out.printf("Score of terminal state: %d \n", terminalState.getScore());
		}
		System.out.println("BestFS (1st heuristic) with closed set search time: " + (double)(end - start) / 1000 /60 + "min.");
		System.out.println("***************");
		
	
	}

}
