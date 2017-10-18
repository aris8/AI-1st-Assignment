import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Teacher> teacher = mapper.readValue(new File("teachers.json"), new TypeReference<ArrayList<Teacher>>(){});
		System.out.println(teacher);
		
		ArrayList<Lesson> lessons = mapper.readValue(new File("lessons.json"), new TypeReference<ArrayList<Lesson>>(){});
		System.out.println(lessons);
		
	}
}
