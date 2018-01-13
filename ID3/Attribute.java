import java.util.Arrays;

public class Attribute {
	
	private int atr_numb;
	private int cls_numb;
	private int[] attrs;
	private int[] classes;
	private String[] cls_names;
	private String[] atr_names;
	private int[][] cls_map;
	private String name;
	private int elements;
	
	
	public Attribute(int atr_numb,int cls_number,String name) {
		super();
		this.name = name;
		this.atr_numb = atr_numb;
		this.cls_numb = cls_number;
		this.attrs = new int[atr_numb];
		this.atr_names = new String[atr_numb];
		this.classes = new int[cls_number];
		this.cls_names = new String[cls_number];
		cls_map = new int[atr_numb][cls_number];
		elements = 0;
	}


	public void addElement(String attr,String cls,int amount){
		for(int i = 0; i < atr_numb;i++){
			if(attr.equals(atr_names[i])){
				attrs[i]++;
				for(int j = 0; j < cls_numb;j++){
					if(cls.equals(cls_names[j])){
						cls_map[i][j]++;
					}
				}
			}
		}
		elements++;
	}


	public int getAtr_numb() {
		return atr_numb;
	}


	public void setAtr_numb(int atr_numb) {
		this.atr_numb = atr_numb;
	}
	
	public int getCls_numb() {
		return cls_numb;
	}


	public void setCls_numb(int cls_numb) {
		this.cls_numb = cls_numb;
	}


	public int[] getAttrs() {
		return attrs;
	}


	public void setAttrs(int[] attrs) {
		this.attrs = attrs;
	}


	public int[] getClasses() {
		return classes;
	}


	public void setClasses(int[] classes) {
		this.classes = classes;
	}


	public String[] getCls_names() {
		return cls_names;
	}


	public void setCls_names(String[] cls_names) {
		this.cls_names = cls_names;
	}


	public String[] getAtr_names() {
		return atr_names;
	}


	public void setAtr_names(String[] atr_names) {
		this.atr_names = atr_names;
	}


	public int[][] getCls_map() {
		return cls_map;
	}


	public void setCls_map(int[][] cls_map) {
		this.cls_map = cls_map;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public int calMaxCls(String cls){
		int iter = -1;
		for(int i = 0;i<cls_numb;i++){
			if(cls_names[i].equals(cls)){
				iter = i;
				break;
			}
		}
		if(iter < 0){
			return 0;
		}
		int max = 0;
		for(int j=0;j<atr_numb;j++){
			max += cls_map[j][iter];
		}
		return max;
		
	}
	
	public int getMaxElements(){
		return elements;
	}
	
	public float calcAtrEntropy(){
		float entropy = 0;
		for(int i=0; i < cls_numb; i++){
			if(this.calMaxCls(cls_names[i]) != 0){
				
				entropy -= (float)this.calMaxCls(cls_names[i])/ (float)this.getMaxElements() * log2((float)(this.calMaxCls(cls_names[i])/(float)this.getMaxElements()));
			}
		}
		return entropy;
	}
	
	public float calcAtrEntropy(String attr){
		int iter = -1;
		for(int i = 0;i < atr_numb;i++){
			if(atr_names[i].equals(attr)){
				iter = i;
			}
		}
		if(iter < 0) return 0;
		int atr_max = this.calMaxAtr(iter);
		if(atr_max == 0) return 0;
		
		float entropy = 0;
		for( int j = 0; j < cls_numb; j++){
			if(cls_map[iter][j] != 0){
				entropy -= (float)cls_map[iter][j]/(float)atr_max * log2((float)cls_map[iter][j]/(float)atr_max );
				
			}
			
		}
		System.out.println("Entropy of attr: " + atr_names[iter] + " is " + entropy);
		return entropy;
	}
	
	
	public float calcGain(){
		float entropy = 0;
		int max = 0;
		float ent = 0f;
		for(int i = 0; i <atr_numb;i++){
			max = calMaxAtr(i);
			System.out.println(max + " / " + elements);
			entropy -= ((float) max / (float) elements) * calcAtrEntropy(atr_names[i]);
			
		}
		return calcAtrEntropy() + entropy;
	}
	
	
	private int calMaxAtr(int iter) {
		int max = 0;
		for(int j = 0; j <cls_numb;j++){
			max += cls_map[iter][j];
		}
		return max;
	}


	public static double logb( double a, double b ){
		return Math.log(a) / Math.log(b);
	}

	public static double log2( double a ){
		return logb(a,2);
	}

	@Override
	public String toString() {
		return "Attribute [  name=" + name + " atr_names=" + Arrays.toString(atr_names) + "]";
	}


	
	
	
}
