
public class Car {
	private String car_class;
	private String price;
	private String maint;
	private String doors;
	private String persons;
	private String lug_boot;
	private String safety;
	
	
	public Car( String price, String maint, String doors, String persons, String lug_boot,
			String safety,String car_class) {
		super();
		this.car_class = car_class;
		this.price = price;
		this.maint = maint;
		this.doors = doors;
		this.persons = persons;
		this.lug_boot = lug_boot;
		this.safety = safety;
	}


	public Car(String price, String maint, String doors, String persons, String lug_boot, String safety) {
		super();
		this.price = price;
		this.maint = maint;
		this.doors = doors;
		this.persons = persons;
		this.lug_boot = lug_boot;
		this.safety = safety;
	}


	public Car() {}


	public String getCar_class() {
		return car_class;
	}


	public void setCar_class(String car_class) {
		this.car_class = car_class;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getMaint() {
		return maint;
	}


	public void setMaint(String maint) {
		this.maint = maint;
	}


	public String getDoors() {
		return doors;
	}


	public void setDoors(String doors) {
		this.doors = doors;
	}


	public String getPersons() {
		return persons;
	}


	public void setPersons(String persons) {
		this.persons = persons;
	}


	public String getLug_boot() {
		return lug_boot;
	}


	public void setLug_boot(String lug_boot) {
		this.lug_boot = lug_boot;
	}


	public String getSafety() {
		return safety;
	}


	public void setSafety(String safety) {
		this.safety = safety;
	}


	public void print() {
		System.out.println(this.toString());
		
	}


	@Override
	public String toString() {
		return "Car [car_class=" + car_class + ", price=" + price + ", maint=" + maint + ", doors=" + doors
				+ ", persons=" + persons + ", lug_boot=" + lug_boot + ", safety=" + safety + "]";
	}
	
	
}