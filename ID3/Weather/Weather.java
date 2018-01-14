
public class Weather {
	
	private String outlook;
	private String temp;
	private String humidity;
	private String wind;
	private String w_class;
	
	public Weather(String outlook, String temp, String humidity, String wind, String w_class) {
		super();
		this.outlook = outlook;
		this.temp = temp;
		this.humidity = humidity;
		this.wind = wind;
		this.w_class = w_class;
	}
	
	
	
	public Weather(String outlook, String temp, String humidity, String wind) {
		super();
		this.outlook = outlook;
		this.temp = temp;
		this.humidity = humidity;
		this.wind = wind;
	}



	public Weather() {
		// TODO Auto-generated constructor stub
	}



	public String getOutlook() {
		return outlook;
	}
	public void setOutlook(String outlook) {
		this.outlook = outlook;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getW_class() {
		return w_class;
	}
	public void setW_class(String w_class) {
		this.w_class = w_class;
	}
	
}
