

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;




public class State {
	/*
	 * We represent the program with 3 different 
	 * 2 dimensions array 1 for each class.
	 */
	private Program a1;
	private Program a2;
	private Program a3;
	private Program b1;
	private Program b2;
	private Program b3;
	private Program c1;
	private Program c2;
	private Program c3;
	
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
		create_random();
	}
	
	//Creates a random program
	private void create_random() throws FileNotFoundException, UnsupportedEncodingException  {
		//Initialization of the program.
		a1 = new Program(0,"A");
		a2 = new Program("A");
		a3 = new Program("A");
		b1 = new Program("B");
		b2 = new Program("B");
		b3 = new Program("B");
		c1 = new Program("C");
		c2 = new Program("C");
		c3 = new Program("C");
		//Save the initial state on texts.
		a1.printArray("A1_start.txt");
		a2.printArray("A2_start.txt");
		a3.printArray("A3_start.txt");
		b1.printArray("B1_start.txt");
		b2.printArray("B2_start.txt");
		b3.printArray("B3_start.txt");
		c1.printArray("C1_start.txt");
		c2.printArray("C2_start.txt");
		c3.printArray("C3_start.txt");
		
		score = euristic();		
	}
	
	// The euristic that calculates the score of the state.
	private int euristic() {
		score = 0;
		score += teleportingTeachers();
		score += spacesInProgam();
		return score;
	}
	
	
	private int spacesInProgam() {
		int count = 0;
		int points = 1; // The points given as penalty for each time the restrictions is violated.
		for(int j = 0; j < 5; j++){
			for(int i = 0; i < 6;i++){
				
				// A1
				if( i == 4 && a1.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(a1.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(a1.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && a1.getTeacherAtPos(i, j).toString().equals("##") &&
						!(a1.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(a1.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// A2
				if( i == 4 && a2.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(a2.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(a2.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && a2.getTeacherAtPos(i, j).toString().equals("##") &&
						!(a2.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(a2.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// A3
				if( i == 4 && a3.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(a3.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(a3.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && a3.getTeacherAtPos(i, j).toString().equals("##") &&
						!(a3.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(a3.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// B1
				if( i == 4 && b1.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(b1.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(b1.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && b1.getTeacherAtPos(i, j).toString().equals("##") &&
						!(b1.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(b1.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// B2
				if( i == 4 && b2.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(b2.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(b2.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && b2.getTeacherAtPos(i, j).toString().equals("##") &&
						!(b2.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(b2.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// B3
				if( i == 4 && b3.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(b3.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(b3.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && b3.getTeacherAtPos(i, j).toString().equals("##") &&
						!(b3.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(b3.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// C1
				if( i == 4 && c1.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(c1.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(c1.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && c1.getTeacherAtPos(i, j).toString().equals("##") &&
						!(c1.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(c1.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// C2
				if( i == 4 && c2.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(c2.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(c2.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && c2.getTeacherAtPos(i, j).toString().equals("##") &&
						!(c2.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(c2.getTeacherAtPos(i, j).toString().equals("##")){
					count++;
				}
				
				// C3
				if( i == 4 && c3.getTeacherAtPos(i, j).toString().equals("##") &&
						( !(c3.getTeacherAtPos(i + 1, j).toString().equals("##")) 
								|| !(c3.getTeacherAtPos(i + 2, j).toString().equals("##")) ) ){
					count++;
				}else if( i == 5 && c3.getTeacherAtPos(i, j).toString().equals("##") &&
						!(c3.getTeacherAtPos(i + 1, j).toString().equals("##")) ){
					count++;					
				}else if(c3.getTeacherAtPos(i, j).toString().equals("##")){
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
		int count = 0;
		int points = 1; // The points given as penalty for each time the restrictions is violated.
		for(int i = 0; i < 7; i++){
			duplicates = new ArrayList<Teacher>(); 
			for(int j = 0; j < 5; j++){
				duplicates.add(a1.getTeacherAtPos(i, j));
				
				if(duplicates.contains(a2.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(a2.getTeacherAtPos(i, j));
				
				if(duplicates.contains(a3.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(a3.getTeacherAtPos(i, j));
				
				if(duplicates.contains(b1.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(b1.getTeacherAtPos(i, j));
				
				if(duplicates.contains(b2.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(b2.getTeacherAtPos(i, j));
				
				if(duplicates.contains(b3.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(b3.getTeacherAtPos(i, j));
				
				if(duplicates.contains(c1.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(c1.getTeacherAtPos(i, j));
				
				if(duplicates.contains(c2.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(c2.getTeacherAtPos(i, j));
				
				if(duplicates.contains(c3.getTeacherAtPos(i, j))){
					count++;
				}else duplicates.add(c3.getTeacherAtPos(i, j));
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
	
}
