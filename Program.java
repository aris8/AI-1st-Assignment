import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Program {
	
	private Hour program[][];
	private int dimension_x = 7;
	private int dimension_y = 5;
	static ArrayList<Teacher> teachers ;	
	static ArrayList<Lesson> lessons ;
	private String cls;
	
	public Program(int x, String cls) {
		setTeachers();
		setLessons();
		this.cls = cls;
		randProgram(cls);
	}
	
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
	
	
	private String addSpaces(int amount) {
		String spaces = "";
		for(int i = 0; i < amount;i++){
			spaces += " ";
		}
		return spaces;
	}

	public int calculateMax(){
		int max = 0;
		for(Lesson lesson : lessons){
			if(max < lesson.getLes_name().length()){
				max = lesson.getLes_name().length();
			}
		}
		return max;
	}

	
	
	

}