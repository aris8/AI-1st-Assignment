import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
	/* 
	 * @lastName It contains the lastname of the teacher.
	 * @firstName It contains the firstname of the teacher.
	 *  @teacher_id The unique id of the teacher.
	 *  @lesson_id All the id's of the lessons the teacher can teach.
	 *  @max_hours The maximum hours a teacher can teach per week.	 * 
	 */
	private String lastName;
	private String firstName;
	private int teacher_id;
	private ArrayList<Integer> lesson_id;
	private int max_hours;
	
	
	
	public Teacher(String lastName, String firstName, int teacher_id, ArrayList<Integer> lesson_id, int max_hours) {		
		setLastName(lastName);
		setFirstName(firstName);
		setTeacher_id(teacher_id);
		setLesson_id(lesson_id);
		setMax_hours(max_hours);
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	 
	// We assume each id of each teacher is unique	
	public int getTeacher_id() {
		return teacher_id;
	}
	
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	
	
	public ArrayList<Integer> getLesson_id() {
		return lesson_id;
	}
	
	/* 
	 * We assume that the lessons id's that are added to this list
	 * are correct and it does not contain any duplicates. 
	 */
	public void setLesson_id(ArrayList<Integer> lesson_id) {
		this.lesson_id = lesson_id;
	}
	
	public int getMax_hours() {
		return max_hours;
	}
	
	public void setMax_hours(int max_hours) {
		/*We perform parameters check to ensure
		 * that our program does not go out of bounds.
		 * max_hours = {0*}
		 */		
		Scanner sc = new Scanner(System.in);
		while(true){				
			if(max_hours < 0){
				System.out.printf("The number of weekly teaching hours of "
						+ "%s %s are not correct.Please enter an appropriate number of hours: ",getLastName(),getFirstName());
				max_hours = sc.nextInt();
				continue;
			}				
			break;				
		}			
		this.max_hours = max_hours;
		sc.close();
	}
	

}
