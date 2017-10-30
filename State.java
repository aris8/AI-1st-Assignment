
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class State {
	/*
	 * We represent the program with 3 different 
	 * 2 dimensions array 1 for each class.
	 */
	private Hour program_A [][];
	private Hour program_B [][];
	private Hour program_C [][];
	/* Variables for the dimensions of the table
	* we use to represent the weekly program.
	*/
	private int dimension_x = 7;
	private int dimension_y = 15;
	/*	We use 2 variables to hold the 
	 * 	co-ordinate of the tile we swap 
	 * 	when we produce child states.
	 */
	private int row_x;
	private int row_y;
	
	/*
	 * Default constructor.It creates a random
	 * program with dimensions 7x15.
	 */
	public State(){
		dimension_x = 7;
		dimension_y = 15;
		create_random(dimension_x,dimension_y);
	}
	/*
	 * Creates a random program with the dimensions 
	 * given.
	 */
	private void create_random(int rows,int columns)  {
		
		
		//Initialize arrays.
		program_A =  new Hour[rows][columns];
		program_B =  new Hour[rows][columns];
		program_C =  new Hour[rows][columns];
		
		//Retrieve the data for lessons and teachers.
		ObjectMapper mapper = new ObjectMapper();
		try {
			ArrayList<Teacher> teacher = mapper.readValue(new File("teachers.json"), new TypeReference<ArrayList<Teacher>>(){});		
			ArrayList<Lesson> lessons = mapper.readValue(new File("lessons.json"), new TypeReference<ArrayList<Lesson>>(){});
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
	
}
