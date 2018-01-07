import java.util.ArrayList;

public class dataset {
	private int[][] data;
	
	private static int unacc = 1;
	private static int acc = 2;
	private static int good = 3;
	private static int vgood = 4;
	private static int atr_total = 5;
	
	private static int b_vhigh = 1;
	private static int b_high = 2;
	private static int b_med = 3;
	private static int b_low = 4;
	private static int m_vhigh = 5;
	private static int m_high = 6;
	private static int m_med = 7;
	private static int m_low = 8;
	private static int d2 = 9;
	private static int d3 = 10;
	private static int d4 = 11;
	private static int d5_more = 12;
	private static int p2 = 13;
	private static int p4 = 14;
	private static int p_more = 15;
	private static int l_small = 16;
	private static int l_med = 17;
	private static int l_big = 18;
	private static int s_low= 19;
	private static int s_med = 20;
	private static int s_high = 21;
	private static int class_total = 22;
	
	private static int correct = 0;
	private static int total_samples = 0;
	
	public dataset(){
		data =  new int[23][6];
		for(int i =0; i < 23;i++){
			for(int j = 0;j < 6;j++){
				data[i][j] = 0;
			}
		}
	}
	
	public void addTrainingSet(Car car){
		int row,column = 0;
		//Setting column
		if(car.getCar_class().equals("unacc")){
			column = unacc;
		}else if(car.getCar_class().equals("acc")){
			column = acc;
		}else if(car.getCar_class().equals("good")){
			column = good;
		}else{
			column = vgood;
		}
		data[class_total][column]++;
		
		//Setting attributes one at a time
		
		//price
		if(car.getPrice().equals("vhigh")){
			row = b_vhigh;
		}else if(car.getPrice().equals("high")){
			row = b_high;
		}else if(car.getPrice().equals("med")){
			row = b_med;
		}else{
			row = b_low;
		}
		data[row][column]++;
		data[row][atr_total]++;
		
		
		//maint
		if(car.getMaint().equals("vhigh")){
			row = m_vhigh;
		}else if(car.getMaint().equals("high")){
			row = m_high;
		}else if(car.getMaint().equals("med")){
			row = m_med;
		}else{
			row = m_low;
		}
		data[row][column]++;
		data[row][atr_total]++;
		
		//doors
		if(car.getDoors().equals("2")){
			row = d2;
		}else if(car.getDoors().equals("3")){
			row = d3;
		}else if(car.getDoors().equals("4")){
			row = d4;
		}else{
			row = d5_more;
		}
		data[row][column]++;
		data[row][atr_total]++;
		
		//persons
		if(car.getPersons().equals("2")){
			row = p2;
		}else if(car.getPersons().equals("4")){
			row = p4;
		}else{
			row = p_more;
		}
		data[row][column]++;
		data[row][atr_total]++;
		
		//lub_boot
		if(car.getLug_boot().equals("small")){
			row = l_small;
		}else if(car.getLug_boot().equals("med")){
			row = l_med;
		}else{
			row = l_big;
		}
		data[row][column]++;
		data[row][atr_total]++;
		
		//safety
		if(car.getSafety().equals("low")){
			row = s_low;
		}else if(car.getSafety().equals("med")){
			row = s_med;
		}else{
			row = s_high;
		}
		data[row][column]++;
		data[row][atr_total]++;
		
		data[class_total][atr_total]++;

	}
	
