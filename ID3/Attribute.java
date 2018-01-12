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


	@Override
	public String toString() {
		return "Attribute [  name=" + name + " atr_names=" + Arrays.toString(atr_names) + "]";
	}


	
	
	
}
