

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
	private int[][] va1;
	private int[][] va2;
	private int[][] va3;
	private int[][] vb1;
	private int[][] vb2;
	private int[][] vb3;
	private int[][] vc1;
	private int[][] vc2;
	private int[][] vc3;
	
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
	private int row_x1;
	private int row_y1;
	private int row_x2;
	private int row_y2;
	private int row_x3;
	private int row_y3;
	private int row_x4;
	private int row_y4;
	private int row_x5;
	private int row_y5;
	private int row_x6;
	private int row_y6;
	private int row_x7;
	private int row_y7;
	private int row_x8;
	private int row_y8;
	private int row_x9;
	private int row_y9;
	

	
	public State(int x) {
		this.setTeachers();
		this.setLessons();
		a1 = this.randProgram("A", a1);		
		a2 = this.randProgram("A", a2);
		a3 = this.randProgram("A", a3);
		b1 = this.randProgram("B", b1);
		b2 = this.randProgram("B", b2);
		b3 = this.randProgram("B", b3);
		c1 = this.randProgram("C", c1);
		c2 = this.randProgram("C", c2);
		c3 = this.randProgram("C", c3);
		
		ArrayList<Integer> t1 = new ArrayList<Integer>();
		t1.add(9);
		t1.add(10);
		Teacher AK = new Teacher("name",100,t1,10);
		AK.setName("AK");
		Teacher MK = new Teacher("name",100,t1,10);
		MK.setName("MK");
		Teacher PG = new Teacher("name",100,t1,10);
		PG.setName("PG");
		Teacher OK = new Teacher("name",100,t1,10);
		OK.setName("OK");
		Teacher KK = new Teacher("name",100,t1,10);
		KK.setName("KK");
		Teacher LG = new Teacher("name",100,t1,10);
		LG.setName("LG");
		Teacher PP = new Teacher("name",100,t1,10);
		PP.setName("PP");
		Teacher ZZ = new Teacher("name",100,t1,10);
		ZZ.setName("ZZ");
		Teacher FT = new Teacher("name",100,t1,10);
		FT.setName("FT");
		Teacher QQ = new Teacher("name",100,t1,10);
		QQ.setName("QQ");
		Teacher BK = new Teacher("name",100,t1,10);
		BK.setName("BK");
		Teacher LK = new Teacher("name",100,t1,10);
		LK.setName("LK");
		Teacher PK = new Teacher("name",100,t1,10);
		PK.setName("PK");
		Teacher PR = new Teacher("name",100,t1,10);
		PR.setName("PR");
		Teacher WW = new Teacher("name",100,t1,10);
		WW.setName("WW");
		Teacher HH = new Teacher("name",100,t1,10);
		HH.setName("HH");
		Teacher FR = new Teacher("name",100,t1,10);
		FR.setName("FR");
		Teacher FK = new Teacher("name",100,t1,10);
		FK.setName("FK");
		Teacher YT = new Teacher("name",100,t1,10);
		YT.setName("YT");
		Teacher PY = new Teacher("name",100,t1,10);
		PY.setName("PY");
		Teacher LT = new Teacher("name",100,t1,10);
		LT.setName("LT");
		Teacher HT = new Teacher("name",100,t1,10);
		HT.setName("HT");
		Teacher SD = new Teacher("name",100,t1,10);
		SD.setName("SD");
		Teacher OO = new Teacher("name",100,t1,10);
		OO.setName("OO");
		Teacher JJ = new Teacher("name",100,t1,10);
		JJ.setName("JJ");
		Teacher LR = new Teacher("name",100,t1,10);
		LR.setName("LR");
		Teacher XG = new Teacher("name",100,t1,10);
		XG.setName("XG");
		Teacher MM = new Teacher("name",100,t1,10);
		MM.setName("MM");
		Teacher AA = new Teacher("name",100,t1,10);
		AA.setName("AA");
		Teacher BO = new Teacher("name",100,t1,10);
		BO.setName("BO");
		Teacher MS = new Teacher("name",100,t1,10);
		MS.setName("MS");
		Teacher LD = new Teacher("name",100,t1,10);
		LD.setName("LD");
		
		Lesson l1 = new Lesson(10,"A","A",10);
		l1.setLes_name("Γλωσσική Διδασκαλία");
		Lesson l2 = new Lesson(10,"A","A",10);
		l2.setLes_name("Νεοελληνική Λογοτεχνία");
		Lesson l3 = new Lesson(10,"A","A",10);
		l3.setLes_name("Αρχαία Ελληνική Γλώσσα");
		Lesson l4 = new Lesson(10,"A","A",10);
		l4.setLes_name("Αρχαία Ελληνικά Κείμενα");
		Lesson l5 = new Lesson(10,"A","A",10);
		l5.setLes_name("Μαθηματικά");
		Lesson l6 = new Lesson(10,"A","A",10);
		l6.setLes_name("Φυσική");
		Lesson l7 = new Lesson(10,"A","A",10);
		l7.setLes_name("Χημεία");
		Lesson l8 = new Lesson(10,"A","A",10);
		l8.setLes_name("Βιολογία");
		Lesson l9 = new Lesson(10,"A","A",10);
		l9.setLes_name("Γεωγραφία");
		Lesson l10 = new Lesson(10,"A","A",10);
		l10.setLes_name("Οικιακή Οικονομία");
		Lesson l11 = new Lesson(10,"A","A",10);
		l11.setLes_name("Ιστορία");
		Lesson l12 = new Lesson(10,"A","A",10);
		l12.setLes_name("Κ.Π.Α.");
		Lesson l13 = new Lesson(10,"A","A",10);
		l13.setLes_name("Θρησκευτικά");
		Lesson l14 = new Lesson(10,"A","A",10);
		l14.setLes_name("Αγγλικά");
		Lesson l15 = new Lesson(10,"A","A",10);
		l15.setLes_name("Ξένη Γλώσσα");
		Lesson l16 = new Lesson(10,"A","A",10);
		l16.setLes_name("Φυσική Αγωγή");
		Lesson l17 = new Lesson(10,"A","A",10);
		l17.setLes_name("Τεχνολογία");
		Lesson l18 = new Lesson(10,"A","A",10);
		l18.setLes_name("Πληροφορική");
		Lesson l19 = new Lesson(10,"A","A",10);
		l19.setLes_name("Μουσική");
		Lesson l20 = new Lesson(10,"A","A",10);
		l20.setLes_name("Καλλιτεχνικά");
		
		Teacher space = new Teacher();
		space.setName("##");
		Lesson space1 = new Lesson();
		space1.setLes_name("----");
		
		
		//Finished
		
		a1[0][0] = new Hour(l5,YT);
		a1[1][0] = new Hour(l15,XG);
		a1[2][0] = new Hour(l11,MK);
		a1[3][0] = new Hour(l9,PR);
		a1[4][0] = new Hour(l11,MK);
		a1[5][0] = new Hour(l3,KK);
		a1[6][0] = new Hour(l18,BO);
		
		a1[0][1] = new Hour(l3,KK);
		a1[1][1] = new Hour(l4,MK);
		a1[2][1] = new Hour(l13,JJ);
		a1[3][1] = new Hour(l5,YT);
		a1[4][1] = new Hour(l16,MM);
		a1[5][1] = new Hour(l2,PG);
		a1[6][1] = new Hour(space1,space);
		
		a1[0][2] = new Hour(l8,LK);
		a1[1][2] = new Hour(l13,JJ);
		a1[2][2] = new Hour(l2,PG);
		a1[3][2] = new Hour(space1,space);
		a1[4][2] = new Hour(l10,SD);
		a1[5][2] = new Hour(l14,LR);
		a1[6][2] = new Hour(l5,YT);
		
		a1[0][3] = new Hour(l1,AK);
		a1[1][3] = new Hour(l17,BO);
		a1[2][3] = new Hour(l20,LD);
		a1[3][3] = new Hour(space1,space);
		a1[4][3] = new Hour(l15,XG);
		a1[5][3] = new Hour(l5,YT);
		a1[6][3] = new Hour(l1,AK);
		
		a1[0][4] = new Hour(l16,MM);
		a1[1][4] = new Hour(l6,PR);
		a1[2][4] = new Hour(l1,AK);
		a1[3][4] = new Hour(l4,MK);
		a1[4][4] = new Hour(l19,MS);
		a1[5][4] = new Hour(l10,SD);
		a1[6][4] = new Hour(l14,LR);
		//finished
		
		// finished
		a2[0][0] = new Hour(l14,LR);
		a2[1][0] = new Hour(l19,MS);
		a2[2][0] = new Hour(l5,FK);
		a2[3][0] = new Hour(space1,space);
		a2[4][0] = new Hour(l5,FK);
		a2[5][0] = new Hour(l1,LG);
		a2[6][0] = new Hour(l18,BO);
		
		a2[0][1] = new Hour(space1,space);
		a2[1][1] = new Hour(l10,SD);
		a2[2][1] = new Hour(l5,FK);
		a2[3][1] = new Hour(space1,space);
		a2[4][1] = new Hour(l13,JJ);
		a2[5][1] = new Hour(l14,LR);
		a2[6][1] = new Hour(l8,PR);
		
		a2[0][2] = new Hour(l20,LD);
		a2[1][2] = new Hour(l3,PG);
		a2[2][2] = new Hour(l2,MK);
		a2[3][2] = new Hour(l4,PP);
		a2[4][2] = new Hour(l16,AA);
		a2[5][2] = new Hour(l3,PG);
		a2[6][2] = new Hour(l4,PP);
		
		a2[0][3] = new Hour(l15,XG);
		a2[1][3] = new Hour(l11,AK);
		a2[2][3] = new Hour(l11,AK);
		a2[3][3] = new Hour(l13,JJ);
		a2[4][3] = new Hour(l9,PR);
		a2[5][3] = new Hour(l1,LG);
		a2[6][3] = new Hour(l10,SD);
		
		a2[0][4] = new Hour(l17,BO);
		a2[1][4] = new Hour(l2,MK);
		a2[2][4] = new Hour(l5,FK);
		a2[3][4] = new Hour(l15,XG);
		a2[4][4] = new Hour(l1,LG);
		a2[5][4] = new Hour(l6,LK);
		a2[6][4] = new Hour(l16,AA);
		

		a3[0][0] = new Hour(l16,AA);
		a3[1][0] = new Hour(l5,PY);
		a3[2][0] = new Hour(space1,space);
		a3[3][0] = new Hour(l1,KK);
		a3[4][0] = new Hour(l2,PG);
		a3[5][0] = new Hour(l14,LR);
		a3[6][0] = new Hour(l16,AA);
		
		a3[0][1] = new Hour(l11,PG);
		a3[1][1] = new Hour(l2,PG);
		a3[2][1] = new Hour(l5,PY);
		a3[3][1] = new Hour(space1,space);
		a3[4][1] = new Hour(l19,MS);
		a3[5][1] = new Hour(l3,MK);
		a3[6][1] = new Hour(l6,LK);
		
		a3[0][2] = new Hour(l5,PY);
		a3[1][2] = new Hour(l18,BO);
		a3[2][2] = new Hour(l13,JJ);
		a3[3][2] = new Hour(l10,SD);
		a3[4][2] = new Hour(l5,PY);
		a3[5][2] = new Hour(l4,OK);
		a3[6][2] = new Hour(l15,XG);
		
		a3[0][3] = new Hour(l17,BO);
		a3[1][3] = new Hour(l13,JJ);
		a3[2][3] = new Hour(l11,PG);
		a3[3][3] = new Hour(l10,SD);
		a3[4][3] = new Hour(l15,XG);
		a3[5][3] = new Hour(l1,KK);
		a3[6][3] = new Hour(l4,OK);
		
		a3[0][4] = new Hour(l14,LR);
		a3[1][4] = new Hour(l1,KK);
		a3[2][4] = new Hour(l20,LD);
		a3[3][4] = new Hour(space1,space);
		a3[4][4] = new Hour(l3,MK);
		a3[5][4] = new Hour(l8,LK);
		a3[6][4] = new Hour(l9,PK);
		
		
		b1[0][0] = new Hour(l7,PR);
		b1[1][0] = new Hour(l4,AK);
		b1[2][0] = new Hour(l19,MS);
		b1[3][0] = new Hour(l5,FK);
		b1[4][0] = new Hour(l17,BO);
		b1[5][0] = new Hour(l8,BK);
		b1[6][0] = new Hour(l5,FK);
		
		b1[0][1] = new Hour(l14,LR);
		b1[1][1] = new Hour(l9,PR);
		b1[2][1] = new Hour(l6,BK);
		b1[3][1] = new Hour(l20,LD);
		b1[4][1] = new Hour(l6,BK);
		b1[5][1] = new Hour(space1,space);
		b1[6][1] = new Hour(space1,space);
		
		b1[0][2] = new Hour(l15,XG);
		b1[1][2] = new Hour(l11,LG);
		b1[2][2] = new Hour(l18,BO);
		b1[3][2] = new Hour(l9,PR);
		b1[4][2] = new Hour(l14,LR);
		b1[5][2] = new Hour(l15,XG);
		b1[6][2] = new Hour(l3,MK);
		
		b1[0][3] = new Hour(l2,MK);
		b1[1][3] = new Hour(l4,AK);
		b1[2][3] = new Hour(l1,OK);
		b1[3][3] = new Hour(l11,LG);
		b1[4][3] = new Hour(l5,FK);
		b1[5][3] = new Hour(l16,MM);
		b1[6][3] = new Hour(l13,JJ);
		
		b1[0][4] = new Hour(space1,space);
		b1[1][4] = new Hour(l1,OK);
		b1[2][4] = new Hour(l5,FK);
		b1[3][4] = new Hour(l13,JJ);
		b1[4][4] = new Hour(l16,MM);
		b1[5][4] = new Hour(l2,MK);
		b1[6][4] = new Hour(l13,MK);
		

		b2[0][0] = new Hour(l8,PR);
		b2[1][0] = new Hour(l5,FK);
		b2[2][0] = new Hour(l19,MS);
		b2[3][0] = new Hour(space1,space);
		b2[4][0] = new Hour(l5,FK);
		b2[5][0] = new Hour(l15,XG);
		b2[6][0] = new Hour(l4,KK);
		
		b2[0][1] = new Hour(l13,JJ);
		b2[1][1] = new Hour(l1,LG);
		b2[2][1] = new Hour(l5,FK);
		b2[3][1] = new Hour(l14,LR);
		b2[4][1] = new Hour(l11,OK);
		b2[5][1] = new Hour(l6,PR);
		b2[6][1] = new Hour(l1,LG);
		
		b2[0][2] = new Hour(space1,space);
		b2[1][2] = new Hour(l2,MK);
		b2[2][2] = new Hour(l16,AA);
		b2[3][2] = new Hour(l16,AA);
		b2[4][2] = new Hour(l4,KK);
		b2[5][2] = new Hour(l5,FK);
		b2[6][2] = new Hour(l9,PK);
		
		b2[0][3] = new Hour(l3,PG);
		b2[1][3] = new Hour(l20,LD);
		b2[2][3] = new Hour(l14,LR);
		b2[3][3] = new Hour(space1,space);
		b2[4][3] = new Hour(l9,PK);
		b2[5][3] = new Hour(l17,BO);
		b2[6][3] = new Hour(l6,PR);
		
		b2[0][4] = new Hour(l11,OK);
		b2[1][4] = new Hour(l3,PG);
		b2[2][4] = new Hour(l15,XG);
		b2[3][4] = new Hour(l2,MK);
		b2[4][4] = new Hour(l18,BO);
		b2[5][4] = new Hour(l13,JJ);
		b2[6][4] = new Hour(l7,PR);
		

		b3[0][0] = new Hour(l3,PP);
		b3[1][0] = new Hour(l1,PG);
		b3[2][0] = new Hour(l6,PK);
		b3[3][0] = new Hour(l2,PG);
		b3[4][0] = new Hour(l11,MK);
		b3[5][0] = new Hour(l9,PK);
		b3[6][0] = new Hour(l17,BO);
		
		b3[0][1] = new Hour(l7,PR);
		b3[1][1] = new Hour(l11,MK);
		b3[2][1] = new Hour(l20,LD);
		b3[3][1] = new Hour(l5,PY);
		b3[4][1] = new Hour(l5,PY);
		b3[5][1] = new Hour(l9,PK);
		b3[6][1] = new Hour(l5,PY);
		
		b3[0][2] = new Hour(l5,PY);
		b3[1][2] = new Hour(l6,PK);
		b3[2][2] = new Hour(l1,PG);
		b3[3][2] = new Hour(space1,space);
		b3[4][2] = new Hour(l3,PP);
		b3[5][2] = new Hour(l13,JJ);
		b3[6][2] = new Hour(l16,AA);
		
		b3[0][3] = new Hour(l16,AA);
		b3[1][3] = new Hour(l13,JJ);
		b3[2][3] = new Hour(l4,PP);
		b3[3][3] = new Hour(l14,LR);
		b3[4][3] = new Hour(space1,space);
		b3[5][3] = new Hour(l18,BO);
		b3[6][3] = new Hour(space1,space);
		
		b3[0][4] = new Hour(l15,XG);
		b3[1][4] = new Hour(l15,XG);
		b3[2][4] = new Hour(l2,PG);
		b3[3][4] = new Hour(l4,PP);
		b3[4][4] = new Hour(l19,MS);
		b3[5][4] = new Hour(l14,LR);
		b3[6][4] = new Hour(l8,PK);
		

		c1[0][0] = new Hour(l5,YT);
		c1[1][0] = new Hour(l4,PP);
		c1[2][0] = new Hour(l6,BK);
		c1[3][0] = new Hour(l11,AK);
		c1[4][0] = new Hour(l7,PK);
		c1[5][0] = new Hour(l4,PP);
		c1[6][0] = new Hour(l13,JJ);
		
		c1[0][1] = new Hour(l15,XG);
		c1[1][1] = new Hour(l2,LG);
		c1[2][1] = new Hour(l12,OO);
		c1[3][1] = new Hour(l16,MM);
		c1[4][1] = new Hour(l5,YT);
		c1[5][1] = new Hour(l20,LD);
		c1[6][1] = new Hour(l6,BK);
		
		c1[0][2] = new Hour(l5,YT);
		c1[1][2] = new Hour(l1,OK);
		c1[2][2] = new Hour(l11,AK);
		c1[3][2] = new Hour(l2,LG);
		c1[4][2] = new Hour(l1,OK);
		c1[5][2] = new Hour(l18,BO);
		c1[6][2] = new Hour(space1,space);
		
		c1[0][3] = new Hour(l16,MM);
		c1[1][3] = new Hour(l13,JJ);
		c1[2][3] = new Hour(l8,BK);
		c1[3][3] = new Hour(l3,LG);
		c1[4][3] = new Hour(l14,LR);
		c1[5][3] = new Hour(l19,MS);
		c1[6][3] = new Hour(l12,OO);
		
		c1[0][4] = new Hour(l15,XG);
		c1[1][4] = new Hour(space1,space);
		c1[2][4] = new Hour(l17,BO);
		c1[3][4] = new Hour(l5,YT);
		c1[4][4] = new Hour(l14,LR);
		c1[5][4] = new Hour(space1,space);
		c1[6][4] = new Hour(l3,LG);
		

		c2[0][0] = new Hour(l3,MK);
		c2[1][0] = new Hour(l14,LR);
		c2[2][0] = new Hour(l3,MK);
		c2[3][0] = new Hour(l5,YT);
		c2[4][0] = new Hour(space1,space);
		c2[5][0] = new Hour(l18,BO);
		c2[6][0] = new Hour(l6,BK);
		
		c2[0][1] = new Hour(l16,AA);
		c2[1][1] = new Hour(l12,OO);
		c2[2][1] = new Hour(l5,YT);
		c2[3][1] = new Hour(l14,LR);
		c2[4][1] = new Hour(l8,BK);
		c2[5][1] = new Hour(l7,BK);
		c2[6][1] = new Hour(l15,XG);
		
		c2[0][2] = new Hour(l4,LG);
		c2[1][2] = new Hour(l11,OK);
		c2[2][2] = new Hour(l13,JJ);
		c2[3][2] = new Hour(l16,AA);
		c2[4][2] = new Hour(l1,KK);
		c2[5][2] = new Hour(l17,BO);
		c2[6][2] = new Hour(l15,XG);
		
		c2[0][3] = new Hour(l19,MS);
		c2[1][3] = new Hour(l2,KK);
		c2[2][3] = new Hour(l6,BK);
		c2[3][3] = new Hour(l11,OK);
		c2[4][3] = new Hour(l20,LD);
		c2[5][3] = new Hour(l12,OO);
		c2[6][3] = new Hour(l5,YT);
		
		c2[0][4] = new Hour(space1,space);
		c2[1][4] = new Hour(l2,KK);
		c2[2][4] = new Hour(l4,LG);
		c2[3][4] = new Hour(space1,space);
		c2[4][4] = new Hour(l1,KK);
		c2[5][4] = new Hour(l13,JJ);
		c2[6][4] = new Hour(l5,YT);
		

		c3[0][0] = new Hour(l5,PY);
		c3[1][0] = new Hour(l12,OO);
		c3[2][0] = new Hour(l7,PK);
		c3[3][0] = new Hour(l3,OK);
		c3[4][0] = new Hour(l20,LD);
		c3[5][0] = new Hour(l5,PY);
		c3[6][0] = new Hour(l14,LR);
		
		c3[0][1] = new Hour(l4,AK);
		c3[1][1] = new Hour(l16,MM);
		c3[2][1] = new Hour(space1,space);
		c3[3][1] = new Hour(l13,JJ);
		c3[4][1] = new Hour(l5,PY);
		c3[5][1] = new Hour(l18,BO);
		c3[6][1] = new Hour(l19,MS);
		
		c3[0][2] = new Hour(l1,LG);
		c3[1][2] = new Hour(l4,AK);
		c3[2][2] = new Hour(l8,PK);
		c3[3][2] = new Hour(l11,AK);
		c3[4][2] = new Hour(l11,AK);
		c3[5][2] = new Hour(l14,LR);
		c3[6][2] = new Hour(l13,JJ);
		
		c3[0][3] = new Hour(space1,space);
		c3[1][3] = new Hour(l2,AK);
		c3[2][3] = new Hour(l6,BK);
		c3[3][3] = new Hour(l3,OK);
		c3[4][3] = new Hour(l5,PY);
		c3[5][3] = new Hour(space1,space);
		c3[6][3] = new Hour(l16,MM);
		
		c3[0][4] = new Hour(l15,XG);
		c3[1][4] = new Hour(l1,LG);
		c3[2][4] = new Hour(l6,BK);
		c3[3][4] = new Hour(l17,BO);
		c3[4][4] = new Hour(l15,XG);
		c3[5][4] = new Hour(l12,OO);
		c3[6][4] = new Hour(l2,AK);



		try {
			this.euristic();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	/*
	 * Default constructor.It creates a random
	 * program with dimensions 7x5.
	 */
	public State() throws FileNotFoundException, UnsupportedEncodingException{	
		this.setTeachers();
		this.setLessons();
		create_random();
		
		/*Teacher t;
		while(teachers.size() > 0) {
			t = teachers.remove(0);
			System.out.println("Teacher :" + t.getTeacher_id() + " With max hours: " + t.getMax_hours());
		}*/
		
	}
	
	public State(Hour[][] a1,Hour[][] a2,Hour[][] a3,Hour[][] b1,Hour[][] b2,Hour[][] b3,Hour[][] c1,Hour[][] c2,Hour[][] c3, int row_x12, int row_x22, int row_x32, int row_x42, int row_x52, int row_x62, int row_x72, int row_x82, int row_x92, int row_y12, int row_y22, int row_y32, int row_y42, int row_y52, int row_y62, int row_y72, int row_y82, int row_y92) throws CloneNotSupportedException {
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
		this.row_x1 = row_x12;
		this.row_y1 = row_y12;
		this.row_x2 = row_x22;
		this.row_y2 = row_y22;
		this.row_x3 = row_x32;
		this.row_y3 = row_y32;
		this.row_x4 = row_x42;
		this.row_y4 = row_y42;
		this.row_x5 = row_x52;
		this.row_y5 = row_y52;
		this.row_x6 = row_x62;
		this.row_y6 = row_y62;
		this.row_x7 = row_x72;
		this.row_y7 = row_y72;
		this.row_x8 = row_x82;
		this.row_y8 = row_y82;
		this.row_x9 = row_x92;
		this.row_y9 = row_y92;
		
		this.score = 0;
		
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
				teacher = cnd.get(cnd.size() -1 );
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
		
		
		try {
			this.euristic();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// The heuristic that calculates the score of the state.
	private void euristic() throws CloneNotSupportedException {
		va1 = new int[7][5];
		va2 = new int[7][5];
		va3 = new int[7][5];
		vb1 = new int[7][5];
		vb2 = new int[7][5];
		vb3 = new int[7][5];
		vc1 = new int[7][5];
		vc2 = new int[7][5];
		vc3 = new int[7][5];
		
		this.score = 0;
		this.score += this.teleportingTeachers();
		this.score += this.spacesInProgam();
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
					va1[i][j]++;
				}else if( i == 5 && a1[i][j].getTeacher().toString().equals("##") &&
						!(a1[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					va1[i][j]++;
				}else if(i < 4 && a1[i][j].getTeacher().toString().equals("##")){
					count++;
					va1[i][j]++;
				}
				
				// A2
				if( i == 4 && a2[i][j].getTeacher().toString().equals("##") &&
						( !(a2[i +1][j].getTeacher().toString().equals("##")) 
								|| !(a2[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					va2[i][j]++;
				}else if( i == 5 && a2[i][j].getTeacher().toString().equals("##") &&
						!(a2[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					va2[i][j]++;
				}else if(i < 4 && a2[i][j].getTeacher().toString().equals("##")){
					count++;
					va2[i][j]++;
				}
				
				// A3
				if( i == 4 && a3[i][j].getTeacher().toString().equals("##") &&
						( !(a3[i +1][j].getTeacher().toString().equals("##")) 
								|| !(a3[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					va3[i][j]++;
				}else if( i == 5 && a3[i][j].getTeacher().toString().equals("##") &&
						!(a3[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					va3[i][j]++;
				}else if(i < 4 && a3[i][j].getTeacher().toString().equals("##")){
					count++;
					va3[i][j]++;
				}
				
				// B1
				if( i == 4 && b1[i][j].getTeacher().toString().equals("##") &&
						( !(b1[i +1][j].getTeacher().toString().equals("##")) 
								|| !(b1[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					vb1[i][j]++;
				}else if( i == 5 && b1[i][j].getTeacher().toString().equals("##") &&
						!(b1[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					vb1[i][j]++;
				}else if(i < 4 && b1[i][j].getTeacher().toString().equals("##")){
					count++;
					vb1[i][j]++;
				}
				
				// B2
				if( i == 4 && b2[i][j].getTeacher().toString().equals("##") &&
						( !(b2[i +1][j].getTeacher().toString().equals("##")) 
								|| !(b2[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					vb2[i][j]++;
				}else if( i == 5 && b2[i][j].getTeacher().toString().equals("##") &&
						!(b2[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					vb2[i][j]++;
				}else if(i < 4 && b2[i][j].getTeacher().toString().equals("##")){
					count++;
					vb2[i][j]++;
				}
				
				// B3
				if( i == 4 && b3[i][j].getTeacher().toString().equals("##") &&
						( !(b3[i +1][j].getTeacher().toString().equals("##")) 
								|| !(b3[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					vb3[i][j]++;
				}else if( i == 5 && b3[i][j].getTeacher().toString().equals("##") &&
						!(b3[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					vb3[i][j]++;
				}else if(i < 4 && b3[i][j].getTeacher().toString().equals("##")){
					count++;
					vb3[i][j]++;
				}
				
				// C1
				if( i == 4 && c1[i][j].getTeacher().toString().equals("##") &&
						( !(c1[i +1][j].getTeacher().toString().equals("##")) 
								|| !(c1[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					vc1[i][j]++;
				}else if( i == 5 && c1[i][j].getTeacher().toString().equals("##") &&
						!(c1[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					vc1[i][j]++;
				}else if(i < 4 && c1[i][j].getTeacher().toString().equals("##")){
					count++;
					vc1[i][j]++;
				}
				
				// C2
				if( i == 4 && c2[i][j].getTeacher().toString().equals("##") &&
						( !(c2[i +1][j].getTeacher().toString().equals("##")) 
								|| !(c2[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					vc2[i][j]++;
				}else if( i == 5 && c2[i][j].getTeacher().toString().equals("##") &&
						!(c2[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					vc2[i][j]++;
				}else if(i < 4 && c2[i][j].getTeacher().toString().equals("##")){
					count++;
					vc2[i][j]++;
				}
				
				// C3
				if( i == 4 && c3[i][j].getTeacher().toString().equals("##") &&
						( !(c3[i +1][j].getTeacher().toString().equals("##")) 
								|| !(c3[i +2][j].getTeacher().toString().equals("##")) ) ){
					count++;
					vc3[i][j]++;
				}else if( i == 5 && c3[i][j].getTeacher().toString().equals("##") &&
						!(c3[i +1][j].getTeacher().toString().equals("##")) ){
					count++;
					vc3[i][j]++;
				}else if(i < 4 && c3[i][j].getTeacher().toString().equals("##")){
					count++;
					vc3[i][j]++;
				}
				
				
			}
		}
		
		return count * points;
	}
	
	// The method that calculates the points for the Teachers doing
	// 2 classes at the same hour.
	private int teleportingTeachers() throws CloneNotSupportedException {
		ArrayList<Teacher> duplicates;
		duplicates = new ArrayList<Teacher>(); 
		Teacher[] violations = new Teacher[9];
		int count = 0;
		int points = 400; // The points given as penalty for each time the restrictions is violated.
		for(int i = 0; i < 7; i++){			 
			for(int j = 0; j < 5; j++){
				//A1
				if(!(a1[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(a1[i][j].getTeacher());
				}else{
					violations[0] = (Teacher) a1[i][j].getTeacher().clone();
					violations[0].setName("a1");
				}
				
				violations[0] = a1[i][j].getTeacher();
				
				//A2
				if(duplicates.contains(a2[i][j].getTeacher())){					
					count++;
					violations[1] = a2[i][j].getTeacher();
					va1[i][j]++;
					va2[i][j]++;
					
				}else if(!(a2[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(a2[i][j].getTeacher());
					violations[1] = a2[i][j].getTeacher();
				}
				else{
					violations[1] = (Teacher) a2[i][j].getTeacher().clone();
					violations[1].setName("a2");
				}
				//A3
				if(duplicates.contains(a3[i][j].getTeacher())){
					count++;
					violations[2] = a3[i][j].getTeacher();
					if(violations[2].equals(violations[1])){
						va2[i][j]++;
						va3[i][j]++;
					}
					if(violations[2].equals(violations[0])){
						va1[i][j]++;
						va3[i][j]++;
					}					
				}else if(!(a3[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(a3[i][j].getTeacher());
					violations[2] = a3[i][j].getTeacher();
				}else{
					violations[2] = (Teacher) a3[i][j].getTeacher().clone();
					violations[2].setName("a3");
				}
				//B1
				if(duplicates.contains(b1[i][j].getTeacher())){
					count++;
					violations[3] = b1[i][j].getTeacher();
					if(violations[3].equals(violations[0])){
						va1[i][j]++;
						vb1[i][j]++;
					}
					if(violations[3].equals(violations[1])){
						va2[i][j]++;
						vb1[i][j]++;
					}
					if(violations[3].equals(violations[2])){
						va3[i][j]++;
						vb1[i][j]++;
					}
				}else if(!(b1[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(b1[i][j].getTeacher());
					violations[3] = b1[i][j].getTeacher();
				}else{
					violations[3] = (Teacher) b1[i][j].getTeacher().clone();
					violations[3].setName("b1");
				}
				//B2
				if(duplicates.contains(b2[i][j].getTeacher())){
					count++;
					violations[4] = b2[i][j].getTeacher();
					if(violations[4].equals(violations[0])){
						va1[i][j]++;
						vb2[i][j]++;
					}
					if(violations[4].equals(violations[1])){
						va2[i][j]++;
						vb2[i][j]++;
					}
					if(violations[4].equals(violations[2])){
						va3[i][j]++;
						vb2[i][j]++;
					}
					if(violations[4].equals(violations[3])){
						vb1[i][j]++;
						vb2[i][j]++;
					}
				}else if(!(b2[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(b2[i][j].getTeacher());
					violations[4] = b2[i][j].getTeacher();
				}else{
					violations[4] = (Teacher) b2[i][j].getTeacher().clone();
					violations[4].setName("b2");
				}				
				//B3
				if(duplicates.contains(b3[i][j].getTeacher())){
					count++;
					violations[5] = b3[i][j].getTeacher();
					if(violations[5].equals(violations[0])){
						va1[i][j]++;
						vb3[i][j]++;
					}
					if(violations[5].equals(violations[1])){
						va2[i][j]++;
						vb3[i][j]++;
					}
					if(violations[5].equals(violations[2])){
						va3[i][j]++;
						vb3[i][j]++;
					}
					if(violations[5].equals(violations[3])){
						vb1[i][j]++;
						vb3[i][j]++;
					}
					if(violations[5].equals(violations[4])){
						vb2[i][j]++;
						vb3[i][j]++;
					}
				}else if(!(b3[i][j].getTeacher().toString().equals("##"))){
					violations[5] = b3[i][j].getTeacher();
					duplicates.add(b3[i][j].getTeacher());					
				}else{
					violations[5] = (Teacher) b3[i][j].getTeacher().clone();
					violations[5].setName("b3");
				}
				//C1
				if(duplicates.contains(c1[i][j].getTeacher())){
					count++;
					violations[6] = c1[i][j].getTeacher();
					if(violations[6].equals(violations[0])){
						va1[i][j]++;
						vc1[i][j]++;
					}
					if(violations[6].equals(violations[1])){
						va2[i][j]++;
						vc1[i][j]++;
					}
					if(violations[6].equals(violations[2])){
						va3[i][j]++;
						vc1[i][j]++;
					}
					if(violations[6].equals(violations[3])){
						vb1[i][j]++;
						vc1[i][j]++;
					}
					if(violations[6].equals(violations[4])){
						vb2[i][j]++;
						vc1[i][j]++;
					}
					if(violations[6].equals(violations[5])){
						vb3[i][j]++;
						vc1[i][j]++;
					}
				}else if(!(c1[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(c1[i][j].getTeacher());
					violations[6] = c1[i][j].getTeacher();
				}else{
					violations[6] = (Teacher) c1[i][j].getTeacher().clone();
					violations[6].setName("c1");
				}
				//C2
				if(duplicates.contains(c2[i][j].getTeacher())){
					count++;
					violations[7] = c2[i][j].getTeacher();
					if(violations[7].equals(violations[0])){
						va1[i][j]++;
						vc2[i][j]++;
					}
					if(violations[7].equals(violations[1])){
						va2[i][j]++;
						vc2[i][j]++;
					}
					if(violations[7].equals(violations[2])){
						va3[i][j]++;
						vc2[i][j]++;
					}
					if(violations[7].equals(violations[3])){
						vb1[i][j]++;
						vc2[i][j]++;
					}
					if(violations[7].equals(violations[4])){
						vb2[i][j]++;
						vc2[i][j]++;
					}
					if(violations[7].equals(violations[5])){
						vb3[i][j]++;
						vc2[i][j]++;
					}
					if(violations[7].equals(violations[6])){
						vc1[i][j]++;
						vc2[i][j]++;
					}
				}else if(!(c2[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(c2[i][j].getTeacher());
					violations[7] = c2[i][j].getTeacher();
				}else{
					violations[7] = (Teacher) c2[i][j].getTeacher().clone();
					violations[7].setName("c2");
				}
				//C3
				if(duplicates.contains(c3[i][j].getTeacher())){
					count++;
					violations[8] = c3[i][j].getTeacher();
					if(violations[8].equals(violations[0])){
						va1[i][j]++;
						vc3[i][j]++;
					}
					if(violations[8].equals(violations[1])){
						va2[i][j]++;
						vc3[i][j]++;
					}
					if(violations[8].equals(violations[2])){
						va3[i][j]++;
						vc3[i][j]++;
					}
					if(violations[8].equals(violations[3])){
						vb1[i][j]++;
						vc3[i][j]++;
					}
					if(violations[8].equals(violations[4])){
						vb2[i][j]++;
						vc3[i][j]++;
					}
					if(violations[8].equals(violations[5])){
						vb3[i][j]++;
						vc3[i][j]++;
					}
					if(violations[8].equals(violations[6])){
						vc1[i][j]++;
						vc3[i][j]++;
					}
					if(violations[8].equals(violations[7])){
						vc2[i][j]++;
						vc3[i][j]++;
					}
				}else if(!(c3[i][j].getTeacher().toString().equals("##"))){
					duplicates.add(c3[i][j].getTeacher());
					violations[8] = c3[i][j].getTeacher();
				}
				else{
					violations[8] = (Teacher) c3[i][j].getTeacher().clone();
					violations[8].setName("c3");
				}
				for(int k = 0; k < 9;k++){
					violations[i] = null;
				}
				duplicates = new ArrayList<Teacher>();
			}
		}
		return count * points;
	}
	
	public int[][] getVa1() {
		return va1;
	}

	public void setVa1(int[][] va1) {
		this.va1 = va1;
	}

	public int[][] getVa2() {
		return va2;
	}

	public void setVa2(int[][] va2) {
		this.va2 = va2;
	}

	public int[][] getVa3() {
		return va3;
	}

	public void setVa3(int[][] va3) {
		this.va3 = va3;
	}

	public int[][] getVb1() {
		return vb1;
	}

	public void setVb1(int[][] vb1) {
		this.vb1 = vb1;
	}

	public int[][] getVb2() {
		return vb2;
	}

	public void setVb2(int[][] vb2) {
		this.vb2 = vb2;
	}

	public int[][] getVb3() {
		return vb3;
	}

	public void setVb3(int[][] vb3) {
		this.vb3 = vb3;
	}

	public int[][] getVc1() {
		return vc1;
	}

	public void setVc1(int[][] vc1) {
		this.vc1 = vc1;
	}

	public int[][] getVc2() {
		return vc2;
	}

	public void setVc2(int[][] vc2) {
		this.vc2 = vc2;
	}

	public int[][] getVc3() {
		return vc3;
	}

	public void setVc3(int[][] vc3) {
		this.vc3 = vc3;
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
			Teacher t1 = a1[row_x1][row_y1].getTeacher();
			Teacher t2 = a1[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x1][row_y1].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x1][row_y1].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x1][row_y1].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x1][row_y1].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x1][row_y1].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x1][row_y1].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x1][row_y1].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x1][row_y1].getTeacher().equals(t2)) return false;
		}
		else if( p == 2){
			Teacher t1 = a2[row_x2][row_y2].getTeacher();
			Teacher t2 = a2[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x2][row_y2].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x2][row_y2].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x2][row_y2].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x2][row_y2].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x2][row_y2].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x2][row_y2].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x2][row_y2].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x2][row_y2].getTeacher().equals(t2)) return false;
		}
		else if( p == 3){
			Teacher t1 = a3[row_x3][row_y3].getTeacher();
			Teacher t2 = a3[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x3][row_y3].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x3][row_y3].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x3][row_y3].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x3][row_y3].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x3][row_y3].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x3][row_y3].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x3][row_y3].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x3][row_y3].getTeacher().equals(t2)) return false;
		}
		else if( p == 4){
			Teacher t1 = b1[row_x4][row_y4].getTeacher();
			Teacher t2 = b1[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x4][row_y4].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x4][row_y4].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x4][row_y4].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x4][row_y4].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x4][row_y4].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x4][row_y4].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x4][row_y4].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x4][row_y4].getTeacher().equals(t2)) return false;
		}
		else if( p == 5){
			Teacher t1 = b2[row_x5][row_y5].getTeacher();
			Teacher t2 = b2[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x5][row_y5].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x5][row_y5].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x5][row_y5].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x5][row_y5].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x5][row_y5].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x5][row_y5].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x5][row_y5].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x5][row_y5].getTeacher().equals(t2)) return false;
		}
		else if( p == 6){
			Teacher t1 = b3[row_x6][row_y6].getTeacher();
			Teacher t2 = b3[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x6][row_y6].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x6][row_y6].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x6][row_y6].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x6][row_y6].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x6][row_y6].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x6][row_y6].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x6][row_y6].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x6][row_y6].getTeacher().equals(t2)) return false;
		}
		else if( p == 7){
			Teacher t1 = c1[row_x7][row_y7].getTeacher();
			Teacher t2 = c1[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x7][row_y7].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x7][row_y7].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x7][row_y7].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x7][row_y7].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x7][row_y7].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x7][row_y7].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x7][row_y7].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x7][row_y7].getTeacher().equals(t2)) return false;
		}
		else if( p == 8){
			Teacher t1 = c2[row_x8][row_y8].getTeacher();
			Teacher t2 = c2[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x8][row_y8].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x8][row_y8].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x8][row_y8].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x8][row_y8].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x8][row_y8].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x8][row_y8].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x8][row_y8].getTeacher().equals(t2)) return false;
			if (c3[i][j].getTeacher().equals(t1) || c3[row_x8][row_y8].getTeacher().equals(t2)) return false;
		}
		else{
			Teacher t1 = c3[row_x9][row_y9].getTeacher();
			Teacher t2 = c3[i][j].getTeacher();
			if (t1.equals(t2)) return false;
			if (a2[i][j].getTeacher().equals(t1) || a2[row_x9][row_y9].getTeacher().equals(t2)) return false;
			if (a3[i][j].getTeacher().equals(t1) || a3[row_x9][row_y9].getTeacher().equals(t2)) return false;
			if (b1[i][j].getTeacher().equals(t1) || b1[row_x9][row_y9].getTeacher().equals(t2)) return false;
			if (b2[i][j].getTeacher().equals(t1) || b2[row_x9][row_y9].getTeacher().equals(t2)) return false;
			if (b3[i][j].getTeacher().equals(t1) || b3[row_x9][row_y9].getTeacher().equals(t2)) return false;
			if (c1[i][j].getTeacher().equals(t1) || c1[row_x9][row_y9].getTeacher().equals(t2)) return false;
			if (c2[i][j].getTeacher().equals(t1) || c2[row_x9][row_y9].getTeacher().equals(t2)) return false;
			if (a1[i][j].getTeacher().equals(t1) || a1[row_x9][row_y9].getTeacher().equals(t2)) return false;
		}
		
		
		
		return true;
	}
	
	public boolean isValidSpaces(int i,int j,int p){
		if( p == 1){
			if(a1[row_x1][row_y1].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(a1[i +1][j].getLesson().toString().equals("----")) 
								|| !(a1[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(a1[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else if( p == 2){
			if(a2[row_x2][row_y2].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(a2[i +1][j].getLesson().toString().equals("----")) 
								|| !(a2[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(a2[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else if( p == 3){
			if(a3[row_x3][row_y3].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(a3[i +1][j].getLesson().toString().equals("----")) 
								|| !(a3[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(a3[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else if( p == 4){
			if(b1[row_x4][row_y4].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(b1[i +1][j].getLesson().toString().equals("----")) 
								|| !(b1[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(b1[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else if( p == 5){
			if(b2[row_x5][row_y5].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(b2[i +1][j].getLesson().toString().equals("----")) 
								|| !(b2[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(b2[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else if( p == 6){
			if(b3[row_x6][row_y6].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(b3[i +1][j].getLesson().toString().equals("----")) 
								|| !(b3[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(b3[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else if( p == 7){
			if(c1[row_x7][row_y7].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(c1[i +1][j].getLesson().toString().equals("----")) 
								|| !(c1[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(c1[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else if( p == 8){
			if(c2[row_x8][row_y8].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(c2[i +1][j].getLesson().toString().equals("----")) 
								|| !(c2[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(c2[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}
		else{
			if(c3[row_x9][row_y9].getLesson().toString().equals("----")){
				if( i == 4 &&
						( !(c3[i +1][j].getLesson().toString().equals("----")) 
								|| !(c3[i +2][j].getLesson().toString().equals("----")) ) ){
					return false;
				}else if( i == 5 && 
						!(c3[i +1][j].getLesson().toString().equals("----")) ){
					return false;
				}else if(i < 4){
					return false;
				} else if(i == 7) return true;
			}else return true;
		}

		return true;
	}
	
	public Collection<? extends State> getChildren() throws FileNotFoundException, UnsupportedEncodingException, CloneNotSupportedException {
		ArrayList<State> children = new ArrayList<State>();
		this.cheatingTheSystem();
		State child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
		for(int p = 1;p <= 9;p++){
			
			for(int i =0; i < 7;i++){
				for(int j = 0; j < 5;j++){
					if (p == 1  && row_x1 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p)){
							child.swap(i,j,p);
							child.euristic();
							
							System.out.printf("Swapped a1 %s with %s to %d,%d with new Score: %d \n",a1[row_x1][row_y1].getLesson().toString(),a1[i][j].getLesson().toString(),i,j,child.getScore());
							
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 2  && row_x2 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p) ){
							child.swap(i,j,p);
							child.euristic();
							
							System.out.printf("Swapped a2 %s with %s %d,%d with new Score: %d \n",a2[row_x2][row_y2].getLesson().toString(),a2[i][j].getLesson().toString(),i,j,child.getScore());
							
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 3  && row_x3 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p)){
							child.swap(i,j,p);
							child.euristic();
							
							System.out.printf("Swapped a3 %s with %s %d,%d with new Score: %d \n",a3[row_x3][row_y3].getLesson().toString(),a3[i][j].getLesson().toString(),i,j,child.getScore());
							
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 4  && row_x4 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p)){
							child.swap(i,j,p);
							child.euristic();	
							
							System.out.printf("Swapped b1 %s with %s %d,%d with new Score: %d\n",b1[row_x4][row_y4].getLesson().toString(),b1[i][j].getLesson().toString(),i,j,child.getScore());
							
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 5  && row_x5 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p) ){
							child.swap(i,j,p);
							child.euristic();	
							
							System.out.printf("Swapped b2 %s with %s %d,%d with new Score: %d\n",b2[row_x5][row_y5].getLesson().toString(),b2[i][j].getLesson().toString(),i,j,child.getScore());
							
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 6  && row_x6 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p)){
							child.swap(i,j,p);
							child.euristic();
							
							System.out.printf("Swapped b3 %s with %s %d,%d with new Score: %d\n",b3[row_x6][row_y6].getLesson().toString(),b3[i][j].getLesson().toString(),i,j,child.getScore());
							
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 7  && row_x7 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p)){
							child.swap(i,j,p);
							child.euristic();
							System.out.printf("Swapped c1 %s with %s %d,%d with new Score: %d\n",c1[row_x7][row_y7].getLesson().toString(),c1[i][j].getLesson().toString(),i,j,child.getScore());
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 8  && row_x8 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p)){
							child.swap(i,j,p);
							child.euristic();
							System.out.printf("Swapped c2 %s with %s %d,%d with new Score: %d\n",c2[row_x8][row_y8].getLesson().toString(),c2[i][j].getLesson().toString(),i,j,child.getScore());
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					if (p == 9  && row_x9 >= 0) {
						if(isValidSpaces(i,j,p) && isSwapValid(i,j,p) ){
							child.swap(i,j,p);
							child.euristic();
							System.out.printf("Swapped c3 %s with %s %d,%d with new Score: %d\n",c3[row_x9][row_y9].getLesson().toString(),c3[i][j].getLesson().toString(),i,j,child.getScore());
							children.add(child);
							child = new State(this.a1,this.a2,this.a3,this.b1,this.b2,this.b3,this.c1,this.c2,this.c3,this.row_x1,this.row_x2,this.row_x3,this.row_x4,this.row_x5,this.row_x6,this.row_x7,this.row_x8,this.row_x9,this.row_y1,this.row_y2,this.row_y3,this.row_y4,this.row_y5,this.row_y6,this.row_y7,this.row_y8,this.row_y9);
						}
					}
					
					
				}
			}
			
		}
		
		return children;
	}

	private void cheatingTheSystem() {
		Random rand = new Random();
		int sum = 0;
		int i;
		int j;
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += va1[k][l];
				}
			}
			if(sum == 0){
				row_x1 = -1;
				row_y1 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(va1[i][j] > 0){
				row_x1 = i;
				row_y1 = j;
				sum = 0;
				break;
			}
		}	
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += va2[k][l];
				}
			}
			if(sum == 0){
				row_x2 = -1;
				row_y2 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(va2[i][j] > 0){
				row_x2 = i;
				row_y2 = j;
				sum = 0;
				break;
			}
		}
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += va3[k][l];
				}
			}
			if(sum == 0){
				row_x3 = -1;
				row_y3 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(va3[i][j] > 0){
				row_x3 = i;
				row_y3 = j;
				sum = 0;
				break;
			}
		}
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += vb1[k][l];
				}
			}
			if(sum == 0){
				row_x4 = -1;
				row_y4 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(vb1[i][j] > 0){
				row_x4 = i;
				row_y4 = j;
				sum = 0;
				break;
			}
		}
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += vb2[k][l];
				}
			}
			if(sum == 0){
				row_x5 = -1;
				row_y5 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(vb2[i][j] > 0){
				row_x5 = i;
				row_y5 = j;
				sum = 0;
				break;
			}
		}
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += vb3[k][l];
				}
			}
			if(sum == 0){
				row_x6 = -1;
				row_y6 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(vb3[i][j] > 0){
				row_x6 = i;
				row_y6 = j;
				sum = 0;
				break;
			}
		}	
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += vc1[k][l];
				}
			}
			if(sum == 0){
				row_x7 = -1;
				row_y7 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(vc1[i][j] > 0){
				row_x7 = i;
				row_y7 = j;
				sum = 0;
				break;
			}
		}
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += vc2[k][l];
				}
			}
			if(sum == 0){
				row_x8 = -1;
				row_y8 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(vc2[i][j] > 0){
				row_x8 = i;
				row_y8 = j;
				sum = 0;
				break;
			}
		}	
		while(true){
			for(int k = 0; k < 7;k++){
				for(int l = 0; l < 5;l++){
					sum += vc3[k][l];
				}
			}
			if(sum == 0){
				row_x9 = -1;
				row_y9 = -1;
				sum = 0;
				break;
			}
			i = rand.nextInt(7);
			j = rand.nextInt(5);
			if(vc3[i][j] > 0){
				row_x9 = i;
				row_y9 = j;
				sum = 0;
				break;
			}
		}	
		
		
	}

	public State swap(int i, int j, int k) {
		if(k == 1){
			Hour temp;
			temp = a1[row_x1][row_y1];
			a1[row_x1][row_y1] = a1[i][j];
			a1[i][j] = temp;
		}
		else if(k == 2){
			Hour temp;
			temp = a2[row_x2][row_y2];
			a2[row_x2][row_y2] = a2[i][j];
			a2[i][j] = temp;
		}
		else if(k == 3 ){
			Hour temp;
			temp = a3[row_x3][row_y3];
			a3[row_x3][row_y3] = a3[i][j];
			a3[i][j] = temp;
		}
		else if(k == 4 ){
			Hour temp;
			temp = b1[row_x4][row_y4];
			b1[row_x4][row_y4] = b1[i][j];
			b1[i][j] = temp;
		}
		else if(k == 5 ){
			Hour temp;
			temp = b2[row_x5][row_y5];
			b2[row_x5][row_y5] = b2[i][j];
			b2[i][j] = temp;
		}
		else if(k == 6 ){
			Hour temp;
			temp = b3[row_x6][row_y6];
			b3[row_x6][row_y6] = b3[i][j];
			b3[i][j] = temp;
		}
		else if(k == 7 ){
			Hour temp;
			temp = c1[row_x7][row_y7];
			c1[row_x7][row_y7] = c1[i][j];
			c1[i][j] = temp;
		}
		else if(k == 8 ){
			Hour temp;
			temp = c2[row_x8][row_y8];
			c2[row_x8][row_y8] = c2[i][j];
			c2[i][j] = temp;
		}
		else {
			Hour temp;
			temp = c3[row_x9][row_y9];
			c3[row_x9][row_y9] = c3[i][j];
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