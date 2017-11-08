

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;




public class State implements Comparable<State> {
	/*
	 * We represent the program with 9 different 
	 * 2 dimensional arrays 1 for each class.
	 */
	private Hour a1[][];
	private Hour a2[][];
	private Hour a3[][];
	private Hour b1[][];
	private Hour b2[][];
	private Hour b3[][];
	private Hour c1[][];
	private Hour c2[][];
	private Hour c3[][];
	
	private final int dimension_x = 7;
	private final int dimension_y = 5;
	
	static ArrayList<Teacher> teachers ;	
	static ArrayList<Lesson> lessons ;
	
	// We use a variable to keep the score of the state.
	private int score;
	
	/*	We use 2 variables to hold the 
	 * 	co-ordinate of the tile we swap 
	 * 	when we produce child states.
	 */
	private int row_x;
	private int row_y;
	
	/*
	 * Default constructor.It creates a random
	 * program with dimensions 7x5.
	 */
	public State() throws FileNotFoundException, UnsupportedEncodingException{	
		this.setTeachers();
		this.setLessons();
		create_random();
		this.row_x = 0;
		this.row_y = 0;
		
	}
	
	public State(Hour[][] a1,Hour[][] a2,Hour[][] a3,Hour[][] b1,Hour[][] b2,Hour[][] b3,Hour[][] c1,Hour[][] c2,Hour[][] c3) throws CloneNotSupportedException {
		this.a1 = new Hour[dimension_x][dimension_y];
		this.a2 = new Hour[dimension_x][dimension_y];
		this.a3 = new Hour[dimension_x][dimension_y];
		this.b1 = new Hour[dimension_x][dimension_y];
		this.b2 = new Hour[dimension_x][dimension_y];
		this.b3 = new Hour[dimension_x][dimension_y];
		this.c1 = new Hour[dimension_x][dimension_y];
		this.c2 = new Hour[dimension_x][dimension_y];
		this.c3 = new Hour[dimension_x][dimension_y];
		for(int i = 0;i < 7;i++) {
			for(int j = 0;j<5;j++) {
				this.a1[i][j] = (Hour) a1[i][j].clone();
				this.a2[i][j] = (Hour) a2[i][j].clone();
				this.a3[i][j] = (Hour) a3[i][j].clone();
				this.b1[i][j] = (Hour) b1[i][j].clone();
				this.b2[i][j] = (Hour) b2[i][j].clone();
				this.b3[i][j] = (Hour) b3[i][j].clone();
				this.c1[i][j] = (Hour) c1[i][j].clone();
				this.c2[i][j] = (Hour) c2[i][j].clone();
				this.c3[i][j] = (Hour) c3[i][j].clone();
			}
		}
		this.score = 0;
		this.row_x = 0;
		this.row_y = 0;		
	}
	
	
 	public void setTeachers() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			teachers = mapper.readValue(new File("teachers.json"), new TypeReference<ArrayList<Teacher>>(){});			
		} catch (IOException e) {			
			e.printStackTrace();
		}
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
				if( t.getMax_hours() == 0) {
					teachers.remove(t);
				}
				break;
			}
		}
		
	}

	private Hour[][] randProgram(String cls,Hour[][] h) {
		
		h = new Hour[dimension_x][dimension_y];
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
				Collections.sort(cnd);
				teacher = cnd.get(0);
				Hour h1 = new Hour(lesson, teacher);
				for (int i=0; i< lesson.getWeekly_hours(); i++) {
					Hour x = null;
					try {
						x = (Hour) h1.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					boolean flag = true;
					int x_pos = 0;
					int y_pos = 0;
					while(flag) {
						x_pos = rand.nextInt(dimension_x);
						y_pos = rand.nextInt(dimension_y);
						if (h[x_pos][y_pos] == null) flag = false;
					}
					h[x_pos][y_pos] = x;
					
				}
				reduceHours(teacher, lesson.getWeekly_hours());
			}
			
		}
		replaceNulls(h);
		return h;
	}
	
	private void replaceNulls(Hour[][] h) {
		Lesson empty = new Lesson();
		empty.setLes_name("----");
		Teacher none = new Teacher();
		none.setName("##");
		Hour h1 = new Hour(empty,none);
		for(int i = 0; i < dimension_x; i++){
			for(int j = 0; j < dimension_y; j++){
				if(h[i][j] == null)
					h[i][j] =  h1;							
			}			
		}
	}
	
	public void printArray(String filename,Hour[][] h) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		int max = this.calculateMax();
		String spaces = "";
		for(int i = 0; i < dimension_x; i++){
			for(int j = 0; j < dimension_y; j++){
				spaces = addSpaces(max - h[i][j].getLesson().getLes_name().length());
				writer.printf("%d: %s -> %s%s ",i + 1,h[i][j].getLesson(), h[i][j].getTeacher(),spaces );				
			}
			writer.print("\n");
		}
	    writer.close();
	}
	
	public void print(Hour[][] h){
		int max = this.calculateMax();
		String spaces = "";
		for(int i = 0; i < dimension_x; i++){
			for(int j = 0; j < dimension_y; j++){				
				spaces = addSpaces(max - h[i][j].getLesson().getLes_name().length());
				System.out.printf("%d: %s -> %s%s ",i + 1,h[i][j].getLesson(), h[i][j].getTeacher(),spaces );				
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
	
	
	//Creates a random program
	private void create_random() throws FileNotFoundException, UnsupportedEncodingException  {
		//Initialization of the program.
		a1 = this.randProgram("A", a1);		
		a2 = this.randProgram("A", a2);
		a3 = this.randProgram("A", a3);
		b1 = this.randProgram("B", b1);
		b2 = this.randProgram("B", b2);
		b3 = this.randProgram("B", b3);
		c1 = this.randProgram("C", c1);
		c2 = this.randProgram("C", c2);
		c3 = this.randProgram("C", c3);
		//Save the initial state on texts.
		
		
		this.euristic();		
	}
	
	// The heuristic that calculates the score of the state.
	private void euristic() {
		this.score = 0;
		this.score += this.teleportingTeachers();
//		this.score += this.spacesInProgam();
//		this.score += this.continiousHours();
//		this.score += this.equality();
//		this.score += this.evenlyDistributed();
	}
	
	
	private int continiousHours() {
		// A 2 dimensional array the represents the hours the teachers teach.
		int count = 0;
		int points = 400;
		ArrayList<Teacher> teachers = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			teachers = mapper.readValue(new File("teachers.json"), new TypeReference<ArrayList<Teacher>>(){});			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
		for(Teacher t : teachers){
			Teacher[][] teach = new Teacher[7][5];
			for(int i = 0;i < 7;i++){
				for(int j = 0;j < 5;j++){
					teach[i][j] = new Teacher();
					teach[i][j].setName("##");
				}
			}
			for(int i = 0;i < 7;i++){
				for(int j = 0;j < 5;j++){
					
					if(a1[i][j].getTeacher().equals(t)){
						teach[i][j] = a1[i][j].getTeacher();
					}
					if(a2[i][j].getTeacher().equals(t)){
						teach[i][j] = a2[i][j].getTeacher();
					}
					if(a3[i][j].getTeacher().equals(t)){
						teach[i][j] = a3[i][j].getTeacher();
					}
					if(b1[i][j].getTeacher().equals(t)){
						teach[i][j] = b1[i][j].getTeacher();
					}
					if(b2[i][j].getTeacher().equals(t)){
						teach[i][j] = b2[i][j].getTeacher();
					}
					if(b3[i][j].getTeacher().equals(t)){
						teach[i][j] = b3[i][j].getTeacher();
					}
					if(c1[i][j].getTeacher().equals(t)){
						teach[i][j] = c1[i][j].getTeacher();
					}
					if(c2[i][j].getTeacher().equals(t)){
						teach[i][j] = c2[i][j].getTeacher();
					}
					if(c3[i][j].getTeacher().equals(t)){
						
						teach[i][j] = c3[i][j].getTeacher();
					}
					
				}			
			}
			for(int j = 0;j < 5;j++){
				for(int i = 0;i < 5;i++){
					if(teach[i][j].toString().equals(teach[i + 1][j].toString()) && teach[i][j].toString().equals(teach[i + 2][j].toString()) && !(teach[i][j].toString().equals("##")) ){
						count++;
					}
				}
			}
		}
		return count*points;
	}

	private int spacesInProgam() {
		int count = 0;
		int points = 400; // The points given as penalty for each time the restrictions is violated.
		for(int j = 0; j < 5; j++){
			for(int i = 0; i < 6;i++){
				
				// A1
				if( i == 4 && a1[i][j].getTeacher().toString().equals("##") &&
						( !(a1[i +1][j].getTeacher().toString().equals("##")) 
								|| !(a1[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && a1[i][j].getTeacher().toString().equals("##") &&
						!(a1[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(a1[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// A2
				if( i == 4 && a2[i][j].getTeacher().toString().equals("##") &&
						( !(a2[i +1][j].getTeacher().toString().equals("##")) 
								|| !(a2[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && a2[i][j].getTeacher().toString().equals("##") &&
						!(a2[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(a2[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// A3
				if( i == 4 && a3[i][j].getTeacher().toString().equals("##") &&
						( !(a3[i +1][j].getTeacher().toString().equals("##")) 
								|| !(a3[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && a3[i][j].getTeacher().toString().equals("##") &&
						!(a3[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(a3[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// B1
				if( i == 4 && b1[i][j].getTeacher().toString().equals("##") &&
						( !(b1[i +1][j].getTeacher().toString().equals("##")) 
								|| !(b1[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && b1[i][j].getTeacher().toString().equals("##") &&
						!(b1[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(b1[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// B2
				if( i == 4 && b2[i][j].getTeacher().toString().equals("##") &&
						( !(b2[i +1][j].getTeacher().toString().equals("##")) 
								|| !(b2[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && b2[i][j].getTeacher().toString().equals("##") &&
						!(b2[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(b2[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// B3
				if( i == 4 && b3[i][j].getTeacher().toString().equals("##") &&
						( !(b3[i +1][j].getTeacher().toString().equals("##")) 
								|| !(b3[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && b3[i][j].getTeacher().toString().equals("##") &&
						!(b3[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(b3[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// C1
				if( i == 4 && c1[i][j].getTeacher().toString().equals("##") &&
						( !(c1[i +1][j].getTeacher().toString().equals("##")) 
								|| !(c1[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && c1[i][j].getTeacher().toString().equals("##") &&
						!(c1[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(c1[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// C2
				if( i == 4 && c2[i][j].getTeacher().toString().equals("##") &&
						( !(c2[i +1][j].getTeacher().toString().equals("##")) 
								|| !(c2[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && c2[i][j].getTeacher().toString().equals("##") &&
						!(c2[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(c2[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				// C3
				if( i == 4 && c3[i][j].getTeacher().toString().equals("##") &&
						( !(c3[i +1][j].getTeacher().toString().equals("##")) 
								|| !(c3[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && c3[i][j].getTeacher().toString().equals("##") &&
						!(c3[i +1][j].getTeacher().toString().equals("##")) ){
					count++;					
				}else if(c3[i][j].getTeacher().toString().equals("##")){
					count++;
				}
				
				
			}
		}
		
		return count * points;
	}
	
	
	// The method that calculates the points for the Teachers doing
	// 2 classes at the same hour.
	private int teleportingTeachers() {
		ArrayList<Teacher> duplicates;
		duplicates = new ArrayList<Teacher>(); 
		int count = 0;
		int points = 400; // The points given as penalty for each time the restrictions is violated.
		for(int i = 0; i < 7; i++){			 
			for(int j = 0; j < 5; j++){
				duplicates.add(a1[i][j].getTeacher());
				
				if(duplicates.contains(a2[i][j].getTeacher())){
					count++;
				}else duplicates.add(a2[i][j].getTeacher());
				
				if(duplicates.contains(a3[i][j].getTeacher())){
					count++;
				}else duplicates.add(a3[i][j].getTeacher());
				
				if(duplicates.contains(b1[i][j].getTeacher())){
					count++;
				}else duplicates.add(b1[i][j].getTeacher());
				
				if(duplicates.contains(b2[i][j].getTeacher())){
					count++;
				}else duplicates.add(b2[i][j].getTeacher());
				
				if(duplicates.contains(b3[i][j].getTeacher())){
					count++;
				}else duplicates.add(b3[i][j].getTeacher());
				
				if(duplicates.contains(c1[i][j].getTeacher())){
					count++;
				}else duplicates.add(c1[i][j].getTeacher());
				
				if(duplicates.contains(c2[i][j].getTeacher())){
					count++;
				}else duplicates.add(c2[i][j].getTeacher());
				
				if(duplicates.contains(c3[i][j].getTeacher())){
					count++;
				}else duplicates.add(c3[i][j].getTeacher());
				duplicates = new ArrayList<Teacher>();
			}
		}
		return count * points;
	}
	
	private int equality() {
		
		int count = 0;
		int points = 2;
		for (int p=1; p < 10; p++) {
			
			int count1, count2, count3, count4, count5, count6, count7, count8, count9;
			for(int j =0; j < 5;j++){
				count1 = 0;
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
				count7 = 0;
				count8 = 0;
				count9 = 0;
				for(int i = 0; i < 7;i++){
					if( p == 1){
						if (a1[i][j].getLesson().getLes_name().equals("----")) count1++;						
					}
					else if( p == 2){
						if (a2[i][j].getLesson().getLes_name().equals("----")) count2++;
					}
					else if( p == 3){
						if (a3[i][j].getLesson().getLes_name().equals("----")) count3++;
					}
					else if( p == 4){
						if (b1[i][j].getLesson().getLes_name().equals("----")) count4++;
					}
					else if( p == 5){
						if (b2[i][j].getLesson().getLes_name().equals("----")) count5++;
					}
					else if( p == 6){
						if (b3[i][j].getLesson().getLes_name().equals("----")) count6++;
					}
					else if( p == 7){
						if (c1[i][j].getLesson().getLes_name().equals("----")) count7++;
					}
					else if( p == 8){
						if (c2[i][j].getLesson().getLes_name().equals("----")) count8++;
					}
					else{
						if (c3[i][j].getLesson().getLes_name().equals("----")) count9++;
					}
					
				}
				if (count1 > 1) count++;
				if (count2 > 1) count++;
				if (count3 > 1) count++;
				if (count4 > 1) count++;
				if (count5 > 1) count++;
				if (count6 > 1) count++;
				if (count7 > 1) count++;
				if (count8 > 1) count++;
				if (count9 > 1) count++;
			}
		}
		return count * points;
		
		
	}
	
	private int evenlyDistributed() {
		int points = 5;
		int count = 0;
		for (int p=1; p < 10; p++) {
			for(int j =0; j < 5;j++){
				ArrayList<Lesson> duplicates;
				duplicates = new ArrayList<Lesson>();
				int value = 7;
				for(int i = 0; i < 7;i++){
					if( p == 1){
						if (a1[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(a1[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(a1[i][j].getLesson())){
									duplicates.add(a1[i][j].getLesson());
								}
							}
						}
						
					}
					else if( p == 2){
						if (a2[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(a2[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(a2[i][j].getLesson())){
									duplicates.add(a2[i][j].getLesson());
								}
							}
						}
					}
					else if( p == 3){
						if (a3[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(a3[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(a3[i][j].getLesson())){
									duplicates.add(a3[i][j].getLesson());
								}
							}
						}
					}
					else if( p == 4){
						if (b1[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(b1[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(b1[i][j].getLesson())){
									duplicates.add(b1[i][j].getLesson());
								}
							}
						}
					}
					else if( p == 5){
						if (b2[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(b2[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(b2[i][j].getLesson())){
									duplicates.add(b2[i][j].getLesson());
								}
							}
						}
					}
					else if( p == 6){
						if (b3[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(b3[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(b3[i][j].getLesson())){
									duplicates.add(b3[i][j].getLesson());
								}
							}
						}
					}
					else if( p == 7){
						if (c1[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(c1[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(c1[i][j].getLesson())){
									duplicates.add(c1[i][j].getLesson());
								}
							}
						}
					}
					else if( p == 8){
						if (c2[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(c2[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(c2[i][j].getLesson())){
									duplicates.add(c2[i][j].getLesson());
								}
							}
						}
					}
					else{
						if (c3[i][j].getLesson().getLes_name().equals("----")){
							value--;
						} else {
							if (i == 0) duplicates.add(c3[i][j].getLesson());
							if (i > 0) {
								if(!duplicates.contains(c3[i][j].getLesson())){
									duplicates.add(c3[i][j].getLesson());
								}
							}
						}
					}
					
				}
				count += value - duplicates.size();
				
			}
		}
		return count * points;
	}
	
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	
	public boolean isTerminal() {
		
		return score < 400;
	}
	
public boolean isSwapValid(int i, int j, int p) {
		
		if( p == 1){
			Teacher t1 = a1[row_x][row_y].getTeacher();
			Teacher t2 = a1[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else if( p == 2){
			Teacher t1 = a2[row_x][row_y].getTeacher();
			Teacher t2 = a2[i][j].getTeacher();
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else if( p == 3){
			Teacher t1 = a3[row_x][row_y].getTeacher();
			Teacher t2 = a3[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else if( p == 4){
			Teacher t1 = b1[row_x][row_y].getTeacher();
			Teacher t2 = b1[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else if( p == 5){
			Teacher t1 = b2[row_x][row_y].getTeacher();
			Teacher t2 = b2[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else if( p == 6){
			Teacher t1 = b3[row_x][row_y].getTeacher();
			Teacher t2 = b3[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else if( p == 7){
			Teacher t1 = c1[row_x][row_y].getTeacher();
			Teacher t2 = c1[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else if( p == 8){
			Teacher t1 = c2[row_x][row_y].getTeacher();
			Teacher t2 = c2[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		else{
			Teacher t1 = c3[row_x][row_y].getTeacher();
			Teacher t2 = c3[i][j].getTeacher();
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x][row_y].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x][row_y].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x][row_y].getTeacher().equals(t2)) return false;
		}
		
		
		
		return true;
	}

	
	public Collection<? extends State> getChildren() throws FileNotFoundException, UnsupportedEncodingException, CloneNotSupportedException {
		ArrayList<State> children = new ArrayList<State>();
		State child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
		for(int p = 1;p <= 9;p++){
			
			for(int i =0; i < 7;i++){
				for(int j = 0; j < 5;j++){
					if( p == 1){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else if( p == 2){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else if( p == 3){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else if( p == 4){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else if( p == 5){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else if( p == 6){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else if( p == 7){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else if( p == 8){
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					else{
						child.swap(i,j,p);
						child.euristic();
						children.add(child);
						child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3);
					}
					
					
				}
			}
			
		}
		
		return children;
	}

	public State swap(int i, int j, int k) {
		if(k == 1){
			Hour temp;
			temp = a1[row_x][row_y];
			a1[row_x][row_y] = a1[i][j];
			a1[i][j] = temp;
		}
		else if(k == 2){
			Hour temp;
			temp = a2[row_x][row_y];
			a2[row_x][row_y] = a2[i][j];
			a2[i][j] = temp;
		}
		else if(k == 3){
			Hour temp;
			temp = a3[row_x][row_y];
			a3[row_x][row_y] = a3[i][j];
			a3[i][j] = temp;
		}
		else if(k == 4){
			Hour temp;
			temp = b1[row_x][row_y];
			b1[row_x][row_y] = b1[i][j];
			b1[i][j] = temp;
		}
		else if(k == 5){
			Hour temp;
			temp = b2[row_x][row_y];
			b2[row_x][row_y] = b2[i][j];
			b2[i][j] = temp;
		}
		else if(k == 6){
			Hour temp;
			temp = b3[row_x][row_y];
			b3[row_x][row_y] = b3[i][j];
			b3[i][j] = temp;
		}
		else if(k == 7){
			Hour temp;
			temp = c1[row_x][row_y];
			c1[row_x][row_y] = c1[i][j];
			c1[i][j] = temp;
		}
		else if(k == 8){
			Hour temp;
			temp = c2[row_x][row_y];
			c2[row_x][row_y] = c2[i][j];
			c2[i][j] = temp;
		}
		else{
			Hour temp;
			temp = c3[row_x][row_y];
			c3[row_x][row_y] = c3[i][j];
			c3[i][j] = temp;
		}

		return this;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 5; j++) {
				if( !(this.a1[i][j].equals(((State) obj).a1[i][j])) ){
					return false;
				}
				if( !(this.a2[i][j].equals(((State) obj).a2[i][j])) ){
					return false;
				}
				if( !(this.a3[i][j].equals(((State) obj).a3[i][j])) ){
					return false;
				}
				if( !(this.b1[i][j].equals(((State) obj).b1[i][j])) ){
					return false;
				}
				if( !(this.b2[i][j].equals(((State) obj).b2[i][j])) ){
					return false;
				}
				if( !(this.b3[i][j].equals(((State) obj).b3[i][j])) ){
					return false;
				}
				if( !(this.c1[i][j].equals(((State) obj).c1[i][j])) ){
					return false;
				}
				if( !(this.c2[i][j].equals(((State) obj).c2[i][j])) ){
					return false;
				}
				if( !(this.c3[i][j].equals(((State) obj).c3[i][j])) ){
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	public Hour[][] getProgram(int p){
		if( p == 1){
			return a1;
		}
		else if( p == 2){
			return a2;
		}
		else if( p == 3){
			return a3;
		}
		else if( p == 4){
			return b1;
		}
		else if( p == 5){
			return b2;
		}
		else if( p == 6){
			return b3;
		}
		else if( p == 7){
			return c1;
		}
		else if( p == 8){
			return c2;
		}
		else{
			return c3;
		}
	}
		
    //We override the compareTo function of this class so only the heuristic scores are compared
	@Override
	public int compareTo(State s)
	{
		return Double.compare(this.score, s.score);
	}
	
	public void print() throws FileNotFoundException, UnsupportedEncodingException{
		this.print(a1);
		System.out.println();
		this.print(a2);
		System.out.println();
		this.print(a3);
		System.out.println();
		this.print(b1);
		System.out.println();
		this.print(b2);
		System.out.println();
		this.print(b3);
		System.out.println();
		this.print(c1);
		System.out.println();
		this.print(c2);
		System.out.println();
		this.print(c3);
	}
	
}