	public void print(){
		System.out.printf( "Typ     unc       acc       goo     vgo     tot\n" );
		for(int i = 1;i < 23;i++){
			for(int j = 0;j < 6;j++){
				if(i == 1 && j == 0){
					System.out.printf( "b_vh     " );
					continue;
				}else if(i == 2 && j == 0){
					System.out.printf( "b_h     " );
					continue;
				}else if(i == 3 && j == 0){
					System.out.printf( "b_m     " );
					continue;
				}else if(i == 4 && j == 0){
					System.out.printf( "b_l    " );
					continue;
				}else if(i == 5 && j == 0){
					System.out.printf( "m_vh     " );
					continue;
				}else if(i == 6 && j == 0){
					System.out.printf( "m_h     " );
					continue;
				}else if(i == 7 && j == 0){
					System.out.printf( "m_m    " );
					continue;
				}else if(i == 8 && j == 0){
					System.out.printf( "m_l    " );
					continue;
				}else if(i == 9 && j == 0){
					System.out.printf( "d2     " );
					continue;
				}else if(i == 10 && j == 0){
					System.out.printf( "d3     " );
					continue;
				}else if(i == 11 && j == 0){
					System.out.printf( "d4     " );
					continue;
				}else if(i == 12 && j == 0){
					System.out.printf( "d5m     " );
					continue;
				}else if(i == 13 && j == 0){
					System.out.printf( "p2     " );
					continue;
				}else if(i == 14 && j == 0){
					System.out.printf( "p4     " );
					continue;
				}else if(i == 15 && j == 0){
					System.out.printf( "p_m     " );
					continue;
				}else if(i == 16 && j == 0){
					System.out.printf( "l_s     " );
					continue;
				}else if(i == 17 && j == 0){
					System.out.printf( "l_m    " );
					continue;
				}else if(i == 18 && j == 0){
					System.out.printf( "l_b    " );
					continue;
				}else if(i == 19 && j == 0){
					System.out.printf( "s_l    " );
					continue;
				}else if(i == 20 && j == 0){
					System.out.printf( "s_m    " );
					continue;
				}else if(i == 2 && j == 0){
					System.out.printf( "s_h    " );
					continue;
				}
				System.out.printf( "[%d]     " , data[i][j]);
			}
			System.out.printf( "\n"  );
		}
	}

