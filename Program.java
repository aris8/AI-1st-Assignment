import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Program {
	
	private Hour program[][];
	static ArrayList<Teacher> teachers ;	
	private ArrayList<Lesson> lessons ;
	
	public Program(int x) {
		setTeachers();
		setLessons();
	}
	
	public Program() {		
		setLessons();
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
	
	public void reduceHours(Teacher teacher,int hours) {
		for(Teacher t : teachers) {
			if( t.equals(teacher)) {
				t.reduceHours(hours);
				removeTeachers();
				break;
			}
		}
		
	}

	private void removeTeachers() {
		for(Teacher t : teachers) {
			if( t.getHours_left() == 0) {
				teachers.remove(t);
				break;
			}
		}
		
	}

}
