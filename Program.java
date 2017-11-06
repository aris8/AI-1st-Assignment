import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Program {
	
	// 2 dimension table to hold the program
	private Hour program[][];
	//The dimensions of the table
	private final int dimension_x = 7;
	private final int dimension_y = 5;
	//Static lists for teachers and lesson.
	// We use static because we need the lists to be shared by all objects of this class.
	static ArrayList<Teacher> teachers ;	
	static ArrayList<Lesson> lessons ;
	//A variable that hold the class that the program is for.
	private String cls;
	
	
	/*
	 * Constructor that initializes the lists.
	 * We use 2 different constructors to avoid doing initializing the lists more
	 * times that we need to. The constructor also initializes a random program.
	 */
	public Program(int x, String cls) {
		setTeachers();
		setLessons();
		this.cls = cls;
		randProgram(cls);
	}
	
	// Constructor that initializes a random program without initializing the lists.
	public Program(String cls) {		
		this.cls = cls;
		randProgram(cls);
	}
	
	public Hour[][] getProgram() {
		return program;
	}

	public void setProgram(Hour[][] program) {
		this.program = program;
	}

	public static ArrayList<Teacher> getTeachers() {
		return teachers;
	}
		
	// Retrieves the data from the teacher.json  
	public static void setTeachers() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			teachers = mapper.readValue(new File("teachers.json"), new TypeReference<ArrayList<Teacher>>(){});			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}	


	public ArrayList<Lesson> getLessons() {
		return lessons;
	}
	
	// Retrieves the data from the lessons.json
	public void setLessons() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			lessons = mapper.readValue(new File("lessons.json"), new TypeReference<ArrayList<Lesson>>(){});			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	// This method reduces the hours of a teacher in the static list teachers.
	public void reduceHours(Teacher teacher,int hours) {
		for(Teacher t : teachers) {
			if( t.equals(teacher)) {
				t.reduceHours(hours);
				if( t.getMax_hours() == 0) {
					teachers.remove(t);
				}
				break;
			}
		}
		
	}

	// The method creates a random program.
	private void randProgram(String cls) {
		
		program = new Hour[dimension_x][dimension_y];
		for (Lesson lesson : lessons) {
			
			if (lesson.get_class().equals(cls)) {
				Teacher teacher;
				ArrayList<Teacher> cnd = new ArrayList<Teacher>();
				for (Teacher temp : teachers) {
					if(temp.getLesson_id().contains(lesson.getLes_id()) && temp.getMax_hours() >= lesson.getWeekly_hours() ) {
						cnd.add(temp);
					}
				}
				Random rand = new Random();
				int n = rand.nextInt(cnd.size());
				teacher = cnd.get(n);
				Hour h = new Hour(lesson, teacher);
				for (int i=0; i< lesson.getWeekly_hours(); i++) {
					boolean flag = true;
					int x_pos = 0;
					int y_pos = 0;
					while(flag) {
						x_pos = rand.nextInt(dimension_x);
						y_pos = rand.nextInt(dimension_y);
						if (program[x_pos][y_pos] == null) flag = false;
					}
					program[x_pos][y_pos] = h;
					
				}
				reduceHours(teacher, lesson.getWeekly_hours());
			}
			
		}
		replaceNulls();
	}
	
	// This method replaces all nulls in the program with a specific String
	// to avoid NullPointerExceptions when we handle the @program
	private void replaceNulls() {
		Lesson empty = new Lesson();
		empty.setLes_name("----");
		Teacher none = new Teacher();
		none.setName("##");
		Hour h = new Hour(empty,none);
		for(int i = 0; i < dimension_x; i++){
			for(int j = 0; j < dimension_y; j++){
				if(program[i][j] == null)
					program[i][j] =  h;							
			}			
		}
	}
	
	// Prints the program in a pretty format easy to interpet.
	public void printArray(String filename) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		int max = this.calculateMax();
		String spaces = "";
		for(int i = 0; i < dimension_x; i++){
			for(int j = 0; j < dimension_y; j++){
				spaces = addSpaces(max - program[i][j].getLesson().getLes_name().length());
				writer.printf("%d: %s -> %s%s ",i + 1,program[i][j].getLesson(), program[i][j].getTeacher(),spaces );				
			}
			writer.print("\n");
		}
	    writer.close();
	}
	
	public void print(){
		int max = this.calculateMax();
		String spaces = "";
		for(int i = 0; i < dimension_x; i++){
			for(int j = 0; j < dimension_y; j++){
				spaces = addSpaces(max - program[i][j].getLesson().getLes_name().length());
				System.out.printf("%d: %s -> %s%s ",i + 1,program[i][j].getLesson(), program[i][j].getTeacher(),spaces );				
			}
			System.out.print("\n");
		}
	}
	
	// Returns a string with specific spaces.
	private String addSpaces(int amount) {
		String spaces = "";
		for(int i = 0; i < amount;i++){
			spaces += " ";
		}
		return spaces;
	}
	
	// Calculates the biggest String in the lessons list.
	public int calculateMax(){
		int max = 0;
		for(Lesson lesson : lessons){
			if(max < lesson.getLes_name().length()){
				max = lesson.getLes_name().length();
			}
		}
		return max;
	}
	
	public Teacher getTeacherAtPos(int i,int j){
		return program[i][j].getTeacher();
	}

	
	public void swap(int row_x, int row_y, int i, int j) {
		Hour temp;
		temp = program[row_x][row_y];
		program[row_x][row_y] = program[i][j];
		program[i][j] = temp;		
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean flag = true;
		
		
			for(int i = 0; i < 7;i++){
				for(int j = 0; j < 5;j++){
					if(program[i][j].getTeacher() != ((Program) obj).getTeacherAtPos(i,j)  || program[i][j].getLesson() != ((Program) obj).getLessonAtPos(i,j) ){
						flag = false;
						break;
					}
				}
				if(flag == false){
					break;
				}
			}
		return flag;
	}

	private Lesson getLessonAtPos(int i, int j) {
		
		return program[i][j].getLesson();
	}

	
	
	

}