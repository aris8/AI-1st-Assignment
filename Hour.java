/*
 * A class that holds the teacher and the 
 * lesson for 1 hour of the program.
 */
public class Hour {
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
	
}