	public void addTest(Car car){
		double prob_unacc;
		double prob_acc;
		double prob_vgood;
		double prob_good;
		
		double p_price;
		double p_maint;
		double p_doors;
		double p_persons;
		double p_lug;
		double p_safety;
		
		//unacc
		
		//price
		if(car.getPrice().equals("vhigh")){
			p_price =data[b_vhigh][unacc] /data[b_vhigh][atr_total];
		}else if(car.getPrice().equals("high")){
			p_price = data[b_high][unacc] /data[b_high][atr_total];
		}else if(car.getPrice().equals("med")){
			p_price = data[b_med][unacc] /data[b_med][atr_total];
		}else{
			p_price = data[b_low][unacc] /data[b_low][atr_total];
		}		
		//maint
		if(car.getMaint().equals("vhigh")){
			p_maint = data[m_vhigh][unacc] /data[m_vhigh][atr_total];
		}else if(car.getMaint().equals("high")){
			p_maint = data[m_high][unacc] /data[m_high][atr_total];
		}else if(car.getMaint().equals("med")){
			p_maint = data[m_med][unacc] /data[m_med][atr_total];
		}else{
			p_maint = data[m_low][unacc] /data[m_low][atr_total];
		}
		//doors
		if(car.getDoors().equals("2")){
			p_doors = data[d2][unacc] /data[d2][atr_total];
		}else if(car.getDoors().equals("3")){
			p_doors = data[d3][unacc] /data[d3][atr_total];
		}else if(car.getDoors().equals("4")){
			p_doors = data[d4][unacc] /data[d4][atr_total];
		}else{
			p_doors = data[d5_more][unacc] /data[d5_more][atr_total];
		}
		//persons
		if(car.getPersons().equals("2")){
			p_persons = data[p2][unacc] /data[p2][atr_total];
		}else if(car.getPersons().equals("4")){
			p_persons = data[p4][unacc] /data[p4][atr_total];
		}else{
			p_persons = data[p_more][unacc] /data[p_more][atr_total];
		}		
		//lub_boot
		if(car.getLug_boot().equals("small")){
			p_lug = data[l_small][unacc] /data[l_small][atr_total];
		}else if(car.getLug_boot().equals("med")){
			p_lug = data[l_med][unacc] /data[l_med][atr_total];
		}else{
			p_lug = data[l_big][unacc] /data[l_big][atr_total];
		}
		//safety
		if(car.getSafety().equals("low")){
			p_safety = data[s_low][unacc] /data[s_low][atr_total];
		}else if(car.getSafety().equals("med")){
			p_safety = data[s_med][unacc] /data[s_med][atr_total];
		}else{
			p_safety = data[s_high][unacc] /data[s_high][atr_total];
		}
		prob_unacc = p_price * p_maint * p_doors * p_persons * p_lug * p_safety * (data[class_total][unacc]/data[class_total][atr_total]);
		
		
		//acc
		
		//price
		if(car.getPrice().equals("vhigh")){
			p_price =data[b_vhigh][acc] /data[b_vhigh][atr_total];
		}else if(car.getPrice().equals("high")){
			p_price = data[b_high][acc] /data[b_high][atr_total];
		}else if(car.getPrice().equals("med")){
			p_price = data[b_med][acc] /data[b_med][atr_total];
		}else{
			p_price = data[b_low][acc] /data[b_low][atr_total];
		}		
		//maint
		if(car.getMaint().equals("vhigh")){
			p_maint = data[m_vhigh][acc] /data[m_vhigh][atr_total];
		}else if(car.getMaint().equals("high")){
			p_maint = data[m_high][acc] /data[m_high][atr_total];
		}else if(car.getMaint().equals("med")){
			p_maint = data[m_med][acc] /data[m_med][atr_total];
		}else{
			p_maint = data[m_low][acc] /data[m_low][atr_total];
		}
		//doors
		if(car.getDoors().equals("2")){
			p_doors = data[d2][acc] /data[d2][atr_total];
		}else if(car.getDoors().equals("3")){
			p_doors = data[d3][acc] /data[d3][atr_total];
		}else if(car.getDoors().equals("4")){
			p_doors = data[d4][acc] /data[d4][atr_total];
		}else{
			p_doors = data[d5_more][acc] /data[d5_more][atr_total];
		}
		//persons
		if(car.getPersons().equals("2")){
			p_persons = data[p2][acc] /data[p2][atr_total];
		}else if(car.getPersons().equals("4")){
			p_persons = data[p4][acc] /data[p4][atr_total];
		}else{
			p_persons = data[p_more][acc] /data[p_more][atr_total];
		}		
		//lub_boot
		if(car.getLug_boot().equals("small")){
			p_lug = data[l_small][acc] /data[l_small][atr_total];
		}else if(car.getLug_boot().equals("med")){
			p_lug = data[l_med][acc] /data[l_med][atr_total];
		}else{
			p_lug = data[l_big][acc] /data[l_big][atr_total];
		}
		//safety
		if(car.getSafety().equals("low")){
			p_safety = data[s_low][acc] /data[s_low][atr_total];
		}else if(car.getSafety().equals("med")){
			p_safety = data[s_med][acc] /data[s_med][atr_total];
		}else{
			p_safety = data[s_high][acc] /data[s_high][atr_total];
		}
		prob_acc = p_price * p_maint * p_doors * p_persons * p_lug * p_safety * (data[class_total][acc]/data[class_total][atr_total]);
		
		//good
		
		//price
		if(car.getPrice().equals("vhigh")){
			p_price =data[b_vhigh][good] /data[b_vhigh][atr_total];
		}else if(car.getPrice().equals("high")){
			p_price = data[b_high][good] /data[b_high][atr_total];
		}else if(car.getPrice().equals("med")){
			p_price = data[b_med][good] /data[b_med][atr_total];
		}else{
			p_price = data[b_low][good] /data[b_low][atr_total];
		}		
		//maint
		if(car.getMaint().equals("vhigh")){
			p_maint = data[m_vhigh][good] /data[m_vhigh][atr_total];
		}else if(car.getMaint().equals("high")){
			p_maint = data[m_high][good] /data[m_high][atr_total];
		}else if(car.getMaint().equals("med")){
			p_maint = data[m_med][good] /data[m_med][atr_total];
		}else{
			p_maint = data[m_low][good] /data[m_low][atr_total];
		}
		
		//doors
		if(car.getDoors().equals("d2")){
			p_doors = data[d2][good] /data[d2][atr_total];
		}else if(car.getDoors().equals("d3")){
			p_doors = data[d3][good] /data[d3][atr_total];
		}else if(car.getDoors().equals("d4")){
			p_doors = data[d4][good] /data[d4][atr_total];
		}else{
			p_doors = data[d5_more][good] /data[d5_more][atr_total];
		}

		//persons
		if(car.getPersons().equals("p2")){
			p_persons = data[p2][good] /data[p2][atr_total];
		}else if(car.getPersons().equals("p4")){
			p_persons = data[p4][good] /data[p4][atr_total];
		}else{
			p_persons = data[p_more][good] /data[p_more][atr_total];
		}

		//lub_boot
		if(car.getLug_boot().equals("s_low")){
			p_lug = data[s_low][good] /data[s_low][atr_total];
		}else if(car.getLug_boot().equals("s_med")){
			p_lug = data[s_med][good] /data[s_med][atr_total];
		}else{
			p_lug = data[s_high][good] /data[s_high][atr_total];
		}
		//safety
		if(car.getSafety().equals("low")){
			p_safety = data[s_low][good] /data[s_low][atr_total];
		}else if(car.getSafety().equals("med")){
			p_safety = data[s_med][good] /data[s_med][atr_total];
		}else{
			p_safety = data[s_high][good] /data[s_high][atr_total];
		}
		prob_good = p_price * p_maint * p_doors * p_persons * p_lug * p_safety * (data[class_total][good]/data[class_total][atr_total]);
		
		//vgood
		
		//price
		if(car.getPrice().equals("vhigh")){
			p_price =data[b_vhigh][vgood] /data[b_vhigh][atr_total];
		}else if(car.getPrice().equals("high")){
			p_price = data[b_high][vgood] /data[b_high][atr_total];
		}else if(car.getPrice().equals("med")){
			p_price = data[b_med][vgood] /data[b_med][atr_total];
		}else{
			p_price = data[b_low][vgood] /data[b_low][atr_total];
		}		
		//maint
		if(car.getMaint().equals("vhigh")){
			p_maint = data[m_vhigh][vgood] /data[m_vhigh][atr_total];
		}else if(car.getMaint().equals("high")){
			p_maint = data[m_high][vgood] /data[m_high][atr_total];
		}else if(car.getMaint().equals("med")){
			p_maint = data[m_med][vgood] /data[m_med][atr_total];
		}else{
			p_maint = data[m_low][vgood] /data[m_low][atr_total];
		}
		
		//doors
		if(car.getDoors().equals("d2")){
			p_doors = data[d2][vgood] /data[d2][atr_total];
		}else if(car.getDoors().equals("d3")){
			p_doors = data[d3][vgood] /data[d3][atr_total];
		}else if(car.getDoors().equals("d4")){
			p_doors = data[d4][vgood] /data[d4][atr_total];
		}else{
			p_doors = data[d5_more][vgood] /data[d5_more][atr_total];
		}

		//persons
		if(car.getPersons().equals("p2")){
			p_persons = data[p2][vgood] /data[p2][atr_total];
		}else if(car.getPersons().equals("p4")){
			p_persons = data[p4][vgood] /data[p4][atr_total];
		}else{
			p_persons = data[p_more][vgood] /data[p_more][atr_total];
		}

		//lub_boot
		if(car.getLug_boot().equals("s_low")){
			p_lug = data[s_low][vgood] /data[s_low][atr_total];
		}else if(car.getLug_boot().equals("s_med")){
			p_lug = data[s_med][vgood] /data[s_med][atr_total];
		}else{
			p_lug = data[s_high][vgood] /data[s_high][atr_total];
		}
		//safety
		if(car.getSafety().equals("low")){
			p_safety = data[s_low][vgood] /data[s_low][atr_total];
		}else if(car.getSafety().equals("med")){
			p_safety = data[s_med][vgood] /data[s_med][atr_total];
		}else{
			p_safety = data[s_high][vgood] /data[s_high][atr_total];
		}
		prob_vgood = p_price * p_maint * p_doors * p_persons * p_lug * p_safety * (data[class_total][vgood]/data[class_total][atr_total]);

	}
}
