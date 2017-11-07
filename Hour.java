/*
 * A class that holds the teacher and the 
 * lesson for 1 hour of the program.
 */
public class Hour implements Cloneable {
	private Lesson lesson;
	private Teacher teacher;
	
	public Hour(Lesson lesson,Teacher teacher){
		this.lesson =  lesson;
		this.teacher = teacher;
	}

	

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String toString() {
		return "Hour [lesson=" + lesson + ", teacher=" + teacher + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.lesson.equals((((Hour) obj).getLesson())) && (this.teacher.equals((((Hour) obj).getTeacher()))) ){
			return true;
		}		
		return false;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Hour hour = (Hour) super.clone();
		Teacher cloned_t = (Teacher) this.teacher.clone();
		Lesson cloned_l = (Lesson) this.lesson.clone(); 
		hour.setLesson(cloned_l);
		hour.setTeacher(cloned_t);
	    return hour;
	}
}
