import java.util.ArrayList;
import java.util.Scanner;

public class Teacher implements Cloneable,Comparable<Teacher> {
    

	/*
     * @lastName It contains the lastname of the teacher.
     * @firstName It contains the firstname of the teacher.
     *  @teacher_id The unique id of the teacher.
     *  @lesson_id All the id's of the lessons the teacher can teach.
     *  @max_hours The maximum hours a teacher can teach per week.	 *
     */
    private String name;
    private int teacher_id;
    private ArrayList<Integer> lesson_id;
    private int max_hours;
    private int hours_left;


    public Teacher(){} //Default constructor. Needed to use the jackson library.
    
    public Teacher(String name, int teacher_id, ArrayList<Integer> lesson_id, int max_hours) {
        setName(name);
        setTeacher_id(teacher_id);
        setLesson_id(lesson_id);
        setMax_hours(max_hours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.max_hours = max_hours;

    }
    
    public void reduceHours(int hours){
    	if(this.max_hours == 0){
    		System.out.printf("The teaches %s can't teach any more hours.",this.name);
    		return;
    	}
    	this.max_hours -= hours;
    	return;    	
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(this.name.equals(((Teacher) obj).getName() ) ){
    		return true;
    	}
    	return false;
    }
    
	@Override
	public String toString() {
		return getName();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
	
	@Override
	public int compareTo(Teacher s)
	{
		return Double.compare(this.max_hours, s.max_hours);
	}
}
